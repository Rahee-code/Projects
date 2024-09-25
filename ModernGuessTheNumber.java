package New1;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.Random;

public class ModernGuessTheNumber {

    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 100;
    private static final Random RANDOM = new Random();
    private JFrame frame;
    private JButton startButton;
    private JButton infoButton;
    private JButton exitButton;

    public static void main(String[] args) {
        // Set the Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the game
        new ModernGuessTheNumber().initialize();
    }

    private void initialize() {
        frame = new JFrame("Guess the Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0, 128, 128)); // teal

        JLabel welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Create buttons
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50)); // Set button size
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.addActionListener(e -> startGame());

        infoButton = new JButton("Information");
        infoButton.setPreferredSize(new Dimension(200, 50));
        infoButton.setFont(new Font("Arial", Font.BOLD, 18));
        infoButton.addActionListener(e -> showInformation());

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(infoButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showInformation() {
        String info = "Guess the Number Game:\n" +
                      "1. You will guess a number between " + RANGE_MIN + " and " + RANGE_MAX + ".\n" +
                      "2. You have " + MAX_ATTEMPTS + " attempts.\n" +
                      "3. Try to guess the number based on hints!\n" +
                      "4. Have fun!";
        JOptionPane.showMessageDialog(frame, info, "Game Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startGame() {
        int totalScore = 0;

        showMessage(String.format("Guess a number between %d and %d.\nYou have %d attempts.", RANGE_MIN, RANGE_MAX, MAX_ATTEMPTS));

        while (true) {
            int targetNumber = generateRandomNumber(RANGE_MIN, RANGE_MAX);
            int attempts = 0;

            while (attempts < MAX_ATTEMPTS) {
                Optional<Integer> guess = getUserGuess();

                if (guess.isEmpty()) {
                    showMessage("Game exited.");
                    return;
                }

                attempts++;
                String message = evaluateGuess(guess.get(), targetNumber, attempts);
                showMessage(message);

                if (guess.get() == targetNumber) {
                    int score = calculateScore(attempts);
                    totalScore += score;
                    showMessage(String.format("Congratulations! You've guessed the number: %d\nAttempts: %d\nScore: %d\nTotal Score: %d",
                            targetNumber, attempts, score, totalScore));
                    break;
                }
            }

            if (attempts == MAX_ATTEMPTS) {
                showMessage(String.format("Sorry! The number was: %d", targetNumber));
            }

            if (!playAgain()) {
                break;
            }
        }

        showMessage(String.format("Thanks for playing! Your total score: %d", totalScore));
        frame.dispose(); // Close the frame after the game ends
    }

    private Optional<Integer> getUserGuess() {
        String input = JOptionPane.showInputDialog("Enter your guess:");
        if (input == null) return Optional.empty();
        try {
            int guess = Integer.parseInt(input);
            if (guess < RANGE_MIN || guess > RANGE_MAX) {
                showMessage(String.format("Please guess a number within the range [%d - %d].", RANGE_MIN, RANGE_MAX));
                return getUserGuess();
            }
            return Optional.of(guess);
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number.");
            return getUserGuess();
        }
    }

    private String evaluateGuess(int guess, int targetNumber, int attempts) {
        if (guess < targetNumber) {
            return String.format("Higher! Attempts left: %d", MAX_ATTEMPTS - attempts);
        } else if (guess > targetNumber) {
            return String.format("Lower! Attempts left: %d", MAX_ATTEMPTS - attempts);
        }
        return ""; // This will never be reached if guessed correctly
    }

    private boolean playAgain() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    private int generateRandomNumber(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    private int calculateScore(int attempts) {
        return Math.max(0, MAX_ATTEMPTS - attempts + 1);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
