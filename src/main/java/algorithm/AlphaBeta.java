package algorithm;

import java.util.List;

import api.Game;
import api.Position;
import score.ScoreCalculator;
import selector.PositionSelector;

public final class AlphaBeta extends ComputerAlgorithm {

    public AlphaBeta(int depth, PositionSelector selector, ScoreCalculator calculator, Game game) {
        super(depth, selector, calculator, game);
    }

    @Override
    public Position playHelper() {
        return alphaBeta(null, Math.min(depth, game.getEmptyPositions().size()), true, Integer.MIN_VALUE, Integer.MAX_VALUE, visitedPositions);
    }

    private Position alphaBeta(Position position, int depth, boolean maximizingPlayer, int alpha, int beta, List<Position> visited) {
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
            return doForMaximizingPlayer(depth, alpha, beta, visited);
        } else {
            return doForMinimizingPlayer(depth, alpha, beta, visited);
        }
    }

    private Position doForMaximizingPlayer(int depth, int alpha, int beta, List<Position> visited) {
        int bestScore = Integer.MIN_VALUE;
        Position bestPosition = null;
        List<Position> selectedPositions = selector.selectPositions(visited);

        for (Position currentPosition : selectedPositions) {
            visited.set(depth - 1, currentPosition);
            alphaBeta(currentPosition, depth - 1, false, alpha, beta, visited);
            //visited.set(depth - 1, null);
            if (currentPosition.getScore() > bestScore) {
                bestPosition = currentPosition;
                bestScore = currentPosition.getScore();
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        if (bestPosition == null) {
            bestPosition = selectedPositions.get(0);
        }

        visited.set(depth - 1, bestPosition);
        return bestPosition;
    }

    private Position doForMinimizingPlayer(int depth, int alpha, int beta, List<Position> visited) {
        int worstScore = Integer.MAX_VALUE;
        Position worstPosition = null;
        List<Position> selectedPositions = selector.selectPositions(visited);

        for (Position currentPosition : selectedPositions) {
            visited.set(depth - 1, currentPosition);
            alphaBeta(currentPosition, depth - 1, true, alpha, beta, visited);
            //visited.set(depth - 1, null);
            if (currentPosition.getScore() < worstScore) {
                worstPosition = currentPosition;
                worstScore = currentPosition.getScore();
                alpha = Math.min(alpha, worstScore);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        if (worstPosition == null) {
            worstPosition = selectedPositions.get(0);
        }

        visited.set(depth - 1, worstPosition);
        return worstPosition;
    }
}
