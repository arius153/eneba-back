package com.eneba.enebaback.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.repositories.ToolCategoryRepository;
import com.eneba.enebaback.repositories.ToolRepository;
import com.eneba.enebaback.services.ToolService;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ToolCategoryRepository toolCategoryRepository;

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
}
