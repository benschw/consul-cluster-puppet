package com.github.benschw.consuldemo;

import com.codahale.metrics.MetricRegistry;
import com.github.benschw.SrvLoadBalancer.CodahaleSpringBootReporter;
import com.github.benschw.SrvLoadBalancer.LoadBalancer;
import com.github.benschw.SrvLoadBalancer.LoadBalancingStrategy;
import com.github.benschw.SrvLoadBalancer.RandomLoadBalancingStrategy;
import com.spotify.dns.DnsSrvResolver;
import com.spotify.dns.DnsSrvResolvers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationConfiguration {

	@Autowired
	MetricRegistry metrics;

	@Bean
	public LoadBalancer loadBalancer() {
		LoadBalancingStrategy strategy = new RandomLoadBalancingStrategy();

		DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
				.cachingLookups(true)
				.retainingDataOnFailures(true)
				.metered(new CodahaleSpringBootReporter(metrics))
				.dnsLookupTimeoutMillis(1000)
				.build();

		return new LoadBalancer(strategy, resolver);
	}
}