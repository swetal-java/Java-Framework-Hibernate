package dao;

import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.userConnection;
import model.userModel;

public class userDao {

	// insertUser
	public static void insertUser(userModel um) {
		Session session = new userConnection().getSession();
		Transaction tx = session.beginTransaction();
		session.save(um);
		tx.commit();
		session.close();
		System.out.println("Regisetered");
	}

	// loginUser
	public static userModel loginUser(userModel um) {
		Session session = new userConnection().getSession();
		Transaction tx = session.beginTransaction();
		String hql = "from userModel u where u.email=:email and u.password=: password";
		Query query = session.createQuery(hql);
		query.setParameter("email", um.getEmail());
		query.setParameter("password", um.getPassword());
		List list = query.list();
		userModel uModel = (userModel) list.get(0);
		System.out.println("login");
		return uModel;

	}

	// updateUser
	public static void updateUser(userModel um) {
		Session session = new userConnection().getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update userModel u set u.fname=:fname,u.lname=:lname,u.email=:email,u.address=:address,u.password=:password,u.gender=:gender where u.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("fname", um.getFname());
		query.setParameter("lname", um.getLname());
		query.setParameter("email", um.getEmail());
		query.setParameter("address", um.getAddress());
		query.setParameter("password", um.getPassword());
		query.setParameter("gender", um.getGender());
		query.setParameter("id", um.getId());
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("Updated..");
	}

	// deleteUser
	public static void deleteUser(int id) {
		Session session = new userConnection().getSession();
		Transaction tx = session.beginTransaction();
		userModel uModel = session.get(userModel.class, id);
		session.delete(uModel);
		tx.commit();
		session.close();
		System.out.println("Deleted.");
	}

	// getUserById
	public static userModel getUserById(int id) {
		Session session = new userConnection().getSession();
		Transaction tx = session.beginTransaction();
		userModel uModel = session.get(userModel.class, id);
		return uModel;
	}
	
	//updatePassword
		public static void updatePassword(userModel um,int id,String pass) {
			Session session = new userConnection().getSession();
			Transaction tx = session.beginTransaction();
			String hql = "update userModel u set u.password=:password where u.id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("password", pass);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			System.out.println("Password updated..");
		}
		
	//checkEmail
		public static boolean chkEmail(String email) {
			boolean flag = false;
			Session session = new userConnection().getSession();
			Transaction tx = session.beginTransaction();
			String hql = "from userModel u where u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List list = query.list();
			userModel uModel = (userModel) list.get(0);
			if(uModel != null) {
				flag = true;
			}
			return flag;
		}
		
		//updatePassword
				public static void updatePasswordByEmail(String email,String password) {
					Session session = new userConnection().getSession();
					Transaction tx = session.beginTransaction();
					String hql = "update userModel u set u.password=:password where u.email=:email";
					Query query = session.createQuery(hql);
					query.setParameter("password", password);
					query.setParameter("email", email);
					int result = query.executeUpdate();
					System.out.println("Password updated..");
				}

}
