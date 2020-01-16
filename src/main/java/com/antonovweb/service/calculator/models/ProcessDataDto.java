package com.antonovweb.service.calculator.models;

/**
 * Dto for storing input date during processing and showing it(data) status.
 * @param <T>
 */
public class ProcessDataDto<T> {
    /**
     * Is data completely processed - several processors can update resultData, before this flag is false,
     * after it will be set to true, processing must be finished.
     */
    private Boolean processed;
    /**
     * Input data - data, which must be processed.
     */
    private String inputData;
    /**
     * Result after processing - may be the same data for numbers or operation result, or some other results.
     */
    private T resultData;

    public ProcessDataDto(String inputData) {
        this.inputData = inputData;
        processed = false;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public ProcessDataDto<T> setProcessed(Boolean processed) {
        this.processed = processed;
        return this;
    }

    public String getInputData() {
        return inputData;
    }

    public ProcessDataDto<T> setInputData(String inputData) {
        this.inputData = inputData;
        return this;
    }

    public T getResultData() {
        return resultData;
    }

    public ProcessDataDto<T> setResultData(T resultData) {
        this.resultData = resultData;
        return this;
    }
}
