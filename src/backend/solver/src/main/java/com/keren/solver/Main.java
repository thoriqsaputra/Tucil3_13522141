package com.keren.solver;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Set<String> validWords = function.getValidWords();
        Scanner scanner = new Scanner(System.in);
        String startWord;
        String endWord;
        Ladder ladder = null;
        int visitedWords = 0;
    
        while (true) {
            System.out.print("Enter start word: ");
            startWord = scanner.next();
            
            while(true){
                if(validWords.contains(startWord.toLowerCase())){
                    break;
                }
                System.out.println("Invalid word! Please enter a valid word.");
                System.out.print("Enter start word: ");
                startWord = scanner.next();
            }
            while(true){

                System.out.print("Enter end word: ");
                endWord = scanner.next();
                while(true){
                    if(validWords.contains(endWord.toLowerCase())){
                        break;
                    }
                    System.out.println("Invalid word! Please enter a valid word.");
                    System.out.print("Enter end word: ");
                    endWord = scanner.next();
                }
                if (startWord.length() == endWord.length()) {
                    break;
                } else {
                    System.out.println("Words must be of equal length!");
                }
            }

            System.out.println("Algorithms:");
            System.out.println("1. Uniform Cost Search");
            System.out.println("2. A* Search");
            System.out.println("3. Greedy Best First Search");
            System.out.println("Enter 0 to exit");
            System.out.print("Choose an algorithm: ");
            int choice = scanner.nextInt();
            
            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            startWord = startWord.toLowerCase();
            endWord = endWord.toLowerCase();

            long starttime = System.nanoTime();
            switch (choice) {
                case 1:
                ladder = Solver.ucsSolver(startWord, endWord, validWords);
                break;
                case 2:
                ladder = Solver.aStarSolver(startWord, endWord, validWords);
                break;
                case 3:
                ladder = Solver.gbfsSolver(startWord, endWord, validWords);
                break;
                default:
                System.out.println("Invalid choice!");
                    break;
            }
            long endtime = System.nanoTime();
            double time = (double) (endtime - starttime) / 1_000_000;
            
            List<String> ladderList = ladder.getLadder();
            visitedWords = ladder.getVisitedWords();

            System.out.println();
            if (ladderList.isEmpty()) {
                System.out.println("No ladder found!");
            } else {
                if(time > 60000){
                    System.out.println("Time taken: " + time / 60000 + " minutes");
                } else if (time > 1000){
                    System.out.println("Time taken: " + time / 1000 + " seconds");
                } else {
                    System.out.println("Time taken: " + time + " ms");
                
                }
                System.out.println("Length of ladder: " + ladderList.size());
                System.out.println("Visited words: " + visitedWords);
                System.out.println(String.join(" -> ", ladderList));
            }
            System.out.println();
        }
        scanner.close(); // Close the scanner after exiting the loop
    }
    
    
    
}
