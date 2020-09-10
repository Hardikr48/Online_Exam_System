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
import VO.QuestionVo;

public class ExamPhaseDao {

	public void insertExamPhase(ExamPhaseVo examPhaseVo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(examPhaseVo);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ExamPhaseVo> searchExamphase(ExamPhaseVo examPhaseVo) {
		List<ExamPhaseVo> examlist = new ArrayList<ExamPhaseVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo where id=" + examPhaseVo.getId());
			examlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return (ArrayList<ExamPhaseVo>) examlist;
		}
		return (ArrayList<ExamPhaseVo>) examlist;
	}

	public ArrayList<ExamPhaseVo> getPhaseList(ExamPhaseVo examPhaseVo) {
		List<ExamPhaseVo> examlist = new ArrayList<ExamPhaseVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from ExamPhaseVo where id ="+ examPhaseVo.getId());
			examlist = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<ExamPhaseVo>) examlist;
	}

}
