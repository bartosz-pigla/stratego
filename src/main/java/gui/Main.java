package gui;

import algorithm.Algorithm;
import algorithm.AlphaBeta;
import api.Game;
import api.Player;
import score.ScoreCalculator;
import score.SimpleScoreCalculator;
import selector.PositionSelector;
import selector.RandomSelector;

final class Main {

    public static void main(String[] args) {
        run(5);
    }

    private static void run(int problemSize) {
        Gui gui = new Gui('x','o',problemSize);
        Game game = new Game(problemSize);
        ScoreCalculator calculator = new SimpleScoreCalculator();
        PositionSelector selector = new RandomSelector(game.getEmptyPositions());

        int level = 4;
        Algorithm playerOneAlgorithm = new AlphaBeta(level, selector, calculator);
        Algorithm playerTwoAlgorithm = new AlphaBeta(level, selector, calculator);
        Player playerOne = new Player(playerOneAlgorithm);
        Player playerTwo = new Player(playerTwoAlgorithm);

    }
}
