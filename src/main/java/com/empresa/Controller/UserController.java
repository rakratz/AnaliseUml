package com.empresa.Controller;

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

import com.empresa.Model.User;
import com.empresa.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User createUser(@RequestBody User user) {
		System.out.println(user.getRole());
		return userRepository.save(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@GetMapping
	public List<User> getAllIsers() {
		return userRepository.findAll();
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User updateUser) {

		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			existingUser.setPassword(updateUser.getPassword());
			existingUser.setScreenName(updateUser.getScreenName());
			existingUser.setProfileImage(updateUser.getProfileImage());
			existingUser.setFollowing(updateUser.getFollowing());
			existingUser.setBio(updateUser.getBio());
			existingUser.setRole(updateUser.getRole());

			return userRepository.save(existingUser);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			userRepository.deleteById(id);
		}
	}
}
