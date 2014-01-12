package org.ws;

public class DefaultFailObject {

	private boolean success;
	private Object payload;
	private String message;

	public DefaultFailObject(boolean success, Object payload, String message) {
		super();
		this.success = success;
		this.payload = payload;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
