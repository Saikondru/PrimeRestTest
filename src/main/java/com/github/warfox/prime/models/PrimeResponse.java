package com.github.warfox.prime.models;

import java.util.List;

public class PrimeResponse {

    private Long limit;

    private List<Number> primes;

    public PrimeResponse(long limit, List<Number> primes) {
        this.limit = limit;
        this.primes = primes;
    }

    public Long getLimit() {
        return limit;
    }

    public List<Number> getPrimes() {
        return primes;
    }

}
