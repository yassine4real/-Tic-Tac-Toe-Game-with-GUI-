import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// yassine abbou , software engineering student & Code enthusiast & problem solver

public class TicTacToeGUI extends JFrame implements ActionListener {

    // 2D array of buttons representing the Tic Tac Toe grid
    private JButton[][] buttons = new JButton[3][3];

    // Boolean variable to keep track of whose turn it is (true for X, false for O)
    private boolean xTurn = true;

    public TicTacToeGUI() {
        // Set up the JFrame
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Initialize the buttons for the Tic Tac Toe grid
        initializeButtons();

        // Make the JFrame visible
        setVisible(true);
    }

    // Method to initialize the buttons for the Tic Tac Toe grid
    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    // ActionListener interface method implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("")) {
            if (xTurn) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.BLUE); // Set X color
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.RED); // Set O color
            }
            xTurn = !xTurn;
            if (checkForWinner()) {
                JOptionPane.showMessageDialog(this, "Game Over! " + (xTurn ? "O" : "X") + " wins!");
                if (!playAgain()) {
                    JOptionPane.showMessageDialog(this, "Thanks for playing!");
                    System.exit(0);
                } else {
                    resetGame();
                }
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                if (!playAgain()) {
                    JOptionPane.showMessageDialog(this, "Thanks for playing!");
                    System.exit(0);
                } else {
                    resetGame();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Move!");
        }
    }

    // Method to check for a winner by examining rows, columns, and diagonals
    private boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText()) &&
                    !buttons[0][j].getText().equals("")) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    // Method to check if the board is full (i.e., a draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to reset the game
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK); // Reset text color
            }
        }
        xTurn = true; // Reset turn to X
    }

    // Method to prompt the user if they want to play again
    private boolean playAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again",
                JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) {
        new TicTacToeGUI(); // Create an instance of TicTacToeGUI to start the game
    }
}
