package com.regain.product.repository;

import com.regain.product.model.entity.LikeComment;
import com.regain.product.model.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    @Query(value = "select * from like_comment where comment_id = :commentId and account_id = :accountId ", nativeQuery = true)
    Optional<LikeComment> findByCommentIdAndAccountId(@Param("commentId") Long commentId, @Param("accountId") Long accountId);

    @Query(value = "SELECT COUNT(*) FROM like_comment WHERE comment_id = :commentId", nativeQuery = true)
    long countLikesByPostId(@Param("commentId") Long commentId);
}
