package connector.database.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateUtil;

import connector.database.model.PlayerInformation;

public class PlayerInformationDAO extends StandardDAO {

	public PlayerInformationDAO() {
		super();
		System.out.println("--> [DAO] init PlayerInformationDAO...");
	}
	
	public boolean doesExist(String name, String guid)
	{
		boolean errorCreated = false;
		Transaction trns = null;
		boolean exists = false;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<PlayerInformation> sInfoList = session
					.createQuery("from PlayerInformation where name = :name and guid = :guid")
					.setString("name", name)
					.setString("guid", guid).list();

			if (sInfoList != null && sInfoList.size() > 0) {
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
	
	public String getGuidByName(String user) {
		boolean errorCreated = false;
		Transaction trns = null;
		PlayerInformation pInfo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<PlayerInformation> sInfoList = session
					.createQuery("from PlayerInformation where name = :name")
					.setString("name", user).list();

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

		if (pInfo == null) return null;
		return pInfo.getGuid();
	}
	
	public PlayerInformation getInfoByName(String user) {
		boolean errorCreated = false;
		Transaction trns = null;
		PlayerInformation pInfo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<PlayerInformation> sInfoList = session
					.createQuery("from PlayerInformation where name = :name")
					.setString("name", user).list();

			if (sInfoList != null) {
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
	
	public PlayerInformation getInfoByGuid(String userGuid) {
		boolean errorCreated = false;
		Transaction trns = null;
		PlayerInformation pInfo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			trns = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<PlayerInformation> sInfoList = session
					.createQuery("from PlayerInformation where guid = :g")
					.setString("g", userGuid).list();

			if (sInfoList != null) {
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
}
