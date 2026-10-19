
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player(1, "Dhanya");
        Player p2 = new Player(2, "Priya");

        List<Player> players = Arrays.asList(p1, p2);

        Board board = new Board(100);

        board.addSnake(new Snake(25, 10));
        board.addSnake(new Snake(99, 40));

        board.addLadder(new Ladder(5, 20));
        board.addLadder(new Ladder(15, 35));

        Game game = new Game(players, board);
        game.startGame();
    }
}
