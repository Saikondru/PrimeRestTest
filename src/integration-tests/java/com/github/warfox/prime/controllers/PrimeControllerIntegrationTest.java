package com.github.warfox.prime.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimeController.class)
public class PrimeControllerIntegrationTest {

    @Inject
    private MockMvc mvc;

    @Test
    public void testPrimesDefaultEndPoint() throws Exception {
        this.mvc.perform(get("/primes/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":10,\"primes\":[2,3,5,7]}"));
    }

    @Test
    public void testPrimesEndPointWithLimit() throws Exception {
        this.mvc.perform(get("/primes/20").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":20,\"primes\":[2,3,5,7,11,13,17,19]}"));
    }

}
