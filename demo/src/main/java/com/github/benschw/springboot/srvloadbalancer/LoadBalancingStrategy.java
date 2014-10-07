package com.github.benschw.springboot.srvloadbalancer;

import com.google.common.net.HostAndPort;

import java.util.List;

public interface LoadBalancingStrategy {
	public HostAndPort next(List<HostAndPort> nodes);
}
