import java.util.Random;

public class NormalDice implements Dice{
    private Random random = new Random();

    @Override
    public int roll(){
        return random.nextInt(6)+1;
    }
}