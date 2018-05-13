package selector;

import java.util.List;

import api.Game;
import api.Position;

public abstract class PositionSelector {

    protected Game game;
    protected int poolSize;

    public PositionSelector(Game game, double rate) {
        this.game = game;
        this.poolSize = (int) (game.getEmptyPositions().size() * rate);
    }

    public abstract List<Position> selectPositions(List<Position> visited);
}
