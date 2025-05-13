package com.onlinefoodordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.config.JwtProvider;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new Exception("User not found" + email);
		}
		return user;
	}

}
