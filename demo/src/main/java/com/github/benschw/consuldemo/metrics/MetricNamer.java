package com.github.benschw.consuldemo.metrics;


public class MetricNamer {

	private String serviceName;
	private String trimString;

	public MetricNamer() {
		serviceName = "unknown";
		trimString = "unknown";
	}

	public MetricNamer(String serviceName, String trimString) {
		this.serviceName = serviceName;
		this.trimString = trimString;
	}

	public String getMetricName(String metricType, String metricKey) {
		String shortKeyName = convertKeyToShortName(metricKey);
		return serviceName + "." + metricType + "." + shortKeyName;
	}

	private String convertKeyToShortName(String key) {
		String shortKeyName = key;

		if (key.startsWith(trimString)) {
			String[] segments = key.split("\\.");
			if (segments.length > 1) {
				shortKeyName = segments[segments.length - 2] + "." + segments[segments.length - 1];
			}
		}
		return shortKeyName;
	}

}
