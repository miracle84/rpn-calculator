package com.antonovweb.service.calculator.exception;

/**
 * Exception for situation when we can process input data for some reasons.
 */
public class DataCanNotBeProcessed extends Exception {
    public DataCanNotBeProcessed(String message) {
        super(message);
    }
}
