package com.github.benschw.consuldemo.resources;


import com.codahale.metrics.annotation.Timed;
import com.github.benschw.consuldemo.api.FooSvcApi;
import com.github.benschw.consuldemo.api.DemoApi;
import com.github.benschw.springboot.SrvLoadBalancer.LoadBalancer;
import com.google.common.net.HostAndPort;
import com.spotify.dns.DnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private LoadBalancer loadBalancer;

	@Timed
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    DemoApi demo() {

        try {
			HostAndPort node = loadBalancer.getAddress("foo-svc");

			String address = LoadBalancer.AddressString("http", node) +  "/foosvc";

            RestTemplate restTemplate = new RestTemplate();
            FooSvcApi foo = restTemplate.getForObject(address, FooSvcApi.class);

            return DemoApi.builder().
					fooSvcResponse(foo).
					selectedAddress(node).
					build();

        } catch (DnsException e) {
            return DemoApi.builder().build();
        }
    }


}