package com.github.benschw.springboot.metrics;

import com.codahale.metrics.*;
import org.springframework.boot.actuate.metrics.Metric;

import java.util.ArrayList;
import java.util.SortedMap;

class MetricCollector {

    private MetricNamer namer;
    private MetricRegistry registry;
    private ArrayList<Metric<?>> metrics = new ArrayList<>();

    public MetricCollector(MetricNamer namer, MetricRegistry registry) {
        this.namer = namer;
        this.registry = registry;
    }

    public ArrayList<Metric<?>> getMetrics() {
        addTimers();
        addMeters();
        addGauges();
        addCounters();
        return metrics;
    }

    private void addTimers() {
        SortedMap<String, Timer> timers = registry.getTimers();
        for (String key : timers.keySet()) {
            Timer timer = timers.get(key);

            if ((timer != null) && (timer.getCount() > 0)) {
                addTimerMetrics(key, timer);
            }
        }
    }

    private void addTimerMetrics(String key, Timer timer) {
        addMeterMetrics(key, timer);

        Snapshot snapshot = timer.getSnapshot();
        addMetric("timer.min", key, snapshot.getMin());
        addMetric("timer.max", key, snapshot.getMax());
        addMetric("timer.median", key, snapshot.getMedian());
        addMetric("timer.mean", key, snapshot.getMean());
        addMetric("timer.standard-deviation", key, snapshot.getStdDev());
    }

    private void addMeters() {
        SortedMap<String, Meter> meters = registry.getMeters();
        for (String key : meters.keySet()) {
            Meter meter = meters.get(key);

            if ((meter != null) && (meter.getCount() > 0)) {
                addMeterMetrics(key, meter);
            }
        }
    }

    private void addMeterMetrics(String key, Metered meter) {
        addMetric("meter.mean", key, meter.getMeanRate());
        addMetric("meter.one-minute", key, meter.getOneMinuteRate());
        addMetric("meter.five-minute", key, meter.getFiveMinuteRate());
        addMetric("meter.fifteen-minute", key, meter.getFifteenMinuteRate());
    }

    private void addGauges() {
        SortedMap<String, Gauge> gauges = registry.getGauges();
        for (String key : gauges.keySet()) {
            Gauge gauge = gauges.get(key);

            if ((gauge != null) && (gauge.getValue() instanceof Number)) {
                addMetric("gauge", key, (Number) gauge.getValue());
            }
        }
    }

    private void addCounters() {
        SortedMap<String, Counter> counters = registry.getCounters();
        for (String key : counters.keySet()) {
            Counter counter = counters.get(key);

            if (counter != null) {
                addMetric("counter", key, counter.getCount());
            }
        }
    }

    private <T extends Number> void addMetric(String metricType, String key, T metric) {
        String name = namer.getMetricName(metricType, key);
        metrics.add(new Metric<>(name, metric));
    }

}
