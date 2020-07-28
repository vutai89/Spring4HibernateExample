package com.sport.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.Ward;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;
@Repository(value = "wardDao")
@Transactional(rollbackFor = Exception.class)
public class WardDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Response<Ward> save(Ward Ward) {
		Response<Ward> result = new Response<Ward>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			String idGen = (String) session.save(Ward);
			if (idGen.equals("")) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
			} else {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
				result.setRespObj(Ward);
			}

		} catch (Exception e) {
			System.out.println("WardDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());
		}
		return result;
	}

	public Response<Ward> saveAll(List<Ward> WardLst) {
		Response<Ward> result = new Response<Ward>();
		Session session = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			int idGen = 0;
			for (int i = 0; i < WardLst.size(); i++) {
				idGen = (Integer) session.save(WardLst.get(i));
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
			System.out.println("WardDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "WardDao saveAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Ward> findAll() {
		Response<Ward> result = new Response<Ward>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Ward> WardLst = session.createQuery("FROM Ward", Ward.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(WardLst);
		} catch (Exception e) {
			System.out.println("WardDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "WardDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Ward> findById(String id) {
		Response<Ward> result = new Response<Ward>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(Ward.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("WardDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "WardDao findById")
							+ e.toString());
		}
		return result;
	}

	public Response<Ward> findByDistrictId(String districtId) {
		Response<Ward> result = new Response<Ward>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Ward> wardLst = session.createQuery("FROM Ward WHERE district_id = :districtId", Ward.class)
					.setParameter("districtId", districtId).getResultList();
			result.setRespListObj(wardLst);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "WardDao findByWardId")
							+ e.toString());
		}
		return result;
	}
}
