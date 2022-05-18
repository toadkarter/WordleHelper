package com.wordlehelper.wordlehelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Credit: https://github.com/dwyl/english-words
public class Dictionary {
    private final Set<String> dictionary = new HashSet<>();

    public Dictionary() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("src/words.txt"));
        while (textFile.hasNext()) {
            dictionary.add(textFile.next().trim().toLowerCase());
        }
        textFile.close();
    }

    public boolean isInDictionary(String word) {
        return dictionary.contains(word);
    }

}
