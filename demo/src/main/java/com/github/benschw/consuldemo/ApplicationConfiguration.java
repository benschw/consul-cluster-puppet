package com.github.benschw.consuldemo;

import com.codahale.metrics.MetricRegistry;
import com.github.benschw.springboot.SrvLoadBalancer.CodahaleSpringBootReporter;
import com.github.benschw.springboot.SrvLoadBalancer.LoadBalancer;
import com.github.benschw.springboot.SrvLoadBalancer.LoadBalancingStrategy;
import com.github.benschw.springboot.SrvLoadBalancer.RandomLoadBalancingStrategy;
import com.github.benschw.springboot.metrics.CodahaleMetricsAdapter;
import com.github.benschw.springboot.metrics.MetricNamer;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.spotify.dns.DnsSrvResolver;
import com.spotify.dns.DnsSrvResolvers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMetrics
public class ApplicationConfiguration {

	@Value("${service.name:unknown}")
	private String serviceName;

	@Value("${service.trimString:unknown}")
	private String trimString;

	@Autowired
	MetricRegistry metricsRegistry;

	@Bean
	public CodahaleMetricsAdapter codahaleMetricsAdapter() {
		MetricNamer metricNamer = new MetricNamer(serviceName, trimString);
		return new CodahaleMetricsAdapter(metricNamer, metricsRegistry);
	}

	@Bean
	public LoadBalancer loadBalancer() {
		LoadBalancingStrategy strategy = new RandomLoadBalancingStrategy();

		DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
				.cachingLookups(true)
				.retainingDataOnFailures(true)
				.metered(new CodahaleSpringBootReporter(metricsRegistry))
				.dnsLookupTimeoutMillis(1000)
				.build();

		return new LoadBalancer(strategy, resolver);
	}
}