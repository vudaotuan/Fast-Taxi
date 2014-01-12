package org.ws;

public class DefaultSuccessObject {

	private boolean success;
	private Object payload;

	public DefaultSuccessObject(boolean success, Object payload) {
		super();
		this.success = success;
		this.payload = payload;
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

}
