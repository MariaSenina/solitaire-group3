package app.enums;

public enum Suit {
    SPADES(1, false),
    HEARTS(2, true),
    DIAMONDS(3, true),
    CLUBS(4, false);

    public int value;
    public boolean isRed;

    Suit(int value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }
}
