package com.regain.product.controller;

import com.regain.product.model.Notification;
import com.regain.product.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification-api")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping("/getAllNotificationUnreadOfUser/{accountId}")
    public ResponseEntity<List<Notification>> getAllNotificationUnreadOfUser(@PathVariable Long accountId) {
        return new ResponseEntity<>(this.notificationService.getAllNotificationUnreadOfUser(accountId), HttpStatus.OK);
    }
}
