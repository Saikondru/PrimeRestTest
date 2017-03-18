package com.github.warfox.prime.controllers;

import com.github.warfox.prime.models.PrimeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeControllerIntegrationTest {

    @Inject
    private TestRestTemplate restTemplate;

    @Inject
    private MockMvc mockMvc;

    @Test
    public void testPrimesDefaultEndPoint() throws Exception {
        mockMvc.perform(get("/primes/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":10,\"primes\":[2,3,5,7]}"));
    }

    @Test
    public void testPrimesEndPointWithLimit() throws Exception {
        mockMvc.perform(get("/primes/20").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":20,\"primes\":[2,3,5,7,11,13,17,19]}"));
    }

    @Test
    public void testDefaultMediaTypeIsApplicationJson() throws Exception {
        mockMvc.perform(get("/primes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testShouldProduceXMLWhenAcceptHeaderIsXML() throws Exception {
        mockMvc.perform(get("/primes").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(content().string("<prime-response><limit>10</limit><primes><prime>2</prime><prime>3</prime><prime>5</prime><prime>7</prime></primes></prime-response>"));
    }

    @Test
    public void testProducesXMLWhenPathExtensionIsXML() throws Exception {
        mockMvc.perform(get("/primes/5.xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(content().string("<prime-response><limit>5</limit><primes><prime>2</prime><prime>3</prime><prime>5</prime></primes></prime-response>"));
    }

    @Test
    public void testProducesXMLWhenMediaTypeParameterIsXML() throws Exception {
        mockMvc.perform(get("/primes/5?mediaType=xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(content().string("<prime-response><limit>5</limit><primes><prime>2</prime><prime>3</prime><prime>5</prime></primes></prime-response>"));
    }

    @Test
    public void testProducesJsonWhenPathExtensionIsJson() throws Exception {
        mockMvc.perform(get("/primes/5.json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":5,\"primes\":[2,3,5]}"));
    }

    @Test
    public void testProducesJsonWhenMediaTypeParameterIsJson() throws Exception {
        mockMvc.perform(get("/primes/5?mediaType=json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"limit\":5,\"primes\":[2,3,5]}"));
    }

    @Test
    public void testResultShouldBeDeserializableFromJSON() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Map> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<PrimeResponse> exchange = restTemplate
                .exchange("/primes", HttpMethod.GET, requestEntity, PrimeResponse.class);
        assertThat(exchange.getBody().getLimit()).isEqualTo(10);
        assertThat(exchange.getBody().getPrimes()).containsExactly(2, 3, 5, 7);
    }

    @Test
    public void testResultShouldBeDeserializableFromXML() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        ResponseEntity<PrimeResponse> exchange = restTemplate
                .exchange("/primes/20", HttpMethod.GET, new HttpEntity<>(headers), PrimeResponse.class);
        assertThat(exchange.getBody().getLimit()).isEqualTo(20);
        assertThat(exchange.getBody().getPrimes()).containsExactly(2, 3, 5, 7, 11, 13, 17, 19);
    }

}
