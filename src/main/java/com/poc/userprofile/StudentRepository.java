package com.poc.userprofile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.howtodoinjava.xml.school.UserProfile;

@Component
public class StudentRepository {
	private static final Map<String, UserProfile> students = new HashMap<>();

	@PostConstruct
	public void initData() {
		
		UserProfile student = new UserProfile();
		student.setUserName("Sajal");
		student.setUserId(5);
		student.setUserStatus("Pune");
		students.put(student.getUserName(), student);
		
		student = new UserProfile();
		student.setUserName("Kajal");
		student.setUserId(5);
		student.setUserStatus("Chicago");
		students.put(student.getUserName(), student);
		
		student = new UserProfile();
		student.setUserName("Lokesh");
		student.setUserId(6);
		student.setUserStatus("Delhi");
		students.put(student.getUserName(), student);
		
		student = new UserProfile();
		student.setUserName("Sukesh");
		student.setUserId(7);
		student.setUserStatus("Noida");
		students.put(student.getUserName(), student);
		
		
	}

	public UserProfile findStudent(String name) {
		Assert.notNull(name, "The Student's name must not be null");
		return students.get(name);
	}
}