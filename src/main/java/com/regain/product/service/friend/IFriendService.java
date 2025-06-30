package com.regain.product.service.friend;

import com.regain.product.model.dto.FriendRequest;
import com.regain.product.model.entity.Friend;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IFriendService {

    Friend saveFriend(FriendRequest newFriend);

    Optional<Friend> findFriendByFormUserAndToUserId(Long formUserId, Long toUserId);

    Friend save(Friend friend) ;

    void delete(Long id);

    Optional<Friend> checkStatusFriend(Long formUserId, Long toUserId);

}
