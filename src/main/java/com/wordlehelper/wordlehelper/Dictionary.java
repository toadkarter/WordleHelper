package com.wordlehelper.wordlehelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Credit: https://github.com/dwyl/english-words
public class Dictionary {
    private final HashMap<String, Double> dictionary = new HashMap<>();

    public Dictionary() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("src/word_frequencies.txt"));
        while (textFile.hasNext()) {
            String[] currentLine = textFile.next().split(":", 2);
            String word = currentLine[0];
            Double frequency = Double.parseDouble(currentLine[1]);
            dictionary.put(word, frequency);
        }
        textFile.close();
    }

    public boolean isInDictionary(String word) {
        return dictionary.containsKey(word);
    }

}
