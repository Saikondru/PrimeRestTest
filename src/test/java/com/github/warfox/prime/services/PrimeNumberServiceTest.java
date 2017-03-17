package com.github.warfox.prime.services;

import com.github.warfox.prime.models.PrimeResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumberServiceTest {

    @Test
    public void testList2() throws Exception {
        PrimeNumberService primeNumberService = new PrimeNumberService();
        PrimeResponse primeResponse = primeNumberService.list(2);
        assertThat(primeResponse.getLimit()).isEqualTo(2);
        assertThat(primeResponse.getPrimes()).containsExactly(2);
    }

    @Test
    public void testList10() throws Exception {
        PrimeNumberService primeNumberService = new PrimeNumberService();
        PrimeResponse primeResponse = primeNumberService.list(10);
        assertThat(primeResponse.getLimit()).isEqualTo(10);
        assertThat(primeResponse.getPrimes()).containsExactly(2, 3, 5, 7);
    }

}
