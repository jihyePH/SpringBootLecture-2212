package com.mulcam.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.demo.dao.UserDao;
import com.mulcam.demo.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserDao userDao;
	
	@Override
	public List<User> getList() {
		List<User> list = userDao.getList();
		return list;
	}

	@Override
	public User get(String uid) {
		User user = userDao.get(uid);
		return user;
	}

	@Override
	public void register(User u) {
		String cryptedPwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt());
		u.setPwd(cryptedPwd);
		userDao.insert(u);
	}

}
