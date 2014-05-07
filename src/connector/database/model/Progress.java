package connector.database.model;

public class Progress extends BaseModel {

	private String playerGuid;
	private Integer progress;
	private String achivementGuid;
	private Long	dateCompleted;	
	
	public String getPlayerGuid() {
		return playerGuid;
	}
	public void setPlayerGuid(String playerGuid) {
		this.playerGuid = playerGuid;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public String getAchivementGuid() {
		return achivementGuid;
	}
	public void setAchivementGuid(String achivementGuid) {
		this.achivementGuid = achivementGuid;
	}
	public Long getDateCompleted() {
		return dateCompleted;
	}
	public void setDateCompleted(Long dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	
	
	
}
