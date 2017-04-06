package com.crud.bean;

public class LoggingResponse {
	private String jSessionId;

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	public LoggingResponse(String sessionId) {
		this.jSessionId = sessionId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		StringBuilder str = new StringBuilder();
		str.append("jsessionid: " +jSessionId);
		return str.toString();
	}

}
