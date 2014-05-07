package rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerCoreDetails extends RequestDetails{

	String name;
	String guid;
	int stepCount;
	int avgTimePerStep;
	boolean isNewUser = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getStepCount() {
		return stepCount;
	}
	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}
	public int getAvgTimePerStep() {
		return avgTimePerStep;
	}
	public void setAvgTimePerStep(int avgTimePerStep) {
		this.avgTimePerStep = avgTimePerStep;
	}
	public boolean isNewUser() {
		return isNewUser;
	}
	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
	
}
