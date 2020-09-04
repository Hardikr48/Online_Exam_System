package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.ExamPhaseVo;
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

	public ArrayList<ExamPhaseVo> searchCollegeStudent(ExamPhaseVo examvo) {
		List<ExamPhaseVo> ExamList = new ArrayList<ExamPhaseVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo AS s where s.collegeid =:id");
			q.setParameter("id", examvo.getCollegeid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamPhaseVo>) ExamList;
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

	public ArrayList<ExamPhaseVo> searchCollegeSubjectExam(ExamPhaseVo examvo) {
		List<ExamPhaseVo> ExamList = new ArrayList<ExamPhaseVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo AS s where s.subjectid =:id");
			q.setParameter("id", examvo.getSubjectid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  (ArrayList<ExamPhaseVo>) ExamList;
		
	}

	public ArrayList<ExamVo> searchCollegeSemesterExam(ExamPhaseVo examvo) {
		List<ExamVo> ExamList = new ArrayList<ExamVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo AS s where s.semesterid =:id");
			q.setParameter("id", examvo.getSemesterid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamVo>) ExamList;
	}

	public ArrayList<ExamVo> searchDepartmentExam(ExamPhaseVo examvo) {
		List<ExamVo> ExamList = new ArrayList<ExamVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo AS s where s.departmentid =:id");
			q.setParameter("id", examvo.getDepartmentid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamVo>) ExamList;
	}
	public ArrayList<ExamPhaseVo> searchDepartmentSemesterExam(ExamPhaseVo examvo) {
		List<ExamPhaseVo> ExamList = new ArrayList<ExamPhaseVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo AS s where s.departmentid =:id And s.semesterid =:semid");
			q.setParameter("id", examvo.getDepartmentid());
			q.setParameter("semid", examvo.getSemesterid());
			ExamList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamPhaseVo>) ExamList;
	}
}
