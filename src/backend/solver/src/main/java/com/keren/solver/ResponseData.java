package com.keren.solver;

public class ResponseData {
    private String startWord;
    private String endWord;
    private String algorithm;

    public ResponseData() {}

    public ResponseData(String startWord, String endWord, String algorithm) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.algorithm = algorithm;
    }

    public String getStartWord() {
        return startWord;
    }

    public void setStartWord(String startWord) {
        this.startWord = startWord;
    }

    public String getEndWord() {
        return endWord;
    }

    public void setEndWord(String endWord) {
        this.endWord = endWord;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
