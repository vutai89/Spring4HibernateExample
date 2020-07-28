package com.sport.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.dao.WardDao;
import com.sport.api.entities.Ward;
import com.sport.api.output.Response;

@Service
@Transactional
public class WardService {
	@Autowired
	WardDao wardDao;

	public Response<Ward> saveAll(List<Ward> wardLst) {
		return wardDao.saveAll(wardLst);
	}

	public Response<Ward> findAll() {
		return wardDao.findAll();
	}

	public Response<Ward> findById(String id) {
		return wardDao.findById(id);
	}

	public Response<Ward> save(Ward Ward) {
		return wardDao.save(Ward);
	}

	public Response<Ward> findByDistrictId(String districtId) {
		return wardDao.findByDistrictId(districtId);
	}

}
