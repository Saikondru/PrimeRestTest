package com.github.warfox.prime.models;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeResponseTest {

    @Test
    public void testGetLimit() throws Exception {
        PrimeResponse primeResponse = new PrimeResponse(10, null);
        assertThat(primeResponse.getLimit()).isEqualTo(10);
    }

    @Test
    public void testGetPrimes() throws Exception {
        PrimeResponse primeResponse = new PrimeResponse(1, Arrays.asList(1, 2, 3, 4));
        assertThat(primeResponse.getLimit()).isEqualTo(1);
        assertThat(primeResponse.getPrimes()).containsExactly(1,2,3,4);
    }

}
