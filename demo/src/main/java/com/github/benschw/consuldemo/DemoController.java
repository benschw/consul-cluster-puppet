package com.github.benschw.consuldemo;


import com.google.common.net.HostAndPort;
import com.spotify.dns.DnsException;
import com.spotify.dns.DnsSrvResolver;
import com.spotify.dns.DnsSrvResolvers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/demo")
public class DemoController {



    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    Message foo() {

        DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
                .cachingLookups(true)
                .retainingDataOnFailures(true)
                .metered(true)
                .dnsLookupTimeoutMillis(1000)
                .build();

        String name = "foo-svc.service.consul";


        List<HostAndPort> nodes = null;

        try {
            nodes = resolver.resolve(name);

            Random rand = new Random();

            int n = rand.nextInt(nodes.size());

            HostAndPort node = nodes.get(n);

            String address = "http://"+node.getHostText().replaceAll(".$", "") +":"+ Integer.toString(node.getPort()) + "/foosvc";

//            return new Message(address);

            RestTemplate restTemplate = new RestTemplate();
            Message msg = restTemplate.getForObject(address, Message.class);

            return msg;
        } catch (DnsException e) {
            return new Message(e.getMessage());
        }
    }

}