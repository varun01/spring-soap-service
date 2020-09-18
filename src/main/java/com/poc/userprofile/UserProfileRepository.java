package com.poc.userprofile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.xml.newuser.UserProfile;

@Component
public class UserProfileRepository {
	private static final Map<Integer, UserProfile> students = new HashMap<>();

	@PostConstruct
	public void initData() {
		
	}

	public UserProfile insertUserProfile(UserProfile user) {
		Assert.notNull(user, "The Student's name must not be null");
		students.put(user.getUserId(), user);
		return students.get(user.getUserId());
	}
	
	public UserProfile gettUserProfile(int userId) {
		return students.get(userId);
	}
}