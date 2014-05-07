package rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class RequestDetails {

	private boolean success = true;
	private String message = "WORKED";
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	
	
}
