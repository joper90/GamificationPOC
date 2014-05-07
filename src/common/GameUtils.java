package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import parsers.EventParser;
import connector.database.DAO.GamificationDAO;
import connector.database.model.AchivementsModel;
import connector.database.model.Progress;
import connector.database.model.StepProgress;

public class GameUtils {

	public static String getGuid()
	{
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}
	
	public static boolean doesUserHaveAchivement(String userName, String achivementGuid)
	{
		String userGuid = GamificationDAO.instance.getPlayerInformationDAO().getGuidByName(userName);
		return GamificationDAO.instance.getProgressDAO().doesUserHaveAchivement(userGuid, achivementGuid);
	}
	
	public static void addHardAchivementToUser(String userName, String achivementGuid)
	{
		String userGuid = GamificationDAO.instance.getPlayerInformationDAO().getGuidByName(userName);
		
		if (doesUserHaveAchivement(userGuid, achivementGuid))
		{
			System.out.println("User already has achivement : " + userName + " : ");
		}else
		{
			Progress p = new Progress();
			p.setPlayerGuid(userGuid);
			p.setAchivementGuid(achivementGuid);
			p.setProgress(GameStatics.ACHIVEMENT_GAINED); //DONE
			p.setDateCompleted( new Date().getTime());
			
			GamificationDAO.instance.getProgressDAO().insertData(p);
		}
	
	}
	
	public static void addXPAchivementToUser(String userName, String achivementGuid)
	{
		String userGuid = GamificationDAO.instance.getPlayerInformationDAO().getGuidByName(userName);
		
		if (doesUserHaveAchivement(userGuid, achivementGuid))
		{
			System.out.println("User already has achivement : " + userName + " : ");
		}else
		{
			Progress p = new Progress();
			p.setPlayerGuid(userGuid);
			p.setAchivementGuid(achivementGuid);
			p.setProgress(GameStatics.ACHIVEMENT_ZERO);
			
			GamificationDAO.instance.getProgressDAO().insertData(p);
		}
	}
	
	public static void addXpAmountToAchivementToUser(String userName, String achivementGuid, int points)
	{
		String userGuid = GamificationDAO.instance.getPlayerInformationDAO().getGuidByName(userName);
		
		if (doesUserHaveAchivement(userGuid, achivementGuid))
		{
			Progress p = GamificationDAO.instance.getProgressDAO().getByUserAndGuid(userGuid, achivementGuid);
			AchivementsModel achivement = GamificationDAO.instance.getAchivementsDAO().getByGuid(achivementGuid);
			
			int max = achivement.getCount();
			
			if (p.getProgress() + points >= max)
			{
				p.setProgress(max);
				p.setDateCompleted(new Date().getTime());
				
			}
			
			p.setProgress(p.getProgress() + points);
			
			//Check if completed.
			
			
			
			GamificationDAO.instance.getProgressDAO().updateModel(p);
		}else
		{
			System.out.println("User does not have this achivement...");
		
		}
	}
	
	public static boolean addOpenStepInfo(EventParser event)
	{
		boolean ret = false;
		
		String stepPvmId = event.getManagedParentObjectId();
		String stepName = event.getStepName();
		String stepVersion = event.getManagedObjectId();
		String stepProgress = GameStatics.OPENED_ITEM;
		String timeStarted = event.getCreationTime().toString();
		
		//Check if this stepName + version + pvmId is in the database.. 
		boolean doesExist = GamificationDAO.instance.getStepProgressDAO().doesExist(stepPvmId, stepName, stepVersion, null);
		
		if (doesExist)
		{
			System.out.println("Duplicate step id.. skipping. " + stepPvmId +" " + stepName + " " +stepVersion);
		}else
		{
			StepProgress s = new StepProgress();
			s.setCompleted(false);
			s.setStepId(stepPvmId);
			s.setStepName(stepName);
			s.setStepVersion(stepVersion);
			s.setStepProgress(stepProgress);
			s.setTimeTaken(timeStarted);
			
			GamificationDAO.instance.getStepProgressDAO().insertData(s);
			
			System.out.println("INSERTED step id.." + stepPvmId +" " + stepName + " " +stepVersion);
		}

		return ret;
	}
	
	public static boolean addCloseStepInfo(EventParser event) throws ParseException
	{
		boolean ret = false;
		
		String stepPvmId = event.getManagedParentObjectId();
		String stepName = event.getStepName();
		String stepVersion = event.getManagedObjectId();
		String stepProgress = GameStatics.CLOSED_ITEM;
		String endDate = event.getCreationTime().toString();
		String userGuid = event.getUserId();
		
		
		boolean doesExist = GamificationDAO.instance.getStepProgressDAO().doesExist(stepPvmId, stepName, stepVersion, GameStatics.OPENED_ITEM);
		
		if (doesExist)
		{
			//found the so update with the
			StepProgress sProgress = GamificationDAO.instance.getStepProgressDAO().getProgress(stepPvmId, stepName, stepVersion, GameStatics.OPENED_ITEM);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date dateStarted = formatter.parse(sProgress.getTimeTaken());
			Date dateFinished =  formatter.parse(endDate);
			
			long difference = getDateDiff(dateStarted, dateFinished, TimeUnit.SECONDS);
			
			sProgress.setCompleted(true);
			sProgress.setTimeTaken(Long.toString(difference));
			sProgress.setStepProgress(stepProgress);
			sProgress.setUserGuid(userGuid);
			
			GamificationDAO.instance.getStepProgressDAO().updateModel(sProgress);
			
			ret = true;
		}
		
		return ret;
	}
	

	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
