package selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import api.Game;
import api.Position;

public final class RandomSelector implements PositionSelector {

    private Game game;
    private Random random = new Random();
    private List<Position> notVisited;

    public RandomSelector(Game game) {
        this.game = game;
        this.notVisited = new ArrayList<>(game.getProblemSize());
    }

    @Override
    public Position nextPosition() {
        //return notVisited.get(random.nextInt(emptyPositions.size()));
        return null;
    }

    @Override
    public boolean hasNext() {
        return notVisited.size() > 0;
    }

    @Override
    public void init() {
        this.notVisited.clear();
        this.notVisited.addAll(game.getEmptyPositions());
    }


}
