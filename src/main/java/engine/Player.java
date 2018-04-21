package engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Player {

    Position currentPosition;
    int score;

}
