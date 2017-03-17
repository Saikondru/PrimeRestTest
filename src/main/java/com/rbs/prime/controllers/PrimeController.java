package com.rbs.prime.controllers;

import com.rbs.prime.PrimeNumber;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class PrimeController {

    @RequestMapping(value = "/primes")
    public List<Number> list() {
        return PrimeNumber.primeNumbers(10);
    }

}
