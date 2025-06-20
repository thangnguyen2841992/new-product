package com.regain.product.service.post;

import com.regain.product.model.Post;
import com.regain.product.model.PostDTO;
import com.regain.product.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<PostDTO> getAllPostsOfUser(Long accountId) {

        return List.of();
    }

    @Override
    public Post savePost(Post post) {
        return this.postRepository.save(post);
    }
}
