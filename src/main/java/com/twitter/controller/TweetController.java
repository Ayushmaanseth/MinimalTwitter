package com.twitter.controller;

import com.twitter.model.Tweet;
import com.twitter.model.TweetInput;
import com.twitter.model.User;
import com.twitter.repository.TweetRepository;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/api/users/{userId}/tweets")
public class TweetController {

	private static final String USER_NOT_FOUND = "User not found";
	private static final String TWEET_CREATED = "Tweet Created successfully";
	@Autowired
	UserRepository userRepository;

	@Autowired
	TweetRepository tweetRepository;

	@PostMapping
	public String createTweet(@PathVariable String userId, @RequestBody TweetInput tweetInput, HttpServletResponse response) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return USER_NOT_FOUND;
		}
		tweetRepository.save(new Tweet(userOptional.get(), tweetInput.getMessage()));
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return TWEET_CREATED;
	}

	@DeleteMapping("/{tweetId}")
	public void deleteTweet(@PathVariable Long tweetId) {
		tweetRepository.deleteById(tweetId);
	}

	@GetMapping
	public List<Tweet> getAllTweetsForUser(@PathVariable String userId, HttpServletResponse response) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return Collections.emptyList();
		}
		return tweetRepository.findByUserIdNot(userId);
	}

	@GetMapping(path="mytweets")
	public List<Tweet> getMyTweetsForUser(@PathVariable String userId)  {
		return tweetRepository.findByUserId(userId);
	}
}
