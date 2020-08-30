package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.LoginVO;
import VO.ProfessorVo;
import VO.StudentVo;

public class StudentDao {

	public void studentInsert(StudentVo studentvo, LoginVO loginvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(studentvo);
			session.save(loginvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentProfileUpdate(StudentVo studentvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(studentvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List editStudentProfile(StudentVo studentvo) {
		List<StudentVo> studentlsit = new ArrayList<StudentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from StudentVo where id=" + studentvo.getId());
			studentlsit = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return studentlsit;
		}
		return studentlsit;
	}

	public ArrayList<StudentVo> searchCollegeStudent(StudentVo studentVo) {
		List<StudentVo> studentList = new ArrayList<StudentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from StudentVo AS s where s.collegeid =:id");
			q.setParameter("id", studentVo.getCollegeid());
			studentList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<StudentVo>) studentList;
	}
}
