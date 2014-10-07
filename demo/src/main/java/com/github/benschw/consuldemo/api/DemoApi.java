package com.github.benschw.consuldemo.api;

import com.google.common.net.HostAndPort;

public class DemoApi {

	private FooSvcApi fooSvcResponse;
	private HostAndPort selectedAddress;

	public DemoApi() {
	}

	public DemoApi(FooSvcApi fooSvcResponse, HostAndPort selectedAddress) {
		this.fooSvcResponse = fooSvcResponse;
		this.selectedAddress = selectedAddress;
	}

	public FooSvcApi getFooSvcResponse() {
		return fooSvcResponse;
	}

	public void setFooSvcResponse(FooSvcApi fooSvcResponse) {
		this.fooSvcResponse = fooSvcResponse;
	}

	public HostAndPort getSelectedAddress() {
		return selectedAddress;
	}

	public void setSelectedAddress(HostAndPort selectedAddress) {
		this.selectedAddress = selectedAddress;
	}

}
