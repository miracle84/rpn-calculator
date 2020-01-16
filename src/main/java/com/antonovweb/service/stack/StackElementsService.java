package com.antonovweb.service.stack;

import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Stack simple implementation.
 * May be change for some another more powerful service in future for example.
 */
@Service
public class StackElementsService implements StackElements<Double> {
    private LinkedList<Double> list;

    public StackElementsService() {
        list = new LinkedList<>();
    }

    @Override
    public boolean push(Double element) {
        return list.add(element);
    }

    @Override
    public Double pop() {
        return list.removeLast();
    }

    @Override
    public Double last() {
        return list.getLast();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }
}
