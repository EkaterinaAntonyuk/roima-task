package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class DemoController {
    private final DemoTransformer demoTransformer;

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    public DemoController(DemoTransformer demoTransformer) {
        this.demoTransformer = demoTransformer;
    }

    @PostMapping(value = "/transform", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public String transformXML(@RequestBody String data) {
        log.info("Received message: {}", data);
        return demoTransformer.transform(data);
    }

}