package depth;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public final class DefaultDepth implements Depth {

    int depth;

    @Override
    public int initDepth() {
        return depth;
    }
}
