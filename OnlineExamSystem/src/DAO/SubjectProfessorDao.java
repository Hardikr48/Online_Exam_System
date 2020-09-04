package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.DepartmentProfessorVo;
import VO.ProfessorVo;
import VO.SemProfessorVo;
import VO.SubjectProfessorVo;

public class SubjectProfessorDao {

	public void insert(SubjectProfessorVo subjectprofessorvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(subjectprofessorvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<SubjectProfessorVo> searchSubjectProfessor(SubjectProfessorVo subjectprofessorvo) {
		List<SubjectProfessorVo> professorList = new ArrayList<SubjectProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectProfessorVo AS p where p.professorid =:id");
			q.setParameter("id", subjectprofessorvo.getProfessorid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectProfessorVo>) professorList;

	}

	public ArrayList<SubjectProfessorVo> SubjectProfessor(SubjectProfessorVo subjectProfessorVo) {
		List<SubjectProfessorVo> professorList = new ArrayList<SubjectProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectProfessorVo AS d where d.subjectid =:subject");
			q.setParameter("subject", subjectProfessorVo.getSubjectid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectProfessorVo>) professorList;
	}

	public ArrayList<SubjectProfessorVo> searchSemesterSubjectProfessor(SubjectProfessorVo subjectProfessorVo) {
		List<SubjectProfessorVo> professorList = new ArrayList<SubjectProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectProfessorVo AS d where d.semprofessorid =:department");
			q.setParameter("department", subjectProfessorVo.getSemprofessorid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectProfessorVo>) professorList;
	}

	public void deleteProfessorSubject(SubjectProfessorVo subjectProfessorVo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("delete from SubjectProfessorVo AS d where d.id =:department");
			q.setParameter("department", subjectProfessorVo.getId());
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SubjectProfessorVo> searchDepartmentSubjectProfessor(SubjectProfessorVo subjectProfessorVo) {
		List<SubjectProfessorVo> professorList = new ArrayList<SubjectProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectProfessorVo AS d where d.departmentprofessorid =:department");
			q.setParameter("department", subjectProfessorVo.getDepartmentprofessorid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectProfessorVo>) professorList;
	}

	public ArrayList<SubjectProfessorVo> getProfessorDepartment(SubjectProfessorVo subjectProfessorVo) {
		List<SubjectProfessorVo> professorList = new ArrayList<SubjectProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SubjectProfessorVo AS d where d.professorid =:department");
			q.setParameter("department", subjectProfessorVo.getProfessorid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SubjectProfessorVo>) professorList;
	}

}
