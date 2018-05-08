package algorithm;

import java.util.ArrayList;
import java.util.List;

import api.Game;
import api.Player;
import api.Position;
import score.ScoreCalculator;
import selector.PositionSelector;

public final class MinMax implements Algorithm {

    private int depth;
    private PositionSelector selector;
    private ScoreCalculator calculator;
    private Game game;
    private List<Position> visitedPositions;

    public MinMax(int depth, PositionSelector selector, ScoreCalculator calculator, Game game) {
        this.depth = depth;
        this.selector = selector;
        this.calculator = calculator;
        this.game = game;
        initVisitedPositions();
    }

    @Override
    public int play(Player player) {
        clearVisitedPositions();
        Position position = minMax(null, Math.min(depth, game.getEmptyPositions().size()), true, visitedPositions);
        game.fillPosition(position.getPosX(), position.getPosY(), true);
        return position.getScore();
    }

    private Position minMax(Position position, int depth, boolean maximizingPlayer, List<Position> visited) {
        if (depth == 0) {
            for (Position pos : visited) {
                pos.setFilled(true);
            }
            calculator.calculate(position);
            for (Position pos : visited) {
                pos.setFilled(false);
            }
            return position;
        } else if (maximizingPlayer) {
            return doForMaximizingPlayer(depth, visited);
        } else {
            return doForMinimizingPlayer(depth, visited);
        }
    }

    private Position doForMaximizingPlayer(int depth, List<Position> visited) {
        int bestScore = Integer.MIN_VALUE;
        Position bestPosition = null;

        for (Position currentPosition : game.getEmptyPositions()) {
            if (!visited.contains(currentPosition)) {
                visited.set(depth - 1, currentPosition);
                minMax(currentPosition, depth - 1, false, visited);
                visited.set(depth - 1, null);
                if (currentPosition.getScore() > bestScore) {
                    bestPosition = currentPosition;
                    bestScore = currentPosition.getScore();
                }
            }
        }

        return bestPosition;
    }

    private Position doForMinimizingPlayer(int depth, List<Position> visited) {
        int worstScore = Integer.MAX_VALUE;
        Position worstPosition = null;

        for (Position currentPosition : game.getEmptyPositions()) {
            if (!visited.contains(currentPosition)) {
                visited.set(depth - 1, currentPosition);
                minMax(currentPosition, depth - 1, true, visited);
                visited.set(depth - 1, null);
                if (currentPosition.getScore() < worstScore) {
                    worstPosition = currentPosition;
                    worstScore = currentPosition.getScore();
                }
            }
        }

        return worstPosition;
    }

    private void initVisitedPositions(){
        visitedPositions = new ArrayList<>(depth);
        for (int i = 0; i < depth; i++) {
            visitedPositions.add(null);
        }
    }

    private void clearVisitedPositions(){
        for (int i = 0; i < depth; i++) {
            visitedPositions.set(i, null);
        }
    }
}
