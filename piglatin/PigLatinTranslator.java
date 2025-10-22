package piglatin;

import java.util.Scanner;

public class PigLatinTranslator {
    public static Book translate(Book input) {
        Book translatedBook = new Book();

        // Assuming Book has a method to get and set text
        String[] lines = input.getText().split("\n");
        for (String line : lines) {
            translatedBook.addText(translate(line) + "\n");
        }

        return translatedBook;
    }
    