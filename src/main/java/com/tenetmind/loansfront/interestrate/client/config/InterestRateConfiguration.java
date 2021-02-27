package com.tenetmind.loansfront.interestrate.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InterestRateConfiguration {

    private final static String ENDPOINT = "interest_rates/";

    @Value("${source.endpoint}" + ENDPOINT)
    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

}
