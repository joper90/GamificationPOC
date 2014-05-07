package rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TibbrDetails {

	private String message;
	private boolean success = true;
	
	private String dataPosted;
	private String poster;
	private String tibbrMessage;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getDataPosted() {
		return dataPosted;
	}
	public void setDataPosted(String dataPosted) {
		this.dataPosted = dataPosted;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTibbrMessage() {
		return tibbrMessage;
	}
	public void setTibbrMessage(String tibbrMessage) {
		this.tibbrMessage = tibbrMessage;
	}
	

	
	
	
}
