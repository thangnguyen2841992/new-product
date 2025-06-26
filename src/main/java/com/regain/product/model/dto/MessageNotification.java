package com.regain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MessageNotification {
    private String from;
    private String to;
    private String toName;
    private String formName;
    private String postTitle;
    private Long postId;
    private Long formUserId;
    private int typeNotification;
    private String subject;
    private String content;


    public int getTypeNotification() {return typeNotification;}

    public void setTypeNotification(int typeNotification) {this.typeNotification = typeNotification;}

    public Long getPostId() {return postId;}

    public void setPostId(Long postId) {this.postId = postId;}

    public Long getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Long formUserId) {
        this.formUserId = formUserId;
    }

    public String getPostTitle() {return postTitle;}

    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}

    public String getFormName() {return formName;}

    public void setFormName(String formName) {this.formName = formName;}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
