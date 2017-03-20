package com.github.warfox.prime.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumber {

    // memoization to avoid re-calculation of already known primes
    // initialized with first 10 prime numbers for optimization
    private static List<Number> PRIMES = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));

    public static List<Number> primeNumbers(int number) {
        List<Number> primes = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static boolean isPrime(int num) {
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
