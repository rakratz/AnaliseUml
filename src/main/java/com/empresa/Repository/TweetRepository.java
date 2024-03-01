package com.empresa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.Model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	


}
