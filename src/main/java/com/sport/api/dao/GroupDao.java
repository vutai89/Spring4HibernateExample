package com.sport.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.Group;
import com.sport.api.output.Response;
import com.sport.api.utils.JFGlobalFunctions;

@Repository(value = "groupDao")
@Transactional(rollbackFor = Exception.class)
public class GroupDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Response<Group> save(Group group) {
		Response<Group> result = new Response<Group>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			String idGen = (String) session.save(group);
			if (idGen.equals("")) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
			} else {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
				result.setRespObj(group);
			}

		} catch (Exception e) {
			System.out.println("GroupDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());
		}
		return result;
	}

	public Response<Group> saveAll(List<Group> groupLst) {
		Response<Group> result = new Response<Group>();
		Session session = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			int idGen = 0;
			for (int i = 0; i < groupLst.size(); i++) {
				idGen = (Integer) session.save(groupLst.get(i));
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
			System.out.println("GroupDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "GroupDao saveAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Group> findAll() {
		Response<Group> result = new Response<Group>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Group> GroupLst = session.createQuery("FROM Group", Group.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(GroupLst);
		} catch (Exception e) {
			System.out.println("GroupDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "GroupDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<Group> findById(String id) {
		Response<Group> result = new Response<Group>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(Group.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("GroupDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "GroupDao findById")
							+ e.toString());
		}
		return result;
	}

	public Response<Group> findByGroupId(String groupId) {
		Response<Group> result = new Response<Group>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Group> groupLst = session.createQuery("FROM Group WHERE group_id = :groupId", Group.class)
					.setParameter("groupId", groupId).getResultList();
			result.setRespListObj(groupLst);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("GroupDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "GroupDao findByGroupId")
							+ e.toString());
		}
		return result;
	}

}
