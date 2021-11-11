package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;

@Service
public class DemoTransformer {

    private static final Logger log = LoggerFactory.getLogger(DemoTransformer.class);

    public DemoTransformer() {

    }

    public String transform(String message) {

        String result = "";

        try {
            StringReader reader = new StringReader(message);
            StringWriter writer = new StringWriter();
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(
                    new javax.xml.transform.stream.StreamSource("src/main/resources/transform.xsl"));
            transformer.setParameter("TRANSFORMATION_DATETIME", Instant.now().toString());

            transformer.transform(
                    new javax.xml.transform.stream.StreamSource(reader),
                    new javax.xml.transform.stream.StreamResult(writer));

            result = writer.toString();
            log.info("Transformed message: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}

