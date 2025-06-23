package com.regain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regain.product.model.Image;
import com.regain.product.model.Post;
import com.regain.product.model.PostRequest;
import com.regain.product.service.image.ImageService;
import com.regain.product.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
public class WebsocketController {
    @Autowired
    IPostService  postService;

    @Autowired
    ImageService imageService;


    @MessageMapping("/message")
    @SendTo("/topic/posts")
    public PostRequest sendMessage(@Payload String message) {
        Post post = new Post();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            PostRequest newPost = objectMapper.readValue(message, PostRequest.class);
            post.setUserId(newPost.getAccountId());
            post.setTitle(newPost.getTitle());
            post.setContent(newPost.getContent());
            post.setTopicPostId(newPost.getTopicPostId());
            post.setStatusId(newPost.getStatusId());
            post.setDateCreated(new Date());
            Post newPostSave = this.postService.savePost(post);
            for (int i = 0; i < newPost.getImageList().length; i++) {
                Image image = new Image();
                image.setImageData(newPost.getImageList()[i].getImageData());
                image.setPostId(newPostSave.getPostId());
                image.setDateCreated(new Date());
                this.imageService.saveImage(image);
            }
            newPost.setPostId(newPostSave.getPostId());
            // Chuyển đổi Date thành Instant
//            Instant instant = newPostSave.getDateCreated().toInstant();

            // Định dạng thời gian theo định dạng ISO 8601
//            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC"));

            // Chuyển đổi Instant thành String
//            String formattedDate = formatter.format(instant);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            newPost.setDateCreated(newPostSave.getDateCreated());
            return newPost;
            // In ra thông tin đối tượng
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
         // Echo the message back
    }

    @ExceptionHandler(Exception.class)
    @SendTo("/topic/errors")
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }

}
