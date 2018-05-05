package api;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import selector.HeuristicSelector;
import selector.PositionSelector;
import selector.RandomSelector;

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

        RandomSelector randomSelector = new RandomSelector(game);
        PositionSelector selector = new HeuristicSelector(randomSelector, game);

        Position pos = selector.nextPosition();
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

        RandomSelector randomSelector = new RandomSelector(game);
        PositionSelector selector = new HeuristicSelector(randomSelector, game);

        Position pos = selector.nextPosition();
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

        RandomSelector randomSelector = new RandomSelector(game);
        PositionSelector selector = new HeuristicSelector(randomSelector, game);

        Position pos = selector.nextPosition();
        assertEquals(pos, new Position(2, 2, false, game));
    }
}
