package app;

import app.enums.PileType;
import app.enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pile extends JLayeredPane {
    private int offset = 15;
    private Card base;
    private List<Card> cards;
    private Suit suitFilter;
    private int width;
    private Pile parentPile;
    private PileType type;

    public Pile(int width) {
        cards = new ArrayList<>();
        this.width = width;

        base = new Card(100, Suit.SPADES);
        add(base, 1, 0);

        type = PileType.NORMAL;
    }

    public void addCard(Card c) {
    	c.setLocation(0, offset * cards.size());
        cards.add(c);

        this.add(c, 1, 0);
        updateSize();
    }

    public void removeCard(Card c) {
        cards.remove(c);
        this.remove(c);

        updateSize();
    }

    public Card drawCard() {
        Card c = cards.get(0);
        removeCard(c);

        return c;
    }

    public void updateSize() {
        int height = base.getSize().height;

        if(!cards.isEmpty()) {
            height += offset * (cards.size() - 1);
        }

        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
    }

    public void setOffset(int offset) {
        this.offset = offset;
        updateSize();
    }

    public Pile split(Card first) {
        Pile p = new Pile(100);

        for(int i = 0; i < cards.size(); ++i) {
            if(cards.get(i) == first) {
                for(; i < cards.size();) {
                    p.addCard(cards.get(i));
                    removeCard(cards.get(i));
                }
            }
        }

        p.parentPile = this;

        return p;
    }

    public void merge(Pile p) {
        for(Card c: p.cards)
            addCard(c);

        updateSize();
    }

    public boolean acceptsPile(Pile p) {
        if(this == p) return false;

        Card newCard = p.cards.get(0);
        Card topCard;

        switch(type) {
            case NORMAL:
                if(cards.isEmpty()) {
                    if(newCard.getValue() == 14) return true;
                    return false;
                }

                topCard = cards.get(cards.size() - 1);
                if(topCard.isReversed()) return false;

                if(topCard.getSuit().isRed != newCard.getSuit().isRed)
                    if(topCard.getValue() == newCard.getValue() + 1 ||
                            topCard.getValue() ==  12 && newCard.getValue() == 10) {
                        return true;
                    }
                break;
            case FINAL:
                if(p.cards.size() > 1) return false;

                if(cards.isEmpty() && newCard.getValue() == 1) {
                    suitFilter = newCard.getSuit();
                    return true;
                }

                if(suitFilter != newCard.getSuit()) return false;

                topCard = cards.get(cards.size() - 1);
                if(topCard.getValue() == newCard.getValue() - 1 ||
                        topCard.getValue() ==  10 && newCard.getValue() == 12) {
                    return true;
                }
                break;
        }
        return false;
    }
    
    public void changeScoreByType(Engine game, Score score, Pile tempPile) {
    	for (Pile p: game.getPiles()) {
    		if (p.equals(this)) {
    			if (game.getPiles().contains(tempPile.getParentPile())) {
    				score.addScore(3);
    				break;
    			}
    			else if (tempPile.getParentPile().equals(game.getGetPile())) {
    				score.addScore(5);
    				break;
    			}
    		}
    	}
    	
    	for (Pile p: game.getFinalPiles()) {
    		if (p.equals(this)) {
    			score.addScore(10);
    			break;
    		}
    	}
    }

    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public String toString() {
        String result = "";

        result += base.saveAsString() + "-";

        for(Card c : cards) {
            result += c.saveAsString() + "-";
        }

        return result;
    }

    @Override
    public Component.BaselineResizeBehavior getBaselineResizeBehavior() {
        return Component.BaselineResizeBehavior.CONSTANT_ASCENT;
    }

    @Override
    public int getBaseline(int width, int height) {
        return 0;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Pile getParentPile() {
        return parentPile;
    }

    public PileType getType() {
        return type;
    }

    public void setType(PileType type) {
        this.type = type;
    }
}
