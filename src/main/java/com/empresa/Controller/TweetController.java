package com.empresa.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.Model.Tweet;
import com.empresa.Repository.TweetRepository;

@RestController
@RequestMapping("/api/tweets")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;

	@PostMapping
	public Tweet createTweet(@RequestBody Tweet tweet) {
		tweet.setPostTime(new Date());
		return tweetRepository.save(tweet);
	}

	@GetMapping
	public List<Tweet> getAllTweers() {
		return tweetRepository.findAll();
	}

	@GetMapping("/{id}")
	public Tweet getTweerById(@PathVariable Integer id) {
		return tweetRepository.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Tweet updateTweet(@PathVariable Integer id, @RequestBody Tweet updateTweet) {
		Tweet existingTweet = tweetRepository.findById(id).orElse(null);

		if (existingTweet != null) {
			// Remover a linha que define postTime para hora atual
			existingTweet.setContent(updateTweet.getContent());
			existingTweet.setTweetUser(updateTweet.getTweetUser());
			return tweetRepository.save(existingTweet);
		}

		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTweet(@PathVariable Integer id) {
		Tweet existingTweet = tweetRepository.findById(id).orElse(null);

		if (existingTweet != null) {
			tweetRepository.deleteById(id);
		}

	}

}
