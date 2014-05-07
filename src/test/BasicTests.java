package test;

import junit.framework.TestCase;

import org.testng.annotations.Test;

import connector.database.DAO.GamificationDAO;
import connector.database.model.PlayerInformation;
import connector.database.model.Progress;

public class BasicTests extends TestCase{

	
	@Test
	public void PlayerInfoTests()
	{
		System.out.println("Testing playerInfotests");
		
		PlayerInformation pInfo = new PlayerInformation();
		pInfo.setName("joe");
		pInfo.setGuid("ba23");
		
		GamificationDAO.instance.getPlayerInformationDAO().insertData(pInfo);
	}
	
	
	@Test
	public void ProgressTests()
	{
		System.out.println("Testing progress");
		
		String USER_GUID = "12345";
		String A_GUID = "999";
		
		Progress pInfo = new Progress();
		pInfo.setPlayerGuid(USER_GUID);
		pInfo.setProgress(-1);
		pInfo.setAchivementGuid(A_GUID);
		
		GamificationDAO.instance.getProgressDAO().insertData(pInfo);
		
		//Can I get it back
		
		Integer result = GamificationDAO.instance.getProgressDAO().getCurentPointsByUserAndGuid(USER_GUID, A_GUID);
		System.out.println(result);
		
		result = GamificationDAO.instance.getProgressDAO().getCurentPointsByUserAndGuid(USER_GUID, "423");
		System.out.println(result);
	}
}
