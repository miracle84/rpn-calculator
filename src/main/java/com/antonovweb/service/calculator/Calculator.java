package com.antonovweb.service.calculator;

/**
 * Basic interface for calculator service
 * @param <T>
 */
public interface Calculator<T> {
    T processInputData(String inputData) throws Exception;
}
