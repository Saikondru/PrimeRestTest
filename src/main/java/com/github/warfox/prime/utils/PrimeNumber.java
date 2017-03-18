package com.github.warfox.prime.utils;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

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
        } else if (num <= 3) {
            return true;
        } else if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        int i = 5;
        while (i * i <= num) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
            i = i + 6;
        }
        return true;
    }

}
