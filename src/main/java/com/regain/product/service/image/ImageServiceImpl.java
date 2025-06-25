package com.regain.product.service.image;

import com.regain.product.model.entity.Image;
import com.regain.product.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image newImage) {
        return imageRepository.save(newImage);
    }

    @Override
    public List<Image> getImagesOfPost(Long postId) {
        return imageRepository.findByPostId(postId);
    }
}
