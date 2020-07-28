package com.sport.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.dao.DistrictDao;
import com.sport.api.entities.District;
import com.sport.api.output.Response;

@Service
@Transactional
public class DistrictService {
	@Autowired
	DistrictDao districtDao;

	public Response<District> saveAll(List<District> districtLst) {
		return districtDao.saveAll(districtLst);
	}

	public Response<District> findAll() {
		return districtDao.findAll();
	}

	public Response<District> findById(String id) {
		return districtDao.findById(id);
	}

	public Response<District> save(District district) {
		return districtDao.save(district);
	}
	
	public Response<District> findByProvinceId(String provinceId){
		return districtDao.findByProvinceId(provinceId);
	}

}
