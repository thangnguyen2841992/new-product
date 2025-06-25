package com.regain.product.service.notification;

import com.regain.product.model.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface INotificationService {
    Notification save(Notification notification);

    List<Notification> getAllNotificationUnreadOfUser(Long accountId);

    Optional<Notification> findByTypeIdAndPostIdAndUserId(Long formAccountId, Long toAccountId, long type);
    Optional<Notification> findByTypeIdAndCommentIdIdAndUserId(Long formAccountId, Long commentId, long type);


}
