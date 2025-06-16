package com.regain.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long topicPostId;

    private String name;
}
