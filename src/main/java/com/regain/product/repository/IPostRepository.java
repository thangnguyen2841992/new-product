package com.regain.product.repository;

import com.regain.product.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {


    @Query(value = "Select * from post where user_id = :accountId order by date_created desc", nativeQuery = true)
    List<Post> findAllPostOfUser(@Param("accountId") Long accountId);

    @Query(value = "Select * from post where user_id <> :accountId and status_id = 1 order by date_created desc", nativeQuery = true)
    List<Post> findAllPostOfOtherUser(@Param("accountId") Long accountId);
}
