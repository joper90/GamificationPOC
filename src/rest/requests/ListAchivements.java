package rest.requests;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.model.Achivements;

import connector.database.DAO.GamificationDAO;
import connector.database.model.AchivementsModel;
import connector.database.model.PlayerInformation;


@Path("/achivements")
public class ListAchivements {

	@GET
	@Path("{requestedUser}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Achivements listAchivements(@PathParam("requestedUser") String userName)
	{
		Achivements achivements = new Achivements();
		//Check if a valid user.
		PlayerInformation playerInfo = GamificationDAO.instance.getPlayerInformationDAO().getInfoByName(userName);
		if (playerInfo==null || !playerInfo.getName().equalsIgnoreCase(userName))
		{
			achivements.setSuccess(false);
			achivements.setMessage("Not a valid user");
			return achivements;
		}
				
		
		List<AchivementsModel> all = GamificationDAO.instance.getAchivementsDAO().getAll();
		
		if (all == null)
		{
			achivements.setSuccess(false);
			achivements.setMessage("No achivements returned");
			return achivements;
		}
		
		achivements.setAchivements(all);
		return achivements;
	}
	
}
