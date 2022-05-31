package app;

import app.enums.PileType;
import app.enums.Suit;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private final int PILE_NUMBER = 7;

    private List<Pile> piles;
    private List<Pile> finalPiles;
    private Pile drawPile;
    private Pile getPile;
    private List<Pile> allPiles;
    private Deck deck;

    public Engine() {
        resetCards();
    }

    public void resetCards() {
        deck = new Deck();
        deck.shuffle();

        drawPile = new Pile(120);
        drawPile.setOffset(0);

        getPile = new Pile(180);
        getPile.setOffset(0);

        finalPiles = new ArrayList<>();
        piles = new ArrayList<>();

        allPiles = new ArrayList<>();
        allPiles.add(drawPile);
        allPiles.add(getPile);
    }

    public void setupGame() {
        // Generate piles
        drawPile.setType(PileType.DRAW);
        getPile.setType(PileType.GET);

        for(int i = 1; i <= PILE_NUMBER; ++i) {
            Pile pile = new Pile(120);

            // Add i cards to the current pile
            for(int j = 1; j <= i; ++j) {
                Card card = deck.drawCard();
                pile.addCard(card);

                if(j!=i) {
                    card.hide();
                } else {
                    card.show();
                }
            }

            piles.add(pile);
            allPiles.add(pile);
        }

        for(Suit suit : Suit.values()) {
            Pile pile = new Pile(100);
            pile.setOffset(0);
            pile.setType(PileType.FINAL);
            finalPiles.add(pile);
            allPiles.add(pile);
        }

        while(deck.size() > 0) {
            Card card = deck.drawCard();
            card.hide();
            drawPile.addCard(card);
        }
    }

    public void drawCard() {
        if(!drawPile.getCards().isEmpty()) {
            Card drew = drawPile.drawCard();
            drew.setReversed(false);
            getPile.addCard(drew);
        }
    }

    public void clickPile(Pile pile) {
        if(!pile.getCards().isEmpty()) {
            Card c = pile.getCards().get(pile.getCards().size() - 1);
            if(c.isReversed()) {
                c.setReversed(false);
            }
        }
    }

    public void turnGetPile() {
        if(!drawPile.getCards().isEmpty()) return;

        while(!getPile.getCards().isEmpty()) {
            Card card = getPile.drawCard();
            card.setReversed(true);

            drawPile.addCard(card);
        }
    }

    public boolean checkWin() {
        for(Pile pile : finalPiles) {
            if(pile.getCards().size() != 13)
                return false;
        }
        return true;
    }

    public List<Pile> getPiles() {
        return piles;
    }

    public List<Pile> getFinalPiles() {
        return finalPiles;
    }

    public Pile getDrawPile() {
        return drawPile;
    }

    public Pile getGetPile() {
        return getPile;
    }

    public Deck getDeck() {
        return deck;
    }
}
