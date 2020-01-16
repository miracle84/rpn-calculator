package com.antonovweb.service.operation.operations;

import org.springframework.stereotype.Component;

/**
 * Subtraction operation for 2 Double params.
 */
@Component
public class Subtraction implements TwoParamOperation<Double> {
    private final static String ABBREVIATION = "-";

    public Double execute(Double param1, Double param2) {
        return param1 - param2;
    }

    @Override
    public String getAbbreviation() {
        return ABBREVIATION;
    }
}
