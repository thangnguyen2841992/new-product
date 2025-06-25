package com.regain.product.service.likePost;

import com.regain.product.model.entity.LikePost;

import java.util.Optional;

public interface ILikePostService {
    LikePost save(LikePost likePost);

    Optional<LikePost> findByPostIdAndAccountId(Long postId, Long accountId);

    long countLikesByPostId(Long postId);


    void deleteLikePost(Long likePostId);
}
