package com.antonovweb.service.operation;

import com.antonovweb.service.operation.operations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service to work with operations.
 * Main responsibility get available operation in one place.
 * Operation aggregate by root interface Operation, NOT by TwoParamOperation, it gives possibility to us to add
 * new operations with different interface - for example with ThreeParamOperation without rewriting this code.
 */
@Service
public class OperationService {
    /**
     * Map of available operation
     * Example "+" => additionOperationBean, "-" => subtractionOperationBean,...
     */
    private final Map<String, Operation> operations;

    @Autowired
    public OperationService(List<Operation> operationList) {
        this.operations = operationList.stream().collect(Collectors.toMap(Operation::getAbbreviation, Function.identity()));
    }

    public Map<String, Operation> getOperations() {
        return operations;
    }
}
