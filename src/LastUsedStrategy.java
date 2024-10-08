import java.util.Arrays;
import java.util.Random;

class LastUsedStrategy implements Strategy {
    private final String lastPlayerChoice = null;
    private final Random random = new Random();

    @Override
    public String determineMove(String playerChoice) {
        if (lastPlayerChoice != null) {
            // Determine the winning choice against the last used
            int lastIndex = Arrays.asList(RockPaperScissorsFrame.choices).indexOf(lastPlayerChoice);
            int winningIndex = (lastIndex + 1) % 3; // Cycle through RockPaperScissorsFrame.choices
            return RockPaperScissorsFrame.choices[winningIndex];
        } else {
            // First round, return a random choice
            return RockPaperScissorsFrame.choices[random.nextInt(RockPaperScissorsFrame.choices.length)];
        }
    }

    @Override
    public String getName() {
        return "Last Used";
    }
}
