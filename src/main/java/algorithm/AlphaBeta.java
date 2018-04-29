package algorithm;

import api.Player;
import api.Position;
import lombok.AllArgsConstructor;
import score.ScoreCalculator;
import selector.PositionSelector;

@AllArgsConstructor
public final class AlphaBeta implements Algorithm {

    private int depth;
    private PositionSelector selector;
    private ScoreCalculator calculator;

    @Override
    public void play(Player player) {
        alphaBeta(null, depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private Position alphaBeta(Position position, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (!selector.hasNext() || depth == 0) {
            calculator.calculate(position);
            return position;
        } else if (maximizingPlayer) {
            return doForMaximizingPlayer(alpha, beta);
        } else {
            return doForMinimizingPlayer(alpha, beta);
        }
    }

    private Position doForMaximizingPlayer(int alpha, int beta) {
        int bestScore = Integer.MIN_VALUE;
        Position currentPosition, bestPosition = null;
        while (selector.hasNext()) {
            currentPosition = alphaBeta(selector.nextPosition(), depth - 1, false, alpha, beta);
            if (currentPosition.getScore() > bestScore) {
                bestScore = currentPosition.getScore();
                bestPosition = currentPosition;
            }
            alpha = Math.max(alpha, bestScore);
            if (alpha >= beta) {
                break;
            }
        }
        return bestPosition;
    }

    private Position doForMinimizingPlayer(int alpha, int beta) {
        return null;
    }
}
