package com.regain.product.service.image;

import com.regain.product.model.Image;
import com.regain.product.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image newImage) {
        return imageRepository.save(newImage);
    }
}
