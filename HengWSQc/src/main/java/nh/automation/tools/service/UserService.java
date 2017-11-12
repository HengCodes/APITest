package nh.automation.tools.service;

import org.springframework.stereotype.Service;

import nh.automation.tools.entity.User;

@Service
public interface UserService {

	public User getUser(String userName);
}
