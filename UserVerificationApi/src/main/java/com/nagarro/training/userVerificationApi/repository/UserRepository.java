package com.nagarro.training.userVerificationApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.userVerificationApi.model.User;

/**
 * @author shreyarathour
 * Repository interface for managing User entities.
 * Extends JpaRepository for basic CRUD operations on User entities.
 *
 * @Repository annotation indicates that this interface is a Spring Data repository.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 // No additional methods are needed here as JpaRepository provides CRUD operations out of the box.
    // The interface is empty because JpaRepository already contains methods for saving, updating, deleting, and querying entities.


}
