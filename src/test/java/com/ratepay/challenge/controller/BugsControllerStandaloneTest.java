package com.ratepay.challenge.controller;

import com.google.gson.Gson;
import com.ratepay.challenge.entity.Bug;
import com.ratepay.challenge.exception.BadRequestException;
import com.ratepay.challenge.exception.ResourceNotFoundException;
import com.ratepay.challenge.model.enums.Priority;
import com.ratepay.challenge.model.enums.Status;
import com.ratepay.challenge.service.serviceInterface.BugsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BugsControllerStandaloneTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BugsService bugsService;

    @BeforeEach
    public void init() {
       bugsService.deleteAll();
    }


    @Test
    public void shouldNotReturnBugs() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/bugs")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void shouldValidateBugEmptyTitle() throws Exception {
        Bug bug = new Bug();

        bug.setTitle("");
        bug.setDescription("Bug Description");
        bug.setStatus(Status.NEW);
        bug.setPriority(Priority.MINOR);

        Gson gson = new Gson();
        String json = gson.toJson(bug);


        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/bugs/")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                .andExpect(result -> assertEquals("Mandatory field title is missing", result.getResolvedException().getMessage()));


    }

    public void shouldValidateBugNullTitle() throws Exception {
        Bug bug = new Bug();

        bug.setDescription("Bug Description");
        bug.setStatus(Status.NEW);
        bug.setPriority(Priority.MINOR);

        Gson gson = new Gson();
        String json = gson.toJson(bug);


        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/bugs/")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                .andExpect(result -> assertEquals("Mandatory field title is missing", result.getResolvedException().getMessage()));


    }

}
