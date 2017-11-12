package nh.automation.tools.dao;


import nh.automation.tools.entity.User;

public interface UserMapper {

	/**
	 * 查询用户，并得到用户信息
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public User getUser(String userName) throws Exception;
	
 }
