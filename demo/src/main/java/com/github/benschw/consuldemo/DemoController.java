package com.github.benschw.consuldemo;


import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.github.benschw.consuldemo.api.FooSvcApi;
import com.github.benschw.consuldemo.api.DemoApi;
import com.google.common.net.HostAndPort;
import com.spotify.dns.DnsException;
import com.spotify.dns.DnsSrvResolver;
import com.spotify.dns.DnsSrvResolvers;
import com.spotify.dns.statistics.DnsReporter;
import com.spotify.dns.statistics.DnsTimingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static DnsReporter REPORTER = new StdoutReporter();

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    DemoApi foo() {

        DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
                .cachingLookups(true)
                .retainingDataOnFailures(true)
                .metered(REPORTER)
                .dnsLookupTimeoutMillis(1000)
                .build();

        String name = "foo-svc.service.consul";


        List<HostAndPort> nodes;

        try {
            nodes = resolver.resolve(name);

            Random rand = new Random();

            int n = rand.nextInt(nodes.size());

            HostAndPort node = nodes.get(n);

            String address = "http://"+node.getHostText().replaceAll(".$", "") +":"+ Integer.toString(node.getPort()) + "/foosvc";

            RestTemplate restTemplate = new RestTemplate();
            FooSvcApi foo = restTemplate.getForObject(address, FooSvcApi.class);
            DemoApi resp = new DemoApi(foo, node, nodes);
            return resp;
        } catch (DnsException e) {
            return new DemoApi();
        }
    }

    public static class StdoutReporter implements DnsReporter {
        @Inject
        private MetricRegistry registry;
        private Timer responses;

        @Override
        public DnsTimingContext resolveTimer() {
            responses = registry.timer("demo.srvlookup");
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

}