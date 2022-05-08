package com.eneba.enebaback.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.eneba.enebaback.dto.*;
import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.repositories.BorrowLogRepository;
import com.eneba.enebaback.repositories.UserRepository;
import com.eneba.enebaback.services.ImageServiceImpl;
import com.eneba.enebaback.services.UserServiceImpl;
import com.eneba.enebaback.utils.ToolSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.repositories.ToolCategoryRepository;
import com.eneba.enebaback.repositories.ToolRepository;
import com.eneba.enebaback.services.ToolService;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

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
    private UserServiceImpl userService;

    @Autowired
    private ImageServiceImpl imageService;

    @Override
    public List<ToolDTO> getAllTools() {
        return toolRepository.findAll()
                .stream()
                .map(ToolDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ToolDTO getTool(Long id) {
        Tool tool = toolRepository.getById(id);

        return ToolDTO.builder()
                .description(tool.getDescription())
                .toolCategory(toolCategoryRepository.getById(id).getCategoryName())
                .geoCordX(tool.getGeoCordX())
                .geoCordY(tool.getGeoCordY())
                .build();
    }

    @Override
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
    public Long registerTool(ToolRegisterDTO toolRegisterDTO, List<MultipartFile> files) {
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
                .pickUpTimeWeekend(toolRegisterDTO.getPickUpTimeWeekend())
                .pickUpTimeWorkDay(toolRegisterDTO.getPickUpTimeWorkDay())
                .availableDays(toolRegisterDTO.getDaysAvailable())
                .build();
        tool = toolRepository.save(tool);
        imageService.saveAllImages(tool, files);
        return tool.getId();
    }

    public BorrowLog borrowTool(BorrowToolDTO borrowToolDTO) {
        BorrowLog borrowLog = new BorrowLog();
        borrowLog.setTool(toolRepository.findById(borrowToolDTO.getToolId()).orElse(null));
        borrowLog.setUser(userRepository.findById(borrowToolDTO.getBorrowedByUserId()).orElse(null));
        borrowLog.setBorrowedAt(borrowToolDTO.getBorrowedAt());
        borrowLog.setReturnedAt(null);
        borrowLogRepository.save(borrowLog);
        return borrowLog;
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

    public List<CategoryDTO> getAllAvailableCategories() {
        return toolCategoryRepository.findAll()
                .stream()
                .map(categorie -> new CategoryDTO(categorie.getId(), categorie.getCategoryName()))
                .collect(Collectors.toList());
    }

    private boolean isToolAvailable(Long toolId) {
        return toolRepository.findAvailableToolById(toolId) != null;
    }
}
