package com.twitter.controller;

import com.twitter.model.Tweet;
import com.twitter.model.TweetInput;
import com.twitter.model.User;
import com.twitter.repository.TweetRepository;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UIController {

	@Autowired
	TweetRepository tweetRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("users/{userId}/tweets")
	public String allTweets(@PathVariable String userId, Model model) {
		Authentication auth = getAuthenticatedUser();
		if (!userId.equals(auth.getName())) {
			return "home";
		}
		model.addAttribute("tweets", tweetRepository.findByUserIdNot(userId));
		return "allTweets";
	}

	@GetMapping("users/{userId}/mytweets")
	public String myTweets(@PathVariable String userId, Model model) {
		if (!userId.equals(getAuthenticatedUser().getName())) {
			return "home";
		}
		model.addAttribute("tweets", tweetRepository.findByUserId(userId));
		return "myTweets";
	}

	@GetMapping("users/{userId}/tweets/create")
	public String createTweet(@PathVariable String userId, Model model) {
		if (!userId.equals(getAuthenticatedUser().getName())) {
			return "home";
		}
		model.addAttribute("userId", userId);
		model.addAttribute("tweetInput", new TweetInput());
		return "createTweet";
	}

	@PostMapping(value = "users/{userId}/tweets/create")
	public String createTweet(@PathVariable String userId, @ModelAttribute(value="tweetInput") TweetInput tweetInput) {
		if (!userId.equals(getAuthenticatedUser().getName())) {
			return "home";
		}
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			return "createTweetError";
		}
		tweetRepository.save(new Tweet(userOptional.get(), tweetInput.getMessage()));
		return String.format("redirect:/users/%s/tweets", userId);
	}

	private Authentication getAuthenticatedUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}



