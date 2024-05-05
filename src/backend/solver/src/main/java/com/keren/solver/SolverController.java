package com.keren.solver;

import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@RestController
public class SolverController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(SolverController.class);


    @PostMapping("/validword")
    public Valid validWord(@RequestBody String word) {
        Set<String> validWords = function.getValidWords();
        logger.info("Valid Words: " + word);
        logger.info("Valid Words: " + validWords.contains(word));
        logger.info("Valid Words: " + word.length());
        word = word.replace("\"", "");   
        word = word.toLowerCase();     logger.info("Valid Words: " + word);
        logger.info("Valid Words: " + word.length());
        logger.info("Valid Words: " + validWords.contains(word));
        return new Valid(validWords.contains(word));
    }

    @PostMapping("/wlsolver")
    public Result postMethodName(@RequestBody ResponseData data) {
        String startWord = data.getStartWord().toLowerCase();
        String endWord = data.getEndWord().toLowerCase();
        String algorithm = data.getAlgorithm();
        
        Result newResult = findPath(startWord, endWord, algorithm);
        logger.info("Result: " + newResult.getProcessedList());
        logger.info("Processing Time: " + newResult.getProcessingTimeMillis() + "ms");
        
        return newResult;
    }

    public Result findPath(String startWord, String endWord, String algorithm) {
        Set<String> validWords = function.getValidWords();
        Ladder ladder = null;
        List<String> laddList = null;
        int visitedWords = 0;
        String metric;
        double startTime = System.nanoTime();
        switch (algorithm) {
            case "ucs":
                ladder = Solver.ucsSolver(startWord, endWord, validWords);
                break;
            case "astar":
                ladder = Solver.aStarSolver(startWord, endWord, validWords);
                break;
            case "gbfs":
                ladder = Solver.gbfsSolver(startWord, endWord, validWords);
                break;
        }
        double endTime = System.nanoTime();
        double processingTimeMillis = (endTime - startTime) / 1_000_000;
        laddList = ladder.getLadder();
        if (processingTimeMillis > 60000) {
            metric = "minutes";
            processingTimeMillis /= 60000;
        } else if (processingTimeMillis > 1000) {
            metric = "seconds";
            processingTimeMillis /= 1000;
        } else {
            metric = "milliseconds";
        } 
        visitedWords = ladder.getVisitedWords();

        return new Result(laddList, processingTimeMillis, metric, visitedWords);
    }
    
}
