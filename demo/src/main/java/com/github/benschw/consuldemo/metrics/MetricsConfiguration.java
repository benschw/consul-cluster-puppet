package com.github.benschw.consuldemo.metrics;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableMetrics
@Configuration
public class MetricsConfiguration extends MetricsConfigurerAdapter {

	@Value("${service.name:unknown}")
	private String serviceName;

	@Value("${service.trimString:unknown}")
	private String trimString;

	@Bean
	public MetricNamer metricNamer() {
		return new MetricNamer(serviceName, trimString);
	}

	@Bean
	public CodahaleMetricsAdapter codahaleMetricsAdapter() {
		return new CodahaleMetricsAdapter();
	}

}
