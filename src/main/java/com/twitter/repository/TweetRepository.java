package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Tweet;
import com.twitter.model.User;
import org.springframework.data.jpa.repository.Query;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	public List<Tweet> findAllByUser(User user);
	public Tweet findByUserIdAndId(String userId, Long tweetId);
	public void deleteByUserIdAndId(String userId, Long tweetId);
	public List<Tweet> findByUserIdNot(String userId);
	public List<Tweet> findByUserId(String userid);
	public void deleteAllByUserId(String userId);
	
}
