package piglatin;

public class App {
    public static void main(String[] args) {
        // Starter book
        Book input = new Book();

        // Example reading from a URL
        input.readFromUrl("The Picture of Dorian Gray", "https://www.gutenberg.org/cache/epub/1513/pg1513.txt");

        Book output = PigLatinTranslator.translate(input);
        System.out.println("Translated Book:");
        output.printlines(0, output.getLineCount());
    }
}