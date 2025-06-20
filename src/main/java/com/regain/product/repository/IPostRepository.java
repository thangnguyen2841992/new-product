package com.regain.product.repository;

import com.regain.product.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    List<Post> findByEmail(String email);
}
