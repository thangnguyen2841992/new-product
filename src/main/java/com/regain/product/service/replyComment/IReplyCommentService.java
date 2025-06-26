package com.regain.product.service.replyComment;

import com.regain.product.model.dto.ReplyRequest;
import com.regain.product.model.entity.ReplyComment;

import java.util.List;

public interface IReplyCommentService {
    List<ReplyRequest> findAllReplyOfComment(Long postId, Long commentId);


    ReplyComment save(ReplyComment comment);


}
