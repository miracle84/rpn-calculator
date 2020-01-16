package com.antonovweb.client;

import com.antonovweb.service.calculator.CalculatorService;
import com.antonovweb.service.operation.OperationService;
import com.antonovweb.service.stack.StackElementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Console client for calculator.
 */
@ShellComponent
public class ConsoleClient {
    private static final String ERROR_DURING_CALCULATION = "Error during calculation";
    private static final String ERROR_DURING_GETTING_AVAILABLE_OPERATIONS = "Error during getting available operations";
    private static final String ERROR_DURING_CLEAR_STACK = "Error during clearing stack";
    private static final String NUMBER_FORMAT = "#0.00";

    private final Logger log = LoggerFactory.getLogger(ConsoleClient.class);
    private final CalculatorService calculatorService;
    private final OperationService operationService;
    private final StackElementsService stackElementsService;
    private final NumberFormat decimalFormat;

    @Autowired
    public ConsoleClient(CalculatorService calculatorService, OperationService operationService, StackElementsService stackElementsService) {
        this.calculatorService = calculatorService;
        this.operationService = operationService;
        this.stackElementsService = stackElementsService;
        decimalFormat = new DecimalFormat(NUMBER_FORMAT);
    }

    @ShellMethod(value = "Execute user command", key = "exec")
    public String processInputData(@ShellOption("number or operation") String inputData) {
        log.info("User input: {}", inputData);
        try {
            return decimalFormat.format(calculatorService.processInputData(inputData));
        } catch (Exception e) {
            log.error("Error during calculation {}", e.getMessage());
            return ERROR_DURING_CALCULATION;
        }
    }

    @ShellMethod(value = "Show all available operations", key = "oper")
    public String availableOperations() {
        log.info("Get available operations");
        try {
            return operationService.getOperations().keySet().stream().reduce((s1, s2) -> s1 + ", " +s2)
                    .orElse("No available operations");
        } catch (Exception e) {
            log.error("Error during getting available operations");
            return ERROR_DURING_GETTING_AVAILABLE_OPERATIONS;
        }
    }

    @ShellMethod(value = "Clear stack", key = "clearstack")
    public String clearStack() {
        try {
            stackElementsService.clear();
        } catch (Exception e) {
            log.error("Error during clear stack");
            return ERROR_DURING_CLEAR_STACK;
        }
        return "Stack has been cleaned successfully!";
    }
}