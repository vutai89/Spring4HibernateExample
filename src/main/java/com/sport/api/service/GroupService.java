package com.sport.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.dao.GroupDao;
import com.sport.api.entities.Group;
import com.sport.api.output.Response;

@Service
@Transactional
public class GroupService {
	@Autowired
	GroupDao groupDao;

	public Response<Group> saveAll(List<Group> groupLst) {
		return groupDao.saveAll(groupLst);
	}

	public Response<Group> findAll() {
		return groupDao.findAll();
	}

	public Response<Group> findById(String id) {
		return groupDao.findById(id);
	}

	public Response<Group> save(Group group) {
		return groupDao.save(group);
	}

	public Response<Group> findByGroupId(String groupId) {
		return groupDao.findByGroupId(groupId);
	}
}
