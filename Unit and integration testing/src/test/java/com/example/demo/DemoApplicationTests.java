package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void contextLoads() {
    }

    @Test
    void itShouldAddTwoNumbers() {
        //given
        int numberOne = 20;
        int numberTwo = 30;

        //when
        int sum = underTest.add(numberOne, numberTwo);
        int expected = numberOne + numberTwo;

        assertThat(sum).
                isEqualTo(expected);
    }

    class Calculator {
        int add(int a, int b) {
            return a * b;
        }
    }
}
