package rest.requests;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rest.model.AchivementProgress;
import rest.model.PlayerDetails;

import common.GameStatics;

import connector.database.DAO.GamificationDAO;
import connector.database.model.AchivementsModel;
import connector.database.model.PlayerInformation;
import connector.database.model.Progress;
import engine.AchivementProcessor;

@Path("/userdetails")
public class UserDetails {

	
	@GET
	@Path("{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PlayerDetails setDetailsWithJsonString(@PathParam("userName") String userName, 
												  @QueryParam("userGuid") String userGuid,
												  @QueryParam("supplyImages") Boolean supplyImages)
	{
		PlayerDetails pDetails = new PlayerDetails();
		
		//Get all the relevent details and progress
		PlayerInformation playerInfo = GamificationDAO.instance.getPlayerInformationDAO().getInfoByGuid(userGuid);

		if (playerInfo == null)
		{
			pDetails.setSuccess(false);
			pDetails.setMessage("Invalid User");
			
			return pDetails;
		}
		
		if (!playerInfo.getName().equalsIgnoreCase(userName))
		{
			pDetails.setSuccess(false);
			pDetails.setMessage("Invalid User");
			
			return pDetails;
		}
		
		AchivementProcessor.doWelcomeProcess(userName);
		
		List<Progress> userProgress = GamificationDAO.instance.getProgressDAO().getAllByUserGuid(userGuid);
		
		//Now build up the stats.
		pDetails.setName(playerInfo.getName());
		pDetails.setGuid(playerInfo.getGuid());
		
		pDetails.setStepCount(playerInfo.getCount());
		pDetails.setAvgTimePerStep(playerInfo.getAvgTime());
		

		ArrayList<AchivementProgress> hardProgress = new ArrayList<AchivementProgress>();
		ArrayList<AchivementProgress> softProgress = new ArrayList<AchivementProgress>();
		//Inject the dummy, so you need start start from an offset of one.
			
		
		//Now iterate thru the progress
		for (Progress p : userProgress)
		{
			AchivementProgress pInject = new AchivementProgress();
			//Get actual raw achivement info based on the guid.
			AchivementsModel actualRawAchivement = GamificationDAO.instance.getAchivementsDAO().getByGuid(p.getAchivementGuid());
			
			if (actualRawAchivement != null)
			{
				//Soft or hard achivement.
				boolean isHard = true;
				if(actualRawAchivement.getCount() != 0) isHard = false;
				
				pInject.setAchivementGUID(actualRawAchivement.getGuid());
				pInject.setAchivementName(actualRawAchivement.getName());
				pInject.setDescription(actualRawAchivement.getDescription());
				pInject.setMaxAmount(actualRawAchivement.getCount());
				pInject.setProgress(p.getProgress());
				
				pInject.setImg("img/icons/" + actualRawAchivement.getNotImg());
				if (p.getProgress() == GameStatics.ACHIVEMENT_GAINED)
				{
					pInject.setImg("img/icons/" + actualRawAchivement.getGotImg());
					pInject.setCompleted(true);
					pInject.setCompletedDate(p.getDateCompleted());
				}else
				{
					pInject.setCompleted(false);
				}
				
				if (isHard)
				{ 
					hardProgress.add(pInject);
				}else
				{
					pInject.setImg("img/icons/" + actualRawAchivement.getGotImg());
					softProgress.add(pInject);
				}
					
			}
		}
		
		pDetails.setHardAchivements(hardProgress);
		pDetails.setSoftAchivements(softProgress);
		
		
		return pDetails;
	}
}
