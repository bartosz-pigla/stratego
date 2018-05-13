package sorter;

import java.util.List;

import api.Position;

public final class DefaultSorter implements PositionsSorter {

    @Override
    public List<Position> sort(List<Position> positions) {
        return positions;
    }
}
