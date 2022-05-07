package com.eneba.enebaback.services.impl;

import com.eneba.enebaback.dto.CategoryDTO;
import com.eneba.enebaback.repositories.ToolCategoryRepository;
import com.eneba.enebaback.services.ToolCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolCategoryServiceImpl implements ToolCategoryService {

    @Autowired
    ToolCategoryRepository toolCategoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        return toolCategoryRepository.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
