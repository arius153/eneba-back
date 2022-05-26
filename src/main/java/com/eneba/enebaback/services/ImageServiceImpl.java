package com.eneba.enebaback.services;

import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.entities.Tool;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.repositories.ImageRepository;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.NotSupportedException;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl {

    @Autowired
    ImageRepository imageRepository;

    @Transactional
    public Set<Image> saveAllImages(Tool tool, List<MultipartFile> images) {
        Set<Image> imageList = new HashSet<>();
        List<byte[]> imageContentList = new ArrayList<>();
        for (MultipartFile multipartFile : images) {
            byte[] bytes = new byte[0];
            try {
                bytes = multipartFile.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageContentList.add(scaleImage(bytes));
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

    private byte[] scaleImage(byte[] image) {
        BufferedImage imageToResize;
        try (InputStream is = new ByteArrayInputStream(image); ByteArrayOutputStream boas = new ByteArrayOutputStream()) {
            imageToResize = ImageIO.read(is);
            imageToResize = Scalr.resize(imageToResize, Scalr.Method.QUALITY, 300);
            ImageIO.write(imageToResize, "png", boas);
            return boas.toByteArray();
        } catch (IOException | IllegalArgumentException e) {
            return image;
        }
    }

    public void deleteOldImagesByIds(List<Long> ids) {
        imageRepository.deleteAllById(ids);
    }
}
