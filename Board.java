import java.util.ArrayList;
import java.util.List;

public class Board{
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size){
        this.size = size;
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
    }

    public int getSize(){
        return size;
    }

    public void addSnake(Snake snake){
        snakes.add(snake);
    }

    public void addLadder(Ladder ladder){
        ladders.add(ladder);
    }

    public int getFinalPosition(int position){
        for(Snake snake:snakes){
            if(snake.getHead() == position){
                return snake.getTail();
            }
        }

        for(Ladder ladder:ladders){
            if(ladder.getBottom() == position){
                return ladder.getTop();
            }
        }

        return position;
    }

    public boolean isValidPosition(int position){
        return position >= 1 && position <= size;
    }


    public List<Snake> getNearbySnakes(int position, int range) {
        List<Snake> nearby = new ArrayList<>();

        for (Snake snake : snakes) {
            if (snake.getHead() > position &&
                snake.getHead() <= position + range) {
                nearby.add(snake);
            }
        }

        return nearby;
    }

    public List<Ladder> getNearbyLadders(int position, int range) {
        List<Ladder> nearby = new ArrayList<>();

        for (Ladder ladder : ladders) {
            if (ladder.getBottom() > position &&
                ladder.getBottom() <= position + range) {
                nearby.add(ladder);
            }
        }

        return nearby;
    }

}