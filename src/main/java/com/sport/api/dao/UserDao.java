package com.sport.api.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.api.entities.User;
import com.sport.api.output.Response;
import com.sport.api.utils.CommonFuncs;
import com.sport.api.utils.JFGlobalFunctions;

@Repository(value = "userDao")
@Transactional(rollbackFor = Exception.class)
public class UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public Response<User> save(User user) {
		Response<User> result = new Response<User>();
		Session session = null;
		try {
			if (checkUserExit(user.getUser_id(), user.getEmail(), user.getPassword(), true, "", "") != 0) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.DataExitsCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.DataExitsMsg").replace("##", "User"));
			} else {
				session = sessionFactory.getCurrentSession();
				user.setCreate_date(new Date());
				user.setPassword(CommonFuncs.ConvertToMD5(user.getPassword()));
				int idGen = (Integer) session.save(user);
				if (idGen == 0) {
					result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
					result.setRespContent(JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Insert"));
				} else {
					result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
					result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Insert"));
					result.setRespObj(user);
				}
			}
		} catch (Exception e) {
			System.out.println("UserDao save Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "save") + e.toString());

		}
		return result;
	}

	public Response<User> findAll() {
		Response<User> result = new Response<User>();
		try {
			Session session = sessionFactory.getCurrentSession();
			List<User> userLst = session.createQuery("FROM User", User.class).getResultList();
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
			result.setRespListObj(userLst);
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "UserDao findAll")
							+ e.toString());
		}
		return result;
	}

	public Response<User> findById(int id) {
		Response<User> result = new Response<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			result.setRespObj(session.get(User.class, id));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "UserDao findById")
							+ e.toString());
		}
		return result;
	}

	public Response<User> findByUserId(String userId) {
		Response<User> result = new Response<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<User> userLst = session.createQuery("FROM User WHERE user_id = :userId", User.class)
					.setParameter("userId", userId).getResultList();
			result.setRespObj(userLst.get(0));
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Lấy dữ liệu"));
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "UserDao findByUserId")
							+ e.toString());
		}
		return result;
	}

	public Response<User> update(User user) {
		Response<User> result = new Response<User>();
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			user.setModified_date(new Date());
			session.update(user);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Cập nhật"));
			result.setRespObj(user);

		} catch (Exception e) {
			System.out.println("UserDao update Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(
					JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "update") + e.toString());

		}
		return result;
	}

	public Response<User> delete(User user) {
		Response<User> result = new Response<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.remove(user);
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Xóa dữ liệu"));
		} catch (Exception e) {
			System.out.println("UserDao findAll Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "UserDao delete")
					+ e.toString());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public int checkUserExit(String userId, String email, String phoneNumber, Boolean isCreate, String oldEmail,
			String oldPhone) {
		int result = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = null;
			if (isCreate) {
				query = session.createQuery(
						"select count(*) from User a where upper(a.user_id)=:upper(userId) and a.phone=:phoneNumber and upper(a.email) =:upper(email)");
				query.setString("userId", userId);
				query.setString("email", email);
				query.setString("phoneNumber", "phoneNumber");
			} else {
				query = session.createQuery(
						"select count(*) from User a where  a.phone=:phoneNumber or upper(a.email) =:upper(email) and upper(a.email) <> :upper(oldEmail) and upper(a.phone) <> :upper(oldPhone)");
				query.setString("userId", userId);
				query.setString("email", email);
				query.setString("phoneNumber", "phoneNumber");
				query.setString("oldEmail", "oldEmail");
				query.setString("oldPhone", "oldPhone");
			}
			result = (Integer) query.uniqueResult();

		} catch (Exception e) {
			System.out.println("UserDao checkUserExit Exception: " + e.toString());
		}
		return result;
	}

	public Response<User> changePassword(String userId, String newPassword) {
		Response<User> result = new Response<User>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (!checkPassword(newPassword, userId)) {
				result.setRespCode(JFGlobalFunctions.getConfigValue("Error.PasswordSameCode"));
				result.setRespContent(JFGlobalFunctions.getConfigValue("Error.PasswordSameMsg"));
			} else {
				newPassword = CommonFuncs.encryptedStr(newPassword);
				String hqlUpdate = "update User u set u.password = :newPassword where c.user_id = :userId";
				@SuppressWarnings("deprecation")
				int updatedEntities = session.createQuery(hqlUpdate).setString("newPassword", newPassword)
						.setString("userId", userId).executeUpdate();
				if (updatedEntities == 0) {
					result.setRespCode(JFGlobalFunctions.getConfigValue("Error.SuccessCode"));
					result.setRespContent(
							JFGlobalFunctions.getConfigValue("Error.SuccessMsg").replace("##", "Thay đổi mật khẩu"));
				} else {
					result.setRespCode(JFGlobalFunctions.getConfigValue("Error.FailCode"));
					result.setRespContent(
							JFGlobalFunctions.getConfigValue("Error.FailCode").replace("##", "Thay đổi mật khẩu"));
				}
			}
		} catch (Exception e) {
			System.out.println("UserDao changePassword Exception: " + e.toString());
			result.setRespCode(JFGlobalFunctions.getConfigValue("Error.ExceptionCode"));
			result.setRespContent(JFGlobalFunctions.getConfigValue("Error.ExceptionMsg").replace("##", "UserDao delete")
					+ e.toString());
		}
		return result;
	}

	public Boolean checkPassword(String newPassword, String userId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String oldPwd = session.createQuery("SELECT password FROM User WHERE user_id = :userId")
					.setParameter("userId", userId).getQueryString();
			oldPwd = CommonFuncs.decryptedStr(oldPwd);
			if (oldPwd.toUpperCase().equals(newPassword.toUpperCase())) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("UserDao checkPassword Exception: " + e.toString());
			return false;
		}
	}
}
