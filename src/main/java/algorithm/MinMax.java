package algorithm;

import java.util.List;

import api.Game;
import api.Position;
import score.ScoreCalculator;
import selector.PositionSelector;
import sorter.PositionsSorter;

public final class MinMax extends ComputerAlgorithm {

    public MinMax(int depth, PositionSelector selector, ScoreCalculator calculator, Game game, PositionsSorter sorter) {
        super(depth, selector, calculator, game, sorter);
    }

    @Override
    public Position playHelper() {
        return minMax(null, depth, true, visitedPositions);
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
        List<Position> selectedPositions = sorter.sort(selector.selectPositions(visited));

        for (Position currentPosition : selectedPositions) {
            visited.set(depth - 1, currentPosition);
            minMax(currentPosition, depth - 1, false, visited);
            //visited.set(depth - 1, null);
            if (currentPosition.getScore() > bestScore) {
                bestPosition = currentPosition;
                bestScore = currentPosition.getScore();
            }
        }

        if (bestPosition == null) {
            bestPosition = selectedPositions.get(0);
        }

        visited.set(depth - 1, bestPosition);
        return bestPosition;
    }

    private Position doForMinimizingPlayer(int depth, List<Position> visited) {
        int worstScore = Integer.MAX_VALUE;
        Position worstPosition = null;
        List<Position> selectedPositions = sorter.sort(selector.selectPositions(visited));

        for (Position currentPosition : selectedPositions) {
            visited.set(depth - 1, currentPosition);
            minMax(currentPosition, depth - 1, true, visited);
            //visited.set(depth - 1, null);
            if (currentPosition.getScore() < worstScore) {
                worstPosition = currentPosition;
                worstScore = currentPosition.getScore();
            }
        }

        if (worstPosition == null) {
            worstPosition = selectedPositions.get(0);
        }

        visited.set(depth - 1, worstPosition);
        return worstPosition;
    }

}
