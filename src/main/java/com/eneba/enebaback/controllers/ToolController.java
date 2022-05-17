package com.eneba.enebaback.controllers;

import java.util.List;

import com.eneba.enebaback.dto.BorrowToolDTO;
import com.eneba.enebaback.dto.BorrowingDTO;
import com.eneba.enebaback.dto.CategoryDTO;
import com.eneba.enebaback.dto.ToolBriefDTO;
import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.dto.ToolFilterModel;
import com.eneba.enebaback.dto.ToolRegisterDTO;
import com.eneba.enebaback.dto.ToolSortModel;
import com.eneba.enebaback.dto.ToolUnavailableTimeslotDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eneba.enebaback.services.impl.ToolServiceImpl;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    ToolServiceImpl toolService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAvailableCategories() {
        return toolService.getAllAvailableCategories();
    }

    @PostMapping
    public Long addTool(@RequestPart("data") ToolRegisterDTO toolRegisterDTO, @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        return toolService.registerTool(toolRegisterDTO, files);
    }

    @GetMapping("/")
    public List<ToolDTO> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/user/{userId}")
    public List<ToolBriefDTO> getAllToolsByUserId(@PathVariable Long userId) {
        return toolService.getAllTools(userId);
    }

    @GetMapping("/all")
    public List<ToolDTO> getFilteredAndSortedTools(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Boolean reversed,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) List<Integer> categories
    ) {

        if (ObjectUtils.anyNotNull(sortBy, reversed, minPrice, maxPrice, categories)) {

            ToolFilterModel toolFilterModel = ToolFilterModel.builder()
                    .minPrice(minPrice)
                    .maxPrice(maxPrice)
                    .category(categories)
                    .build();

            ToolSortModel toolSortModel = ToolSortModel.builder()
                    .sortBy(sortBy)
                    .reversed(reversed)
                    .build();

            return toolService.getSortedAndFilteredTools(toolSortModel, toolFilterModel);
        }

        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    private ToolDTO getTool(@PathVariable Long id) {
        return toolService.getTool(id);
    }

    @GetMapping("/history")
    private List<BorrowToolDTO> getBorrowedToolLog() {
        return toolService.getBorrowedToolLog();
    }

    @GetMapping("/my")
    private List<ToolBriefDTO> getCurrentlyLoggedUserTools() {
        return toolService.getLoggedUserTools();
    }

    @PostMapping("/borrow")
    public Long borrowTool(@RequestBody BorrowingDTO borrowingDTO) {
        return toolService.borrowTool(borrowingDTO);
    }

    @GetMapping("/tool-unavailable-timeslots/{id}")
    public List<ToolUnavailableTimeslotDTO> getToolUnavailableTimeslots(@PathVariable Long id) {
        return toolService.getToolUnavailableTimeslots(id);
    }
}
