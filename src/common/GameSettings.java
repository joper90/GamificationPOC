package common;

public class GameSettings {

	public static final GameSettings instance = new GameSettings();
	private GameSettings(){};
	
	private String firestartUUID = "";
	private String outTheBlocksUUID = "";
	private String welcomeToThePleasureDome = "";
	
	public String getFirestartUUID() {
		return firestartUUID;
	}
	public void setFirestartUUID(String firestartUUID) {
		this.firestartUUID = firestartUUID;
		System.out.println("Out the blocks guid : " + firestartUUID);
	}
	public String getOutTheBlocksUUID() {
		return outTheBlocksUUID;
	}
	public void setOutTheBlocksUUID(String outTheBlocksUUID) {
		this.outTheBlocksUUID = outTheBlocksUUID;
	}
	public String getWelcomeToThePleasureDome() {
		return welcomeToThePleasureDome;
	}
	public void setWelcomeToThePleasureDome(String welcomeToThePleasureDome) {
		this.welcomeToThePleasureDome = welcomeToThePleasureDome;
	}

	
	
	
}
