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
import VO.ProfessorVo;
import VO.StudentVo;

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

	public void forgetPasswordCollege(CollegeVo collegevo, LoginVO login) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE CollegeVo set password =:password1 " + "WHERE email =:email1";
			Query query = session.createQuery(dep);
			query.setParameter("password1", collegevo.getPassword());
			query.setParameter("email1", collegevo.getEmail());

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

	public ArrayList<ProfessorVo> professorVerify(ProfessorVo professorvo) {
		List<ProfessorVo> li1 = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ProfessorVo where email= :email and password = :password");
			q.setParameter("email", professorvo.getEmail());
			q.setParameter("password", professorvo.getPassword());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) li1;
	}

	public ArrayList<StudentVo> studentVerify(StudentVo studentvo) {
		List<StudentVo> li1 = new ArrayList<StudentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from StudentVo where email= :email and password = :password");
			q.setParameter("email", studentvo.getEmail());
			q.setParameter("password", studentvo.getPassword());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<StudentVo>) li1;
	}

	public void forgetPasswordProfessor(ProfessorVo professorvo, LoginVO login) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE ProfessorVo set password =:password1 " + "WHERE email =:email1";
			Query query = session.createQuery(dep);
			query.setParameter("password1", professorvo.getPassword());
			query.setParameter("email1", professorvo.getEmail());

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

	public void forgetPasswordStudent(StudentVo studentvo, LoginVO login) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE StudentVo set password =:password1 " + "WHERE email =:email1";
			Query query = session.createQuery(dep);
			query.setParameter("password1", studentvo.getPassword());
			query.setParameter("email1", studentvo.getEmail());

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
}
