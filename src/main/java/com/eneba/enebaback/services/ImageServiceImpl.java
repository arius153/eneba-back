package com.eneba.enebaback.services;

import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> saveAllImages(Tool tool, List<byte []> images) {
        List<Image> imageList = new ArrayList<>();
        for(byte[] image : images) {
            imageList.add(saveImage(tool, image));
        }
        return imageList;
    }

    public Image saveImage(Tool tool, byte[] image) {
        Image img = new Image();
        img.setTool(tool);
        img.setImage(image);
        return imageRepository.save(img);
    }
}
