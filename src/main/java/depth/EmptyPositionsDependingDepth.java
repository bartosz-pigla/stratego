package depth;

import api.Game;
import lombok.Getter;

public final class EmptyPositionsDependingDepth implements Depth {

    private Game game;

    @Getter
    private int depth;

    public EmptyPositionsDependingDepth(Game game) {
        this.game = game;
    }

    @Override
    public int initDepth() {
        double rate = game.getEmptyPositions().size() / game.getProblemSize() * game.getProblemSize();
        if (rate >= 0.7) {
            depth = 1;
        } else if (rate >= 0.3) {
            depth = 2;
        } else {
            depth = 3;
        }
        return depth;
    }
}
