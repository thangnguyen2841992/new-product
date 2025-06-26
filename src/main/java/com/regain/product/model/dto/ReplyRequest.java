package com.regain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequest {
    private Long replyCommentId;

    private Long commentId;

    private Long postId;

    private String content;

    private Long commentAccountId;

    private Long postAccountId;
    private Long accountId;

    private Date dateCreated;

    private String fullName;
    private String avatar;

    private long totalReplies;

    public Long getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

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

    public Long getCommentAccountId() {
        return commentAccountId;
    }

    public void setCommentAccountId(Long commentAccountId) {
        this.commentAccountId = commentAccountId;
    }

    public Long getPostAccountId() {
        return postAccountId;
    }

    public void setPostAccountId(Long postAccountId) {
        this.postAccountId = postAccountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

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

    public long getTotalReplies() {
        return totalReplies;
    }

    public void setTotalReplies(long totalReplies) {
        this.totalReplies = totalReplies;
    }
}
