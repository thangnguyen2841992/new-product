package com.regain.product.controller;

import com.regain.product.model.dto.PostDTO;
import com.regain.product.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-api")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IPostService postService;


    @GetMapping("/getAllPostsOfUser/{accountId}")
    public ResponseEntity<List<PostDTO>> getAllPost(@PathVariable Long accountId) {
       List<PostDTO> postDTOS = this.postService.getAllPostsOfUser(accountId);
        return ResponseEntity.ok().body(postDTOS);
    }

    @GetMapping("/getAllPostsOfOtherUser/{accountId}")
    public ResponseEntity<List<PostDTO>> getAllPostsOfOtherUser(@PathVariable Long accountId) {
       List<PostDTO> postDTOS = this.postService.getAllPostsOfOtherUser(accountId);
        return ResponseEntity.ok().body(postDTOS);
    }
}
