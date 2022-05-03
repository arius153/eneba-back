package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.CategoryDTO;
import com.eneba.enebaback.dto.ToolRegisterDTO;
import com.eneba.enebaback.services.ToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        toolService.registerTool(toolRegisterDTO, files);
    }

}
