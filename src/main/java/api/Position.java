package api;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
public final class Position {

    private int posX, posY;

    @Setter
    private boolean filled;

    private Game game;

    @Setter
    private Position[][] lines;

    @Setter
    private int score;

    public Position(int posX, int posY, boolean filled, Game game) {
        this.posX = posX;
        this.posY = posY;
        this.filled = filled;
        this.game = game;
    }

    public void fillAndRemoveFromEmpties() {
        filled = true;
        game.getEmptyPositions().remove(this);
    }

    public void blankAndAddToEmpties() {
        filled = false;
        game.getEmptyPositions().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return posX == position.posX &&
                posY == position.posY;
    }

    @Override
    public int hashCode() {

        return Objects.hash(posX, posY);
    }

    @Override
    public String toString() {
        return "Position{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", filled=" + filled +
                '}';
    }
}
