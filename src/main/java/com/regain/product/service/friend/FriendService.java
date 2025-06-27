package com.regain.product.service.friend;

import com.regain.product.model.dto.FriendRequest;
import com.regain.product.model.entity.Friend;
import com.regain.product.repository.IFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FriendService implements IFriendService {
    @Autowired
    private IFriendRepository friendRepository;

    @Override
    public Friend saveFriend(FriendRequest newFriend) {
        Friend friend = new Friend();
        friend.setFormUserId(newFriend.getFormUserId());
        friend.setToUserId(newFriend.getToUserId());
        friend.setDateCreated(new Date());
        friend.setApproved(false);
        return friendRepository.save(friend);
    }

    @Override
    public Optional<Friend> findFriendByFormUserAndToUserId(Long formUserId, Long toUserId) {
        return this.friendRepository.findFriendByFormUserAndToUserId(formUserId, toUserId);
    }

    @Override
    public Friend save(Friend friend) {
        return this.friendRepository.save(friend);
    }

    @Override
    public void delete(Long id) {
        this.friendRepository.deleteById(id);
    }
}
