package com.eneba.enebaback.services;

import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> saveAllImages(Tool tool, List<MultipartFile> images) {
        List<Image> imageList = new ArrayList<>();
        List<byte[]> imageContentList = new ArrayList<>();
        for (MultipartFile multipartFile : images) {
            byte[] bytes = new byte[0];
            try {
                bytes = multipartFile.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageContentList.add(bytes);
        }

        for(byte[] image : imageContentList) {
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
