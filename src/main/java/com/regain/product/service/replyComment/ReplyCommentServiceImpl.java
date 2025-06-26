package com.regain.product.service.replyComment;

import com.regain.product.client.AccountService;
import com.regain.product.model.dto.AccountDTO;
import com.regain.product.model.dto.ReplyRequest;
import com.regain.product.model.entity.ReplyComment;
import com.regain.product.repository.IReplyCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyCommentServiceImpl implements IReplyCommentService{
    @Autowired
    private IReplyCommentRepository replyCommentRepository;

    @Autowired
    private AccountService accountService;


    @Override
    public List<ReplyRequest> findAllReplyOfComment(Long postId, Long commentId) {
        List<ReplyComment> replyCommentList = this.replyCommentRepository.findAllReplyOfComment(postId, commentId);
        List<ReplyRequest> replyRequests = new ArrayList<>();
        for (ReplyComment replyComment : replyCommentList) {
            ReplyRequest replyRequest = new ReplyRequest();
            replyRequest.setReplyCommentId(replyComment.getReplyCommentId());
            replyRequest.setCommentId(replyComment.getCommentId());
            replyRequest.setDateCreated(replyComment.getDateCreated());
            replyRequest.setContent(replyComment.getContent());
            replyRequest.setPostId(postId);
            AccountDTO accountDTO1 = this.accountService.findAccountByAccountId(replyComment.getAccountId()).getBody();
            assert accountDTO1 != null;
            replyRequest.setAvatar(accountDTO1.getAvatar());
            replyRequest.setFullName(accountDTO1.getFullName());
            replyRequest.setAccountId(replyComment.getAccountId());
            replyRequests.add(replyRequest);
        }
        return replyRequests;
    }

    @Override
    public ReplyComment save(ReplyComment comment) {
        return this.replyCommentRepository.save(comment);
    }


}
