/*
 * Interfaces that extend Spring Data JPA JpaRepository to interact with Database.
 * Will be imported into Controller.
 */

package com.example.xoxo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.xoxo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
