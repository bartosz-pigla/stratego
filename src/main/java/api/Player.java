package api;

import algorithm.Algorithm;
import lombok.Data;

@Data
public final class Player {

    int score;
    Algorithm algorithm;

    public Player(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void play(){
        algorithm.play(this);
    }
}
