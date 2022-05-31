package app;

import app.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for(Suit suit : Suit.values()) {
            for(int value = 1; value <= 14; ++value) {
                if(value != 11) {
                    cards.add(new Card(value, suit));
                }
            }
        }
    }

    public void shuffle() {
        Random randomIndex = new Random();
        int size = cards.size();

        for(int shuffles = 1; shuffles <= 20; ++shuffles) {
            for (int i = 0; i < size; i++) {
                Collections.swap(cards, i, randomIndex.nextInt(size));
            }
        }

    }

    public int size() {
        return cards.size();
    }

    public Card drawCard() {
        Card card = cards.get(0);
        cards.remove(0);

        return card;
    }

    public List<Card> getCards() {
        return cards;
    }
}
