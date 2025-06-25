package com.regain.product.controller;

import com.regain.product.model.entity.Image;
import com.regain.product.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image-api")
@CrossOrigin("*")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/getAllImageOfPost/{postId}")
    public ResponseEntity<List<Image>> getAllImageOfPost(@PathVariable Long postId) {
        return new ResponseEntity<>(imageService.getImagesOfPost(postId), HttpStatus.OK);
    }
}
