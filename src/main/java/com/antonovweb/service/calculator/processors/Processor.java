package com.antonovweb.service.calculator.processors;

import com.antonovweb.service.calculator.exception.NotEnoughElementsException;
import com.antonovweb.service.calculator.models.ProcessDataDto;

/**
 * Marker for processors, and show it interface.
 * Processor contains logic for communication between input data and operations.
 * Processor using parser internally for parse data and logic how data should be mapped to operations or what we should
 * do with simple numbers(add to stack, to some checking or something else).
 * Parser logic may be separate from processor.
 *
 * @param <T>
 */
public interface Processor<T> {
    void process(ProcessDataDto<T> inputData) throws NotEnoughElementsException;
}
