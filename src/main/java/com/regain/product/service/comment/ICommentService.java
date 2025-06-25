package com.regain.product.service.comment;

import com.regain.product.model.dto.CommentRequest;
import com.regain.product.model.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Comment saveComment(CommentRequest comment);

    List<CommentRequest> findAllCommentByPostId(Long postId);

    Optional<Comment> findCommentById(Long commentId);
}
