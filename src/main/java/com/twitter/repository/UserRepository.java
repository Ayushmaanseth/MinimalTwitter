package com.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.User;

public interface UserRepository extends JpaRepository<User, String>{
}
