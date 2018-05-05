package selector;

import api.Position;

public interface PositionSelector {

    void init();

    Position nextPosition();

    boolean hasNext();
}
