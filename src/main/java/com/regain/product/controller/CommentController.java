package com.regain.product.controller;

import com.regain.product.model.entity.Comment;
import com.regain.product.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment-api")
public class CommentController {
    @Autowired
    private ICommentService commentService;

}
