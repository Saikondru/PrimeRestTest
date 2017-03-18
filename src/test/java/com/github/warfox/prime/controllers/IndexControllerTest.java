package com.github.warfox.prime.controllers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexControllerTest {

    @Test
    public void testIndex() throws Exception {
        String index = new IndexController().index();
        assertThat(index).isEqualTo("index");
    }

}
