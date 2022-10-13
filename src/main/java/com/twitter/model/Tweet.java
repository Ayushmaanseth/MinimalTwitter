package com.twitter.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;
import java.util.Date;

@Entity
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database will generate the tweet id using autoincrement, given id will be overwritten", required=false)
	@Column(name="tweetId")
	private Long id;
	@Column(name="message")
	private String message;
	@Column(name="created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	protected Tweet() {
	}

	public Tweet(User user, String message) {
		this.user = user;
		this.message = message;
		this.createdDateTime = Date.from(Instant.now());
	}

	public String getMessage() {
		return message;
	}

	public User getUser() {
		return user;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEqualTo(Tweet other) {
		return (this.message.equals(other.getMessage()) && this.id.equals(other.getId()));
	}

	@Override
	public String toString() {
		return String.format("{id:%s, message:%s}", id, message);
	}
}
