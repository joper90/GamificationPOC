package connector.database.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateUtil;

import connector.database.model.StepProgress;

public class StepProgressDAO extends StandardDAO {
	public StepProgressDAO()
	{
		super();
		System.out.println("--> [DAO] init StepProgressDAO...");
	}
	
	public StepProgress getProgress(String stepPvmId, String stepName, String stepVersion, String stepProgress)
	{
		boolean errorCreated = false;
		Transaction trns = null;
		StepProgress sProgress = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		
		try {
			trns = session.beginTransaction();
		
			@SuppressWarnings("unchecked")
			List<StepProgress> sInfoList = session
						.createQuery("from StepProgress where stepId = :s and stepName = :n and stepVersion =:v and stepProgress=:p")
						.setString("s", stepPvmId)
						.setString("n", stepName)
						.setString("v", stepVersion)
						.setString("p", stepProgress).list();
			
			if (sInfoList != null  && sInfoList.size() > 0) {
				sProgress = sInfoList.get(0);
				
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] " + e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated)
				session.flush();
			session.close();
		}
		return sProgress;
	}
	
	@SuppressWarnings("unchecked")
	public boolean doesExist(String stepPvmId, String stepName, String stepVersion, String stepProgress) {
		boolean errorCreated = false;
		Transaction trns = null;
		boolean exists = false;
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<StepProgress> sInfoList = new ArrayList<StepProgress>();
		
		try {
			trns = session.beginTransaction();
		
			if (stepProgress == null)
			{
			
				sInfoList = session
						.createQuery("from StepProgress where stepId = :s and stepName = :n and stepVersion = :v")
						.setString("s", stepPvmId)
						.setString("n", stepName)
						.setString("v", stepVersion).list();
			}else
			{
				sInfoList = session
						.createQuery("from StepProgress where stepId = :s and stepName = :n and stepVersion = :v and stepProgress = :p")
						.setString("s", stepPvmId)
						.setString("n", stepName)
						.setString("v", stepVersion)
						.setString("p", stepProgress).list();
			}
			if (sInfoList != null  && sInfoList.size() > 0) {
				exists = true;
				
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] " + e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated)
				session.flush();
			session.close();
		}
		return exists;
	}
	
	@SuppressWarnings("unchecked")
	public List<StepProgress> getCompletedByGuid(String userGuid)
	{
		boolean errorCreated = false;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<StepProgress> sInfoList = null;
		
		try {
			trns = session.beginTransaction();
		
			sInfoList = session
						.createQuery("from StepProgress where userGuid = :u and completed = :c")
						.setString("u", userGuid)
						.setBoolean("c",true)
						.list();
			
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			System.out.println("--> [HIB] " + e.getLocalizedMessage());
			errorCreated = true;
		} finally {
			if (!errorCreated)
				session.flush();
			session.close();
		}
		return sInfoList;
	}
}
