package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Getter;

@Getter
public final class Game {

    private Position[][] gameMatrix;
    private int problemSize;
    private Random random = new Random();
    private List<Position> emptyPositions;

    private Position[][] columns;
    private Position[][] leftDiagonals;
    private Position[][] rightDiagonals;

    public Game(int problemSize) {
        this.problemSize = problemSize;
        this.emptyPositions = new ArrayList<>(problemSize * problemSize);
        initPositions();
        initColumns();
        initDiagonals();
        initPositionLines();
    }

    private void initPositions() {
        this.gameMatrix = new Position[problemSize][];
        for (int i = 0; i < problemSize; i++) {
            this.gameMatrix[i] = new Position[problemSize];
            for (int j = 0; j < problemSize; j++) {
                this.gameMatrix[i][j] = new Position(i, j, false, this);
                this.emptyPositions.add(this.gameMatrix[i][j]);
            }
        }
    }

    private void initColumns() {
        columns = new Position[problemSize][];
        for (int column = 0; column < problemSize; column++) {
            columns[column] = new Position[problemSize];
            for (int row = 0; row < problemSize; row++) {
                columns[column][row] = gameMatrix[row][column];
            }
        }
    }

    private void initDiagonals() {
        leftDiagonals = new Position[2 * problemSize - 5][];
        initLeftDiagonals();

        rightDiagonals = new Position[2 * problemSize - 5][];
        initRightDiagonals();
    }

    private void initLeftDiagonals() {
        int idx = 0;
        int i = leftDiagonals.length / 2;
        while (i > 0) {
            leftDiagonals[idx] = new Position[problemSize - i];
            initLeftDiagonalHelper(i, 0, leftDiagonals[idx]);

            leftDiagonals[++idx] = new Position[problemSize - i];
            initLeftDiagonalHelper(0, i, leftDiagonals[idx]);

            idx++;
            i--;
        }
        leftDiagonals[idx] = new Position[problemSize];
        initLeftDiagonalHelper(0, 0, leftDiagonals[idx]);
    }

    private void initLeftDiagonalHelper(int posX, int posY, Position[] diagonal) {
        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] = gameMatrix[posX + i][posY + i];
        }
    }

    private void initRightDiagonals() {
        int idx = 0;
        int i = 2;
        while (i < problemSize - 1) {
            rightDiagonals[idx] = new Position[i + 1];
            initRightDiagonalHelper(i, 0, rightDiagonals[idx]);

            rightDiagonals[++idx] = new Position[i + 1];
            initRightDiagonalHelper(problemSize - 1, (problemSize - 1) - i, rightDiagonals[idx]);

            idx++;
            i++;
        }
        rightDiagonals[idx] = new Position[problemSize];
        initRightDiagonalHelper(problemSize - 1, 0, rightDiagonals[idx]);
    }

    private void initRightDiagonalHelper(int posX, int posY, Position[] diagonal) {
        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] = gameMatrix[posX - i][posY + i];
        }
    }

    private void initPositionLines() {
        for (int i = 0; i < problemSize; i++) {
            for (int j = 0; j < problemSize; j++) {
                Position[][] lines;
                Position[] leftDiagonal = findDiagonal(i, j, leftDiagonals);
                Position[] rightDiagonal = findDiagonal(i, j, rightDiagonals);
                if (leftDiagonal != null && rightDiagonal != null) {
                    lines = new Position[4][];
                    lines[0] = gameMatrix[i];
                    lines[1] = columns[j];
                    lines[2] = leftDiagonal;
                    lines[3] = rightDiagonal;
                } else if (leftDiagonal != null) {
                    lines = new Position[3][];
                    lines[0] = gameMatrix[i];
                    lines[1] = columns[j];
                    lines[2] = leftDiagonal;
                } else if (rightDiagonal != null) {
                    lines = new Position[3][];
                    lines[0] = gameMatrix[i];
                    lines[1] = columns[j];
                    lines[2] = rightDiagonal;
                } else {
                    lines = new Position[2][];
                    lines[0] = gameMatrix[i];
                    lines[1] = columns[j];
                }
                gameMatrix[i][j].setLines(lines);
            }
        }
    }

    private Position[] findDiagonal(int posX, int posY, Position[][] diagonals) {
        for (int i = 0; i < diagonals.length; i++) {
            for (int j = 0; j < diagonals[i].length; j++) {
                if (diagonals[i][j].getPosX() == posX && diagonals[i][j].getPosY() == posY) {
                    return diagonals[i];
                }
            }
        }
        return null;
    }

    public Position randomPosition() {
        return emptyPositions.get(random.nextInt(emptyPositions.size()));
    }

    public Position getPosition(int posX, int posY) {
        return gameMatrix[posX][posY];
    }

    public void fillPosition(int posX, int posY, boolean filled) {
        if (filled) {
            gameMatrix[posX][posY].fillAndRemoveFromEmpties();
        } else {
            gameMatrix[posX][posY].blankAndAddToEmpties();
        }
    }

    public void updateMatrix(boolean[][] matrix) {
        for (int i = 0; i < problemSize; i++) {
            for (int j = 0; j < problemSize; j++) {
                fillPosition(i, j, matrix[i][j]);
            }
        }
    }

    public Position calculatePosition() {
        Position positionFromRow;
        for (int row = 0; row < problemSize; row++) {
            if ((positionFromRow = calculatePositionHelper(gameMatrix[row])) != null) {
                return positionFromRow;
            }
        }

        Position positionFromColumn;
        for (int column = 0; column < problemSize; column++) {
            if ((positionFromColumn = calculatePositionHelper(columns[column])) != null) {
                return positionFromColumn;
            }
        }

        Position positionFromDiagonal;
        for (int i = problemSize - 1; i >= 0; i--) {
            if ((positionFromDiagonal = calculatePositionHelper(leftDiagonals[i])) != null) {
                return positionFromDiagonal;
            } else if ((positionFromDiagonal = calculatePositionHelper(rightDiagonals[i])) != null) {
                return positionFromDiagonal;
            }
        }
        return randomPosition();
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
        return getPosition(posX, posY);
    }
}
