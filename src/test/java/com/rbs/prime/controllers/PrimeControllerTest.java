package com.rbs.prime.controllers;

import com.rbs.prime.models.PrimeResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeControllerTest {

    @Test
    public void testDefaultListShouldBeTill10() throws Exception {
        PrimeController primeController = new PrimeController();
        PrimeResponse primeResponse = primeController.list();
        assertThat(primeResponse.getLimit()).isEqualTo(10);
        assertThat(primeResponse.getPrimes()).containsExactly(2, 3, 5, 7);
    }

    @Test
    public void testPrimeWithLimit() throws Exception {
        PrimeController primeController = new PrimeController();
        PrimeResponse primeResponse = primeController.list(20);
        assertThat(primeResponse.getLimit()).isEqualTo(20);
        assertThat(primeResponse.getPrimes()).containsExactly(2, 3, 5, 7, 11, 13, 17, 19);
    }

}
