package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.SubjectVo;

public class SubjectDao {
	public ArrayList<SubjectVo> chackSubject(SubjectVo subjectvo) {
		List<SubjectVo> subjectlist = new ArrayList<SubjectVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectVo AS s where s.subject =:subjectname");
			q.setParameter("subjectname", subjectvo.getSubject());
			subjectlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectVo>) subjectlist;
	}

	public void insertSubject(SubjectVo subjectvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(subjectvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SubjectVo> searchAllSubject(SubjectVo subjectvo) {
		List<SubjectVo> subjectlist = new ArrayList<SubjectVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectVo AS a where a.collegeid =:id");
			q.setParameter("id", subjectvo.getCollegeid());
			subjectlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectVo>) subjectlist;
	}

	public ArrayList<SubjectVo> searchSemSubject(SubjectVo subjectvo) {
		List<SubjectVo> subjectlist = new ArrayList<SubjectVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectVo AS a where a.collegeid =:id And a.semid =:sem");
			q.setParameter("id", subjectvo.getCollegeid());
			q.setParameter("sem", subjectvo.getSemid());
			subjectlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectVo>) subjectlist;
	}

	public ArrayList<SubjectVo> searchDepartmentSubject(SubjectVo subjectvo) {
		List<SubjectVo> subjectlist = new ArrayList<SubjectVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectVo AS a where a.collegeid =:id And a.departmentid =:department");
			q.setParameter("id", subjectvo.getCollegeid());
			q.setParameter("department", subjectvo.getDepartmentid());
			subjectlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectVo>) subjectlist;
	}
	public ArrayList<SubjectVo> searchSem(SubjectVo subjectvo) {
		List<SubjectVo> subjectlist = new ArrayList<SubjectVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectVo AS a where a.semid =:sem");
			q.setParameter("sem", subjectvo.getSemid());
			subjectlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectVo>) subjectlist;
	}
}