package com.regain.product.service.likeComment;

import com.regain.product.model.entity.LikeComment;
import com.regain.product.repository.ILikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements ILikeCommentService {
    @Autowired
    private ILikeCommentRepository likeCommentRepository;

    @Override
    public LikeComment save(LikeComment likeComment) {
        return this.likeCommentRepository.save(likeComment);
    }

    @Override
    public Optional<LikeComment> findByCommentIdAndAccountId(Long commentId, Long accountId) {
        return this.likeCommentRepository.findByCommentIdAndAccountId(commentId, accountId);
    }

    @Override
    public void deleteLikeComment(Long likeCommentId) {
        this.likeCommentRepository.deleteById(likeCommentId);
    }

    @Override
    public long countLikesByPostId(Long commentId) {
        return this.likeCommentRepository.countLikesByPostId(commentId);
    }
}
