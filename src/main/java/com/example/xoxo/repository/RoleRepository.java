/*
 * Interfaces that extend Spring Data JPA JpaRepository to interact with Database.
 * Will be imported into Controller.
 */

package com.example.xoxo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xoxo.models.ERole;
import com.example.xoxo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
