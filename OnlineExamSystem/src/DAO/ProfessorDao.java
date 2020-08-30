package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.DepartmentProfessorVo;
import VO.DepartmentVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.SemProfessorVo;
import VO.SubjectProfessorVo;

public class ProfessorDao {
	public void professorInsert(ProfessorVo professorvo, LoginVO loginvo, DepartmentProfessorVo departmentprofessorvo,
			SemProfessorVo semprofessorvo, SubjectProfessorVo subjectprofessorvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(professorvo);
			session.save(loginvo);
			session.save(departmentprofessorvo);
			session.save(semprofessorvo);
			session.save(subjectprofessorvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List editProfessorProfile(ProfessorVo professorvo) {
		List<ProfessorVo> professorlsit = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ProfessorVo where id=" + professorvo.getId());
			professorlsit = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return professorlsit;
		}
		return professorlsit;
	}

	public ArrayList<ProfessorVo> professorHodSearch(ProfessorVo professorvo) {
		List<ProfessorVo> Professorlist = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery(
					"from ProfessorVo AS p where p.departmentid =:dep And p.semid =:sem And p.roll =:roll1");
//			q.setParameter("dep",professorvo.getDepartmentid());
//			q.setParameter("sem",professorvo.getSemid());
			q.setParameter("roll1", professorvo.getRoll());
			Professorlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) Professorlist;
	}

	public void professorProfileUpdate(ProfessorVo professorvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(professorvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ProfessorVo> searchCollegeProfessor(ProfessorVo professorvo) {
		List<ProfessorVo> professorList = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ProfessorVo AS p where p.collegeid =:id");
			q.setParameter("id", professorvo.getCollegeid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) professorList;
	}

	public ArrayList<ProfessorVo> searchCollegeSemProfessor(ProfessorVo professorvo) {
		List<ProfessorVo> professorList = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ProfessorVo AS p where p.collegeid =:id And p.semid =:sem");
			q.setParameter("id", professorvo.getCollegeid());
//			q.setParameter("sem", professorvo.getSemid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) professorList;
	}

	public ArrayList<ProfessorVo> searchCollegeDepartmentProfessor(ProfessorVo professorvo) {
		List<ProfessorVo> professorList = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session
					.createQuery("from ProfessorVo AS p where p.collegeid =:id And p.departmentid =:department");
			q.setParameter("id", professorvo.getCollegeid());
//			q.setParameter("department", professorvo.getDepartmentid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) professorList;
	}

	public ArrayList<ProfessorVo> searchCollegeSubjectProfessor(ProfessorVo professorvo) {
		List<ProfessorVo> professorList = new ArrayList<ProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ProfessorVo AS p where p.collegeid =:id And p.subjectid =:subject");
			q.setParameter("id", professorvo.getCollegeid());
//			q.setParameter("subject", professorvo.getSubjectid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ProfessorVo>) professorList;
	}
}
