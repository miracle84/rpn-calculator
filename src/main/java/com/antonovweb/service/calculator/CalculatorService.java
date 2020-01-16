package com.antonovweb.service.calculator;

import com.antonovweb.service.calculator.exception.DataCanNotBeProcessed;
import com.antonovweb.service.calculator.models.ProcessDataDto;
import com.antonovweb.service.calculator.exception.NotEnoughElementsException;
import com.antonovweb.service.calculator.processors.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Calculator service.
 * Common responsibilities:
 * 1. Get user data.
 * 2. Processing data with processors(internal parse and appropriate logic implementation)
 * 3. Return value.
 */
@Component
public class CalculatorService implements Calculator<Double> {
    private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    /**
     * List of processors for working with user input data.
     */
    private final List<Processor<Double>> processors;

    @Autowired
    public CalculatorService(List<Processor<Double>> processors) {
        this.processors = processors;
    }

    /**
     * Process input data, by using processors, if processor appropriate for input data,
     * than data will be processed by it. Processor knows, itself is it appropriate for data or not.
     * You can easily add new processors with out changing old working code.
     *
     * @param inputData data from user input
     * @return result of processing
     * @throws DataCanNotBeProcessed - exception if no one processor can process specific user input, or it can be
     * thrown by some processor(for example - incorrect format data, not found operation and so on.).
     */
    public Double processInputData(String inputData) throws DataCanNotBeProcessed, NotEnoughElementsException {
        log.info("process input data {}", inputData);

        ProcessDataDto<Double> processDataDto = new ProcessDataDto<>(inputData);
        for (Processor<Double> processor : processors) {
            log.info("Try to process by {}", processor);
            try {
                processor.process(processDataDto);
            } catch (NotEnoughElementsException e) {
                log.error("Error during process input {}", e.getMessage());
                throw new NotEnoughElementsException(e.getMessage());
            }

            if (processDataDto.getProcessed()) {
                break;
            }
        }

        if (!processDataDto.getProcessed()) {
            log.error("Error during processing input data");
            throw new DataCanNotBeProcessed(inputData);
        }

        return processDataDto.getResultData();
    }
}