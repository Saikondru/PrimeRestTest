package com.rbs.prime;

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

    public static boolean isPrime(int i) {
        int counter = 0;
        for (int num = i - 1; num >= 1; num--) {
            if (i % num == 0) {
                counter++;
            }
        }
        return (counter == 1);
    }

}
