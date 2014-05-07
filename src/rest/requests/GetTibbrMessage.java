package rest.requests;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import connector.database.DAO.GamificationDAO;
import connector.database.model.PlayerInformation;
import connector.tibbr.TibbrConnector;
import rest.model.TibbrDetails;



@Path("/lastmessage")
public class GetTibbrMessage {

	@GET
	@Path("{requestedUser}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TibbrDetails getLastTibbrMessage(@PathParam("requestedUser") String userName)
	{
		TibbrDetails tibbrDetails = new TibbrDetails();
		PlayerInformation playerInfo = GamificationDAO.instance.getPlayerInformationDAO().getInfoByName(userName);
		if (playerInfo==null || !playerInfo.getName().equalsIgnoreCase(userName))
		{
			tibbrDetails.setSuccess(false);
			tibbrDetails.setMessage("Not a valid user");
			return tibbrDetails;
		}
		
		//Call out to get the required info from tibbr.. (just fuding as hill)
		String lastTibbrMessage = TibbrConnector.instance.getLastTibbrMessage();
		if (lastTibbrMessage == null)
		{
			tibbrDetails.setSuccess(false);
			tibbrDetails.setMessage("Null response from Tibbr Server");
			return tibbrDetails;
		}
		
		String [] values = lastTibbrMessage.split("\\^");
		
		tibbrDetails.setSuccess(true);
		tibbrDetails.setMessage("worked");
		tibbrDetails.setDataPosted(values[0]);
		tibbrDetails.setPoster(values[1]);
		tibbrDetails.setTibbrMessage(values[2]);
				
		return tibbrDetails;
	}

	
}
