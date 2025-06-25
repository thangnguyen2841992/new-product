package com.regain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long commentId;

    private Long postId;

    private Long accountId;
    private Long postAccountId;

    private String content;

    private Date dateCreated;
    private String fullName;
    private String avatar;

    private long totalLikeComments;

    public Long getPostAccountId() {
        return postAccountId;
    }

    public void setPostAccountId(Long postAccountId) {
        this.postAccountId = postAccountId;
    }

    public long getTotalLikeComments() {return totalLikeComments;}

    public void setTotalLikeComments(long totalLikeComments) {this.totalLikeComments = totalLikeComments;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}

    public Long getCommentId() {return commentId;}

    public void setCommentId(Long commentId) {this.commentId = commentId;}

    public Long getPostId() {return postId;}

    public void setPostId(Long postId) {this.postId = postId;}

    public Long getAccountId() {return accountId;}

    public void setAccountId(Long accountId) {this.accountId = accountId;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public Date getDateCreated() {return dateCreated;}

    public void setDateCreated(Date dateCreated) {this.dateCreated = dateCreated;}
}
