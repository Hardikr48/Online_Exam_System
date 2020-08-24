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
			return chack="erorr";
		}
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

}
