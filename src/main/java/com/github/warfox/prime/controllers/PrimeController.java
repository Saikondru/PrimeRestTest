package com.github.warfox.prime.controllers;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.services.PrimeNumberService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@EnableAutoConfiguration
public class PrimeController {

    @Inject
    private PrimeNumberService primeNumberService;

    public PrimeController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @RequestMapping(value = "/primes")
    public PrimeResponse list() {
        return list(10);
    }

    @RequestMapping(value = "/primes/{limit}")
    public PrimeResponse list(@PathVariable int limit) {
        return primeNumberService.list(limit);
    }

}
