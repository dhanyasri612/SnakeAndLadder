import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private GameState state;
    private Player winner;
    private final Scanner scanner;

    public Game(List<Player> players, Board board) {
        if (players == null || players.size() < 2
                || players.size() > 4) {
            throw new IllegalArgumentException(
                "Game requires 2 to 4 players"
            );
        }

        if (board == null) {
            throw new IllegalArgumentException(
                "Board cannot be null"
            );
        }

        this.players = players;
        this.board = board;
        this.currentPlayerIndex = 0;
        this.state = GameState.WAITING;
        this.winner = null;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        if (state != GameState.WAITING) {
            return;
        }

        state = GameState.IN_PROGRESS;
        System.out.println("Welcome to Snakes and Ladders!");

        while (state == GameState.IN_PROGRESS) {
            playTurn();
        }

        System.out.println("Winner: " + winner.getName());
        // Main owns System.in; don't close the Scanner here.
    }

    public void playTurn() {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        System.out.println("\n" + player.getName() + "'s turn");
        System.out.println("Current position: " + position);

        showNearbyObjects(position);

        Dice dice = chooseDice();

        int steps = dice.roll();
        System.out.println("You rolled: " + steps);

        movePlayer(player, steps);

        System.out.println(
            "Your final position: " + player.getPosition()
        );

        if (!checkWin(player)) {
            nextPlayer();
        }
    }

    private void showNearbyObjects(int position) {
        int range = 6;

        List<Snake> snakes =
            board.getNearbySnakes(position, range);
        List<Ladder> ladders =
            board.getNearbyLadders(position, range);

        System.out.println("Nearby snakes:");

        if (snakes.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Snake snake : snakes) {
                System.out.println(
                    "  Snake at " + snake.getHead()
                    + " goes down to " + snake.getTail()
                );
            }
        }

        System.out.println("Nearby ladders:");

        if (ladders.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Ladder ladder : ladders) {
                System.out.println(
                    "  Ladder at " + ladder.getBottom()
                    + " goes up to " + ladder.getTop()
                );
            }
        }
    }

    private Dice chooseDice() {
        while (true) {
            System.out.println("\nChoose your dice:");
            System.out.println("1. Normal dice (1-6)");
            System.out.println("2. Odd dice (1, 3, 5)");
            System.out.println("3. Even dice (2, 4, 6)");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    return new NormalDice();
                case "2":
                    return new OddNumberDice();
                case "3":
                    return new EvenNumberDice();
                default:
                    System.out.println(
                        "Invalid choice. Enter 1, 2, or 3."
                    );
            }
        }
    }

    public void movePlayer(Player player, int steps) {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + steps;

        if (newPosition > board.getSize()) {
            System.out.println(
                "You need an exact roll to reach "
                + board.getSize() + ". You stay put."
            );
            return;
        }

        int finalPosition = board.getFinalPosition(newPosition);

        if (finalPosition < newPosition) {
            System.out.println("Oops! A snake!");
        } else if (finalPosition > newPosition) {
            System.out.println("Great! You climbed a ladder!");
        }

        player.setPosition(finalPosition);
    }

    public boolean checkWin(Player player) {
        if (player.getPosition() == board.getSize()) {
            winner = player;
            state = GameState.FINISHED;
            return true;
        }

        return false;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex =
            (currentPlayerIndex + 1) % players.size();
    }

    public GameState getGameState() {
        return state;
    }
}
