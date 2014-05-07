package connector.database.DAO;

public class GamificationDAO {

	public static GamificationDAO instance = new GamificationDAO();
	private GamificationDAO(){};
	
	private PlayerInformationDAO playerInformationDAO = new PlayerInformationDAO();
	private AchivementsDAO achivementsDAO = new AchivementsDAO();
	private StepProgressDAO stepProgressDAO = new StepProgressDAO();
	private ProgressDAO progressDAO = new ProgressDAO();
	
	
	
	//Getters and setters:
	public PlayerInformationDAO getPlayerInformationDAO() {
		return playerInformationDAO;
	}

	public AchivementsDAO getAchivementsDAO() {
		return achivementsDAO;
	}

	public StepProgressDAO getStepProgressDAO() {
		return stepProgressDAO;
	}

	public ProgressDAO getProgressDAO()
	{
		return progressDAO;
	}	
	
}
