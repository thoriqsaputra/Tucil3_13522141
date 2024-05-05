package com.keren.solver;

import java.util.List;

public class Result {
    private List<String> processedList;
    private double processingTimeMillis;
    private String metric;
    private int visitedWords;

    public Result(List<String> processedList, double processingTimeMillis, String metric, int visitedWords) {
        this.processedList = processedList;
        this.processingTimeMillis = processingTimeMillis;
        this.metric = metric;
        this.visitedWords = visitedWords;
    }

    public String getMetric() {
        return metric;
    }

    public List<String> getProcessedList() {
        return processedList;
    }

    public double getProcessingTimeMillis() {
        return processingTimeMillis;
    }

    public int getVisitedWords() {
        return visitedWords;
    }
}