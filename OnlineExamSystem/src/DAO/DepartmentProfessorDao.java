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

	public ArrayList<DepartmentProfessorVo> searchdepartmentprofessor(DepartmentProfessorVo departmentProfessorVo) {
		System.out.println(departmentProfessorVo.getProfrssorid().getId());
		List<DepartmentProfessorVo> professorList = new ArrayList<DepartmentProfessorVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentProfessorVo AS d where d.professorid.id="+departmentProfessorVo.getProfrssorid().getId());
			professorList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentProfessorVo>) professorList;
	}

}
