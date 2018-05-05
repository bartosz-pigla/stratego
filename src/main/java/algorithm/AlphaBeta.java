package algorithm;

import api.Game;
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
    private Game game;

    @Override
    public int play(Player player) {
        Position position = alphaBeta(null, depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return position.getScore();
    }

    private Position alphaBeta(Position position, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (game.getEmptyPositions().size() == 0 || depth == 0) {
            calculator.calculate(position);
            return position;
        } else if (maximizingPlayer) {
            return doForMaximizingPlayer(depth, alpha, beta);
        } else {
            return doForMinimizingPlayer(depth, alpha, beta);
        }
    }

    private Position doForMaximizingPlayer(int depth, int alpha, int beta) {
        int bestScore = Integer.MIN_VALUE;
        Position currentPosition, bestPosition = null;
        for (int i = 0; i < game.getEmptyPositions().size(); i++) {
            currentPosition = game.getEmptyPositions().get(i);
            game.getEmptyPositions().remove(i);
            alphaBeta(currentPosition, depth - 1, false, alpha, beta);
            game.getEmptyPositions().set(i, currentPosition);
            if (currentPosition.getScore() > bestScore) {
                bestPosition = currentPosition;
                bestScore = currentPosition.getScore();
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        game.getEmptyPositions().remove(bestPosition);
        return bestPosition;
    }

    private Position doForMinimizingPlayer(int depth, int alpha, int beta) {
        int worstScore = Integer.MAX_VALUE;
        Position currentPosition, worstPosition = null;
        for (int i = 0; i < game.getEmptyPositions().size(); i++) {
            currentPosition = game.getEmptyPositions().get(i);
            game.getEmptyPositions().remove(i);
            alphaBeta(currentPosition, depth - 1, true, alpha, beta);
            game.getEmptyPositions().set(i, currentPosition);
            if (currentPosition.getScore() < worstScore) {
                worstPosition = currentPosition;
                worstScore = currentPosition.getScore();
                alpha = Math.min(alpha, worstScore);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        game.getEmptyPositions().remove(worstPosition);
        return worstPosition;
    }
}
