package gui;

import algorithm.Algorithm;
import algorithm.Human;
import api.Game;
import api.Player;
import api.PlayerIterator;
import score.ScoreCalculator;
import score.SimpleScoreCalculator;
import selector.PositionSelector;
import selector.RandomSelector;

final class Main {

    public static void main(String[] args) {
        run(3);
    }

    private static void run(int problemSize) {
        Game game = new Game(problemSize);
        ScoreCalculator calculator = new SimpleScoreCalculator();
        PositionSelector selector = new RandomSelector(game);
        Gui gui = new Gui('*', 'o', problemSize);

        int level = 4;
//        Algorithm playerOneAlgorithm = new AlphaBeta(level, selector, calculator, game);
//        Algorithm playerTwoAlgorithm = new AlphaBeta(level, selector, calculator, game);
        Algorithm playerOneAlgorithm = new Human(gui, game, calculator);
        Algorithm playerTwoAlgorithm = new Human(gui, game, calculator);

        Player playerOne = new Player("player1", playerOneAlgorithm);
        Player playerTwo = new Player("player2", playerTwoAlgorithm);

        PlayerIterator iterator = new PlayerIterator(playerOne, playerTwo);
        Player currentPlayer;

        while (game.getEmptyPositions().size() > 0) {
            currentPlayer = iterator.next();
            gui.printStateOfGame(game.getGameMatrix());
            currentPlayer.play();
            gui.printScoreOfPlayer(currentPlayer);
        }

        gui.printSummary(iterator.getPlayerList());
    }
}
