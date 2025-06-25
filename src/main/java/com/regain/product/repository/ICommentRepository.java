package com.regain.product.repository;

import com.regain.product.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment where post_id = :postId order by date_created desc", nativeQuery = true)
    List<Comment> findAllCommentByPostId(@Param("postId") Long postId);
}
