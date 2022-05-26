package com.eneba.enebaback.services;

import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.entities.Tool;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImageService {

    public Set<Image> saveAllImages(Tool tool, List<MultipartFile> images);

    public Image saveImage(Tool tool, byte[] image);

    public void deleteOldImagesByIds(List<Long> ids);
}
