package com.kadir.inventory.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kadir.inventory.enumeration.Status;
import com.kadir.inventory.exception.AuthenticationException;
import com.kadir.inventory.model.Response;
import com.kadir.inventory.model.User;
import com.kadir.inventory.security.JwtGenerator;
import com.kadir.inventory.service.UserService;

/**
 * @author Mohammad Kadir Ali
 */

@RestController
public class LoginController
{
	@Autowired
	UserService userService;
	
	private JwtGenerator jwtGenerator;

	public LoginController(JwtGenerator jwtGenerator)
	{
		this.jwtGenerator = jwtGenerator;
	}
	
	
	/**
	 * @param fullname
   	 * @param password
     * @param userType
   	 * @param email
     * @param roles
	 * @return User has been registered successfully
	 */
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Response createNewUser(@RequestBody User user, BindingResult bindingResult) throws AuthenticationException
	{
		Response respose = new Response();
		User userExists = userService.findUserByEmail(user.getEmail());
		Map<String, Object> object = new LinkedHashMap<String, Object>();
		if (userExists != null)
		{
			respose.setStatus(Status.NOT_ACCEPTABLE);
			object.put("Error Message", "There is already a user registered with the username provided");
			object.put("user", user);
			respose.setResponse(object);
		} else
		{
			userService.saveUsers(user);
			respose.setStatus(Status.OK);
			object.put("successMessage", "User has been registered successfully");
			object.put("user", user);
			object.put("view name", "login");
			respose.setResponse(object);
			respose.setSuccessResponse(Status.OK, null, object);
		}
		return respose;
	}

	/**
	 * @param email
   	 * @param password
	 * @return ModelAndView Object {user : "", "fullName" : "", "Message" : "", token : ""} 
	 * @throws AuthenticationException 
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user) throws AuthenticationException
	{
		Response respose = new Response();
		User userdata = userService.findUserByEmail(user.getEmail());
		if (userdata != null)
		{
			Map<String, Object> object = new LinkedHashMap<String, Object>();
			String tokenId = jwtGenerator.generate(user);
			respose.setTokenId(tokenId);
			object.put("user id", user.getEmail());
			object.put("Success Message", "Welcome " + user.getFullname());
			respose.setResponse(object);
			respose.setSuccessResponse(Status.OK, tokenId, object);
		} else
		{
			Map<String, Object> object = new LinkedHashMap<String, Object>();
			object.put("Error Message", "You've not register, try to sign up first !");
			object.put("user name", user.getEmail());
			object.put("password", user.getPassword());
			respose.setResponse(object);
			respose.setSuccessResponse(Status.OK, null, object);
			throw new AuthenticationException("You've not register, try to sign up first !");
		}
		return respose;
	}
	
}
