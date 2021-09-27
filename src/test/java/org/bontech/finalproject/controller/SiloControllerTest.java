package org.bontech.finalproject.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.bontech.finalproject.FinalApplication;
import org.bontech.finalproject.model.dto.CreateSilo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinalApplication.class)
@WebAppConfiguration
class SiloControllerTest {


    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createSilo() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateSilo createSilo = new CreateSilo();
        createSilo.setName("test");
        createSilo.setMaximumCapacity(5000000000000000L);
        String json = null;

        json = objectMapper.writeValueAsString(createSilo);
        MvcResult result = mvc.perform(post("/silo/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue( content.contains("silo was created successfully"));

    }
}