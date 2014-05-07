package connector.database.model;

public class PlayerInformation extends BaseModel {

	String name;
	String guid;
	Integer avgTime;
	Integer	count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
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
	public Integer getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(Integer avgTime) {
		this.avgTime = avgTime;
	}
		
}
