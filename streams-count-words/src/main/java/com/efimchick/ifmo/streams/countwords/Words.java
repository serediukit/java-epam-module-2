package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {
    private static final String REGEX = "\\P{javaLetter}+";
    private static final int MIN_LENGTH_WORD = 4;
    private static final int MIN_AMOUNT_WORDS = 10;

    public String countWords(List<String> lines) {
        String string = lines.stream()
                .flatMap(line -> Arrays.stream(line.split(REGEX))
                        .map(String::toLowerCase))
                .filter(word -> word.length() >= MIN_LENGTH_WORD)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(collectorListEntry -> collectorListEntry.getValue() >= MIN_AMOUNT_WORDS)
                .sorted(Map.Entry.comparingByKey())
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(stringLongEntry -> String.format("%s - %d\n", stringLongEntry.getKey(), stringLongEntry.getValue()))
                .collect(Collectors.joining());
        StringBuilder stringBuilder = removeLastChar(string);
        return stringBuilder.toString();
    }

    private StringBuilder removeLastChar(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder;
    }
}