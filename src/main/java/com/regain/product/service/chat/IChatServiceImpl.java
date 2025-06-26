package com.regain.product.service.chat;

import com.regain.product.client.AccountService;
import com.regain.product.model.dto.AccountDTO;
import com.regain.product.model.dto.ChatRequest;
import com.regain.product.model.entity.Chat;
import com.regain.product.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IChatServiceImpl implements IChatService{
    @Autowired
    private IChatRepository chatRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Chat save(ChatRequest chatRequest) {
        Chat newChat = new Chat();
        newChat.setDateCreated(new Date());
        newChat.setMessage(chatRequest.getMessage());
        newChat.setFormUserId(chatRequest.getFormUserId());
        newChat.setToUserId(chatRequest.getToUserId());
        return chatRepository.save(newChat);
    }

    @Override
    public List<ChatRequest> findAllChatOfFormUserAndToUser(Long formUserId, Long toUserId) {
        List<Chat> chatList = chatRepository.findAllChatOfFormUserAndToUser(formUserId, toUserId);
        List<ChatRequest> chatRequestList = new ArrayList<>();
        for (Chat chat : chatList) {
            ChatRequest chatRequest = new ChatRequest();
            chatRequest.setChatId(chat.getChatId());
            chatRequest.setFormUserId(chat.getFormUserId());
            chatRequest.setToUserId(chat.getToUserId());
            chatRequest.setMessage(chat.getMessage());
            chatRequest.setDateCreated(formatDateChat(chat.getDateCreated()));

            AccountDTO formAccountDTO = accountService.findAccountByAccountId(formUserId).getBody();
            assert formAccountDTO != null;
            chatRequest.setFormUserAvatar(formAccountDTO.getAvatar());
            chatRequest.setFormUserFullName(formAccountDTO.getFullName());

            AccountDTO toAccountDTO = accountService.findAccountByAccountId(toUserId).getBody();
            assert toAccountDTO != null;
            chatRequest.setToUserAvatar(toAccountDTO.getAvatar());
            chatRequest.setToUserFullName(toAccountDTO.getFullName());
            chatRequestList.add(chatRequest);
        }
        return chatRequestList;
    }

    @Override
    public String formatDateChat(Date dateCreated) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return formatter.format(dateCreated);

    }
}
