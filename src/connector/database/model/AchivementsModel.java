package connector.database.model;

public class AchivementsModel extends BaseModel {

	private String name;
	private int count;
	private String notImg;
	private String gotImg;
	private String description;
	private String guid;
	
	public void setAllValues(String name, String description, int count, String notImg, String gotImg, String guid)
	{
		this.name = name;
		this.count = count;
		this.description = description;
		this.notImg = notImg;
		this.gotImg = gotImg;
		this.guid = guid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getNotImg() {
		return notImg;
	}

	public void setNotImg(String notImg) {
		this.notImg = notImg;
	}

	public String getGotImg() {
		return gotImg;
	}
	public void setGotImg(String gotImg) {
		this.gotImg = gotImg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
}
