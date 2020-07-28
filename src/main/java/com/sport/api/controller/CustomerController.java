package com.sport.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sport.api.config.BaseMain;
import com.sport.api.entities.District;
import com.sport.api.entities.User;
import com.sport.api.output.Response;
import com.sport.api.service.DistrictService;
import com.sport.api.service.UserService;

@RestController
public class CustomerController extends BaseMain {

	@Autowired
	private UserService userService;
	
	@Autowired
	DistrictService districtService; 

	@PostMapping("/user")
	public Response<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/user")
	public Response<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/user-by-userid")
	public Response<User> findByUserId(@RequestParam("userId") String userId) {
		return userService.findByUserId(userId);
	}
	
	@GetMapping("/user-by-id")
	public Response<User> findById(@RequestParam("id") int id) {
		return userService.findById(id);
	}
	
	@GetMapping("/district-by-provinceid")
	public Response<District> findByProvinceId(@RequestParam("provinceId") String provinceId) {
		return districtService.findByProvinceId(provinceId);
	}
	
	@PostMapping("/district")
	public Response<District> districtSaveAll(@RequestBody List<District> districtLst){
		return districtService.saveAll(districtLst);
	}
}
