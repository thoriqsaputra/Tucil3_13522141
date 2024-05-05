package com.keren.solver;
import java.util.*;

public class Solver {

    private static class Node implements Comparable<Node> {
        String word;
        Node parent;
        int cost;

        public Node(String word, Node parent, int cost) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(cost, other.cost);
        }
    }

    static int heuristic(String word, String endWord){
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != endWord.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static Ladder ucsSolver(String startWord, String endWord, Set<String> validWords) {
        // Priority queue untuk menyimpan node yang akan di proses
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // Menambahkan node dengan startWord ke queue
        queue.add(new Node(startWord, null, 0));
        
        // Set untuk menyimpan kata yang sudah di lewati
        Set<String> visited = new HashSet<>();
        // Menambahkan startWord ke visited
        visited.add(startWord);

        // Loop sampai queue kosong atau ditemukan endWord
        while (!queue.isEmpty()) {
            // Mengambil node dengan cost terkecil
            Node current = queue.poll();
            // Mengambil kata dari node
            String word = current.word;

            // Jika kata sama dengan endWord
            if (word.equals(endWord)) {
                // Membuat list untuk menyimpan ladder
                List<String> ladder = new ArrayList<>();
                // Mengambil node parent dari node current
                Node node = current;
                // Loop sampai node null dan menambahkan kata ke ladder
                while (node != null) {
                    ladder.add(0, node.word);
                    node = node.parent;
                }
                // Mengembalikan ladder berupa list kata dan jumlah kata yang di lewati
                return new Ladder(ladder, visited.size());
            }
            
            // Proses perubahan kata
            for (int i = 0; i < word.length(); i++) {
                // Mengubah kata dengan mengganti karakter ke-i dengan a-z
                char[] wordArray = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String nextWord = new String(wordArray);
                    // Jika kata valid dan belum di lewati maka tambahkan ke queue dengan parent cost + 1
                    // Tambahkan juga ke visited
                    if (validWords.contains(nextWord) && !visited.contains(nextWord)) {
                        int cost = current.cost + 1;
                        queue.add(new Node(nextWord, current, cost));
                        visited.add(nextWord);
                    }
                }
            }
        }
        // Jika tidak ditemukan ladder maka kembalikan ladder kosong
        return new Ladder(Collections.emptyList(), visited.size());
    }

    public static Ladder gbfsSolver(String startWord, String endWord, Set<String> validWords) {
        // Priority queue untuk menyimpan node yang akan di proses
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // Set untuk menyimpan kata yang sudah di lewati
        Set<String> visited = new HashSet<>();
        // Menambahkan node dengan startWord ke queue
        queue.add(new Node(startWord, null, 0));
        visited.add(startWord);

        Node currentNode = null;

        // Loop sampai queue kosong atau ditemukan endWord
        while (!queue.isEmpty()) {
            // Mengambil node dengan cost terkecil
            currentNode = queue.poll();
            String word = currentNode.word;
            // queue di clear untuk tidak mencegah backtracking
            queue.clear();
            // Jika kata sama dengan endWord
            if (word.equals(endWord)) {
                // Membuat list untuk menyimpan ladder
                List<String> ladder = new ArrayList<>();
                Node node = currentNode;
                // Loop sampai node null dan menambahkan kata ke ladder
                while (node != null) {
                    ladder.add(0, node.word);
                    node = node.parent;
                }
                // Mengembalikan ladder berupa list kata dan jumlah kata yang di lewati
                return new Ladder(ladder, visited.size());
            }

            // Proses perubahan kata
            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String nextWord = new String(wordArray);
                    // Jika kata valid dan belum di lewati maka tambahkan ke queue dengan cost berupa 1 + heuristic
                    // Tambahkan juga ke visited
                    if (validWords.contains(nextWord) && visited.add(nextWord)) {
                        int cost = heuristic(nextWord, endWord) + 1;
                        queue.add(new Node(nextWord, currentNode, cost));
                        visited.add(nextWord);
                    }
                }
            }
        }
        // Jika tidak ditemukan ladder maka kembalikan ladder kosong
        return new Ladder(Collections.emptyList(), visited.size());
    }

    public static Ladder aStarSolver(String startWord, String endWord, Set<String> validWords) {
        // Priority queue untuk menyimpan node yang akan di proses
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // Set untuk menyimpan kata yang sudah di lewati
        Set<String> visited = new HashSet<>();
        // Menambahkan node dengan startWord ke queue
        queue.add(new Node(startWord, null, 0));
        visited.add(startWord);

        // Loop sampai queue kosong atau ditemukan endWord
        while (!queue.isEmpty()) {
            // Mengambil node dengan cost terkecil
            Node currentNode = queue.poll();
            String word = currentNode.word;

            // Jika kata sama dengan endWord
            if (word.equals(endWord)) {
                // Membuat list untuk menyimpan ladder
                List<String> ladder = new ArrayList<>();
                Node node = currentNode;
                // Loop sampai node null dan menambahkan kata ke ladder
                while (node != null) {
                    ladder.add(0, node.word);
                    node = node.parent;
                }
                // Mengembalikan ladder berupa list kata dan jumlah kata yang di lewati
                return new Ladder(ladder, visited.size());
            }
            
            // Proses perubahan kata
            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                // Mengubah kata dengan mengganti karakter ke-i dengan a-z
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String nextWord = new String(wordArray);
                    // Jika kata valid dan belum di lewati maka tambahkan ke queue dengan cost berupa cost parent + 1 + heuristic
                    if (validWords.contains(nextWord) && visited.add(nextWord)) {
                        int cost = currentNode.cost + 1 + heuristic(nextWord, endWord);
                        queue.add(new Node(nextWord, currentNode, cost));
                        visited.add(nextWord);
                    }
                }
            }
        }
        // Jika tidak ditemukan ladder maka kembalikan ladder kosong
        return new Ladder(Collections.emptyList(), visited.size());
    }

}
