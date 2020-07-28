package com.sport.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.Province;
import com.sport.api.output.Response;

@Service
@Transactional
public class ProvinceService {
	@Autowired
	ProvinceService provinceService;

	public Response<Province> saveAll(List<Province> provinceLst) {
		return provinceService.saveAll(provinceLst);
	}

	public Response<Province> findAll() {
		return provinceService.findAll();
	}

	public Response<Province> findById(String id) {
		return provinceService.findById(id);
	}

	public Response<Province> save(Province Province) {
		return provinceService.save(Province);
	}
}
