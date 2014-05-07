package connector.database.DAO;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateUtil;

import connector.database.model.AchivementsModel;

public class AchivementsDAO extends StandardDAO{

	public AchivementsDAO()
	{
		super();
		System.out.println("--> [DAO] init AchivementsDAO...");
	}
	
	@SuppressWarnings("unchecked")
	public List<AchivementsModel> getAll()
	{
		boolean errorCreated =false;
		Transaction trns = null;
		List<AchivementsModel> sInfoList = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();


			sInfoList = session.createQuery("from AchivementsModel").list();
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] "+ e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated) session.flush();
			session.close();
		}
		return sInfoList;
	}
	
	public Integer getIdByGuid(String guid)
	{
		boolean errorCreated =false;
		Transaction trns = null;
		AchivementsModel a = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<AchivementsModel> sInfoList = session.createQuery("from AchivementsModel where guid = :guid")
													.setString("guid", guid)
													.list();
			
			if (sInfoList != null)
			{
				a =sInfoList.get(0);
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] "+ e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated) session.flush();
			session.close();
		}
		return a.getId();
	}
	
	public AchivementsModel getByGuid(String guid)
	{
		boolean errorCreated =false;
		Transaction trns = null;
		AchivementsModel a = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<AchivementsModel> sInfoList = session.createQuery("from AchivementsModel where guid = :guid")
													.setString("guid", guid)
													.list();
			
			if (sInfoList != null)
			{
				a =sInfoList.get(0);
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] "+ e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated) session.flush();
			session.close();
		}
		return a;
	}
	
	
	public String getGuidByName(String name)
	{
		boolean errorCreated =false;
		Transaction trns = null;
		String a = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<AchivementsModel> sInfoList = session.createQuery("from AchivementsModel where name = :name")
													.setString("name", name)
													.list();
			
			if (sInfoList != null)
			{
				a = sInfoList.get(0).getGuid();
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] "+ e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated) session.flush();
			session.close();
		}
		return a;
	}
	
}
