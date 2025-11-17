package cards;

public class Card {
    private int suit;
    private int value;

    public Card(int suit, int value) {
        this.suit = Math.max(0, Math.min(suit, 3));
        this.value = Math.max(0, Math.min(value, 12));
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        String[] suits = {"♦", "♣", "♥", "♠"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return suits[suit] + values[value];
    }
}