package com.keren.solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;

public class function {
    // kamus kata yang valid
    private static final String DICTIONARY_FILE = "/engDic.txt";

    public static Set<String> getValidWords() {
        // Menyimpan kata-kata yang valid pada set
        Set<String> validWords = new HashSet<>();
        // Membaca file kamus kata
        // Menggunkan getResourceAsStream untuk membaca file dari resources
        try (InputStream inputStream = function.class.getResourceAsStream(DICTIONARY_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            // Membaca file baris per baris yang kemudian dimasukkan ke dalam set
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/n");
                if (parts.length > 0) {
                    validWords.add(parts[0].toLowerCase().trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Mengembalikan set kata-kata yang valid
        return validWords;
    }
}
