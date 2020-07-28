package com.sport.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.Role;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;

@Repository(value = "roleDao")
@Transactional(rollbackFor = Exception.class)
public class RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Response<Role> save(Role role) {
		Response<Role> result = new Response<Role>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			String idGen = (String) session.save(role);
			if (idGen.equals("")) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
			} else {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
				result.setRespObj(role);
			}

		} catch (Exception e) {
			System.out.println("RoleDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());
		}
		return result;
	}

	public Response<Role> saveAll(List<Role> roleLst) {
		Response<Role> result = new Response<Role>();
		Session session = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			int idGen = 0;
			for (int i = 0; i < roleLst.size(); i++) {
				idGen = (Integer) session.save(roleLst.get(i));
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
			System.out.println("RoleDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "RoleDao saveAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Role> findAll() {
		Response<Role> result = new Response<Role>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Role> RoleLst = session.createQuery("FROM Role", Role.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(RoleLst);
		} catch (Exception e) {
			System.out.println("RoleDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "RoleDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Role> findById(String id) {
		Response<Role> result = new Response<Role>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(Role.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("RoleDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "RoleDao findById")
							+ e.toString());
		}
		return result;
	}

	public Response<Role> findByRoleId(String roleId) {
		Response<Role> result = new Response<Role>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Role> RoleLst = session.createQuery("FROM Role WHERE role_id = :roleId", Role.class)
					.setParameter("RoleId", roleId).getResultList();
			result.setRespListObj(RoleLst);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("RoleDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "RoleDao findByRoleId")
							+ e.toString());
		}
		return result;
	}
}
