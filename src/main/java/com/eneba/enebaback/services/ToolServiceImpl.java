package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.BorrowToolDTO;
import com.eneba.enebaback.dto.ReturnToolDTO;
import com.eneba.enebaback.dto.ToolRegisterDTO;
import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO INSTEAD OF ORELSE(NULL) ADD EXCEPTION THROWING
@Service
public class ToolServiceImpl {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private BorrowLogRepository borrowLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private ToolCategoryRepository toolCategoryRepository;

    public Tool registerTool(ToolRegisterDTO toolRegisterDTO) {
        Tool tool = new Tool();
        tool.setComment(toolRegisterDTO.getComment());
        tool.setGeoCordX(toolRegisterDTO.getGeoCordX());
        tool.setGeoCordY(toolRegisterDTO.getGeoCordY());
        tool.setUser(userRepository.findById(toolRegisterDTO.getOwnerUserId()).orElse(null));
        tool.setToolCategory(toolCategoryRepository.findById(toolRegisterDTO.getToolCategoryId()).orElse(null));
        tool = toolRepository.save(tool);
        imageService.saveAllImages(tool, toolRegisterDTO.getImages());
        return tool;
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

    private boolean isToolAvailable(Long toolId) {
        return toolRepository.findAvailableToolById(toolId) != null;
    }

}
