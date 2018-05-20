package selector;

import java.util.List;

import api.Game;
import api.Position;

public abstract class PositionSelector {

    protected Game game;
    protected int poolSize;
    protected int emptiesAllowed;

    public PositionSelector(Game game, double rate, double rate2) {
        this.game = game;
        this.poolSize = (int) (game.getEmptyPositions().size() * rate);
        this.emptiesAllowed = Math.max(1, (int)(game.getEmptyPositions().size()*rate2));
    }

    public abstract List<Position> selectPositions(List<Position> visited);
}
