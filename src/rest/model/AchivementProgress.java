package rest.model;



public class AchivementProgress {

	String achivementName;
	String description;
	String achivementGUID;
	boolean completed;
	Long completedDate;
	
	int progress;
	int maxAmount;
	String img;
	
	public AchivementProgress(){}
	
	public String getAchivementName() {
		return achivementName;
	}
	public void setAchivementName(String achivementName) {
		this.achivementName = achivementName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAchivementGUID() {
		return achivementGUID;
	}
	public void setAchivementGUID(String achivementGUID) {
		this.achivementGUID = achivementGUID;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	public Long getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Long completedDate) {
		this.completedDate = completedDate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
