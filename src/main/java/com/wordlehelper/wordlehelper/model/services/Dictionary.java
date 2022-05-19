package com.wordlehelper.wordlehelper.model.services;

import com.wordlehelper.wordlehelper.WordleHelper;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

// Credit: https://github.com/dwyl/english-words
public class Dictionary {
    private final HashMap<String, Double> dictionary = new HashMap<>();

    public Dictionary() throws IOException {
        InputStream in = Dictionary.class.getResourceAsStream("word_frequencies.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in)));
        String line = reader.readLine();
        while (line != null) {
            String[] currentLine = line.split(":", 2);
            String word = currentLine[0];
            Double frequency = Double.parseDouble(currentLine[1]);
            dictionary.put(word, frequency);
            line = reader.readLine();
        }
        in.close();
        reader.close();
    }

    public boolean isInDictionary(String word) {
        return dictionary.containsKey(word);
    }

    public Double getFrequency(String word) {
        return dictionary.get(word);
    }

}
