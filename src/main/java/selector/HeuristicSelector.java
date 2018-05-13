package selector;

import java.util.ArrayList;
import java.util.List;

import api.Game;
import api.Position;

public final class HeuristicSelector extends PositionSelector {

    private RandomSelector randomSelector;

    public HeuristicSelector(RandomSelector randomSelector, Game game) {
        super(game, 1);
        this.randomSelector = randomSelector;
    }

    public HeuristicSelector(RandomSelector randomSelector, Game game, double rate) {
        super(game, rate);
        this.randomSelector = randomSelector;
    }

    @Override
    public List<Position> selectPositions(List<Position> visited) {
        List<Position> positions = new ArrayList<>(poolSize);

        Position positionFromRow;
        for (int row = 0; row < game.getProblemSize(); row++) {
            if ((positionFromRow = calculatePositionHelper(game.getGameMatrix()[row], visited)) != null) {
                if (poolSize > positions.size()) {
                    positions.add(positionFromRow);
                } else {
                    return positions;
                }
            }
        }

        Position positionFromColumn;
        for (int column = 0; column < game.getProblemSize(); column++) {
            if ((positionFromColumn = calculatePositionHelper(game.getColumns()[column], visited)) != null) {
                if (poolSize > positions.size()) {
                    positions.add(positionFromColumn);
                } else {
                    return positions;
                }
            }
        }

        Position positionFromDiagonal;
        for (int i = game.getProblemSize() - 1; i >= 0; i--) {
            if ((positionFromDiagonal = calculatePositionHelper(game.getLeftDiagonals()[i], visited)) != null) {
                if (poolSize > positions.size()) {
                    positions.add(positionFromDiagonal);
                } else {
                    return positions;
                }
            } else if ((positionFromDiagonal = calculatePositionHelper(game.getRightDiagonals()[i], visited)) != null) {
                if (poolSize > positions.size()) {
                    positions.add(positionFromDiagonal);
                } else {
                    return positions;
                }
            }
        }

        Position currentPosition;
        while (poolSize > positions.size()) {
            currentPosition = randomSelector.selectPosition(visited);
            if (currentPosition != null) {
                positions.add(currentPosition);
            } else {
                return positions;
            }
        }

        return positions;
    }

    private Position calculatePositionHelper(Position[] line, List<Position> visited) {
        int posX = -1, posY = -1, emptyCount = 0;
        for (Position pos : line) {
            if (!(pos.isFilled() || visited.contains(pos))) {
                if (emptyCount == 1) {
                    return null;
                } else {
                    emptyCount++;
                    posX = pos.getPosX();
                    posY = pos.getPosY();
                }
            }
        }
        return game.getPosition(posX, posY);
    }
}
