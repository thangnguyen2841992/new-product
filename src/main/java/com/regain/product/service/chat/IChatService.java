package com.regain.product.service.chat;

import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.entity.Chat;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IChatService {
    Chat save(ChatRequest chatRequest);


    List<ChatRequest> findAllChatOfFormUserAndToUser( Long formUserId,  Long toUserId);

    String formatDateChat(Date dateCreated);
}
