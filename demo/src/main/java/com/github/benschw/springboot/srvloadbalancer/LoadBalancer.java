package com.github.benschw.springboot.srvloadbalancer;

import com.google.common.net.HostAndPort;
import com.spotify.dns.DnsSrvResolver;

import java.util.List;

public class LoadBalancer {

	private LoadBalancingStrategy lbStrategy;
	private DnsSrvResolver resolver;

	public LoadBalancer(LoadBalancingStrategy lbStrategy, DnsSrvResolver resolver) {
		this.lbStrategy = lbStrategy;
		this.resolver = resolver;
	}

	public HostAndPort getAddress(String serviceName) {

		String srvName = serviceName + ".service.consul";


		List<HostAndPort> nodes = resolver.resolve(srvName);

		return lbStrategy.next(nodes);
	}

	public static String AddressString(String protocol, HostAndPort node) {
		return "http://"+node.getHostText().replaceAll(".$", "") +":"+ Integer.toString(node.getPort());
	}

}
