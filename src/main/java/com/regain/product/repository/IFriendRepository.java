package com.regain.product.repository;

import com.regain.product.model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "select * from friend where (form_user_id = :formUserId and to_user_id = :toUserId) or (form_user_id = :toUserId and to_user_id = :formUserId) ", nativeQuery = true)
    Optional<Friend> findFriendByFormUserAndToUserId(@Param("formUserId") Long formUserId, @Param("toUserId") Long toUserId);





}
