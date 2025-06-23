package com.regain.product.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String content;
    private String title;
    private String fullName;
    private String avatar;
    private Long statusId;
    private String statusPostName;
    private Long accountId;
    private Long topicPostId;
    private String topicPostName;
    private Date dateCreated;
    private ImageRequest[] imageList;

    public String getStatusPostName() {
        return statusPostName;
    }

    public void setStatusPostName(String statusPostName) {
        this.statusPostName = statusPostName;
    }

    public String getTopicPostName() {
        return topicPostName;
    }

    public void setTopicPostName(String topicPostName) {
        this.topicPostName = topicPostName;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
