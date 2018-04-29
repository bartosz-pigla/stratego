package selector;

import api.Game;
import api.Position;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class HeuristicSelector implements PositionSelector {

    private RandomSelector randomSelector;
    private Game game;

    @Override
    public Position nextPosition() {
        Position positionFromRow;
        for (int row = 0; row < game.getProblemSize(); row++) {
            if ((positionFromRow = calculatePositionHelper(game.getGameMatrix()[row])) != null) {
                return positionFromRow;
            }
        }

        Position positionFromColumn;
        for (int column = 0; column < game.getProblemSize(); column++) {
            if ((positionFromColumn = calculatePositionHelper(game.getColumns()[column])) != null) {
                return positionFromColumn;
            }
        }

        Position positionFromDiagonal;
        for (int i = game.getProblemSize() - 1; i >= 0; i--) {
            if ((positionFromDiagonal = calculatePositionHelper(game.getLeftDiagonals()[i])) != null) {
                return positionFromDiagonal;
            } else if ((positionFromDiagonal = calculatePositionHelper(game.getRightDiagonals()[i])) != null) {
                return positionFromDiagonal;
            }
        }
        return randomSelector.nextPosition();
    }

    @Override
    public boolean hasNext() {
        return game.getEmptyPositions().size() > 0;
    }

    private Position calculatePositionHelper(Position[] line) {
        int posX = -1, posY = -1, emptyCount = 0;
        for (int i = 0; i < line.length; i++) {
            if (!line[i].isFilled()) {
                if (emptyCount == 1) {
                    return null;
                }
                emptyCount++;
                posX = line[i].getPosX();
                posY = line[i].getPosY();
            }
        }
        return game.getPosition(posX, posY);
    }
}
