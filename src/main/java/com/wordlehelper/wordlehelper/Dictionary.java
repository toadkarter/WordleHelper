package com.wordlehelper.wordlehelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Credit: https://github.com/dwyl/english-words
public class Dictionary {
    public void readFile() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("src/words.txt"));
    }
}
