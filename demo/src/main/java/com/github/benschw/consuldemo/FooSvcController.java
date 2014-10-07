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

import com.github.benschw.consuldemo.api.FooSvcApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/foosvc")
public class FooSvcController {


	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody
    FooSvcApi foo() {
        String hostName = "unknown";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            //pass
        }
        return new FooSvcApi("Hello from " + hostName);
	}

}