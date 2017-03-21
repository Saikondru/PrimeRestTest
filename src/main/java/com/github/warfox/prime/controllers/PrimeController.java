package com.github.warfox.prime.controllers;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.services.PrimeNumberService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
public class PrimeController {

    @Inject
    private PrimeNumberService primeNumberService;

    public PrimeController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @RequestMapping(value = "/primes")
    @ResponseBody
    public PrimeResponse list() {
        return list(10);
    }

    @RequestMapping(value = "/primes/{limit}")
    @ResponseBody
    public PrimeResponse list(@PathVariable int limit) {
        return primeNumberService.list(limit);
    }

    @ModelAttribute
    public void setCacheControlHeader(HttpServletResponse response) {
        String headerValue = CacheControl.maxAge(365, TimeUnit.DAYS).getHeaderValue();
        response.addHeader(HttpHeaders.CACHE_CONTROL, headerValue);
    }

}
