package engine;

import java.text.ParseException;
import java.util.List;

import parsers.EventParser;

import common.GameSettings;
import common.GameUtils;

import connector.database.DAO.GamificationDAO;
import connector.database.model.PlayerInformation;
import connector.database.model.StepProgress;
import connector.tibbr.TibbrConnector;

public class AchivementProcessor {

	private EventParser event = null;
	
	private static String PROCESS_STARTED = "Process Instance started";
	private	static String PROCESS_COMPLETED = "Process Instance completed";
	private static String OPEN_WORK_ITEM ="Opened Work Item";
	private static String END_WORK_ITEM = "End complete work item service";
	
	public AchivementProcessor(EventParser event)
	{
		this.event = event;
	}
		
	public void digest()
	{
		System.out.println(event.toString());
		
		if (event.getEvent().contains(PROCESS_STARTED)) {doStartProcess();}
		else if (event.getEvent().contains(PROCESS_COMPLETED)) {doCompleteProcess();}
		else if (event.getEvent().contains(OPEN_WORK_ITEM)) {doOpenWorkItem();}
		else if (event.getEvent().contains(END_WORK_ITEM)) {doCompleteWorkItem();}
	}
	
	public static void doWelcomeProcess(String userName)
	{
		if (GameUtils.doesUserHaveAchivement(userName, GameSettings.instance.getWelcomeToThePleasureDome()))
		{
			System.out.println("User doesn't have the achivement [Welcome].. adding");
			GameUtils.addHardAchivementToUser(userName, GameSettings.instance.getWelcomeToThePleasureDome());
			TibbrConnector.instance.postNewAchivement(userName, "Welcome to the pleasure dome.. ");
		}
	}
	
	
	private void doStartProcess()
	{
		if (!GameUtils.doesUserHaveAchivement(event.getUser(), GameSettings.instance.getOutTheBlocksUUID()))
		{
			System.out.println("User doesn't have the achivement[start].. adding");
			GameUtils.addHardAchivementToUser(event.getUser(), GameSettings.instance.getOutTheBlocksUUID());
			TibbrConnector.instance.postNewAchivement(event.getUser(), "Started a first process..");
		}
	}
	
	private void doCompleteProcess()
	{
		if (!GameUtils.doesUserHaveAchivement(event.getUser(), GameSettings.instance.getFirestartUUID()))
		{
			System.out.println("User doesn't have the achivement[complete].. adding");
			GameUtils.addXPAchivementToUser(event.getUser(), GameSettings.instance.getFirestartUUID());
		}
		
		GameUtils.addXpAmountToAchivementToUser(event.getUser(), GameSettings.instance.getFirestartUUID(), 1);
		
	}
	
	private void doOpenWorkItem()
	{
		GameUtils.addOpenStepInfo(event);
	}
	
	private void doCompleteWorkItem()
	{
		try {
			GameUtils.addCloseStepInfo(event);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Updated user Average now
		List<StepProgress> steps = GamificationDAO.instance.getStepProgressDAO().getCompletedByGuid(event.getUserId());
		
		if (steps!=null && steps.size() >0)
		{
			int size = steps.size();
			int count = 0;
			for (StepProgress s : steps)
			{
				count += new Integer(s.getTimeTaken());
			}
			
			float avgTime = count/size;
			int avgTimeInt = Math.round(avgTime);
			
			//find the user by the guid
			PlayerInformation infoByGuid = GamificationDAO.instance.getPlayerInformationDAO().getInfoByGuid(event.getUserId());
			infoByGuid.setAvgTime(avgTimeInt);
			infoByGuid.setCount(size);
			
			System.out.println("");
			
			GamificationDAO.instance.getPlayerInformationDAO().updateModel(infoByGuid);
		}
	}
}
