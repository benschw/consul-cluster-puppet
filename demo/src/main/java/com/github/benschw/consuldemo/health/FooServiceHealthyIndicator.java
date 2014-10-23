package com.github.benschw.consuldemo.health;

import com.github.benschw.springboot.srvloadbalancer.LoadBalancer;
import com.google.common.net.HostAndPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class FooServiceHealthyIndicator extends AbstractHealthIndicator {

	@Autowired
	private LoadBalancer loadBalancer;

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		HostAndPort node = loadBalancer.getAddress("foo");

		if (node != null) {
			builder.up();
		} else {
			builder.status("WARN");
		}
	}
}