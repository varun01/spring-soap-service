package com.poc.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.xml.getuser.GetUserProfile;
import com.example.xml.getuser.GetUserProfileRequest;
import com.example.xml.getuser.GetUserProfileResponse;
import com.example.xml.newuser.UserProfile;

@Endpoint
public class GetUserProfileEndpoint {

	private static final String NAMESPACE_URI = "http://www.example.com/xml/getuser";

	private UserProfileRepository StudentRepository;

	@Autowired
	public GetUserProfileEndpoint(UserProfileRepository StudentRepository) {
		this.StudentRepository = StudentRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserProfileRequest")
	@ResponsePayload
	public GetUserProfileResponse getStudent(@RequestPayload GetUserProfileRequest request) {
		GetUserProfileResponse response = new GetUserProfileResponse();
		UserProfile profile = StudentRepository.gettUserProfile(request.getUserId());
		GetUserProfile userProfile = new GetUserProfile();
		userProfile.setUserId(profile.getUserId());
		userProfile.setUserName(profile.getUserName());
		userProfile.setUserStatus(profile.getUserStatus());
		response.setGetUserProfile(userProfile);
		return response;
	}
}
