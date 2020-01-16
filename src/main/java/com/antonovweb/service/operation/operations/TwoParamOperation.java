package com.antonovweb.service.operation.operations;

/**
 * Interface for 2 params operations
 */
public interface TwoParamOperation<T> extends Operation {
    T execute(T param1, T param2);
}
