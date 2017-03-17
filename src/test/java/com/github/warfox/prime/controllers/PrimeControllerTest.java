package com.github.warfox.prime.controllers;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.services.PrimeNumberService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PrimeControllerTest {

    @Test
    public void testDefaultListShouldBeTill10() throws Exception {
        PrimeNumberService primeNumberServiceSpy = spy(PrimeNumberService.class);
        PrimeController primeController = new PrimeController(primeNumberServiceSpy);
        PrimeResponse primeResponse = primeController.list();
        verify(primeNumberServiceSpy).list(10);
        assertThat(primeResponse.getLimit()).isEqualTo(10);
        assertThat(primeResponse.getPrimes()).containsExactly(2, 3, 5, 7);

        verify(primeNumberServiceSpy).list(10);
    }

    @Test
    public void testPrimeWithLimit() throws Exception {
        PrimeNumberService primeNumberServiceSpy = spy(PrimeNumberService.class);
        PrimeController primeController = new PrimeController(primeNumberServiceSpy);
        PrimeResponse primeResponse = primeController.list(20);
        verify(primeNumberServiceSpy).list(20);
        assertThat(primeResponse.getLimit()).isEqualTo(20);
        assertThat(primeResponse.getPrimes()).containsExactly(2, 3, 5, 7, 11, 13, 17, 19);
    }

}
