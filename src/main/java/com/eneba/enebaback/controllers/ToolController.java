package com.eneba.enebaback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.services.ToolService;

@RestController
@RequestMapping("/tool")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping("/all")
    public List<ToolDTO> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    private ToolDTO getTool(@PathVariable Long id) {
        return toolService.getTool(id);
    }
}
