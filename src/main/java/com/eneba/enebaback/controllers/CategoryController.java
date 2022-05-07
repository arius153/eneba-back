package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.CategoryDTO;
import com.eneba.enebaback.services.ToolCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ToolCategoryService toolCategoryService;

    @GetMapping("/")
    public List<CategoryDTO> getAllCategories() {
        return toolCategoryService.findAll();
    }
}
