package com.regain.product.service.topicPost;

import com.regain.product.model.TopicPost;
import com.regain.product.repository.ITopicPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicPostServiceImpl implements ITopicPostService{
    @Autowired
    private ITopicPostRepository topicPostRepository;

    @Override
    public List<TopicPost> getTopicPosts() {
        return topicPostRepository.findAll();
    }
}
