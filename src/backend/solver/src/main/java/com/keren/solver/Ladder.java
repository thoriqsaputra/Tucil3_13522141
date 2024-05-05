package com.keren.solver;

import java.util.*;

public class Ladder {
    private List<String> ladder;
    private int visitedWords;

    public Ladder(List<String> ladder, int visitedWords) {
        this.ladder = ladder;
        this.visitedWords = visitedWords;
    }

    public List<String> getLadder() {
        return ladder;
    }

    public int getVisitedWords() {
        return visitedWords;
    }
}
