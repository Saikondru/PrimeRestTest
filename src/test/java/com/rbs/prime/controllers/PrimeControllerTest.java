package com.rbs.prime.controllers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeControllerTest {

    @Test
    public void testDefaultListShouldBeTill10() throws Exception {
        PrimeController primeController = new PrimeController();
        assertThat(primeController.list()).containsExactly(2, 3, 5, 7);
    }

    @Test
    public void testPrimeWithLimit() throws Exception {
        PrimeController primeController = new PrimeController();
        assertThat(primeController.list(20)).containsExactly(2, 3, 5, 7, 11, 13, 17, 19);
    }

}
