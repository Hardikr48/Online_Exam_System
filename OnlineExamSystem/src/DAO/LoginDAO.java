package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.CollegeVo;
import VO.LoginVO;

public class LoginDAO {

	public ArrayList<LoginVO> verify(LoginVO v1) {
		List<LoginVO> li1 = new ArrayList<LoginVO>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from LoginVO where email= :email and password = :password");
			q.setParameter("email", v1.getEmail());
			q.setParameter("password", v1.getPassword());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (ArrayList<LoginVO>) li1;
	}

	public ArrayList<CollegeVo> collegeVerify(CollegeVo v1) {

		List<CollegeVo> li1 = new ArrayList<CollegeVo>();
		try {
			System.out.println(v1.getEmail());
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from CollegeVo where email= :email and password = :password");
			q.setParameter("email", v1.getEmail());
			q.setParameter("password", v1.getPassword());
			li1 = q.list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (ArrayList<CollegeVo>) li1;
	}

//	public ArrayList<EmployeeVo> everify(EmployeeVo v1) {
//
//		List<EmployeeVo> li1 = new ArrayList<EmployeeVo>();
//		try {
//
//			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			Session session = sessionfactory.openSession();
//			Transaction transaction = session.beginTransaction();
////			Query q = session.createQuery("from EmployeeVo where email= :email and password = :password and status= :status1");
//			Query q = session.createQuery("from EmployeeVo where email= :email and password = :password");
//			q.setParameter("email", v1.getEmail());
//			q.setParameter("password", v1.getPassword());
////			q.setParameter("status1", v1.getStatus());
//			li1 = q.list();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return (ArrayList<EmployeeVo>) li1;
//	}

	public ArrayList<LoginVO> emailverify(LoginVO v1) {

		List<LoginVO> li1 = new ArrayList<LoginVO>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from LoginVO where email= :email");
			q.setParameter("email", v1.getEmail());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<LoginVO>) li1;
	}

	public void forgetpasswordcompany(CollegeVo compny, LoginVO login) {
		try {
			System.out.println("login.getPassword()");
			System.out.println(login.getPassword());
			System.out.println("login.getPassword()");
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE CollegeVo set password =:password1 " + "WHERE email =:email1";
			Query query = session.createQuery(dep);
			query.setParameter("password1", compny.getPassword());
			query.setParameter("email1", compny.getEmail());

			String log = "UPDATE LoginVO set password =:password1 " + "WHERE email =:email1";
			Query query1 = session.createQuery(log);
			query1.setParameter("password1", login.getPassword());
			query1.setParameter("email1", login.getEmail());

			int result = query.executeUpdate();
			int result1 = query1.executeUpdate();

			System.out.println("Rows Affected: " + result + "  " + result1);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void forgetpasswordemp(EmployeeVo emp, LoginVO login) {
//		try {
//
//			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			Session session = sessionfactory.openSession();
//			Transaction transaction = session.beginTransaction();
//			String dep = "UPDATE EmployeeVo set password =:password1 " + "WHERE email =:email1";
//			Query query = session.createQuery(dep);
//			query.setParameter("password1", emp.getPassword());
//			query.setParameter("email1", emp.getEmail());
//
//			String log = "UPDATE LoginVO set password =:password1 " + "WHERE email =:email1";
//			Query query1 = session.createQuery(log);
//			query1.setParameter("password1", login.getPassword());
//			query1.setParameter("email1", login.getEmail());
//
//			int result = query.executeUpdate();
//			int result1 = query1.executeUpdate();
//
//			System.out.println("Rows Affected: " + result + "  " + result1);
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void logintime(LoginVO login) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String log = "UPDATE LoginVO set lastlogin =:login1 " + "WHERE id =:id1";
			Query query = session.createQuery(log);
			query.setParameter("login1", login.getLastlogin());
			query.setParameter("id1", login.getId());
			int result = query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LoginVO> timeeeeeeees(LoginVO v1) {
		List<LoginVO> li1 = new ArrayList<LoginVO>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from LoginVO where id= :id1");
			q.setParameter("id1", v1.getId());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<LoginVO>) li1;
	}

	public String loginupdate(LoginVO login) {
		String chack;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String log = "UPDATE LoginVO set password =:password1 " + "WHERE email =:email1";
			Query query1 = session.createQuery(log);
			query1.setParameter("password1", login.getPassword());
			query1.setParameter("email1", login.getEmail());
			int result1 = query1.executeUpdate();

			transaction.commit();
			session.close();
		} catch (Exception e) {
			return chack = "erorr";
		}
		return chack = "add";

	}

	public String collegeLoginUpdate(LoginVO loginvo) {
		String chack;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("update LoginVO as a set a.email =:email1 , a.password =:password1 where a.collegeid =:collegeid");
			q.setParameter("collegeid", loginvo.getCollegeid());
			q.setParameter("email1", loginvo.getEmail());
			q.setParameter("password1", loginvo.getPassword());
			q.executeUpdate();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			return chack="erorr"; 
		}
		return chack="add";
	}

	public String profrssorLoginUpdate(LoginVO loginvo) {
		String chack;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("update LoginVO as a set a.email =:email1 , a.password =:password1 where a.professorid =:professorid");
			q.setParameter("professorid", loginvo.getProfessorid());
			q.setParameter("email1", loginvo.getEmail());
			q.setParameter("password1", loginvo.getPassword());
			q.executeUpdate();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			return chack="erorr"; 
		}return chack="add";
	}

	public String studentLoginUpdate(LoginVO loginvo) {
		String chack;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("update LoginVO as a set a.email =:email1 , a.password =:password1 where a.studentid =:studentid");
			q.setParameter("studentid", loginvo.getStudentid());
			q.setParameter("email1", loginvo.getEmail());
			q.setParameter("password1", loginvo.getPassword());
			q.executeUpdate();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			return chack="erorr"; 
		}return chack="add";
	}

//	public String loginupdate1(LoginVO login) {
//		String chack;
//		try {
//			System.out.println(login.getCompnayid().getId());
//			System.out.println(login.getEmail() + "" + login.getPassword());
//			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			Session session = sessionfactory.openSession();
//			Transaction transaction = session.beginTransaction();
//			Query q = session.createQuery(
//					"update LoginVO as a set a.email =:end1 , a.password =:status1 where a.compnayid =:id1");
//			q.setParameter("id1", login.getCompnayid());
//			q.setParameter("end1", login.getEmail());
//			q.setParameter("status1", login.getPassword());
//			q.executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			return chack = "erorr";
//		}
//		return chack = "add";
//	}

//	public String loginupdate2(LoginVO loginvo) {
//		String chack;
//		try {
//			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			Session session = sessionfactory.openSession();
//			Transaction transaction = session.beginTransaction();
//			Query q = session.createQuery(
//					"update LoginVO as a set a.email =:end1 , a.password =:status1 where a.employeeid =:id1");
//			q.setParameter("id1", loginvo.getEmployeeid());
//			q.setParameter("end1", loginvo.getEmail());
//			q.setParameter("status1", loginvo.getPassword());
//			q.executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			return chack = "erorr";
//		}
//		return chack = "add";
//	}

}
