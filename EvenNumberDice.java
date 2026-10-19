import java.util.Random;

public class EvenNumberDice implements Dice{
    private Random random = new Random();

    @Override
    public int roll(){
        int[] evenNumbers = {2,4,6};
        return evenNumbers[random.nextInt(3)];
    }
}