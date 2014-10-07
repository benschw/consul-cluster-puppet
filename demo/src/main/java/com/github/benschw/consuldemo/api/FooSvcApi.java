package com.github.benschw.consuldemo.api;


public class FooSvcApi {
	private String message;

	public FooSvcApi() {
	}

	public FooSvcApi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
