package com.github.warfox.prime.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumber {

    /**
     * memoization to avoid re-calculation of already known primes
     * initialized with first 10 prime numbers for optimization
     */
    private static List<Number> PRIMES = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));

    /**
     * For performance optimization this method skips all even numbers
     * 2 is the only even prime number
     *
     * @param number
     * @return - a list of prime numbers till and including number
     */
    public static List<Integer> primeNumbers(int number) {
        List<Integer> primes = new ArrayList<>();
        if (number > 1) {
            primes.add(2);
        }
        for (int i = 3; i <= number; i += 2) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    /**
     * Returns true if a given number is Prime
     *
     * @param num - number to be tested for primality
     * @return - true if num is prime, else false
     */
    public static boolean isPrime(int num) {
        // early return for obvious conditions
        if (num <= 1) {
            return false;
        } else if (PRIMES.contains(num)) {
            return true;
        } else if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        int i = 5;
        while (i * i <= num) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        PRIMES.add(num);
        return true;
    }

}
