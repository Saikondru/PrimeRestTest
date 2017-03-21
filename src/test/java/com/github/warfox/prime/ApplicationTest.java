package com.github.warfox.prime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpringApplication.class)
public class ApplicationTest {

    @Test
    public void testCanCreateNewInstance() throws Exception {
        new Application();
    }

    @Test
    public void testMainInvokesSpringApplicationRun() throws Exception {
        PowerMockito.mockStatic(SpringApplication.class);
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
        String[] args = {"arg1", "arg2"};
        when(SpringApplication.run(Application.class, args)).thenReturn(context);

        Application.main(args);
        PowerMockito.verifyStatic(Mockito.times(1));
        SpringApplication.run(Application.class, args);
        PowerMockito.verifyStatic();
    }

}
