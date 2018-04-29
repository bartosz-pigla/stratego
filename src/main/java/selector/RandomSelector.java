package selector;

import java.util.List;
import java.util.Random;

import api.Position;

public final class RandomSelector implements PositionSelector {

    private List<Position> emptyPositions;
    private Random random = new Random();

    public RandomSelector(List<Position> emptyPositions) {
        this.emptyPositions = emptyPositions;
    }

    @Override
    public Position nextPosition() {
        return emptyPositions.get(random.nextInt(emptyPositions.size()));
    }

    @Override
    public boolean hasNext() {
        return emptyPositions.size()>0;
    }
}
