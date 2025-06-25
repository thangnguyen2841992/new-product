package com.regain.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TopicPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long topicPostId;

    private String name;

    public Long getTopicPostId() {
        return topicPostId;
    }

    public void setTopicPostId(Long topicPostId) {
        this.topicPostId = topicPostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
