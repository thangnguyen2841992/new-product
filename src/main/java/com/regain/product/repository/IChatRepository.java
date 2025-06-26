package com.regain.product.repository;

import com.regain.product.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "select * from chat where (form_user_id = :formUserId and to_user_id = :toUserId) or (form_user_id = :toUserId and to_user_id = :formUserId) order by date_created desc", nativeQuery = true)
    List<Chat> findAllChatOfFormUserAndToUser(@Param("formUserId") Long formUserId, @Param("toUserId") Long toUserId);
}
