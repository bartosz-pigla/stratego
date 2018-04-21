package engine;

import lombok.Getter;
import lombok.Setter;

@Getter
public final class Position {

    private int posX, posY;

    @Setter
    private boolean filled;

    private Game game;

    @Setter
    private Position[][] lines;

    public Position(int posX, int posY, boolean filled, Game game) {
        this.posX = posX;
        this.posY = posY;
        this.filled = filled;
        this.game = game;
    }

    public void fillAndRemoveFromEmpties() {
        filled = true;
        game.getEmptyPositions().remove(this);
    }

    public void blankAndAddToEmpties() {
        filled = false;
        game.getEmptyPositions().add(this);
    }

    public int calculateScore(){
        int score=0;
        for(int i=0;i<lines.length;i++){
            score+=lines[i].length;
            for(int j=0;j<lines[i].length;j++){
                if(!lines[i][j].isFilled()){
                    score-=lines[i].length;
                    break;
                }
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", filled=" + filled +
                '}';
    }
}
