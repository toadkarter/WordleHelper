package com.wordlehelper.wordlehelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Credit: https://github.com/dwyl/english-words
public class Dictionary {
    private final Set<String> dictionary = new HashSet<>();
    private final HashMap<String, Double> dictionaryFrequencies = new HashMap<>();

    public Dictionary() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("src/words.txt"));
        while (textFile.hasNext()) {
            dictionary.add(textFile.next().trim().toLowerCase());
        }
        textFile.close();

        Scanner frequencies = new Scanner(new File("src/word_frequencies.txt"));
        while (frequencies.hasNext()) {
            String[] currentLine = frequencies.next().split(":", 2);
            String word = currentLine[0];
            Double frequency = Double.parseDouble(currentLine[1]);
            dictionaryFrequencies.put(word, frequency);
        }

        System.out.println(dictionaryFrequencies);

    }

    public boolean isInDictionary(String word) {
        return dictionary.contains(word);
    }

}
