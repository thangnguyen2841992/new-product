package com.regain.product.repository;

import com.regain.product.model.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReplyCommentRepository extends JpaRepository<ReplyComment, Long> {

    @Query(value = "select * from reply_comment where post_id = :postId and comment_id = :commentId order by date_created desc", nativeQuery = true)
    List<ReplyComment> findAllReplyOfComment(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
