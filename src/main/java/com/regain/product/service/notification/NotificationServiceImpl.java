package com.regain.product.service.notification;

import com.regain.product.model.entity.Notification;
import com.regain.product.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotificationService{
    @Autowired
    private INotificationRepository notificationRepository;


    @Override
    public Notification save(Notification notification) {
        return this.notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotificationUnreadOfUser(Long accountId) {
        return this.notificationRepository.getAllNotificationUnreadOfUser(accountId);
    }

    @Override
    public Optional<Notification> findByTypeIdAndPostIdAndUserId(Long formAccountId, Long toAccountId, long type) {
        return this.notificationRepository.findByTypeIdAndPostIdAndUserId(formAccountId, toAccountId, type);
    }

    @Override
    public Optional<Notification> findByTypeIdAndCommentIdIdAndUserId(Long formAccountId, Long commentId, long type) {
        return this.notificationRepository.findByTypeIdAndCommentIdIdAndUserId(formAccountId, commentId, type);
    }
}
