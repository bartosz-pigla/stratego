package algorithm;

import java.util.ArrayList;
import java.util.List;

import api.Game;
import api.Player;
import api.Position;
import score.ScoreCalculator;
import selector.PositionSelector;

public final class AlphaBeta implements Algorithm {

    private int depth;
    private PositionSelector selector;
    private ScoreCalculator calculator;
    private Game game;
    private List<Position> visitedPositions;

    public AlphaBeta(int depth, PositionSelector selector, ScoreCalculator calculator, Game game) {
        this.depth = depth;
        this.selector = selector;
        this.calculator = calculator;
        this.game = game;
        initVisitedPositions();
    }

    @Override
    public int play(Player player) {
        clearVisitedPositions();
        Position position = alphaBeta(null, Math.min(depth, game.getEmptyPositions().size()), true, Integer.MIN_VALUE, Integer.MAX_VALUE, visitedPositions);
        game.fillPosition(position.getPosX(), position.getPosY(), true);
        return position.getScore();
    }

    private Position alphaBeta(Position position, int depth, boolean maximizingPlayer, int alpha, int beta, List<Position> visited) {
        if (depth == 0) {
            for (Position pos : visited) {
                game.getGameMatrix()[pos.getPosX()][pos.getPosY()].setFilled(true);
            }
            calculator.calculate(position);
            for (Position pos : visited) {
                game.getGameMatrix()[pos.getPosX()][pos.getPosY()].setFilled(false);
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

        for (Position currentPosition : game.getEmptyPositions()) {
            if (!visited.contains(currentPosition)) {
                visited.set(depth - 1, currentPosition);
                alphaBeta(currentPosition, depth - 1, false, alpha, beta, visited);
                visited.set(depth - 1, null);
                if (currentPosition.getScore() > bestScore) {
                    bestPosition = currentPosition;
                    bestScore = currentPosition.getScore();
                    alpha = Math.max(alpha, bestScore);
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }

        return bestPosition;
    }

    private Position doForMinimizingPlayer(int depth, int alpha, int beta, List<Position> visited) {
        int worstScore = Integer.MAX_VALUE;
        Position worstPosition = null;

        for (Position currentPosition : game.getEmptyPositions()) {
            if (!visited.contains(currentPosition)) {
                visited.set(depth - 1, currentPosition);
                alphaBeta(currentPosition, depth - 1, true, alpha, beta, visited);
                visited.set(depth - 1, null);
                if (currentPosition.getScore() < worstScore) {
                    worstPosition = currentPosition;
                    worstScore = currentPosition.getScore();
                    alpha = Math.min(alpha, worstScore);
                    if (alpha >= beta) {
                        break;
                    }
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
