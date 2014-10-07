package com.github.benschw.springboot.SrvLoadBalancer;

import com.google.common.net.HostAndPort;

import java.util.List;

public interface LoadBalancingStrategy {
	public HostAndPort next(List<HostAndPort> nodes);
}
