package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.ExamVo;
import VO.StudentVo;

public class ExamDao {

	public void examIsert(ExamVo examVo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(examVo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ExamVo> searchCollegeStudent(ExamVo examvo) {
		List<ExamVo> ExamList = new ArrayList<ExamVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamVo AS s where s.collegeid =:id");
			q.setParameter("id", examvo.getCollegeid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamVo>) ExamList;
	}

	public List editExam(ExamVo examvo) {
		List<ExamVo> examlsit = new ArrayList<ExamVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamVo where id=" + examvo.getId());
			examlsit = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return examlsit;
		}
		return examlsit;
	}

	public void deleteExam(ExamVo examvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(examvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
