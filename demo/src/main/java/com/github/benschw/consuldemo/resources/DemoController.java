package com.github.benschw.consuldemo.resources;


import com.codahale.metrics.annotation.Timed;
import com.github.benschw.consuldemo.api.Demo;
import com.github.benschw.consuldemo.api.Foo;
import com.github.benschw.springboot.srvloadbalancer.LoadBalancer;
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
    Demo demo() {

        try {
			HostAndPort node = loadBalancer.getAddress("foo");

			String address = LoadBalancer.AddressString("http", node) +  "/foo";

            RestTemplate restTemplate = new RestTemplate();
            Foo foo = restTemplate.getForObject(address, Foo.class);

            return Demo.builder().
					fooResponse(foo).
					selectedAddress(node).
					build();

        } catch (DnsException e) {
            return Demo.builder().build();
        }
    }


}