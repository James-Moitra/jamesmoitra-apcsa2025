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

    public static String translate(String input) {
        System.out.println("  -> translate('" + input + "')");

        Scanner scanner = new Scanner(input);
        StringBuilder result = new StringBuilder();

        while (scanner.hasNext()) {
            String word = scanner.next();
            result.append(translateWord(word)).append(" ");
        }

        scanner.close();
        return result.toString().trim();
    }

    private static String translateWord(String input) {
        System.out.println("  -> translateWord('" + input + "')");

        // Check for punctuation
        String punctuation = "";
        if (!Character.isLetter(input.charAt(input.length() - 1))) {
            punctuation = input.substring(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        // Preserve capitalization
        boolean capitalize = Character.isUpperCase(input.charAt(0));
        boolean allCaps = isAllCaps(input);
        input = input.toLowerCase();

        // Check for hyphenated words
        if (input.contains("-")) {
            String[] parts = input.split("-");
            String translated = translateWord(parts[0]) + "-" + translateWord(parts[1]);
            return restoreCapitalization(translated, capitalize, allCaps) + punctuation;
        }

        // Translate word
        int vowelIndex = findFirstVowelIndex(input);
        String result;
        if (vowelIndex == 0) {
            result = input + "ay";
        } else {
            result = input.substring(vowelIndex) + input.substring(0, vowelIndex) + "ay";
        }

        return restoreCapitalization(result, capitalize, allCaps) + punctuation;
    }

    private static int findFirstVowelIndex(String word) {
        String vowels = "aeiou";
        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) != -1) {
                return i;
            }
        }
        return 0; // If no vowel is found, move the entire word
    }

    private static boolean isAllCaps(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c) && !Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    private static String restoreCapitalization(String word, boolean capitalize, boolean allCaps) {
        if (allCaps) {
            return word.toUpperCase();
        } else if (capitalize) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        } else {
            return word;
        }
    }
}








