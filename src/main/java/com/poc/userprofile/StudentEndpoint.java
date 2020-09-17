package com.poc.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.howtodoinjava.xml.school.UserProfileRequest;
import com.howtodoinjava.xml.school.UserProfileResponse;

@Endpoint
public class StudentEndpoint {
	private static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

	private StudentRepository StudentRepository;

	@Autowired
	public StudentEndpoint(StudentRepository StudentRepository) {
		this.StudentRepository = StudentRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDetailsRequest")
	@ResponsePayload
	public UserProfileResponse getStudent(@RequestPayload UserProfileRequest request) {
		UserProfileResponse response = new UserProfileResponse();
		response.setUserProfile(StudentRepository.findStudent(request.getUserName()));

		return response;
	}
}