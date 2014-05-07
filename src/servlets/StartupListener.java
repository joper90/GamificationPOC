package servlets;

import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jmsConnector.JmsConnector;

import common.GameSettings;
import common.GameUtils;

import connector.database.DAO.GamificationDAO;
import connector.database.model.AchivementsModel;
import connector.tibbr.TibbrConnector;

public class StartupListener  implements ServletContextListener{

	private JmsConnector jmsConnector;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("JmsConnector distroyed..");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		String resetDBfromContext = event.getServletContext().getInitParameter("resetDatabase");
		boolean resetDB = Boolean.parseBoolean(resetDBfromContext);
		
		String tibbrEnabledfromContext = event.getServletContext().getInitParameter("enableTibbr");
		boolean enableTibbr = Boolean.parseBoolean(tibbrEnabledfromContext);
		
		if(enableTibbr) TibbrConnector.instance.setEnabled(enableTibbr);
		
		
		System.out.println("====>Starting JMS...");
		jmsConnector = new JmsConnector();
		jmsConnector.connectAndSpawn();
		System.out.println("====>Started JMS...");
		
		
		if (resetDB)
		{
			System.out.println("====>Inject Achivements...");
			AchivementsModel achivement = new AchivementsModel();
			String UUID = GameUtils.getGuid();
			achivement.setAllValues("Out the Blocks", "Started a case", 0, "jobdone_n.jpg", "jobdone_g.png",UUID);
			GamificationDAO.instance.getAchivementsDAO().insertData(achivement);
			GameSettings.instance.setOutTheBlocksUUID(UUID);
			
			UUID = GameUtils.getGuid();
			achivement.setAllValues("Fire Starter", "Started a number of cases", 100, "welcome_n.jpg", "firestarter_g.png",UUID);
			GamificationDAO.instance.getAchivementsDAO().insertData(achivement);	
			GameSettings.instance.setFirestartUUID(UUID);
			
			UUID = GameUtils.getGuid();
			achivement.setAllValues("Welcome to the Pleasure Dome", "First day", 0, "jobdone_n.jpg", "castle_g.png",UUID);
			GamificationDAO.instance.getAchivementsDAO().insertData(achivement);	
			GameSettings.instance.setWelcomeToThePleasureDome(UUID);
		}else
		{
			System.out.println("====> NOT RESETING DB... updateing guids");
			String uuid = GamificationDAO.instance.getAchivementsDAO().getGuidByName("Out the Blocks");
			GameSettings.instance.setOutTheBlocksUUID(uuid);
			
			uuid = GamificationDAO.instance.getAchivementsDAO().getGuidByName("Fire Starter");
			GameSettings.instance.setFirestartUUID(uuid);
			
			uuid = GamificationDAO.instance.getAchivementsDAO().getGuidByName("Welcome to the Pleasure Dome");
			GameSettings.instance.setWelcomeToThePleasureDome(uuid);
		}
		//TibbrConnector.instance.postNewAchivement("Clint Hill", "Test achivement gained");
		//TibbrConnector.instance.postWorkItemMessage("test new Gamificaiton");
				
		System.out.println("====>Completed Inject Achivements...");
		System.out.println("====>End init the system...\n\n");
	}

}
