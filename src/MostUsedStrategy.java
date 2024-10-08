import java.util.Arrays;

class MostUsedStrategy implements Strategy {
    private final int[] choiceCounts = new int[3];

    @Override
    public String determineMove(String playerChoice) {
        int index = Arrays.asList(RockPaperScissorsFrame.choices).indexOf(playerChoice);
        choiceCounts[index]++;

        int mostUsedIndex = 0;
        for (int i = 1; i < choiceCounts.length; i++) {
            if (choiceCounts[i] > choiceCounts[mostUsedIndex]) {
                mostUsedIndex = i;
            }
        }
        int winningIndex = (mostUsedIndex + 1) % 3;
        return RockPaperScissorsFrame.choices[winningIndex];
    }

    @Override
    public String getName() {
        return "Most Used";
    }
}

