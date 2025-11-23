package cards;

public class Hand {
    private Card[] cards;
    private int size;

    public Hand(int capacity) {
        cards = new Card[capacity];
        size = 0;
    }

    public void add(Card card) {
        if (size < cards.length) {
            cards[size++] = card;
        }
    }

    public Card get(int index) {
        if (index >= 0 && index < size) {
            return cards[index];
        }
        return null;
    }

    public int getHandSize() {
        return size;
    }

    public int length() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(cards[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}