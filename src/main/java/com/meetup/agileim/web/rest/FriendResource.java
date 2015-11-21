package com.meetup.agileim.web.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.meetup.agileim.domain.User;
import com.meetup.agileim.repository.FriendRepository;
import com.meetup.agileim.repository.UserRepository;
import com.meetup.agileim.security.SecurityUtils;


@RestController
@RequestMapping("/api")
public class FriendResource {
	
	@Inject
    private UserRepository userRepository;
	
	@Inject
	private FriendRepository friendRepository;
	
	@RequestMapping(value = "/friends/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
    @Timed
    public ResponseEntity<User> markAsFriend(@PathVariable(value = "id") Long friendId) {
		return userRepository.findOneById(friendId)
				.map(friend -> {
					User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
					friendRepository.markAsFriend(currentUser.getId(), friend.getId());
					return new ResponseEntity<User>(friend, HttpStatus.OK);
				})
				.orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
	}

}
