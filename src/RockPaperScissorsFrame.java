import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    // GUI Components
    private final JPanel buttonPanel;
    private final JButton rockButton, paperButton, scissorsButton, quitButton;
    private final JPanel statsPanel;
    private final JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private final JTextField playerWinsField, computerWinsField, tiesField;
    private final JTextArea resultsArea;

    // Game Variables
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    public static final String[] choices = {"Rock", "Paper", "Scissors"};
    private final Random random = new Random();
    private Strategy currentStrategy;

    public RockPaperScissorsFrame() {
        // Set up the JFrame
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Create the button panel
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Make Your Choice"));

        // Create the buttons with icons

        ImageIcon rockButtonIcon = new ImageIcon("C:\\Users\\becke\\IdeaProjects\\Lab 05 Rock Paper Scissors\\src\\rock.png");
        Image resizedImageR = rockButtonIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconR = new ImageIcon(resizedImageR);

        rockButton = new JButton(resizedIconR);
        rockButton.setPreferredSize(new Dimension(50, 50));

        ImageIcon paperButtonIcon = new ImageIcon("C:\\Users\\becke\\IdeaProjects\\Lab 05 Rock Paper Scissors\\src\\paper.png");
        Image resizedImageP = paperButtonIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconP = new ImageIcon(resizedImageP);

        paperButton = new JButton(resizedIconP);
        paperButton.setPreferredSize(new Dimension(50, 50));

        ImageIcon scissorsButtonIcon = new ImageIcon("C:\\Users\\becke\\IdeaProjects\\Lab 05 Rock Paper Scissors\\src\\scissors.png");
        Image resizedImageS = scissorsButtonIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconS = new ImageIcon(resizedImageS);

        scissorsButton = new JButton(resizedIconS);
        scissorsButton.setPreferredSize(new Dimension(50, 50));

        ImageIcon quitButtonIcon = new ImageIcon("C:\\Users\\becke\\IdeaProjects\\Lab 05 Rock Paper Scissors\\src\\quit.png");
        Image resizedImageQ = quitButtonIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconQ = new ImageIcon(resizedImageQ);

        quitButton = new JButton(resizedIconQ);
        quitButton.setPreferredSize(new Dimension(50, 50));

        // Add buttons to the button panel
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Create the stats panel
        statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));

        // Create the labels and text fields for stats
        playerWinsLabel = new JLabel("Player Wins:");
        playerWinsField = new JTextField(String.valueOf(playerWins), 5);
        playerWinsField.setEditable(false);
        computerWinsLabel = new JLabel("Computer Wins:");
        computerWinsField = new JTextField(String.valueOf(computerWins), 5);
        computerWinsField.setEditable(false);
        tiesLabel = new JLabel("Ties:");
        tiesField = new JTextField(String.valueOf(ties), 5);
        tiesField.setEditable(false);

        // Add the labels and text fields to the stats panel
        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);

        // Create the results panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Game Results"));
        resultsArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the panels to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.WEST);
        add(resultsPanel, BorderLayout.CENTER);

        // Set up the action listeners for the buttons
        rockButton.addActionListener(new ButtonListener("Rock"));
        paperButton.addActionListener(new ButtonListener("Paper"));
        scissorsButton.addActionListener(new ButtonListener("Scissors"));
        quitButton.addActionListener(_ -> System.exit(0));

        // Initialize the computer strategy

        int randomIndex = random.nextInt(5); // Generates a random number between 0 and 4 (inclusive)


        switch (randomIndex) {
            case 0:
                currentStrategy = new CheatStrategy();
                break;
            case 1:
                currentStrategy = new LastUsedStrategy();
                break;
            case 2:
                currentStrategy = new LeastUsedStrategy();
                break;
            case 3:
                currentStrategy = new MostUsedStrategy();
                break;
            case 4:
                currentStrategy = new RandomStrategy();
                break;
        }

        // Make the frame visible
        setVisible(true);
    }


    // Inner class for button listener
    private class ButtonListener implements ActionListener {
        private final String playerChoice;

        public ButtonListener(String playerChoice) {
            this.playerChoice = playerChoice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the computer's choice
            int randomIndex = random.nextInt(5); // Generates a random number between 0 and 4 (inclusive)


            switch (randomIndex) {
                case 0:
                    currentStrategy = new CheatStrategy();
                    break;
                case 1:
                    currentStrategy = new LastUsedStrategy();
                    break;
                case 2:
                    currentStrategy = new LeastUsedStrategy();
                    break;
                case 3:
                    currentStrategy = new MostUsedStrategy();
                    break;
                case 4:
                    currentStrategy = new RandomStrategy();
                    break;
            }
            String computerChoice = currentStrategy.determineMove(playerChoice);

            // Determine the winner
            String result = determineWinner(playerChoice, computerChoice);

            // Update the results area
            resultsArea.append(result + "\n");

            // Update the stats
            updateStats();
        }

        // Helper method to determine the winner
        private String determineWinner(String playerChoice, String computerChoice) {
            if (playerChoice.equals(computerChoice)) {
                ties++;
                return playerChoice + " ties with " + computerChoice + " (Tie)";
            } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                    (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                    (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
                playerWins++;
                return playerChoice + " beats " + computerChoice + " (Player wins " + currentStrategy.getName() + ")";
            } else {
                computerWins++;
                return computerChoice + " beats " + playerChoice + " (Computer wins " + currentStrategy.getName() + ")";
            }
        }

        // Helper method to update the stats
        private void updateStats() {
            playerWinsField.setText(String.valueOf(playerWins));
            computerWinsField.setText(String.valueOf(computerWins));
            tiesField.setText(String.valueOf(ties));
        }
    }
}
