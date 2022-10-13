
package com.twitter.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.twitter.model.UserInput;
import com.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twitter.model.User;
import com.twitter.repository.UserRepository;

@RestController
@RequestMapping(path="/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TweetRepository tweetRepository;
	
	@GetMapping
	public @ResponseBody List<User> readUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public User createUser(@RequestBody UserInput userInput){
		User user = new User(userInput.getUserId(), userInput.getUserName());
		return userRepository.save(user);		
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId){
		tweetRepository.deleteAllByUserId(userId);
		userRepository.deleteById(userId);
	}
	
	@GetMapping("/{userId}")
	public @ResponseBody User readUser(@PathVariable String userId){
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.orElseThrow(() -> new NoSuchElementException("User with userId not present"));
	}
	
	@PutMapping("/{id}")
	public @ResponseBody User updateUser(@RequestBody User user){
		return userRepository.save(user);
	}
	
}
