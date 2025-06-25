package com.regain.product.service.likeComment;

import com.regain.product.model.entity.LikeComment;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ILikeCommentService {
    LikeComment save(LikeComment likeComment);

    Optional<LikeComment> findByCommentIdAndAccountId(Long commentId, Long accountId);

    void deleteLikeComment(Long likeCommentId);

    long countLikesByPostId(Long commentId);
}
