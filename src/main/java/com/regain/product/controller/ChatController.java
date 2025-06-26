package com.regain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.dto.PostRequest;
import com.regain.product.model.entity.Chat;
import com.regain.product.model.entity.Post;
import com.regain.product.service.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private IChatService chatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Chuyển đổi chuỗi JSON thành đối tượng Java
            ChatRequest newChat = objectMapper.readValue(message, ChatRequest.class);
            Chat newChatSave = chatService.save(newChat);
            newChat.setDateCreated(newChatSave.getDateCreated());
            simpMessagingTemplate.convertAndSend("/topic/chat", newChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
