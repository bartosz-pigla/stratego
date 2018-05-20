package depth;

public interface Depth {

    int MAX_DEPTH = 4;

    int initDepth();

    int getDepth();

    default int getMaxDepth() {
        return MAX_DEPTH;
    }
}
