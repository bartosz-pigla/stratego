package gui;

import engine.Game;

/**
 *
 */
final class Main {

    public static void main(String[] args) {
        boolean[][] stateOfGame = {
                { false, false, false },
                { false, true, false },
                { false, false, false }
        };

        Game game = new Game(3);
        game.updateMatrix(stateOfGame);

        Gui gui = new Gui('*', 'o', 3);
        gui.printStateOfGame(game.getGameMatrix());
    }
}
