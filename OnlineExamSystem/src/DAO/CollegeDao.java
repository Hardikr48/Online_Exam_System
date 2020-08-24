package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.CollegeVo;
import VO.LoginVO;

public class CollegeDao {

	public void collegeInsert(CollegeVo collegevo, LoginVO loginvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(collegevo);
			session.save(loginvo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List editCollegeProfile(CollegeVo collegevo) {
		List<CollegeVo> collegelist = new ArrayList<CollegeVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from CollegeVo where id=" + collegevo.getId());
			collegelist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return collegelist;
		}
		return collegelist;
	}

	public void updateCollegeProfile(CollegeVo collegevo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(collegevo);
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
