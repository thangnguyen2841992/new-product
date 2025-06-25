package com.regain.product.service.comment;

import com.regain.product.client.AccountService;
import com.regain.product.model.dto.AccountDTO;
import com.regain.product.model.dto.CommentRequest;
import com.regain.product.model.entity.Comment;
import com.regain.product.repository.ICommentRepository;
import com.regain.product.service.likeComment.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ILikeCommentService likeCommentService;

    @Override
    public Comment saveComment(CommentRequest comment) {
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setAccountId(comment.getAccountId());
        newComment.setPostId(comment.getPostId());
        newComment.setDateCreated(new Date());
        return this.commentRepository.save(newComment);
    }

    @Override
    public List<CommentRequest> findAllCommentByPostId(Long postId) {
        List<Comment> comments = this.commentRepository.findAllCommentByPostId(postId);
        List<CommentRequest> commentRequests = new ArrayList<>();
        for (Comment comment : comments) {
            CommentRequest commentRequest = new CommentRequest();
            commentRequest.setAccountId(comment.getAccountId());
            commentRequest.setPostId(comment.getPostId());
            commentRequest.setContent(comment.getContent());
            commentRequest.setDateCreated(comment.getDateCreated());
            commentRequest.setCommentId(comment.getCommentId());
            AccountDTO accountDTO = accountService.findAccountByAccountId(comment.getAccountId()).getBody();
            assert accountDTO != null;
            commentRequest.setAvatar(accountDTO.getAvatar());
            commentRequest.setFullName(accountDTO.getFullName());
            long totalLikeComment = this.likeCommentService.countLikesByPostId(comment.getCommentId());
            commentRequest.setTotalLikeComments(totalLikeComment);
            commentRequests.add(commentRequest);
        }
        return commentRequests;
    }

    @Override
    public Optional<Comment> findCommentById(Long commentId) {
        return this.commentRepository.findById(commentId);
    }
}
