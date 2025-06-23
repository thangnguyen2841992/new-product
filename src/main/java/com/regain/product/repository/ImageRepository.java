package com.regain.product.repository;

import com.regain.product.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select * from image where post_id = :postId order by date_created desc", nativeQuery = true)
    List<Image> findByPostId(@Param("postId") Long postId);
}
