package cards;

public class Hand {
    private Card[] cards;
    private int size;

    public Hand(int maxSize) {
        cards = new Card[maxSize];
        size = 0;
    }

    public void add(Card card) {
        if (size < cards.length) {
            cards[size++] = card;
        }
    }


    public Card get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return cards[index];
    }

    public Card remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Card card = cards[index];
        for (int i = index; i < size - 1; i++) {
            cards[i] = cards[i + 1];
        }
        size--;
        return card;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(cards[i]).append(" ");
        }
        return sb.toString();
    }
}