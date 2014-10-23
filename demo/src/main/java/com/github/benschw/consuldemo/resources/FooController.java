
package com.github.benschw.consuldemo.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.benschw.consuldemo.api.Foo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/foo")
public class FooController {

	@Timed
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody
	Foo foo() {
		String hostName = "unknown";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			//pass
		}
		return Foo.builder().
				message("Hello from " + hostName).
				build();
	}

}