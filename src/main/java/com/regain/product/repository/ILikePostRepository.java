package com.regain.product.repository;

import com.regain.product.model.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {

    @Query(value = "select * from like_post where post_id = :postId and account_id = :accountId ", nativeQuery = true)
    Optional<LikePost> findByPostIdAndAccountId(@Param("postId") Long postId, @Param("accountId") Long accountId);

    @Query(value = "SELECT COUNT(*) FROM like_post WHERE post_id = :postId", nativeQuery = true)
    long countLikesByPostId(@Param("postId") Long postId);

}
