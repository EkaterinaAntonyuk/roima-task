package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void transformXMLTest() throws Exception {
        String inputXML = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/data.xml"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        mvc.perform(post("/transform")
                .contentType(MediaType.APPLICATION_XML).content(inputXML))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("order/orderId").string("123_7"))
                .andExpect(xpath("order/orderRows/orderRow/rowNumber").string("1"))
                .andExpect(xpath("order/orderRows/orderRow/description").string("text1"));
    }
}
