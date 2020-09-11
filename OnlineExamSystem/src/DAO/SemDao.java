package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.DepartmentVo;
import VO.SemVo;

public class SemDao {

	public String insertSame(SemVo samvo) {
		String chack=null;
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(samvo);
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("...........................");
		return chack="add";
	}

	public ArrayList<SemVo>searchSem(SemVo vo){
		List<SemVo> li = new ArrayList<SemVo>();
		try{
			System.out.println(vo.getCollege());
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SemVo AS d where d.college ="+vo.getCollege().getId());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SemVo>) li;
	}

	public void deleteSem(SemVo semvo) {
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(semvo);
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SemVo> searchDepartmentSem(SemVo semvo) {
		List<SemVo> li = new ArrayList<SemVo>();
		try{
			
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SemVo AS a where a.departmentid =:id");
			q.setParameter("id", semvo.getDepartmentid());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SemVo>) li;
	}
	public ArrayList<SemVo> chackhDepartmentSem(SemVo semvo) {
		List<SemVo> li = new ArrayList<SemVo>();
		try{
			
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SemVo AS a where a.departmentid =:id And a.semname =:sem");
			q.setParameter("id", semvo.getDepartmentid());
			q.setParameter("sem", semvo.getSemname());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SemVo>) li;
	}

	public ArrayList<SemVo> searchDepartment(SemVo semvo) {
		List<SemVo> li = new ArrayList<SemVo>();
		try{
			
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from SemVo AS a where a.departmentid =:id");
			q.setParameter("id", semvo.getDepartmentid());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<SemVo>) li;
	}
}
