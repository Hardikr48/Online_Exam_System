package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.DepartmentVo;

public class DepartmentDao {

	public String insertSame(DepartmentVo departmentvo) {
		String chack = null;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(departmentvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return chack = "erorr";
		}
		return chack = "add";
	}

	public void insertDepartment(DepartmentVo departmentvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(departmentvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<DepartmentVo> searchCollegeDepartment(DepartmentVo departmentvo) {
		List<DepartmentVo> depList = new ArrayList<DepartmentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo AS d where d.departmentcollegeid =:id");
			q.setParameter("id", departmentvo.getDepartmentcollegeid());
			depList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentVo>) depList;
	}

	public ArrayList<DepartmentVo> searchSemDepartment(DepartmentVo departmentvo) {
		List<DepartmentVo> depList = new ArrayList<DepartmentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo AS d where d.semid =:id");
			q.setParameter("id", departmentvo.getSemid());
			depList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentVo>) depList;
	}

	public ArrayList<DepartmentVo> chackDepartment(DepartmentVo departmentvo) {
		List<DepartmentVo> depList = new ArrayList<DepartmentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo AS d where d.department =:daepatname");
			q.setParameter("daepatname", departmentvo.getDepartment());
			depList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentVo>) depList;
	}

	public ArrayList<DepartmentVo> getdepartmentcode(DepartmentVo departmentVo) {
		List<DepartmentVo> depList = new ArrayList<DepartmentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo AS d where d.id =:daepatname");
			q.setParameter("daepatname", departmentVo.getId());
			depList = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentVo>) depList;
		
	}
}
