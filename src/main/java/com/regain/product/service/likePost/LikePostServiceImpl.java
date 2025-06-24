package com.regain.product.service.likePost;

import com.regain.product.model.LikePost;
import com.regain.product.repository.ILikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostServiceImpl implements ILikePostService{
    @Autowired
    private ILikePostRepository likePostRepository;

    @Override
    public LikePost save(LikePost likePost) {
        return this.likePostRepository.save(likePost);
    }

    @Override
    public Optional<LikePost> findByPostIdAndAccountId(Long postId, Long accountId) {
        return this.likePostRepository.findByPostIdAndAccountId(postId, accountId);
    }

    @Override
    public long countLikesByPostId(Long postId) {
        return this.likePostRepository.countLikesByPostId(postId);
    }

    @Override
    public void deleteLikePost(Long likePostId) {
        this.likePostRepository.deleteById(likePostId);
    }
}
