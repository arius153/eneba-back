package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tool/")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping("/all")
    public List<ToolDTO> getAllTools() {
        return toolService.getAllTools();
    }
}
