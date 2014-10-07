package com.github.benschw.springboot.metrics;

import com.codahale.metrics.MetricRegistry;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

import java.util.Collection;

public class CodahaleMetricsAdapter implements PublicMetrics {

	private MetricNamer metricNamer;
	private MetricRegistry metricRegistry;

	public CodahaleMetricsAdapter(MetricNamer metricNamer, MetricRegistry metricRegistry) {
		this.metricNamer = metricNamer;
		this.metricRegistry = metricRegistry;
	}

	@Override
	public Collection<Metric<?>> metrics() {
		MetricCollector metrics = new MetricCollector(metricNamer, metricRegistry);
		return metrics.getMetrics();
	}


}
