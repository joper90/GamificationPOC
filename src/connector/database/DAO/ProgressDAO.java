package connector.database.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateUtil;

import connector.database.model.Progress;

public class ProgressDAO extends StandardDAO {

	public ProgressDAO()
	{
		super();
		System.out.println("--> [DAO] init ProcessDAO...");
	}
	
	public boolean doesUserHaveAchivement(String userGuid, String guid) {
		boolean errorCreated = false;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean retValue = false;
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Progress> sInfoList = session
					.createQuery("from Progress where playerGuid = :user and achivementGuid = :guid")
					.setString("user", userGuid).setString("guid",guid).list();

			if (sInfoList != null && sInfoList.size() > 0) {
				retValue = true;
				
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
		return retValue;
	}
	
	public Integer getCurentPointsByUserAndGuid(String userGuid, String guid) {
		boolean errorCreated = false;
		Transaction trns = null;
		Progress pInfo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Integer retValue = null;
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Progress> sInfoList = session
					.createQuery("from Progress where playerGuid = :user and achivementGuid = :guid")
					.setString("user", userGuid).setString("guid",guid).list();

			if (sInfoList != null && sInfoList.size() > 0) {
				pInfo = sInfoList.get(0);
				retValue = pInfo.getProgress();
				
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
		return retValue;
	}
	
	public Progress getByUserAndGuid(String userGuid, String guid) {
		boolean errorCreated = false;
		Transaction trns = null;
		Progress pInfo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Progress> sInfoList = session
					.createQuery("from Progress where playerGuid = :user and achivementGuid = :guid")
					.setString("user", userGuid).setString("guid",guid).list();

			if (sInfoList != null && sInfoList.size() > 0) {
				pInfo = sInfoList.get(0);
				
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
		return pInfo;
	}
	
	public List<Progress> getAllByUserGuid(String userGuid) {
		boolean errorCreated = false;
		Transaction trns = null;
		
		List<Progress> ret = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Progress> sInfoList = session
					.createQuery("from Progress where playerGuid = :user")
					.setString("user", userGuid).list();

			ret=sInfoList;
		
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
		return ret;
	}
}
