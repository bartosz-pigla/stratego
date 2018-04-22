package engine;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameTest {

    @Test
    public void shouldReturnPositionFromLeftDiagonal() {
        Game game = new Game(5);
        boolean[][] matrix = {
                { false, false, true, false, false },
                { false, false, false, true, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false }
        };

        game.updateMatrix(matrix);

        Position pos = game.calculatePosition();
        assertEquals(pos, new Position(2, 4, false, game));
    }

    @Test
    public void shouldReturnPositionFromRow() {
        Game game = new Game(5);
        boolean[][] matrix = {
                { false, false, false, false, false },
                { false, false, false, false, false },
                { true, true, false, true, true },
                { false, false, false, false, false },
                { false, false, false, false, false }
        };

        game.updateMatrix(matrix);

        Position pos = game.calculatePosition();
        assertEquals(pos, new Position(2, 2, false, game));
    }

    @Test
    public void shouldReturnPositionFromRowWhenManyDiagonals() {
        Game game = new Game(5);
        boolean[][] matrix = {
                { false, false, true, false, false },
                { false, false, false, false, false },
                { true, true, false, true, true },
                { false, false, false, false, false },
                { false, false, false, false, false }
        };

        game.updateMatrix(matrix);

        Position pos = game.calculatePosition();
        assertEquals(pos, new Position(2, 2, false, game));
    }
}
