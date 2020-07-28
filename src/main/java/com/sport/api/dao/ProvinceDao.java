package com.sport.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.Province;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;

@Repository(value = "provinceDao")
@Transactional(rollbackFor = Exception.class)
public class ProvinceDao{
	@Autowired
	private SessionFactory sessionFactory;

	public Response<Province> save(Province Province) {
		Response<Province> result = new Response<Province>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			String idGen = (String) session.save(Province);
			if (idGen.equals("")){
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
			} else {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
				result.setRespObj(Province);
			}

		} catch (Exception e) {
			System.out.println("ProvinceDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());
		}
		return result;
	}

	public Response<Province> saveAll(List<Province> ProvinceLst) {
		Response<Province> result = new Response<Province>();
		Session session = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			int idGen = 0;
			for (int i = 0; i < ProvinceLst.size(); i++) {
				idGen = (Integer) session.save(ProvinceLst.get(i));
				if (idGen == 0) {
					result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
					result.setRespContent(
							JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert All"));
					break;
				}
			}
			if (idGen != 0) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert All"));
			}
		} catch (Exception e) {
			System.out.println("ProvinceDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "ProvinceDao saveAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Province> findAll() {
		Response<Province> result = new Response<Province>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Province> ProvinceLst = session.createQuery("FROM Province", Province.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(ProvinceLst);
		} catch (Exception e) {
			System.out.println("ProvinceDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "ProvinceDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Province> findById(String id) {
		Response<Province> result = new Response<Province>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(Province.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("ProvinceDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "ProvinceDao findById")
							+ e.toString());
		}
		return result;
	}
}
