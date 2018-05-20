package gui;

import algorithm.Algorithm;
import algorithm.AlphaBeta;
import algorithm.MinMax;
import api.Game;
import api.Player;
import api.PlayerIterator;
import score.NegativeScoreCalculator;
import score.ScoreCalculator;
import selector.HeuristicSelector;
import selector.PositionSelector;
import selector.RandomSelector;
import sorter.DescendingSorter;

final class Main {

    public static void main(String[] args) {
        run(15);
    }

    private static void run(int problemSize) {
        Game game = new Game(problemSize);
        ScoreCalculator calculator = new NegativeScoreCalculator();
        PositionSelector selector = new HeuristicSelector(new RandomSelector(game, 1, 1), game, 0.3, 0.8);
        Gui gui = new Gui('*', 'o', problemSize);

        int level = 2;
        //Algorithm playerOneAlgorithm = new AlphaBeta(level, selector, calculator, game);
        Algorithm playerTwoAlgorithm = new AlphaBeta(level, selector, calculator, game, new DescendingSorter(calculator));
        Algorithm playerOneAlgorithm = new MinMax(level, selector, calculator, game, new DescendingSorter(calculator));
        //Algorithm playerOneAlgorithm = new HumanAlgorithm(gui, game, calculator);
        //Algorithm playerTwoAlgorithm = new HumanAlgorithm(gui, game, calculator);

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
