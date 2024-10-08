import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random = new Random();

    @Override
    public String determineMove(String playerChoice) {
        return RockPaperScissorsFrame.choices[random.nextInt(RockPaperScissorsFrame.choices.length)];
    }

    @Override
    public String getName() {
        return "Random";
    }
}