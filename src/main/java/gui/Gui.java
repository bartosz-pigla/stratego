package gui;

import api.Position;
import lombok.Value;

@Value
public final class Gui {

    char filled;
    char empty;
    int problemSize;
    StringBuilder sb;

    public Gui(char filled, char empty, int problemSize) {
        this.filled = filled;
        this.empty = empty;
        this.problemSize = problemSize;
        this.sb = new StringBuilder(2 * problemSize * problemSize);
    }

    public void printStateOfGame(Position[][] gameMatrix) {
        sb.delete(0, sb.length());
        for (int i = 0; i < problemSize; i++) {
            for (int j = 0; j < problemSize - 1; j++) {
                sb.append(gameMatrix[i][j].isFilled() ? filled : empty).append(' ');
            }
            sb.append(gameMatrix[i][problemSize - 1].isFilled() ? filled : empty).append('\n');
        }
        System.out.println(sb.toString());
    }
}
