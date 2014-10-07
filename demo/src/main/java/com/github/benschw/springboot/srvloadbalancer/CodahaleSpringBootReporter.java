package com.github.benschw.springboot.srvloadbalancer;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.spotify.dns.statistics.DnsReporter;
import com.spotify.dns.statistics.DnsTimingContext;

public class CodahaleSpringBootReporter implements DnsReporter {

	private MetricRegistry metrics;
	private Timer responses;

	public CodahaleSpringBootReporter(MetricRegistry metrics) {
		this.metrics = metrics;
		responses = metrics.timer(MetricRegistry.name(CodahaleSpringBootReporter.class, "srvlookup"));
	}

	@Override
	public DnsTimingContext resolveTimer() {
		return new DnsTimingContext() {
			final Timer.Context context = responses.time();
			private final long start = System.currentTimeMillis();

			@Override
			public void stop() {
				final long now = System.currentTimeMillis();
				final long diff = now - start;
				context.stop();

				System.out.println("Request took " + diff + "ms");
			}
		};
	}

	@Override
	public void reportFailure(Throwable error) {
		System.err.println("Error when resolving: " + error);
		error.printStackTrace(System.err);
	}

	@Override
	public void reportEmpty() {
		System.out.println("Empty response from server.");
	}
}
