package com.regain.product.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String content;
    private String title;
    private Long statusId;
    private Long accountId;
    private Long topicPostId;
    private ImageRequest[] imageList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTopicPostId() {
        return topicPostId;
    }

    public void setTopicPostId(Long topicPostId) {
        this.topicPostId = topicPostId;
    }

    public ImageRequest[] getImageList() {
        return imageList;
    }

    public void setImageList(ImageRequest[] imageList) {
        this.imageList = imageList;
    }
}
