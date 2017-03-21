package com.github.warfox.prime.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Response wrapper object, that can be serialized
 * and deserialized to/from xml and json
 */
@JacksonXmlRootElement(localName = "prime-response")
public class PrimeResponse {

    private Long limit;

    @JacksonXmlElementWrapper(localName = "primes")
    @JacksonXmlProperty(localName = "prime")
    private List<Integer> primes;

    public PrimeResponse(long limit, List<Integer> primes) {
        this.limit = limit;
        this.primes = primes;
    }

    public PrimeResponse() {
    }

    public Long getLimit() {
        return limit;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

}
