package com.norriswu.codetestbundlecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void whenRunMain_thenSayHelloWorld() {
        String actualResponse = Main.greeting("Norris");
        String expectedResponse = "Hello Norris!";
        Assertions.assertEquals(actualResponse, expectedResponse);
    }
}
