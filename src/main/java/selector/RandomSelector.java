package selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import api.Game;
import api.Position;

public final class RandomSelector extends PositionSelector {

    private Random random = new Random();

    public RandomSelector(Game game, double rate, double rate2) {
        super(game, rate, rate2);
    }

    public RandomSelector(Game game) {
        super(game, 1, 1);
    }

    @Override
    public List<Position> selectPositions(List<Position> visited) {
        List<Position> positions = new ArrayList<>(poolSize);
        Position currentPosition;
        while (poolSize > positions.size()) {
            currentPosition = selectPosition(visited);
            if (currentPosition != null) {
                positions.add(currentPosition);
            } else {
                break;
            }
        }
        return positions;
    }

    public Position selectPosition(List<Position> visited) {
        if (game.getEmptyPositions().size() == 0 || game.getEmptyPositions().size() == visited.size()) {
            return null;
        }

        Position randomizedPos;
        do {
            randomizedPos = game.getEmptyPositions().get(random.nextInt(game.getEmptyPositions().size()));

        } while (visited.contains(randomizedPos));
        return randomizedPos;
    }

}
