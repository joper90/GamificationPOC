package parsers;

import java.util.Calendar;

import com.ibm.ac.commonbaseevent101.CommonBaseEventsType;
import com.ibm.ac.commonbaseevent101.ContextDataElementType;

import connector.database.DAO.GamificationDAO;
import connector.database.model.PlayerInformation;

public class EventParser {

	private CommonBaseEventsType cbe = null;
	private String user;
	private String userId;
	private String event;
	private String eventGuid;
	private String moduleName = null;
	private String stepName = null;
	private String messageCatergory = null;
	private String managedObjectStatus = null;
	private String managedObjectName = null;
	private String managedObjectId = null;
	private String managedParentObjectId = null;
	private Calendar creationTime = null;
	
	private static String USER 		= "principalName";
	private static String USERID 	= "principalId";
	private static String MODULE_NAME = "moduleName";
	private static String STEP_NAME = "applicationActivityName";
	private static String MESSAGE_CAT = "messageCategory";
	private static String MANAGED_OBJECT_STATUS = "managedObjectStatus";
	private static String MANAGED_OBJECT_NAME= "managedObjectName";
	private static String MANAGED_OBJECT_ID = "managedObjectId";
	private static String MANAGED_PARENT_ID = "parentObjectId";
	
	public EventParser(CommonBaseEventsType cbe)
	{
		this.cbe = cbe;
	}
	
	public void process()
	{
		//System.out.println(this.cbe);		
		this.creationTime = cbe.getCommonBaseEventArray(0).getCreationTime();
		this.eventGuid = cbe.getCommonBaseEventArray(0).getGlobalInstanceId();
		this.event = cbe.getCommonBaseEventArray(0).getMsg();
		
		ContextDataElementType[] cbeArray = cbe.getCommonBaseEventArray(0).getContextDataElementsArray();
				
		subParser(cbeArray);
		
		//Have we seen this userBefore.
		checkUserExistAndProcess(user,userId);
	}
	
	private void checkUserExistAndProcess(String userName, String userGuid)
	{
		if (!GamificationDAO.instance.getPlayerInformationDAO().doesExist(userName, userGuid))
		{
			PlayerInformation p = new PlayerInformation();
			p.setName(userName);
			p.setGuid(userGuid);
			p.setAvgTime(0);
			p.setCount(0);
			
			GamificationDAO.instance.getPlayerInformationDAO().insertData(p);
		}
		
	}
	
	public String toString()
	{
		return "EVENT:" + user +" | "+ userId +" | "+event+" | "+eventGuid +" | "+ creationTime + "\n\t"
				+ stepName+" | "+ messageCatergory+" | "+ moduleName+"\n\t"
				+ managedObjectStatus+" | "+managedObjectName+" | "+managedObjectId+" | "+managedParentObjectId;
		
	}
	
	private void subParser(ContextDataElementType[]  cArray)
	{
		for (ContextDataElementType c : cArray)
		{
			if (c.getName().contains(USER))
			{
				this.user = c.getContextValue();
			}
			else if (c.getName().contains(USERID)) 
			{
				this.userId = c.getContextValue();
			}
			else if (c.getName().contains(MODULE_NAME)) 
			{
				this.moduleName = c.getContextValue();
			}
			else if (c.getName().contains(STEP_NAME)) 
			{
				this.stepName = c.getContextValue();
			}
			else if (c.getName().contains(MESSAGE_CAT)) 
			{
				this.messageCatergory= c.getContextValue();
			}
			else if (c.getName().contains(MANAGED_OBJECT_STATUS)) 
			{
				this.managedObjectStatus = c.getContextValue();
			}
			else if (c.getName().contains(MANAGED_OBJECT_NAME)) 
			{
				this.managedObjectName = c.getContextValue();
			}
			else if (c.getName().contains(MANAGED_OBJECT_ID)) 
			{
				this.managedObjectId = c.getContextValue();
			}
			else if (c.getName().contains(MANAGED_PARENT_ID)) 
			{
				this.managedParentObjectId = c.getContextValue();
			}
		}
	}

	public String getUser() {
		return user;
	}

	public String getUserId() {
		return userId;
	}

	public String getEvent() {
		return event;
	}

	public String getEventGuid() {
		return eventGuid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getStepName() {
		return stepName;
	}

	public String getMessageCatergory() {
		return messageCatergory;
	}

	public String getManagedObjectStatus() {
		return managedObjectStatus;
	}

	public String getManagedObjectName() {
		return managedObjectName;
	}

	public String getManagedObjectId() {
		return managedObjectId;
	}

	public String getManagedParentObjectId() {
		return managedParentObjectId;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}
	
	
	
	//Getters/Setters:
	
	
}
