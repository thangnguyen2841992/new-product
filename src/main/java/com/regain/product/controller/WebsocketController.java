package com.regain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regain.product.client.AccountService;
import com.regain.product.model.*;
import com.regain.product.service.image.ImageService;
import com.regain.product.service.likePost.ILikePostService;
import com.regain.product.service.notification.INotificationService;
import com.regain.product.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
public class WebsocketController {
    @Autowired
    IPostService postService;

    @Autowired
    ImageService imageService;

    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


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
            newPost.setDateCreated(newPostSave.getDateCreated());
            newPost.setTotalLikes(0);
            return newPost;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        // Echo the message back
    }

    @MessageMapping("/likePost")
//    @SendTo("/topic/likePost")
    public void sendLikePost(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            LikePost newLikePost = objectMapper.readValue(message, LikePost.class);
            Optional<LikePost> checkExistLikePost = this.likePostService.findByPostIdAndAccountId(newLikePost.getPostId(), newLikePost.getAccountId());
            if (checkExistLikePost.isEmpty()) {
                newLikePost.setDateCreated(new Date());
                LikePost newSaveLikePost = this.likePostService.save(newLikePost);
                long totalLike = this.likePostService.countLikesByPostId(newSaveLikePost.getPostId());
                newSaveLikePost.setTotalLikes(totalLike);
                simpMessagingTemplate.convertAndSend("/topic/likePost", newSaveLikePost);
                Optional<Post> post = this.postService.findPostById(newLikePost.getPostId());
                if (post.isPresent()) {
                    if (!Objects.equals(post.get().getUserId(), newLikePost.getAccountId())) {
                        Optional<Notification> notificationOptional = this.notificationService.findByTypeIdAndPostIdAndUserId(newLikePost.getAccountId(), post.get().getPostId(), 1);
                        if (notificationOptional.isEmpty()) {
                            Notification newNotification = new Notification();
                            newNotification.setFormAccountId(newLikePost.getAccountId());
                            newNotification.setToAccountId(post.get().getUserId());
                            newNotification.setRead(false);
                            newNotification.setDateCreated(new Date());
                            newNotification.setPostId(post.get().getPostId());
                            newNotification.setType(1);
                            AccountDTO accountDTO = this.accountService.findAccountByAccountId(newLikePost.getAccountId()).getBody();
                            assert accountDTO != null;
                            newNotification.setContent(" đã thích bài viết của bạn!");
                            Notification newSaveNotification = this.notificationService.save(newNotification);
                            simpMessagingTemplate.convertAndSend("/topic/notification", newSaveNotification);
                        }
                    }
                }
            } else {
                this.likePostService.deleteLikePost(checkExistLikePost.get().getLikeCommentId());
                long totalLike = this.likePostService.countLikesByPostId(newLikePost.getPostId());
                LikePost newSaveLikePost = new LikePost();
                newSaveLikePost.setPostId(newLikePost.getPostId());
                newSaveLikePost.setTotalLikes(totalLike);
                simpMessagingTemplate.convertAndSend("/topic/likePost", newSaveLikePost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ExceptionHandler(Exception.class)
    @SendTo("/topic/errors")
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }

}
