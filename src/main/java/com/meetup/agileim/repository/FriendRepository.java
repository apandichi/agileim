package com.meetup.agileim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.meetup.agileim.domain.User;

/**
 * Spring Data JPA repository for friends .
 */
public interface FriendRepository extends JpaRepository<User, Long> {
	
	@Modifying
	@Query(value = "insert into friends (user_id, friend_id) VALUES (?1, ?2)", nativeQuery = true)
	public void markAsFriend(Long userId, Long friendId);
    
}
