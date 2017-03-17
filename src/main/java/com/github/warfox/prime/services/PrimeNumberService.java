package com.github.warfox.prime.services;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.utils.PrimeNumber;

public class PrimeNumberService {

    public PrimeResponse list(int limit) {
        return new PrimeResponse(limit, PrimeNumber.primeNumbers(limit));
    }

}
