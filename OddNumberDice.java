import java.util.Random;

public class OddNumberDice implements Dice{
    private Random random = new Random();

    @Override
    public int roll(){
        int[] evenNumbers = {1,3,5};
        return evenNumbers[random.nextInt(3)];
    }
}