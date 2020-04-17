/**
 * 
 */
package com.kadir.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.kadir.inventory.model.User;

/**
 * @author Mohammad Kadir Ali
 *
 */
public interface UserRepository extends CrudRepository<User, String>
{
	/**
	 * Write your own Method which is not present in the CrudRepository Interface
	 */
	public User findByEmail(String email);
}
