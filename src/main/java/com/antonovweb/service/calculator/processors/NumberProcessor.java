package com.antonovweb.service.calculator.processors;

import com.antonovweb.service.calculator.models.ProcessDataDto;
import com.antonovweb.service.stack.StackElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Processor for work with double data(special case for number, but for other type, you must create another processor,
 * because logic of processing may be different)
 */
@Component
public class NumberProcessor implements Processor<Double> {
    private static final Logger log = LoggerFactory.getLogger(NumberProcessor.class);
    /**
     * Stack Service for storing data
     */
    private final StackElements<Double> stackService;
    /**
     * Should we write to log or not, if input data can't be processed by current processor.
     */
    private boolean writeLogForUnprocessed;

    @Autowired
    public NumberProcessor(StackElements<Double> stackService,
                           @Value("${processor.number.logging.unprocessed:false}") boolean writeLogForUnprocessed) {
        this.stackService = stackService;
        this.writeLogForUnprocessed = writeLogForUnprocessed;
    }

    @Override
    public void process(ProcessDataDto<Double> processData) {
        try {
            Double value = Double.parseDouble(processData.getInputData());
            processData.setResultData(value);
            processData.setProcessed(true);
            log.info("Data {} has bee successfully processed by {}", processData, NumberProcessor.class);
            stackService.push(value);
            log.info("Data {} has been successfully added to stack", value);
        } catch (Exception e) {
            if (writeLogForUnprocessed) {
                log.error("Can not process data {} by {}", processData, NumberProcessor.class);
            }
        }
    }
}
