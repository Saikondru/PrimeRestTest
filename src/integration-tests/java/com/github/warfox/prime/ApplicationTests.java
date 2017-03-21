package com.github.warfox.prime;

import com.github.warfox.prime.controllers.IndexController;
import com.github.warfox.prime.controllers.PrimeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Inject
    private PrimeController primeController;

    @Inject
    private IndexController indexController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(primeController).isNotNull();
        assertThat(indexController).isNotNull();
    }

}
