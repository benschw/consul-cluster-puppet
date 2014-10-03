package com.github.benschw.consuldemo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/com.github.benschw.hello-world")
public class HelloWorldController {

    private static final String template = "Hello, %s!";

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return String.format(template, name);
    }

}