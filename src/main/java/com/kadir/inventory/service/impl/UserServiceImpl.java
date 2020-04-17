
package com.kadir.inventory.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kadir.inventory.exception.AuthenticationException;
import com.kadir.inventory.model.User;
import com.kadir.inventory.repository.UserRepository;
import com.kadir.inventory.service.UserService;

/**
 * @author Mohammad Kadir Ali
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository userRepository;

	public void saveUsers(User user)
	{
		user.setEnabled(true);
		userRepository.save(user);
	}

	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	public List<User> getUser()
	{
		return (List<User>) userRepository.findAll();
	}

	public User update(User user, String l)
	{
		return userRepository.save(user);
	}

	public void deleteUserById(String id)
	{
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws AuthenticationException
	{

		User user = userRepository.findByEmail(email);
		if (user != null)
		{
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			return buildUserForAuthentication(user, authorities);
		} else
		{
			throw new AuthenticationException("username not found");
		}
	}

	private List<GrantedAuthority> getUserAuthority(String userRoles)
	{
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(userRoles));
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities)
	{
		return new org.springframework.security.core.userdetails.User(user.getEmail(), String.valueOf(user.getPassword()), authorities);
	}
}
