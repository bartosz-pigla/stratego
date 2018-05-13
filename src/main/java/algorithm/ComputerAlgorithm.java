package algorithm;

import java.util.ArrayList;
import java.util.List;

import api.Game;
import api.Player;
import api.Position;
import score.ScoreCalculator;
import selector.PositionSelector;

public abstract class ComputerAlgorithm implements Algorithm {

    protected int depth;
    protected PositionSelector selector;
    protected ScoreCalculator calculator;
    protected Game game;
    protected List<Position> visitedPositions;

    public ComputerAlgorithm(int depth, PositionSelector selector, ScoreCalculator calculator, Game game) {
        this.depth = depth;
        this.selector = selector;
        this.calculator = calculator;
        this.game = game;
        initVisitedPositions();
    }

    @Override
    public int play(Player player) {
        clearVisitedPositions();
        Position position;
        if (game.getEmptyPositions().size() > 2) {
            position = playHelper();
        } else if (game.getEmptyPositions().size() == 2) {
            position = getBestOfTwoPositions(game.getEmptyPositions().get(0), game.getEmptyPositions().get(1));
        } else {
            position = game.getEmptyPositions().get(0);
        }
        game.fillPosition(position.getPosX(), position.getPosY(), true);
        return position.getScore();
    }

    public abstract Position playHelper();

    private void initVisitedPositions() {
        visitedPositions = new ArrayList<>(depth);
        for (int i = 0; i < depth; i++) {
            visitedPositions.add(null);
        }
    }

    private void clearVisitedPositions() {
        for (int i = 0; i < depth; i++) {
            visitedPositions.set(i, null);
        }
    }

    private Position getBestOfTwoPositions(Position first, Position second) {
        calculator.calculate(first);
        calculator.calculate(second);

        if (first.getScore() >= second.getScore()) {
            return first;
        } else {
            return second;
        }
    }
}
