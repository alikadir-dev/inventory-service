package com.kadir.inventory.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.kadir.inventory.exception.AuthenticationException;
import com.kadir.inventory.model.User;

/**
 * @author Mohammad Kadir Ali
 */
public interface UserService
{
	public void saveUsers(User user);

	public List<User> getUser();

	public User update(User user, String l);

	public void deleteUserById(String id);

	public UserDetails loadUserByUsername(String email) throws AuthenticationException;

	public User findUserByEmail(String email);

}
