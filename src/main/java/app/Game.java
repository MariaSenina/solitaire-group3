package app;

public class Game {

    public Game() {
        new GUI(new Engine());
    }

    public static void main(String[] args) {
        new Game();
    }
}
