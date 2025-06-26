package com.regain.product.service.chat;

import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.entity.Chat;

public interface IChatService {
    Chat save(ChatRequest chatRequest);
}
