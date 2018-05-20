package depth;

import api.Game;

public final class ProblemSizeDependingDepth implements Depth {

    private Game game;

    private int depth;

    public ProblemSizeDependingDepth(Game game) {
        this.game = game;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public int initDepth() {
        if (game.getProblemSize() >= 100) {
            depth = 1;
        } else if (game.getProblemSize() > 40 && game.getProblemSize() < 100) {
            depth = 2;
        } else {
            depth = 3;
        }
        return depth;
    }
}
