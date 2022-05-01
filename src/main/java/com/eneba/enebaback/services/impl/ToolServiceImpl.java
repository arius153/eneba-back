package com.eneba.enebaback.services.impl;

import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.repositories.ToolRepository;
import com.eneba.enebaback.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public List<ToolDTO> getAllTools() {
        return toolRepository.findAll()
                .stream()
                .map(ToolDTO::new)
                .collect(Collectors.toList());
    }
}
