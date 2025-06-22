package com.regain.product.service.post;

import com.regain.product.client.AccountService;
import com.regain.product.model.*;
import com.regain.product.repository.IPostRepository;
import com.regain.product.repository.IStatusRepository;
import com.regain.product.repository.ITopicPostRepository;
import com.regain.product.service.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private ITopicPostRepository topicPostRepository;

    @Override
    public List<PostDTO> getAllPostsOfUser(Long accountId) {
        AccountDTO accountDTO = accountService.findAccountByAccountId(accountId).getBody();
        List<Post> posts = this.postRepository.findAllPostOfUser(accountId);
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(post.getPostId());
            postDTO.setStatusPostId(post.getStatusId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            assert accountDTO != null;
            postDTO.setAvatar(accountDTO.getAvatar());
            postDTO.setFullName(accountDTO.getFullName());
            postDTO.setDateCreated(post.getDateCreated());
            Status status = statusRepository.findById(post.getStatusId()).orElseThrow(() -> new RuntimeException("Status Not Found " + post.getStatusId()));
            postDTO.setStatusPostName(status.getStatusName());
            TopicPost topicPost = topicPostRepository.findById(post.getTopicPostId()).orElseThrow(() -> new RuntimeException("TopicPost Not Found " + post.getTopicPostId()));
            postDTO.setTopicPostName(topicPost.getName());
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    @Override
    public Post savePost(Post post) {
        return this.postRepository.save(post);
    }
}
