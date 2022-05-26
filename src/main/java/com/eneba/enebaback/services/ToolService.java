package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.*;
import com.eneba.enebaback.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public interface ToolService {

    List<ToolDTO> getAllTools();

    List<ToolBriefDTO> getAllTools(Long userId);

    ToolDTO getTool(Long id);

    List<ToolDTO> getSortedAndFilteredTools(ToolSortModel toolSortModel, ToolFilterModel toolFilterModel);

    ToolBriefDTO registerTool(ToolRegisterDTO toolRegisterDTO, List<MultipartFile> files);

    ToolDTO editTool(ToolRegisterDTO toolRegisterDTO, List<MultipartFile> files, Long id);

    Long borrowTool(BorrowingDTO borrowingDTO);

    List<CategoryDTO> getAllAvailableCategories();

    List<BorrowToolDTO> getBorrowedToolLog();

    List<ToolBriefDTO> getLoggedUserTools();

    List<ToolUnavailableTimeslotDTO> getToolUnavailableTimeslots(Long toolId);

    ToolRegisterDTO getToolForEditing(Long id);

    List<ReservedToolDTO> getCurrentlyRentedLogs();

}
