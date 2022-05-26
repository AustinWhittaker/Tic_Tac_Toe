/*
Name: Austin Whittaker
Date: 9/30/21
Class: CSC 331-001
Purpose: In this class the program TicTacToeWhittakerAustin.java is used using this
         class to play the game of Tic-Tac-Toe. A private 2 dimensional array called
         board is initialize and created with the enum empty within the constructor to
         fill the board with empty spaces. This class is used to access the private 2d
         array of board.
*/

public class TicTacToe {
    // Class used to play the game of Tic-Tac-Toe.

    enum Choice {
        //Establish enum choices of X, O, and EMPTY.
        X,
        O,
        EMPTY;
    }
    private String [][] board = new String[3][3];   // declare or private 2d array.


    public TicTacToe() {
        // When TicTacToe constructor is called it creates a playing board with empty spaces.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                this.board[i][j] = String.valueOf(Choice.EMPTY);
            }
        }
        this.board = board;
    }

    public void setBoard(String [][] board) {
        // setter
        this.board = board;

    }
    public String [][] getBoard(){
        // getter
        return board;
    }
}