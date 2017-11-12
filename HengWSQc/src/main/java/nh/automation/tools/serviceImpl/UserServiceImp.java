package nh.automation.tools.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nh.automation.tools.dao.UserMapper;
import nh.automation.tools.entity.User;

@Service
public class UserServiceImp {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public User getUser(String userName){
		User user=null;
		try {
			user = userMapper.getUser(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
		
		return user;
	}
}
