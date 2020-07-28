package com.sport.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.District;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;

@Repository(value = "districtDao")
@Transactional(rollbackFor = Exception.class)
public class DistrictDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Response<District> save(District district) {
		Response<District> result = new Response<District>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			int idGen = (Integer) session.save(district);
			if (idGen == 0) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
			} else {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
				result.setRespObj(district);
			}

		} catch (Exception e) {
			System.out.println("DistrictDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());
		}
		return result;
	}

	public Response<District> saveAll(List<District> districtLst) {
		Response<District> result = new Response<District>();
		Session session = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			int idGen = 0;
			for (int i = 0; i < districtLst.size(); i++) {
				idGen = (Integer) session.save(districtLst.get(i));
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
			System.out.println("DistrictDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "DistrictDao saveAll")
							+ e.toString());
		}
		return result;
	}

	public Response<District> findAll() {
		Response<District> result = new Response<District>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<District> districtLst = session.createQuery("FROM District", District.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(districtLst);
		} catch (Exception e) {
			System.out.println("DistrictDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "DistrictDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<District> findById(String id) {
		Response<District> result = new Response<District>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(District.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("DistrictDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "DistrictDao findById")
							+ e.toString());
		}
		return result;
	}

	public Response<District> findByProvinceId(String provinceId) {
		Response<District> result = new Response<District>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<District> userLst = session
					.createQuery("FROM District WHERE province_id = :provinceId", District.class)
					.setParameter("provinceId", provinceId).getResultList();
			result.setRespListObj(userLst);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "DistrictDao DistrictDao")
							+ e.toString());
		}
		return result;
	}
}
