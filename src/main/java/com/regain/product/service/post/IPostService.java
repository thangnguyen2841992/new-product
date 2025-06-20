package com.regain.product.service.post;

import com.regain.product.model.Post;
import com.regain.product.model.PostDTO;

import java.util.List;

public interface IPostService {

    List<PostDTO> getAllPostsOfUser(Long accountId);


    Post savePost(Post post);
}
