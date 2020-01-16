package com.antonovweb.service.stack;

/**
 * Simple interface for stack
 * @param <T>
 */
public interface StackElements<T> {
    /**
     * Add to end
     * @return true if success
     */
    boolean push(T element);

    /**
     * Get and remove last
     */
    T pop();

    /**
     * Get last
     */
    T last();

    /**
     * @return amount of elemts in stack
     */
    int size();
    /**
     * Clear all
     */
    void clear();
}
