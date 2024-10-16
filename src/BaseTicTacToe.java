import java.util.*;

/*
BaseTicTacToe is a class that implements the TicTacToe interface.
It is a base class that can be extended to create different types of TicTacToe games.
It contains the basic functionality of a TicTacToe game, such as playing the game, checking for a win, and displaying the board.
 */

public class BaseTicTacToe implements TicTacToe {

    private Player player1;
    private Player player2;
    private Team team1;
    private Team team2;
    private BaseBoard baseBoard;
    private int size;
    private int turn;

    private final char X = 'X';
    private final char O = 'O';

    public BaseTicTacToe(Player player1, Player player2, Team team1, Team team2, BaseBoard board, int size) {
        this.player1 = player1;
        this.player2 = player2;
        this.team1 = team1;
        this.team2 = team2;
        this.baseBoard = board;
        this.size = size;
    }

    public BaseTicTacToe(Player player1, Player player2, int size) {
        this.player1 = player1;
        this.player2 = player2;
        this.size = size;
    }

    public BaseTicTacToe(Team team1, Team team2, int size) {
        this.team1 = team1;
        this.team2 = team2;
        this.size = size;
    }

    public void playGame_Single() {
        boolean continueGame = true;
        baseBoard = new BaseBoard(size);
        while (continueGame) {
            baseBoard.displayBoard();
            turn = 1;
            while (true) {
                if (turn % 2 == 1) {
                    System.out.println(player1.getName() + "'s turn");
                    baseBoard.fillUnit(player1.getSymbol());
                }
                else {
                    System.out.println(player2.getName() + "'s turn");
                    baseBoard.fillUnit(player2.getSymbol());
                }
                baseBoard.displayBoard();
                if (baseBoard.checkWin()) {
                    if (turn % 2 == 1) {
                        System.out.println(player1.getName() + " wins!");
                        player1.incrementWins();
                        player2.incrementLosses();
                    }
                    else {
                        System.out.println(player2.getName() + " wins!");
                        player2.incrementWins();
                        player1.incrementLosses();
                    }
                    break;
                }
                if (turn == size * size) {
                    System.out.println("It's a draw!");
                    break;
                }
                turn++;
            }
            System.out.println("Do you want to play again? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.next();
            if (response.equalsIgnoreCase("N")) {
                continueGame = false;
                System.out.println(player1.getName() + " winrate: " + player1.getWinRate());
                System.out.println(player2.getName() + " winrate: " + player2.getWinRate());
            }
        }
    }

    public void playGame_Team() {
        boolean continueGame = true;
        baseBoard = new BaseBoard(size);
        Player currentPlayer1 = team1.randomPlayer();
        Player currentPlayer2 = team2.randomPlayer();
        while (continueGame) {
            baseBoard.displayBoard();
            turn = 1;
            System.out.println("Team1's player: " + currentPlayer1.getName());
            System.out.println("Team2's player: " + currentPlayer2.getName());
            while (true) {
                if (turn % 2 == 1) {
                    currentPlayer1 = team1.switchPlayer(team1, currentPlayer1);
                    System.out.println(currentPlayer1.getName() + "'s turn");
                    baseBoard.fillUnit(currentPlayer1.getSymbol());
                }
                else {
                    currentPlayer2 = team2.switchPlayer(team2, currentPlayer2);
                    System.out.println(currentPlayer2.getName() + "'s turn");
                    baseBoard.fillUnit(currentPlayer2.getSymbol());
                }
                baseBoard.displayBoard();
                if (baseBoard.checkWin()) {
                    if (turn % 2 == 1) {
                        System.out.println("Team 1 wins!");
                        team1.incrementWins();
                        team2.incrementLosses();
                    }
                    else {
                        System.out.println("Team 2 wins!");
                        team2.incrementWins();
                        team1.incrementLosses();
                    }
                    break;
                }
                if (turn == size * size) {
                    System.out.println("It's a draw!");
                    break;
                }
                turn++;
            }
            System.out.println("Do you want to play again? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.next();
            if (response.equalsIgnoreCase("N")) {
                continueGame = false;
                team1.printWinRate();
                team2.printWinRate();
            }
        }
    }
}
