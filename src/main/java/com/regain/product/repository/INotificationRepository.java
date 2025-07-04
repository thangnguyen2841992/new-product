package com.regain.product.repository;

import com.regain.product.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT * FROM notification WHERE to_account_id = :accountId and is_read = false order by date_created desc", nativeQuery = true)
    List<Notification> getAllNotificationUnreadOfUser(@Param("accountId") Long accountId);

    @Query(value = "select * from notification where form_account_id = :fromAccountId and post_id = :postId and type = :type ", nativeQuery = true)
    Optional<Notification> findByTypeIdAndPostIdAndUserId(@Param("fromAccountId") Long formAccountId, @Param("postId") Long postId, @Param("type") long type);

    @Query(value = "select * from notification where form_account_id = :fromAccountId and comment_id = :commentId and type = :type ", nativeQuery = true)
    Optional<Notification> findByTypeIdAndCommentIdIdAndUserId(@Param("fromAccountId") Long formAccountId, @Param("commentId") Long commentId, @Param("type") long type);
}
