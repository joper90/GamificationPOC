package connector.tibbr;

import runtime.TibbrHelper;

import common.GameStatics;

import engine.TibbrCommands;


public class TibbrConnector {

	public static TibbrConnector instance = new TibbrConnector();
	
	private boolean isEnabled = false;
	
	private TibbrConnector(){};
	
	public boolean postNewAchivement(String user, String achivement)
	{
		
		if (isEnabled)
		{
			System.out.println("----------> Sending message to the Tibbr Server. <-------------");
			System.out.println("Message : guid : " + achivement);
			
			try {
				TibbrHelper.callOut(TibbrCommands.WORK.CREATE_MESSAGE,
									GameStatics.TIBBR_SERVER,
									GameStatics.TIBBR_USER,
									GameStatics.TIBBR_PASS,
									GameStatics.TIBBR_TOPIC, 
									"Congratulations to "+ user +", Achievement gained '" + achivement +"'");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public boolean postWorkItemMessage(String message)
	{
		if (isEnabled)
		{
			System.out.println("----------> Sending message to the Tibbr Server. <-------------");
			System.out.println("Message : guid : " + message);
			try {
				TibbrHelper.callOut(TibbrCommands.WORK.CREATE_MESSAGE,
									GameStatics.TIBBR_SERVER,
									GameStatics.TIBBR_USER,
									GameStatics.TIBBR_PASS,
									GameStatics.TIBBR_TOPIC, 
									message);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}				
		return true;
	}
	
	public String getLastTibbrMessage()
	{
		if(isEnabled)
		{
			System.out.println("----------> Getting last message from the Tibbr server. <-------------");
			
			try {
				String message = TibbrHelper.getLatestMessage(GameStatics.TIBBR_SERVER,
											 GameStatics.TIBBR_HILL_USER,
											 GameStatics.TIBBR_HILL_PASSWORD);
				
				return message;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
