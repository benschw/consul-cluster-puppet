package com.github.benschw.consuldemo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.net.HostAndPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoApi {

	private FooSvcApi fooSvcResponse;
	private HostAndPort selectedAddress;

}
