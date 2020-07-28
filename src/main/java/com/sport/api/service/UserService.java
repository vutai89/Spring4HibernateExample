package com.sport.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.dao.UserDao;
import com.sport.api.entities.User;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;

	public Response<User> saveUser(User user) {
		return userDao.save(user);
	}

	public Response<User> findByUserId(String userId) {
		return userDao.findByUserId(userId);
	}

	public Response<User> findAll() {
		return userDao.findAll();
	}

	public Response<User> findById(int id) {
		return userDao.findById(id);
	}

	public Response<User> update(User user) {
		Response<User> result = new Response<User>();
		User userOld = userDao.findById(user.getId()).getRespObj();
		if (userOld != null) {
			if (userDao.checkUserExit(user.getUser_id(), user.getEmail(), user.getPassword(), false, userOld.getEmail(),
					userOld.getPhone()) != 0) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.DataExitsCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.DataExitsMsg").replace("##", "User"));
			} else {
				result = userDao.update(user);
			}
		} else {
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExitsCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.ExitsMsg").replace("##", "User"));
		}
		return result;
	}

	public Response<User> deleteUser(int id) {
		User user = userDao.findById(id).getRespObj();
		return userDao.delete(user);
	}
}
