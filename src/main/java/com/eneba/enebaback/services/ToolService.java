package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.ToolDTO;
import com.eneba.enebaback.dto.ToolFilterModel;
import com.eneba.enebaback.dto.ToolSortModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToolService {

    List<ToolDTO> getAllTools();

    ToolDTO getTool(Long id);

    List<ToolDTO> getSortedAndFilteredTools(ToolSortModel toolSortModel, ToolFilterModel toolFilterModel);
}
