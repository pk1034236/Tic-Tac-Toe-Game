import java.util.Scanner;
public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain) {
            char[][] board = initializeBoard();
            char currentPlayer = 'X';

            boolean gameEnded = false;
            while (!gameEnded) {
                displayBoard(board);
                int[] move = getPlayerMove(scanner, currentPlayer);
                makeMove(board, move[0], move[1], currentPlayer);

                if (checkWin(board, currentPlayer)) {
                    displayBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull(board)) {
                    displayBoard(board);
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Play again? (Y/N): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("Y");
        }

        scanner.close();
    }

    public static char[][] initializeBoard() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static void displayBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static int[] getPlayerMove(Scanner scanner, char currentPlayer) {
        int[] move = new int[2];
        System.out.print("Player " + currentPlayer + ", enter your move (row [0-2] and column [0-2]): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();
        return move;
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static void makeMove(char[][] board, int row, int col, char currentPlayer) {
        if (isValidMove(board, row, col)) {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move. Try again.");
            int[] move = getPlayerMove(new Scanner(System.in), currentPlayer);
            makeMove(board, move[0], move[1], currentPlayer);
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check main diagonal
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Check anti-diagonal
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
