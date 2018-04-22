package gui;

import engine.Game;
import engine.Position;

/**
 *
 */
final class Main {

    public static void main(String[] args) {
//        boolean[][] stateOfGame = {
//                { false, false, false },
//                { false, true, false },
//                { false, false, false }
//        };

        Game game = new Game(5);
        //game.updateMatrix(stateOfGame);

        Gui gui = new Gui('*', 'o', 5);
        gui.printStateOfGame(game.getGameMatrix());

        Position pos = game.calculatePosition();
    }
}
