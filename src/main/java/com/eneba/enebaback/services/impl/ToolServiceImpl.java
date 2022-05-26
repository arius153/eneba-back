package com.eneba.enebaback.services.impl;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.eneba.enebaback.dto.*;
import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.logging.Logging;
import com.eneba.enebaback.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.repositories.BorrowLogRepository;
import com.eneba.enebaback.repositories.ToolCategoryRepository;
import com.eneba.enebaback.repositories.ToolRepository;
import com.eneba.enebaback.repositories.UserRepository;
import com.eneba.enebaback.utils.ToolSpecification;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ToolCategoryRepository toolCategoryRepository;

    @Autowired
    private BorrowLogRepository borrowLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserReviewService userReviewService;

    @Override
    @Logging("All tools requested")
    public List<ToolDTO> getAllTools() {
        return toolRepository.findAll()
                .stream()
                .map(ToolDTO::new)
                .collect(Collectors.toList());
    }

    public List<ToolBriefDTO> getAllTools(Long userId) {
        return toolRepository.findAllToolsByUserId(userId);

    }

    @Override
    @Transactional
    @Logging("Access specific tool")
    public ToolDTO getTool(Long id) {
        Tool tool = toolRepository.getById(id);
        var simplifiedUserDto = getSimplifiedUserDTO(tool.getUser().getId());
        return mapToolToToolDTO(tool, simplifiedUserDto);

    }

    @Override
    @Logging("Access sorted tools")
    public List<ToolDTO> getSortedAndFilteredTools(ToolSortModel toolSortModel, ToolFilterModel toolFilterModel) {
        if (!toolSortModel.isEmpty() && toolFilterModel.isEmpty()) {
            return toolRepository
                    .findAll(Sort.by(toolSortModel.getSortDirection(), toolSortModel.getSortBy()))
                    .stream()
                    .map(ToolDTO::new)
                    .collect(Collectors.toList());
        }

        ToolSpecification toolSpecification = new ToolSpecification(toolFilterModel);

        if (toolSortModel.isEmpty() && !toolFilterModel.isEmpty()) {
            return toolRepository
                    .findAll(toolSpecification)
                    .stream()
                    .map(ToolDTO::new)
                    .collect(Collectors.toList());
        }

        return toolRepository
                .findAll(toolSpecification, Sort.by(toolSortModel.getSortDirection(), toolSortModel.getSortBy()))
                .stream()
                .map(ToolDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Logging("Register new tool")
    public ToolBriefDTO registerTool(ToolRegisterDTO toolRegisterDTO, List<MultipartFile> files) {
        Long loggedUserId = userService.getLoggedUserId();
        if (loggedUserId == null) {
            return null;
        }
        Tool tool = Tool.builder()
                .name(toolRegisterDTO.getName())
                .user(userRepository.findById(loggedUserId).orElse(null))
                .toolCategory(toolCategoryRepository.findById(toolRegisterDTO.getCategory()).orElse(null))
                .price(toolRegisterDTO.getPrice())
                .description(toolRegisterDTO.getDescription())
                .geoCordX(toolRegisterDTO.getLat())
                .geoCordY(toolRegisterDTO.getLng())
                .formattedAddress(toolRegisterDTO.getAddress())
                .assistedTransportation(toolRegisterDTO.getAssistedTransportation())
                .pickUpTimeWeekend(toolRegisterDTO.getPickUpTimeWeekend())
                .pickUpTimeWorkDay(toolRegisterDTO.getPickUpTimeWorkDay())
                .availableDays(toolRegisterDTO.getDaysAvailable())
                .build();
        tool = toolRepository.saveAndFlush(tool);
        if (!CollectionUtils.isEmpty(files)) {
            imageService.saveAllImages(tool, files);
        }
        return new ToolBriefDTO(tool);
    }

    @Transactional
    @Logging("Editing a tool")
    public ToolDTO editTool(ToolRegisterDTO toolRegisterDTO, List<MultipartFile> files, Long id) {
        var tool = toolRepository.findById(id).orElse(null);
        if (tool == null) {
            return null;
        }
        if (tool.getUser() != null && !Objects.equals(tool.getUser().getId(), userService.getLoggedUserId())) {
            return null;
        }
        if (tool.getVersion() > toolRegisterDTO.getVersion() && !toolRegisterDTO.getOverride()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Request with old version");
        }
        tool.setName(toolRegisterDTO.getName());
        tool.setAssistedTransportation(toolRegisterDTO.getAssistedTransportation());
        tool.setToolCategory(toolCategoryRepository.findById(toolRegisterDTO.getCategory()).orElse(null));
        tool.setPrice(toolRegisterDTO.getPrice());
        tool.setDescription(toolRegisterDTO.getDescription());
        tool.setGeoCordX(toolRegisterDTO.getLat());
        tool.setGeoCordY(toolRegisterDTO.getLng());
        tool.setFormattedAddress(toolRegisterDTO.getAddress());
        tool.setPickUpTimeWeekend(toolRegisterDTO.getPickUpTimeWeekend());
        tool.setPickUpTimeWorkDay(toolRegisterDTO.getPickUpTimeWorkDay());
        tool.setAvailableDays(toolRegisterDTO.getDaysAvailable());
        tool.setVersion(toolRegisterDTO.getVersion() != null ? toolRegisterDTO.getVersion() : 0);
        tool = toolRepository.save(tool);
        imageService.deleteOldImagesByIds(tool.getImages().stream().map(Image::getId).collect(Collectors.toList()));
        Set<Image> images = new HashSet<>();
        if (!CollectionUtils.isEmpty(files)) {
            images = imageService.saveAllImages(tool, files);
        }
        tool.setImages(images);
        var simplifiedUserDto = getSimplifiedUserDTO(tool.getUser().getId());
        return mapToolToToolDTO(tool, simplifiedUserDto);

    }

    @Logging("Borrow a tool")
    public Long borrowTool(BorrowingDTO borrowingDTO) {
        BorrowLog borrowLog = new BorrowLog();
        borrowLog.setTool(toolRepository.findById(borrowingDTO.getToolId()).orElse(null));
        borrowLog.setUser(userService.getLoggedUserEntity());
        borrowLog.setBorrowedAt(borrowingDTO.getBorrowedAt());
        borrowLog.setReturnedAt(borrowingDTO.getReturnedAt());
        return borrowLogRepository.save(borrowLog).getId();
    }

    public BorrowLog returnTool(ReturnToolDTO returnToolDTO) {
        BorrowLog borrowLog = borrowLogRepository.getById(returnToolDTO.getBorrowLogId());
        borrowLog.setReturnedAt(returnToolDTO.getReturnedAt());
        return borrowLogRepository.save(borrowLog);
    }

    public List<Tool> getAllAvailableTools() {
        return toolRepository.findAllAvailableTools();
    }

    public List<Tool> getAllAvailableToolsByCategory(Long categoryId) {
        return toolRepository.findAllAvailableToolsByCategory(categoryId);
    }

    @Logging("Access available tool categories")
    public List<CategoryDTO> getAllAvailableCategories() {
        return toolCategoryRepository.findAll()
                .stream()
                .map(categorie -> new CategoryDTO(categorie.getId(), categorie.getCategoryName()))
                .collect(Collectors.toList());
    }

    private boolean isToolAvailable(Long toolId) {
        return toolRepository.findAvailableToolById(toolId) != null;
    }

    @Logging("Access borrowed tool logs")
    public List<BorrowToolDTO> getBorrowedToolLog() {
        User user = userService.getLoggedUserEntity();
        if (user == null) {
            return null;
        }
        List<BorrowLog> borrowLog = borrowLogRepository.findByUser(user);

        return borrowLog.stream().map(log -> BorrowToolDTO.builder()
                .ownerId(log.getTool().getUser().getId())
                .ownerName(log.getUser().getName())
                .ownerLastName(log.getUser().getSurname())
                .borrowedAtDate(log.getBorrowedAt().toLocalDate())
                .returnedAtDate(log.getReturnedAt().toLocalDate())
                .toolName(log.getTool() == null ? null : log.getTool().getName())
                .toolPrice(log.getTool() == null ? null : log.getTool().getPrice())
                .pricePaid(log.getTool() == null ? null : log.getTool().getPrice() * (ChronoUnit.DAYS.between(log.getBorrowedAt().toLocalDate(), log.getReturnedAt().toLocalDate())))
                .toolPlace(log.getTool() == null ? null : log.getTool().getFormattedAddress())
                .ownerGeoCordX(log.getTool() == null ? null : log.getTool().getGeoCordX())
                .ownerGeoCordY(log.getTool() == null ? null : log.getTool().getGeoCordY())
                .toolId(log.getTool() == null ? null : log.getTool().getId())
                .build()).collect(Collectors.toList());
    }

    public List<ReservedToolDTO> getCurrentlyRentedLogs() {
        User user = userService.getLoggedUserEntity();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "There is no user!");
        }

       return borrowLogRepository.findCurrentlyRentedByUserId(user.getId());
    }

    @Logging("Access user's own tools")
    public List<ToolBriefDTO> getLoggedUserTools() {
        Long userId = userService.getLoggedUserId();
        return toolRepository.findAllToolsByUserId(userId);
    }

    @Logging("Check tool availability")
    public List<ToolUnavailableTimeslotDTO> getToolUnavailableTimeslots(Long toolId) {
        List<BorrowLog> borrowLogs = borrowLogRepository.findFutureBorrows(toolId);
        List<ToolUnavailableTimeslotDTO> toolUnavailableTimeslotDTOS = new ArrayList<>();
        for (BorrowLog borrowLog : borrowLogs) {
            toolUnavailableTimeslotDTOS.add(new ToolUnavailableTimeslotDTO(borrowLog));
        }
        return toolUnavailableTimeslotDTOS;
    }

    @Transactional
    public ToolRegisterDTO getToolForEditing(Long id) {
        Tool toolToEdit = toolRepository.findById(id).orElse(null);
        if (toolToEdit == null) {
            return null;
        }
        if (toolToEdit.getUser() != null && !Objects.equals(userService.getLoggedUserId(), toolToEdit.getUser().getId())) {
            return null;
        }
        return mapToToolRegisterDTO(toolToEdit);
    }

    @Transactional
    public ToolRegisterDTO mapToToolRegisterDTO(Tool tool) {
        return ToolRegisterDTO.builder()
                .id(tool.getId())
                .name(tool.getName())
                .category(tool.getToolCategory() != null ? tool.getToolCategory().getId() : null)
                .price(tool.getPrice())
                .assistedTransportation(tool.getAssistedTransportation())
                .description(tool.getDescription())
                .address(tool.getFormattedAddress())
                .lat(tool.getGeoCordX())
                .lng(tool.getGeoCordY())
                .daysAvailable(tool.getAvailableDays())
                .pickUpTimeWeekend(tool.getPickUpTimeWeekend())
                .pickUpTimeWorkDay(tool.getPickUpTimeWorkDay())
                .files(tool.getImages().stream().map(Image::getImage).collect(Collectors.toList()))
                .version(tool.getVersion())
                .build();
    }

    private ToolDTO mapToolToToolDTO(Tool tool, SimplifiedUserDTO simplifiedUserDTO) {
        return ToolDTO.builder()
                .description(tool.getDescription())
                .toolCategory(tool.getToolCategory().getCategoryName())
                .geoCordX(tool.getGeoCordX())
                .geoCordY(tool.getGeoCordY())
                .name(tool.getName())
                .price(tool.getPrice())
                .assistedTransportation(tool.getAssistedTransportation())
                .images(tool.getImages().stream().map(x -> Base64.getEncoder().encodeToString(x.getImage())).collect(Collectors.toList()))
                .formattedAddress(tool.getFormattedAddress())
                .pickUpTimeWorkDay(tool.getPickUpTimeWorkDay())
                .pickUpTimeWeekend(tool.getPickUpTimeWeekend())
                .availableDays(tool.getAvailableDays())
                .simplifiedUserDTO(simplifiedUserDTO)
                .owner(Objects.equals(tool.getUser().getId(), userService.getLoggedUserId()))
                .build();
    }

    private SimplifiedUserDTO getSimplifiedUserDTO(Long userId) {
        SimplifiedUserDTO simplifiedUserDTO = userReviewService.getUserAverage(userId);
        simplifiedUserDTO.setFullName(userService.getUserFullName(simplifiedUserDTO.getUserId()));
        return simplifiedUserDTO;
    }
}
