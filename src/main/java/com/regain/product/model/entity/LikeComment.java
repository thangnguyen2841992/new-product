package com.regain.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentId;

    private Long accountId;

    private Long commentId;

    private Long postId;

    private Date dateCreated;
    private long totalLikes;

    public long getTotalLikes() {return totalLikes;}

    public void setTotalLikes(long totalLikes) {this.totalLikes = totalLikes;}

    public Long getLikeCommentId() {return likeCommentId;}

    public void setLikeCommentId(Long likeCommentId) {this.likeCommentId = likeCommentId;}

    public Long getAccountId() {return accountId;}

    public void setAccountId(Long accountId) {this.accountId = accountId;}

    public Long getCommentId() {return commentId;}

    public void setCommentId(Long commentId) {this.commentId = commentId;}

    public Long getPostId() {return postId;}

    public void setPostId(Long postId) {this.postId = postId;}

    public Date getDateCreated() {return dateCreated;}

    public void setDateCreated(Date dateCreated) {this.dateCreated = dateCreated;}
}
