package number.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGame extends JFrame implements ActionListener {
    private JTextField guessField;
    private JTextArea outputArea;
    private JLabel attemptsLabel;
    private JButton guessButton, restartButton;
    private int randomNumber;
    private int attempts;

    public NumberGame() {
        setTitle("Guess The Number");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(8, 8));
        panel.setBackground(new Color(250, 250, 240));

        JLabel title = new JLabel("Guess a number between 1 and 100");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout());
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.setBackground(new Color(200, 230, 255));
        restartButton = new JButton("Restart");
        restartButton.setBackground(new Color(255, 210, 210));

        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(restartButton);

        panel.add(inputPanel, BorderLayout.CENTER);

        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setForeground(Color.DARK_GRAY);
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        attemptsLabel = new JLabel("Attempts left: 10", SwingConstants.CENTER);
        panel.add(attemptsLabel, BorderLayout.WEST);

        add(panel);

        guessButton.addActionListener(this);
        restartButton.addActionListener(e -> restartGame());

        startNewGame();
    }

    private void startNewGame() {
        Random r = new Random();
        randomNumber = r.nextInt(100) + 1;
        attempts = 10;
        attemptsLabel.setText("Attempts left: " + attempts);
        outputArea.setText("");
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    private void restartGame() {
        startNewGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (attempts <= 0) {
            outputArea.append("No attempts left. The number was " + randomNumber + "\n");
            guessButton.setEnabled(false);
            return;
        }

        String input = guessField.getText().trim();
        if (!input.matches("\\d+")) {
            outputArea.append("Enter a valid number.\n");
            guessField.setText("");
            return;
        }

        int guess = Integer.parseInt(input);
        attempts--;
        attemptsLabel.setText("Attempts left: " + attempts);

        if (guess == randomNumber) {
            outputArea.append("You got it! in " + (10 - attempts) + " tries.\n");
            guessButton.setEnabled(false);
        } else if (guess > randomNumber) {
            outputArea.append("Too high. Try again.\n");
        } else {
            outputArea.append("Too low. Try again.\n");
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGame().setVisible(true);
        });
    }
}

    

