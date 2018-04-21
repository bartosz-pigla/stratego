package engine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScoreTest {

    @Test
    public void shuldSumRowAndColumn() {
        Game game = new Game(4);
        boolean[][] matrix = {
                {true,false,false,false},
                {true,false,false,false},
                {false,true,true,true},
                {true,false,false,false}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(2,0);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 8);
    }

    @Test
    public void shouldSumRowColumnLeftDiagonal() {
        Game game = new Game(4);
        boolean[][] matrix = {
                {true,false,false,true},
                {true,false,true,false},
                {true,true,false,false},
                {false,true,true,true}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(3,0);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 12);
    }

    @Test
    public void shouldSumRowColumnRightDiagonal() {
        Game game = new Game(4);
        boolean[][] matrix = {
                {true,true,true,false},
                {true,false,true,true},
                {true,true,false,true},
                {true,true,true,true}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(0,3);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 12);
    }

    @Test
    public void shouldSumRowColumnLeftAndRightDiagonal() {
        Game game = new Game(5);
        boolean[][] matrix = {
                {true,false,true,false,true},
                {false,true,true,true,false},
                {true,true,false,true,true},
                {false,true,true,true,false},
                {true,false,true,false,true}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(2,2);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 20);
    }

    @Test
    public void shouldNotSumWhenRowNotFilled(){
        Game game = new Game(4);
        boolean[][] matrix = {
                {false,false,false,false},
                {true,true,false,false},
                {false,false,false,false},
                {false,false,false,false}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(1,2);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 0);
    }

    @Test
    public void shouldNotSumWhenColumnNotFilled(){
        Game game = new Game(4);
        boolean[][] matrix = {
                {false,false,true,false},
                {false,false,false,false},
                {false,false,true,false},
                {false,false,false,false}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(1,2);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 0);
    }

    @Test
    public void shouldNotSumWhenLeftDiagonalNotFilled(){
        Game game = new Game(4);
        boolean[][] matrix = {
                {true,false,false,false},
                {false,false,false,false},
                {false,false,true,false},
                {false,false,false,false}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(1,1);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 0);
    }

    @Test
    public void shouldNotSumWhenRightDiagonalNotFilled(){
        Game game = new Game(4);
        boolean[][] matrix = {
                {false,false,false,true},
                {false,false,false,false},
                {false,true,false,false},
                {false,false,false,false}
        };

        game.updateMatrix(matrix);
        Position position = game.getPosition(1,2);
        position.fillAndRemoveFromEmpties();
        assertEquals(position.calculateScore(), 0);
    }
}
