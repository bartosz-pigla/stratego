package gui;

import java.util.List;
import java.util.Scanner;

import api.Game;
import api.Player;
import api.Position;
import lombok.Value;

@Value
public final class Gui {

    char filled;
    char empty;
    int problemSize;
    StringBuilder sb;

    Gui(char filled, char empty, int problemSize) {
        this.filled = filled;
        this.empty = empty;
        this.problemSize = problemSize;
        this.sb = new StringBuilder(2 * problemSize * problemSize);
    }

    public void printSummary(List<Player> players) {
        System.out.println("PODSUMOWANIE");
        players.stream().sorted((p1, p2) -> p2.getScore() - p1.getScore()).forEach(p ->
                System.out.println(p.getName() + ": " + p.getScore()));
    }

    public void printScoreOfPlayer(Player player) {
        System.out.println("[" + player.getName() + "]: WYNIK: " + player.getScore());
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

    public Position getPosition(Game game, Player player) {
        System.out.println("[" + player.getName() + "]: Proszę wpisać współrzędne");
        System.out.println("Nr. wiersza: ");
        int row = getNumber(game);
        System.out.println("Nr. kolumny: ");
        int column = getNumber(game);
        return game.getPosition(row, column);
    }

    private int getNumber(Game game) {
        Scanner sc = new Scanner(System.in);
        try {
            int number = sc.nextInt();
            if (number < 0 && number >= game.getProblemSize()) {
                System.out.println("Proszę wpisać wartość od 0 do " + game.getProblemSize());
                return getNumber(game);
            } else {
                return number;
            }
        } catch (Exception e) {
            System.out.println("Błąd");
            return getNumber(game);
        }
    }
}
