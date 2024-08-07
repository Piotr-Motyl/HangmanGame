package com.github.piotrmotyl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class RandomWordsFromFile {
    private final List<String> words;
    private final Random random;

    public RandomWordsFromFile(String filePath) throws IOException {
        this.words = Files.readAllLines(Path.of(filePath));
        this.random = new Random();
    }

    public String generateWord() {
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}