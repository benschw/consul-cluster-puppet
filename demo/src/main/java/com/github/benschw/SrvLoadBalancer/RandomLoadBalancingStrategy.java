package com.github.benschw.SrvLoadBalancer;

import com.google.common.net.HostAndPort;

import java.util.List;
import java.util.Random;

public class RandomLoadBalancingStrategy implements LoadBalancingStrategy {

	public HostAndPort next(List<HostAndPort> nodes) {
		Random rand = new Random();

		int n = rand.nextInt(nodes.size());

		return nodes.get(n);
	}
}
