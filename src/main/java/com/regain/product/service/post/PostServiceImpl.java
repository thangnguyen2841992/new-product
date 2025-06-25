package com.regain.product.service.post;

import com.regain.product.client.AccountService;
import com.regain.product.model.dto.AccountDTO;
import com.regain.product.model.dto.CommentRequest;
import com.regain.product.model.dto.PostDTO;
import com.regain.product.model.entity.*;
import com.regain.product.repository.*;
import com.regain.product.service.comment.ICommentService;
import com.regain.product.service.likePost.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private ITopicPostRepository topicPostRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ICommentService commentService;

    @Override
    public List<PostDTO> getAllPostsOfUser(Long accountId) {
        List<Post> posts = this.postRepository.findAllPostOfUser(accountId);
        return mappingListPostToPostDTO(posts);
    }

    private List<PostDTO> mappingListPostToPostDTO(List<Post> posts) {
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(post.getPostId());
            postDTO.setStatusPostId(post.getStatusId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            AccountDTO accountDTO = accountService.findAccountByAccountId(post.getUserId()).getBody();
            assert accountDTO != null;
            postDTO.setAvatar(accountDTO.getAvatar());
            postDTO.setFullName(accountDTO.getFullName());
            postDTO.setDateCreated(post.getDateCreated());
            Status status = statusRepository.findById(post.getStatusId()).orElseThrow(() -> new RuntimeException("Status Not Found " + post.getStatusId()));
            postDTO.setStatusPostName(status.getStatusName());
            TopicPost topicPost = topicPostRepository.findById(post.getTopicPostId()).orElseThrow(() -> new RuntimeException("TopicPost Not Found " + post.getTopicPostId()));
            postDTO.setTopicPostName(topicPost.getName());
            List<Image> images = this.imageRepository.findByPostId(post.getPostId());
            postDTO.setImages(images);
            long totalLikes = this.likePostService.countLikesByPostId(post.getPostId());
            postDTO.setTotalLikes(totalLikes);
            List<CommentRequest> comments = this.commentService.findAllCommentByPostId(post.getPostId());
            postDTO.setCommentList(comments);
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    @Override
    public List<PostDTO> getAllPostsOfOtherUser(Long accountId) {
        List<Post> posts = this.postRepository.findAllPostOfOtherUser(accountId);
        return mappingListPostToPostDTO(posts);
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return this.postRepository.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return this.postRepository.save(post);
    }
}
