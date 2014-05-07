package rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import connector.database.model.AchivementsModel;

@XmlRootElement
public class Achivements extends RequestDetails
{
	List<AchivementsModel> achivements = null;

	public List<AchivementsModel> getAchivements() {
		return achivements;
	}

	public void setAchivements(List<AchivementsModel> achivements) {
		this.achivements = achivements;
	}
	
	
}
