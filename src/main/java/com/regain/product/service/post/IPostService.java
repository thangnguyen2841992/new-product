package com.regain.product.service.post;

import com.regain.product.model.entity.Post;
import com.regain.product.model.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<PostDTO> getAllPostsOfUser(Long accountId);
    List<PostDTO> getAllPostsOfOtherUser(Long accountId);
    Optional<Post> findPostById(Long id);

    Post savePost(Post post);
}
