package depth;

import api.Game;
import lombok.Getter;

public final class DynamicDepth implements Depth {

    @Getter
    private int depth;

    private EmptyPositionsDependingDepth emptyPositionsDependingDepth;
    private ProblemSizeDependingDepth problemSizeDependingDepth;

    public DynamicDepth(Game game) {
        this.emptyPositionsDependingDepth = new EmptyPositionsDependingDepth(game);
        this.problemSizeDependingDepth = new ProblemSizeDependingDepth(game);
    }

    @Override
    public int initDepth() {
        return depth = Math.min(getMaxDepth(), emptyPositionsDependingDepth.initDepth() * problemSizeDependingDepth.initDepth());
    }
}
