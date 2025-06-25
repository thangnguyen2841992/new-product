package com.regain.product.repository;

import com.regain.product.model.entity.TopicPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "topics")
public interface ITopicPostRepository extends JpaRepository<TopicPost, Long> {
}
