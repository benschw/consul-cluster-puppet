package com.github.benschw.consuldemo;

import com.github.benschw.consuldemo.metrics.MetricsConfiguration;
import org.springframework.boot.SpringApplication;

public class Application {
    public static void main(String[] args) {
		Class[] cfg = new Class[]{ApplicationConfiguration.class, MetricsConfiguration.class};
        SpringApplication.run(cfg, args);
    }
}