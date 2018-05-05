package api;

import algorithm.Algorithm;
import lombok.Data;

@Data
public final class Player {

    String name;
    int score;
    Algorithm algorithm;

    public Player(String name, Algorithm algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }

    public void play() {
        int score = algorithm.play(this);
        this.score += score;
    }
}
