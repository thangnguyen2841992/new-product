package com.regain.product.controller;

import com.regain.product.model.TopicPost;
import com.regain.product.service.topicPost.ITopicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class ITopicPostRestController {
    @Autowired
    private ITopicPostService topicPostService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TopicPost>> getAll() {
        List<TopicPost> topicPosts = topicPostService.getTopicPosts();
        return new ResponseEntity<>(topicPosts, HttpStatus.OK);
    }
}
