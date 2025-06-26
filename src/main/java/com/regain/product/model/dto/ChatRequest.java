package com.regain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {
    private Long chatId;

    private Long formUserId;
    private String formUserFullName;
    private String formUserAvatar;

    private Long toUserId;
    private String toUserFullName;
    private String toUserAvatar;

    private String message;

    private Date dateCreated;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Long formUserId) {
        this.formUserId = formUserId;
    }

    public String getFormUserFullName() {
        return formUserFullName;
    }

    public void setFormUserFullName(String formUserFullName) {
        this.formUserFullName = formUserFullName;
    }

    public String getFormUserAvatar() {
        return formUserAvatar;
    }

    public void setFormUserAvatar(String formUserAvatar) {
        this.formUserAvatar = formUserAvatar;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserFullName() {
        return toUserFullName;
    }

    public void setToUserFullName(String toUserFullName) {
        this.toUserFullName = toUserFullName;
    }

    public String getToUserAvatar() {
        return toUserAvatar;
    }

    public void setToUserAvatar(String toUserAvatar) {
        this.toUserAvatar = toUserAvatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
