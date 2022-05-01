package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.ToolDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToolService {

    List<ToolDTO> getAllTools();

}
