package com.github.benschw.SrvLoadBalancer;

import com.google.common.net.HostAndPort;

import java.util.List;

public interface LoadBalancingStrategy {
	public HostAndPort next(List<HostAndPort> nodes);
}
