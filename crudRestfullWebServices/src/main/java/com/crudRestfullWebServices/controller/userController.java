package com.crudRestfullWebServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudRestfullWebServices.enitity.user;
import com.crudRestfullWebServices.exception.ResourceNotFoundException;
import com.crudRestfullWebServices.repository.userRepository;

@RestController
@RequestMapping("/api/users")
public class userController {

	@Autowired
	private userRepository userRepo;

	@RequestMapping
	public List<user> getAllUsers() {
		return this.userRepo.findAll();
	}

	// get by the user id
	@RequestMapping("/{id}")
	public user getUserById(@PathVariable(value = "id") long userid) {
		return this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user not found"));
	}

	// create user
	@PostMapping
	public user createUser(@RequestBody user user) {
		return this.userRepo.save(user);
	}

	// update
	@PutMapping("/{id}")
	public user updateUserById(@RequestBody user user, @PathVariable(value = "id") long userid) {
		user existing = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return this.userRepo.save(existing);
	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<user> deleteUser(@PathVariable(value = "id") long userid) {
		user existing = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		this.userRepo.delete(existing);
		return ResponseEntity.ok().build();
	}
}
