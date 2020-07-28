package com.sport.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.dao.RoleDao;
import com.sport.api.entities.Role;
import com.sport.api.output.Response;

@Service
@Transactional
public class RoleService {
	@Autowired
	RoleDao roleDao;

	public Response<Role> saveAll(List<Role> roleLst) {
		return roleDao.saveAll(roleLst);
	}

	public Response<Role> findAll() {
		return roleDao.findAll();
	}

	public Response<Role> findById(String id) {
		return roleDao.findById(id);
	}

	public Response<Role> save(Role role) {
		return roleDao.save(role);
	}

	public Response<Role> findByRoleId(String roleId) {
		return roleDao.findByRoleId(roleId);
	}
}
