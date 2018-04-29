package selector;

import api.Position;

public interface PositionSelector {

    Position nextPosition();
    boolean hasNext();
}
