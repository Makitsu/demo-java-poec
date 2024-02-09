package com.jmd.poec.java.demojpa2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class WilderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_mvc_post() throws Exception {
        //GIVEN
        //WHEN
        //THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/wilders")
                        .content(asJsonString(new Wilder(null, "TEST", "TEST", "TEST")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}