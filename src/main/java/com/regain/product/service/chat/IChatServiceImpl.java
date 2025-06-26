package com.regain.product.service.chat;

import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.entity.Chat;
import com.regain.product.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IChatServiceImpl implements IChatService{
    @Autowired
    private IChatRepository chatRepository;

    @Override
    public Chat save(ChatRequest chatRequest) {
        Chat newChat = new Chat();
        newChat.setDateCreated(new Date());
        newChat.setMessage(chatRequest.getMessage());
        newChat.setFormUserId(chatRequest.getFormUserId());
        newChat.setToUserId(chatRequest.getToUserId());
        return chatRepository.save(newChat);
    }
}
