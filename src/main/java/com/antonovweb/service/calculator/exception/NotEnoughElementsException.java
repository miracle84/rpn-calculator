package com.antonovweb.service.calculator.exception;

/**
 * Not enough elements to perform some operation.
 */
public class NotEnoughElementsException extends Exception {
    public NotEnoughElementsException(String s) {
        super(s);
    }
}
