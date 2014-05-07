package connector.database.model;

public class StepProgress  extends BaseModel{

	private String stepId;
	private String stepName;
	private String stepVersion;
	private String stepProgress;
	private boolean completed;
	private String timeTaken;
	private String userGuid;

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepVersion() {
		return stepVersion;
	}

	public void setStepVersion(String stepVersion) {
		this.stepVersion = stepVersion;
	}

	public String getStepProgress() {
		return stepProgress;
	}

	public void setStepProgress(String stepProgress) {
		this.stepProgress = stepProgress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	
	
	
	
}
