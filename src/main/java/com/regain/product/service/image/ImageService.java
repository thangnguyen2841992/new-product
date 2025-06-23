package com.regain.product.service.image;

import com.regain.product.model.Image;

import java.util.List;

public interface ImageService {
    Image saveImage(Image image);

    List<Image> getImagesOfPost(Long postId);
}
