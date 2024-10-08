import java.util.Arrays;
import java.util.Random;

class CheatStrategy implements Strategy {
    private final Random random = new Random();


    @Override
    public String determineMove(String playerChoice) {
        if (random.nextDouble() < 0.1) {
            // Cheat 10% of the time
            int winningIndex = (Arrays.asList(RockPaperScissorsFrame.choices).indexOf(playerChoice) + 1) % 3;
            return RockPaperScissorsFrame.choices[winningIndex];
        } else {
            // Otherwise, play randomly
            return RockPaperScissorsFrame.choices[random.nextInt(RockPaperScissorsFrame.choices.length)];
        }
    }

    @Override
    public String getName() {
        return "Cheat";
    }
}