package com.regain.product.model.dto;

import com.regain.product.model.entity.Comment;
import com.regain.product.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;

    private String title;

    private String content;

    private Date dateCreated;

    private Long topicPostId;
    private Long userId;

    private String topicPostName;

    private Long statusPostId;

    private String statusPostName;

    private String fullName;

    private String avatar;

    private List<Image> images;

    private long totalLikes;

    private long totalNotificationUnread;

    private List<CommentRequest> commentList;

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public List<CommentRequest> getCommentList() {return commentList;}

    public void setCommentList(List<CommentRequest> commentList) {this.commentList = commentList;}

    public long getTotalNotificationUnread() {return totalNotificationUnread;}

    public void setTotalNotificationUnread(long totalNotificationUnread) {this.totalNotificationUnread = totalNotificationUnread;}

    public long getTotalLikes() {return totalLikes;}

    public void setTotalLikes(long totalLikes) {this.totalLikes = totalLikes;}

    public List<Image> getImages() {return images;}

    public void setImages(List<Image> images) {this.images = images;}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {this.dateCreated = dateCreated;}

    public Long getTopicPostId() {return topicPostId;}

    public void setTopicPostId(Long topicPostId) {this.topicPostId = topicPostId;}

    public String getTopicPostName() {return topicPostName;}

    public void setTopicPostName(String topicPostName) {this.topicPostName = topicPostName;}

    public Long getStatusPostId() {return statusPostId;}

    public void setStatusPostId(Long statusPostId) {this.statusPostId = statusPostId;}

    public String getStatusPostName() {return statusPostName;}

    public void setStatusPostName(String statusPostName) {this.statusPostName = statusPostName;}
}
