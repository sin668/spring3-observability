package com.acme.observability.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.opentelemetry.api.trace.Span;

@RestController
class RestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(RestEndpoint.class);
    private RestTemplate restTemplate;
    
    @Value("${com.acme.server.base-url}")
    String serverUrl;
    
    public RestEndpoint(RestTemplate restTemplate){
      this.restTemplate = restTemplate;
    }
    
    @GetMapping("/client/{userId}")
    String userName(@PathVariable("userId") String userId) {
        log.info("Got a request! User Id is: {}", userId);
        log.info("Calling Server. Using URL: {}", serverUrl + "/server/{userId}");

        Span span = Span.current();

        span.setAttribute("event", userId);
        span.setAttribute("message", "this is a log message for userId is " + userId);
        
        return restTemplate.getForObject(serverUrl + "/server/{userId}", String.class, userId); 
    }
}
