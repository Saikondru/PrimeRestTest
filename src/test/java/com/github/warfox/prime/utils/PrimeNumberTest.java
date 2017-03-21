package com.github.warfox.prime.utils;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumberTest {

    @Test
    public void test5isPrime() throws Exception {
        assertThat(PrimeNumber.isPrime(5)).isTrue();
    }

    @Test
    public void test3isPrime() throws Exception {
        assertThat(PrimeNumber.isPrime(3)).isTrue();
    }

    @Test
    public void test10isNotPrime() throws Exception {
        assertThat(PrimeNumber.isPrime(10)).isFalse();
    }

    @Test
    public void testPrimeNumbersTill10() throws Exception {
        List<Integer> primes = PrimeNumber.primeNumbers(10);
        assertThat(primes)
                .hasSize(4)
                .containsExactly(2, 3, 5, 7);
    }

    @Test
    public void testPrimeNumbersTill100() throws Exception {
        List<Integer> primes = PrimeNumber.primeNumbers(110);
        assertThat(primes).containsExactly(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109);
    }

    @Test
    public void testPrimeNumbersListShouldIncludeTheNumberIfItIsPrime() throws Exception {
        List<Integer> primes = PrimeNumber.primeNumbers(7);
        assertThat(primes)
                .hasSize(4)
                .containsExactly(2, 3, 5, 7);
    }

    @Test
    public void testNegativeNumbersAreNotPrime() throws Exception {
        assertThat(PrimeNumber.isPrime(-1)).isFalse();
        assertThat(PrimeNumber.isPrime(-2)).isFalse();
        assertThat(PrimeNumber.isPrime(-3)).isFalse();
        assertThat(PrimeNumber.isPrime(-7)).isFalse();
    }

    @Test
    public void testPrimeNumbersListEmptyForZero() throws Exception {
        assertThat(PrimeNumber.primeNumbers(0)).isEmpty();
    }

    @Test
    public void testPrimeNumbersListEmptyForNegativeNumbers() throws Exception {
        assertThat(PrimeNumber.primeNumbers(-1)).isEmpty();
        assertThat(PrimeNumber.primeNumbers(-2)).isEmpty();
    }

}
