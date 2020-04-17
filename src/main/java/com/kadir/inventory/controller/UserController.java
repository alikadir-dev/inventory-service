package com.kadir.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kadir.inventory.model.User;
import com.kadir.inventory.service.UserService;

/**
 * @author Mohammad Kadir Ali
 */

@RestController
@RequestMapping(value ={ "/", "/user" })
public class UserController
{
	@Autowired
	UserService userService;
	
	/**
	 * @param fullname
   	 * @param password
     * @param userType
   	 * @param email
     * @param roles
	 * @return User has been registered successfully
	 */
	
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
	{
		userService.saveUsers(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * @return List<User> 
	 */
	
	@GetMapping(value = "/getUserList", headers = "Accept=application/json")
	public List<User> getAllUser()
	{
		return  userService.getUser();
	}

}
