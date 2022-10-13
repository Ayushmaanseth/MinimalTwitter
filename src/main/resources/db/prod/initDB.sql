CREATE DATABASE IF NOT EXISTS twitterdatabase;

USE twitterdatabase;

CREATE TABLE `User` (
	`userId` varchar(50) NOT NULL UNIQUE ,
	`userName` varchar(50) NOT NULL,
	PRIMARY KEY (`userId`)
);

CREATE TABLE `Tweet` (
	`tweetId` bigint NOT NULL AUTO_INCREMENT,
	`userId` varchar(50) NOT NULL,
	`message` varchar(160) NOT NULL,
	`created` DATETIME NOT NULL,
	PRIMARY KEY (`tweetId`)
);

ALTER TABLE `Tweet` ADD CONSTRAINT `Tweets_fk0` FOREIGN KEY (`userId`) REFERENCES `User`(`userId`);