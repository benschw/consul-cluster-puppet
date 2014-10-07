package com.github.benschw.consuldemo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.httpclient.HttpClientMetricNameStrategies;
import com.codahale.metrics.httpclient.InstrumentedHttpClient;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.codahale.metrics.logback.InstrumentedAppender;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;


import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMetrics
public class Application extends MetricsConfigurerAdapter {
    private MetricRegistry registry;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public MetricRegistry getMetricRegistry() {

        if (this.registry == null) {
            registry = new MetricRegistry();

            // register JVM metrics
            registry.registerAll(new GarbageCollectorMetricSet());
            registry.registerAll(new MemoryUsageGaugeSet());
            registry.registerAll(new ThreadStatesGaugeSet());

            // register logging metrics
            LoggerContext factory = (LoggerContext) LoggerFactory.getILoggerFactory();
            Logger root = factory.getLogger(Logger.ROOT_LOGGER_NAME);

            InstrumentedAppender metrics = new InstrumentedAppender(registry);
            metrics.setContext(root.getLoggerContext());
            metrics.start();
            root.addAppender(metrics);
        }

        return registry;
    }

    @Override
    public HealthCheckRegistry getHealthCheckRegistry() {
        return new HealthCheckRegistry();
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        JmxReporter.forRegistry(metricRegistry).build().start();
    }

}