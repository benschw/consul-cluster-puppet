/*
 * Copyright (c) 2012-2014 Spotify AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.benschw.consuldemo;

//import com.spotify.statistics.MuninReporter;
//import com.spotify.statistics.MuninReporterConfig;
//import com.yammer.metrics.Metrics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HostAndPort;
import com.spotify.dns.DnsException;
import com.spotify.dns.DnsSrvResolver;
import com.spotify.dns.DnsSrvResolvers;

import java.util.List;

@RestController
@RequestMapping("/srv")
public class SrvController {


	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<HostAndPort> foo() {
		DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
			.cachingLookups(true)
			.retainingDataOnFailures(true)
			.metered(true)
			.dnsLookupTimeoutMillis(1000)
			.build();

		String name = "my-svc.service.consul";

		String resp = "";


//        MuninReporterConfig reporterConfig = new MuninReporterConfig();
//        DnsSrvResolvers.configureMuninGraphs(reporterConfig.category("dns"));
//        MuninReporter reporter = new MuninReporter(Metrics.defaultRegistry(), reporterConfig);
//        reporter.start();

        List<HostAndPort> nodes = null;

        try {
			nodes = resolver.resolve(name);
		} catch (DnsException e) {
			e.printStackTrace(System.out);
		}

		return nodes;
	}

}