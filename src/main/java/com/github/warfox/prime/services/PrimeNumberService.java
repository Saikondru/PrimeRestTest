package com.github.warfox.prime.services;

import com.github.warfox.prime.models.PrimeResponse;
import com.github.warfox.prime.utils.PrimeNumber;
import org.springframework.stereotype.Service;

@Service
public class PrimeNumberService {

    public PrimeResponse list(int limit) {
        return new PrimeResponse(limit, PrimeNumber.primeNumbers(limit));
    }

}
