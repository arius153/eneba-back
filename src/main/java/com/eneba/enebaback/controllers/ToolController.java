package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.*;
import com.eneba.enebaback.services.impl.ToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

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
}
