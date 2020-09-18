package com.poc.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.xml.newuser.NewUserProfileRequest;
import com.example.xml.newuser.NewUserProfileResponse;
import com.example.xml.newuser.UserProfile;

@Endpoint
public class UserProfileEndpoint {
	private static final String NAMESPACE_URI = "http://www.example.com/xml/newuser";

	private UserProfileRepository StudentRepository;

	@Autowired
	public UserProfileEndpoint(UserProfileRepository StudentRepository) {
		this.StudentRepository = StudentRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "NewUserProfileRequest")
	@ResponsePayload
	public NewUserProfileResponse getStudent(@RequestPayload NewUserProfileRequest request) {
		NewUserProfileResponse response = new NewUserProfileResponse();
		UserProfile profile = new UserProfile();
		profile.setUserId(request.getUserId());
		profile.setUserName(request.getUserName());
		profile.setUserStatus(request.getUserStatus());
		response.setResponse("User with ID:"+StudentRepository.insertUserProfile(profile)+" is inserted");

		return response;
	}
}