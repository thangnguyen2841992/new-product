package com.regain.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.regain.product.client.AccountService;
import com.regain.product.model.dto.AccountDTO;
import com.regain.product.model.dto.ApproFriendRequest;
import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.dto.FriendRequest;
import com.regain.product.model.entity.Chat;
import com.regain.product.model.entity.Friend;
import com.regain.product.model.entity.Notification;
import com.regain.product.service.chat.IChatService;
import com.regain.product.service.friend.IFriendService;
import com.regain.product.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class FriendController {
    @Autowired
    private IFriendService friendService;

    @Autowired
    private IChatService chatService;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/friend-api/checkStatusFriend")
    public ResponseEntity<Friend> checkStatusFriend(@RequestParam(name = "formUserId") Long formUserId, @RequestParam(name = "toUserId") Long toUserId) {
        Optional<Friend> friend = this.friendService.checkStatusFriend(formUserId, toUserId);
        return friend.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Friend(), HttpStatus.OK));
    }

    @MessageMapping("/friend")
    public void sendMessage(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            FriendRequest newFriendRequest = objectMapper.readValue(message, FriendRequest.class);
            Optional<Friend> friendOptional = this.friendService.findFriendByFormUserAndToUserId(newFriendRequest.getFormUserId(), newFriendRequest.getToUserId());
            if (friendOptional.isEmpty()) {
                Friend newFriendSave = friendService.saveFriend(newFriendRequest);
                newFriendRequest.setDateCreated(chatService.formatDateChat(newFriendSave.getDateCreated()));
                simpMessagingTemplate.convertAndSend("/topic/friend", newFriendRequest);

                Notification newNotification = new Notification();
                newNotification.setFormAccountId(newFriendRequest.getFormUserId());
                newNotification.setToAccountId(newFriendRequest.getToUserId());
                newNotification.setRead(false);
                newNotification.setDateCreated(new Date());
                newNotification.setType(5);
                AccountDTO accountDTO = this.accountService.findAccountByAccountId(newFriendRequest.getFormUserId()).getBody();
                assert accountDTO != null;
                newNotification.setContent(" đã gửi yêu cầu kết bạn!");
                Notification newSaveNotification = this.notificationService.save(newNotification);
                simpMessagingTemplate.convertAndSend("/topic/notification", newSaveNotification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @MessageMapping("/approvalFriend")
    public void sendMessageApproFriend(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            ApproFriendRequest newApprovalRequest = objectMapper.readValue(message, ApproFriendRequest.class);
            Optional<Friend> friendOptional = this.friendService.findFriendByFormUserAndToUserId(newApprovalRequest.getFormUserId(), newApprovalRequest.getToUserId());
            if (friendOptional.isPresent()) {
                Friend friend = friendOptional.get();
                if (newApprovalRequest.getIsAccepted() == 1) {
                    friend.setApproved(true);
                    friend.setDateApproved(new Date());
                    this.friendService.save(friend);
                } else {
                    this.friendService.delete(friend.getFriendId());
                }
                Optional<Notification> notification = this.notificationService.findById(newApprovalRequest.getNotificationId());
                assert notification.isPresent();
                this.notificationService.deleteById(notification.get().getNotificationId());
                if (newApprovalRequest.getIsAccepted() == 1) {
                    Notification newNotification = new Notification();
                    newNotification.setFormAccountId(newApprovalRequest.getFormUserId());
                    newNotification.setToAccountId(newApprovalRequest.getToUserId());
                    newNotification.setRead(false);
                    newNotification.setDateCreated(new Date());
                    newNotification.setType(6);
                    newNotification.setContent(" và bạn đã trở thanh bạn bè!");
                    Notification newSaveNotification = this.notificationService.save(newNotification);
                    simpMessagingTemplate.convertAndSend("/topic/notification", newSaveNotification);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
