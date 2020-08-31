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

public class DepartmentProfessorDao {

	public void insert(DepartmentProfessorVo departmentprofessorvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(departmentprofessorvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public ArrayList<DepartmentProfessorVo> searchdepartmentprofessor(DepartmentProfessorVo departmentProfessorVo) {
//		
//		List<DepartmentProfessorVo> professorList = new ArrayList<DepartmentProfessorVo>();
//		try {
//			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			Session session = sessionfactory.openSession();
//			Transaction transaction = session.beginTransaction();
//			Query q = session.createQuery("from DepartmentProfessorVo AS d where d.professorid="+ departmentProfessorVo.getProfessorid().getId());
//			professorList = q.list();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return (ArrayList<DepartmentProfessorVo>) professorList;
//	}

	public ArrayList<DepartmentProfessorVo> searchDepartmentProfessor(DepartmentProfessorVo departmentprofessorvo) {
		List<DepartmentProfessorVo> professorList = new ArrayList<DepartmentProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentProfessorVo AS d where d.departmentid =:department");
			q.setParameter("department", departmentprofessorvo.getDepartmentid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentProfessorVo>) professorList;
	}

	public ArrayList<DepartmentProfessorVo> chackHodProfessor(DepartmentProfessorVo departmentprofessorvo) {
		List<DepartmentProfessorVo> professorList = new ArrayList<DepartmentProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session
					.createQuery("from DepartmentProfessorVo AS d where d.departmentid =:department And d.roll =:rll");
			q.setParameter("department", departmentprofessorvo.getDepartmentid());
			q.setParameter("rll", departmentprofessorvo.getRoll());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentProfessorVo>) professorList;
	}

	public ArrayList<DepartmentProfessorVo> searchCollegeDepartmentProfessor(
			DepartmentProfessorVo departmentProfessorVo) {
		List<DepartmentProfessorVo> professorList = new ArrayList<DepartmentProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentProfessorVo AS p where p.professorid =:id");
			q.setParameter("id", departmentProfessorVo.getProfessorid());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentProfessorVo>) professorList;
	}
}