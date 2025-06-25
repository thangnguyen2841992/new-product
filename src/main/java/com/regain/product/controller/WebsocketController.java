package com.regain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regain.product.client.AccountService;
import com.regain.product.model.dto.*;
import com.regain.product.model.entity.*;
import com.regain.product.service.comment.ICommentService;
import com.regain.product.service.image.ImageService;
import com.regain.product.service.likeComment.ILikeCommentService;
import com.regain.product.service.likePost.ILikePostService;
import com.regain.product.service.notification.INotificationService;
import com.regain.product.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ILikeCommentService likeCommentService;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


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
                            AccountDTO accountDTOSendEmail = this.accountService.findAccountByAccountId(post.get().getUserId()).getBody();
                            MessageNotification messageNotification = new MessageNotification();
                            messageNotification.setFrom("nguyenthang29tbdl@gmail.com");
                            assert accountDTOSendEmail != null;
                            messageNotification.setTo(accountDTOSendEmail.getEmail());
                            messageNotification.setToName(accountDTOSendEmail.getFullName());
                            messageNotification.setContent(accountDTO.getFullName() + " đã thích bài viết" + post.get().getTitle()+ " của bạn!");
                            kafkaTemplate.send("send-email-notification", messageNotification);
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
    @MessageMapping("/commentPost")
    public void sendCommentPost(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            CommentRequest newCommentRequest = objectMapper.readValue(message, CommentRequest.class);
            Comment newCommentSave = this.commentService.saveComment(newCommentRequest);
            newCommentRequest.setDateCreated(newCommentSave.getDateCreated());
            newCommentRequest.setCommentId(newCommentSave.getCommentId());
            AccountDTO accountDTO = this.accountService.findAccountByAccountId(newCommentRequest.getAccountId()).getBody();
            assert accountDTO != null;
            newCommentRequest.setFullName(accountDTO.getFullName());
            newCommentRequest.setAvatar(accountDTO.getAvatar());
            simpMessagingTemplate.convertAndSend("/topic/comment", newCommentRequest);
            if (!Objects.equals(newCommentRequest.getPostAccountId(), newCommentRequest.getAccountId())) {
                Notification notification = new Notification();
                notification.setPostId(newCommentSave.getPostId());
                notification.setFormAccountId(newCommentSave.getAccountId());
                Optional<Post> postDTO = this.postService.findPostById(newCommentSave.getPostId());
                postDTO.ifPresent(post -> notification.setToAccountId(post.getUserId()));
                notification.setType(2);
                notification.setRead(false);
                notification.setDateCreated(new Date());
                notification.setContent(" đã bình luận ở bài viết của bạn!");
                Notification notificationSave = this.notificationService.save(notification);
                simpMessagingTemplate.convertAndSend("/topic/notification", notificationSave);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MessageMapping("/likeComment")
    public void sendLikeComment(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            LikeComment newLikeComment = objectMapper.readValue(message, LikeComment.class);
            Optional<LikeComment> checkExistLikeComment = this.likeCommentService.findByCommentIdAndAccountId(newLikeComment.getCommentId(), newLikeComment.getAccountId());
            if (checkExistLikeComment.isEmpty()) {
                newLikeComment.setDateCreated(new Date());
                LikeComment newLikeCommentSave = this.likeCommentService.save(newLikeComment);
                long totalLikeComment = this.likeCommentService.countLikesByPostId(newLikeComment.getCommentId());
                newLikeCommentSave.setTotalLikes(totalLikeComment);
                simpMessagingTemplate.convertAndSend("/topic/likeComment", newLikeCommentSave);
                    if (!Objects.equals(newLikeComment.getAccountCommentId(), newLikeComment.getAccountId())) {
                        Optional<Notification> notificationOptional = this.notificationService.findByTypeIdAndCommentIdIdAndUserId(newLikeComment.getAccountId(), newLikeComment.getCommentId(), 3);
                        if (notificationOptional.isEmpty()) {
                            Notification newNotification = new Notification();
                            newNotification.setFormAccountId(newLikeComment.getAccountId());
                            newNotification.setToAccountId(newLikeComment.getAccountCommentId());
                            newNotification.setRead(false);
                            newNotification.setCommentId(newLikeComment.getCommentId());
                            newNotification.setDateCreated(new Date());
                            newNotification.setPostId(newLikeComment.getPostId());
                            newNotification.setType(3);
                            newNotification.setContent(" đã thích bình luận của bạn!");
                            Notification newSaveNotification = this.notificationService.save(newNotification);
                            simpMessagingTemplate.convertAndSend("/topic/notification", newSaveNotification);
                        }
                    }
            } else {
                this.likeCommentService.deleteLikeComment(checkExistLikeComment.get().getLikeCommentId());
                long totalLikeComment = this.likeCommentService.countLikesByPostId(newLikeComment.getCommentId());
                LikeComment newSaveLikeComment = new LikeComment();
                newSaveLikeComment.setPostId(newLikeComment.getPostId());
                newSaveLikeComment.setCommentId(newLikeComment.getCommentId());
                newSaveLikeComment.setTotalLikes(totalLikeComment);
                simpMessagingTemplate.convertAndSend("/topic/likeComment", newSaveLikeComment);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ExceptionHandler(Exception.class)
    @SendTo("/topic/errors")
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }

}
