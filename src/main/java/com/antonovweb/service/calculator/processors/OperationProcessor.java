package com.antonovweb.service.calculator.processors;

import com.antonovweb.service.calculator.exception.NotEnoughElementsException;
import com.antonovweb.service.calculator.models.ProcessDataDto;
import com.antonovweb.service.operation.OperationService;
import com.antonovweb.service.operation.operations.Operation;
import com.antonovweb.service.operation.operations.TwoParamOperation;
import com.antonovweb.service.stack.StackElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Processor for working with operations. Based on Double type.
 */
@Component
public class OperationProcessor implements Processor<Double> {
    private static final Logger log = LoggerFactory.getLogger(OperationProcessor.class);
    /**
     * Stack Service for storing data
     */
    private final StackElements<Double> stackService;
    /**
     * Service that provided available operations
     */
    private final OperationService operationService;
    /**
     * Should we write to log or not, if input data can't be processed by current processor.
     */
    private boolean writeLogForUnprocessed;

    @Autowired
    public OperationProcessor(StackElements<Double> stackService, OperationService operationService,
                           @Value("${processor.processor.logging.unprocessed:false}") boolean writeLogForUnprocessed) {
        this.stackService = stackService;
        this.operationService = operationService;
        this.writeLogForUnprocessed = writeLogForUnprocessed;
    }
    @Override
    public void process(ProcessDataDto<Double> processData) throws NotEnoughElementsException {
        if (operationService.getOperations().containsKey(processData.getInputData())) {
            Operation operation = operationService.getOperations().get(processData.getInputData());

            /**
            * Other interfaces may be process in other processor, or may be add common algorithm with getting
            * amount of params by reflection
            * Current checking need, because we can add operation with different interfaces.
             */
            if (operation instanceof TwoParamOperation) {
                if (stackService.size() < 2) {
                    log.error("Not enough elements in stack to perform operation");
                    throw new NotEnoughElementsException("Need 2, exists " + stackService.size());
                }
                Double param1 = stackService.pop();
                Double param2 = stackService.pop();
                Double value = ((TwoParamOperation<Double>) operation).execute(param2, param1);
                processData.setResultData(value);
                processData.setProcessed(true);
                log.info("Data {} has bee successfully processed by {}", processData, OperationProcessor.class);
                stackService.push(value);
                log.info("Data {} has been successfully added to stack", value);
            } else if (writeLogForUnprocessed) {
                log.error("Operation {} not found!", processData.getInputData());
            }
        } else if (writeLogForUnprocessed) {
            log.error("Operation {} not found!", processData.getInputData());
        }
    }
}