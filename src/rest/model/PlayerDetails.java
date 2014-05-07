package rest.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerDetails extends PlayerCoreDetails {

	ArrayList<AchivementProgress> hardAchivements = new ArrayList<AchivementProgress>();
	ArrayList<AchivementProgress> softAchivements = new ArrayList<AchivementProgress>();
	public ArrayList<AchivementProgress> getHardAchivements() {
		return hardAchivements;
	}
	public void setHardAchivements(ArrayList<AchivementProgress> hardAchivements) {
		this.hardAchivements = hardAchivements;
	}
	public ArrayList<AchivementProgress> getSoftAchivements() {
		return softAchivements;
	}
	public void setSoftAchivements(ArrayList<AchivementProgress> softAchivements) {
		this.softAchivements = softAchivements;
	}
	
	
	
}

