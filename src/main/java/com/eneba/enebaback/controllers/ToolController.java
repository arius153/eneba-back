package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.CategoryDTO;
import com.eneba.enebaback.services.ToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}