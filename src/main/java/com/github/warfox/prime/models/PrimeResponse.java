package com.github.warfox.prime.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "prime-response")
public class PrimeResponse {

    private Long limit;

    @JacksonXmlElementWrapper(localName = "primes")
    @JacksonXmlProperty(localName = "prime")
    private List<Number> primes;

    public PrimeResponse(long limit, List<Number> primes) {
        this.limit = limit;
        this.primes = primes;
    }

    public PrimeResponse() {
    }

    public Long getLimit() {
        return limit;
    }

    public List<Number> getPrimes() {
        return primes;
    }

}
