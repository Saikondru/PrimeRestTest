package com.github.warfox.prime.controllers;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.services.PrimeNumberService;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    public void testSetCacheControlHeader() throws Exception {
        PrimeNumberService primeNumberServiceSpy = mock(PrimeNumberService.class);
        PrimeController primeController = new PrimeController(primeNumberServiceSpy);
        HttpServletResponse httpServletResponseSpy = spy(HttpServletResponse.class);
        primeController.setCacheControlHeader(httpServletResponseSpy);
        verify(httpServletResponseSpy).addHeader("Cache-Control", "max-age=31536000");
    }

}
