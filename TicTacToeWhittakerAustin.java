/*
Name: Austin Whittaker
Date: 9/30/21
Class: CSC 331-001
Purpose: In this Program we create the game Tic-Tac-Toe. We welcome the player and ask if they would like
         to play against a friend or against a computer and which player they would like to be. The board is
         obtained from the private board from the class and then used to play the game. Once set in the Class
         it is then used in this program. The rules are displayed along with the empty board. If the player
         plays against another person they will each have a turn to place their move. If the move is already taken
         they will be prompted to try again until their move is within an empty spot. This will continue until
         either one of them wins or it is a draw. The winner will be displayed at the bottom of the screen or will
         notify that it was a draw. They will then be asked to play again. If the user plays against the Computer
         they will then be asked if they want to be player one or player 2. The player will then place their move
         before or after the computer depending on their player number. This will continue until there is a winner
         or if it is a draw. They will then be asked if they would like to play again.
*/


import java.util.Scanner;                           // Receive input for the move to be place.

public class TicTacToeWhittakerAustin {
    public static void main(String[] arg){
        // In Main is where the game is created and played with the use of the class TicTacToe.java


        int again = 0;                                   // Used to loop through if the player(s) want to continue playing
        while (again == 0){

            int whoVwho = playerSelect();               // User selects if they want to play 2 player or against a computer //
            int playerNumber = playerNumber();        // User selects if they would rather be player one or player two //
            TicTacToe board = new TicTacToe();

            String[][] freshBoard = board.getBoard();   // Board is created and
            board.setBoard(freshBoard);
            System.out.println();
            // Display rules & how to play.
            System.out.println("\n\n                                          Rules          \n"+
                    "                               '''''''''''''''''''''''''"+"\n\n"+
                    "     You will first be asked to enter the Row followed by the Column to place your move."+"\n"+
                    "      If the move that you wish to place is not \"EMPTY\", you will be asked to try again."+"\n"+
                    "               The first Player to get 3 in a row/below/diagonal will win."+"\n"+
                    "                         Otherwise it will be considered a draw."+"\n"+
                    "                Rows begin from zero up to two going from top to bottom. "+"\n"+
                    "               Columns begin from zero up to two going from left to right. \n"+
                    "For example if you want to place your move in the center it will be "+
                    "considered row 1, column 1\n\n"+
                    "---------------------------------------------------------------------------------------------------");
            currentBoard(freshBoard);                  // Show empty board
            again = play(freshBoard,whoVwho,playerNumber, again);   // where the magic happens

        }
        System.out.println("\n\nTHANKS FOR PLAYING! SEE YOU NEXT TIME!");
//
    }

    private static int play(String[][] freshBoard, int whoVwho, int playerNumber, int again) {
        // This is the playing field for the game where everything is called and calculating to
        // determine a winner or draw.


        int moveCount = 1;
        if (whoVwho == 1){                         // if the player is playing with another person.
            boolean winner = false;
            playerMoveSelection(freshBoard, moveCount);    // Call to get the players move choice.
            moveCount++;                                   // increase the move count.

            while (winner == false){                            // until there is a winner continue to play
                System.out.println("Move Count: "+moveCount);
                playerMoveSelection(freshBoard, moveCount);
                moveCount++;
                winner = checkForWinner(freshBoard);       // check to see if any of the possibilities of winning exist
                if (moveCount >= 10){                      // if there are no more moves break out of the loop
                    winner = true;
                }
            }
            winner= checkForWinner(freshBoard);            // check and make sure someone didn't win on the last move.
            if (moveCount>=10 & winner==false){
                System.out.println("IT'S A DRAW!");        // if no one one it is a draw
            }
            else if(moveCount%2 == 1){                     // if remaining moves has a remainder of 1 player 2 wins
                System.out.println("Player 2 wins!!");
            }
            else{
                System.out.println("Player 1 wins!!");     // even remainder player 1 wins.
            }
        }

        else{                                              // if the user selected that they want to play against a CPU
            boolean winner = false;
            cpuPlay(playerNumber, freshBoard, moveCount); // call on method cpu play to determine who goes first.
            if (whoVwho==2 & playerNumber==2){
                moveCount=0;                               // if player is player 2 change the movecount to satisfy
            }                                              // all methods and winner status.
            moveCount++;
            System.out.println();
            currentBoard(freshBoard);                      // print out a visual of the board.
            moveCount++;                                   // second is used to help with whether player 1 or 2.

            while (winner == false) {                      // until we find a winner.
                cpuPlay(playerNumber, freshBoard, moveCount);  // call on cpu play to progress game until winner found.
                winner = checkForWinner(freshBoard);
                moveCount++;
                System.out.println();
                currentBoard(freshBoard);                      // show the board progress.
                moveCount++;
                if (moveCount >= 10) {                         // break the loop if no more moves.
                    winner = true;
                }
            }
            int emptyCount=numEmptySpaces(freshBoard);         // this is used to determine the number of empty
            emptyCount--;                                      // spaces left. It helped me determine the winner

            if (moveCount>=10){
                winner = checkForWinner(freshBoard);           // If there are no moves left to play see if there was
                if (winner == true){                           // a winner on the last move.
                    if (playerNumber==2){
                        if (emptyCount==0){                    // if user is player 2 and won with 1 empty space left
                            System.out.println("Player TWO WINS!!");
                        } else if (emptyCount == -1) {         // if no spaces left and cpu won on the last move.
                            System.out.println("Player ONE WINS!!");
                        } else {
                            System.out.println("IT'S A DRAW");  // no winner
                        }
                    }
                    else if (moveCount%2==1){                      // winner in middle of game movecount determines win
                        System.out.println("Player ONE WINS!!");
                    }
                    else{                                          // winner in mid game movecount determines win
                        System.out.println("PLAYER TWO WINS!!");
                    }
                }
                else{
                    System.out.println("IT'S A DRAW!!");           // no one wins
                }
            }
            else if (emptyCount % 2 == 1 & playerNumber==2) {      // extra scenarios of when a player won if player 2
                System.out.println("Player ONE WINS!!");
            }
            else if (emptyCount % 2 == 0 & playerNumber==2) {      // extra scenarios of when a player won if player 2
                System.out.println("Player TWO WINS!!");
            }
            else if (emptyCount % 2 == 1 & playerNumber==1) {      // extra scenarios of when a player won if player 1
                System.out.println("Player ONE WINS!!");
            }
            else if (emptyCount%2 == 0 & playerNumber == 1){       // extra scenarios of when a player won if player 1
                System.out.println("PLAYER TWO WINS!!");
            }
        }
        Scanner question = new Scanner(System.in);                 // ask user if they would like to play again and
        // restart.
        System.out.println("Press zero if you want to play again or anything else to quit:");
        again = question.nextInt();
        return again;
    }


    private static void cpuPlay(int playerNumber, String[][] freshBoard, int moveCount) {
        /* In cpuPlay we create a choice made by the computer going in the correct order. If the user is player two,
        the cpu will go first picking a random number between 0 and 2 for row and column. After this scenario the user
        will make there choice by calling playerMoveSelection(). Based on whether the user is Player 1 or 2 the CPU will
        make the correct choice of X or O depending on its turn.
         */

        if(playerNumber==2){                                // if the user is player 2 CPU will go first
            if(moveCount==1){
                System.out.print("CPU's Turn  \n\n");
                int row = (int)(Math.random()*3);           // random spot on the board and it will be an X
                int column = (int)(Math.random()*3);
                freshBoard[row][column] =String.valueOf(TicTacToe.Choice.X) ;

            }
            else{                                               // Still player 2 just make sure there are spots left.
                boolean cpuCheck = false;
                playerMoveSelection(freshBoard, moveCount);     // player moves
                int emptyCount = numEmptySpaces(freshBoard);    // count the number of empty spaces left
                System.out.println();
                boolean winner =checkForWinner(freshBoard);     // check to see if the player won
                if (emptyCount==0|winner == true){              // if there are no spaces or player won set CPUcheck to true
                    cpuCheck = true;
                }
                int row = -1;                                   // make sure we don't get stuck in a loop
                int column = -1;
                while (cpuCheck == false) {                     // while there are spaces available and no winner

                    row = (int) (Math.random()*3);
                    column = (int)(Math.random()*3);
                    cpuCheck = cpuSelectionChoice(freshBoard, row, column);     // call to Make sure the spot is empty
                }
                if (row != -1 & column !=-1){
                    System.out.print("CPU's Turn    \n\n");     // play cpu choice.
                    freshBoard[row][column] =String.valueOf(TicTacToe.Choice.X);
                }
            }
        }

        else if (playerNumber ==1){                                 // if player is player one.

            boolean cpuCheck = false;
            boolean winner = false;
            playerMoveSelection(freshBoard, moveCount);             // let the user go first before the cpu
            winner = checkForWinner(freshBoard);                    // check to see if they won
            if (winner == true){
                cpuCheck= true;                                     // if player won don't let the CPU play
            }
            int emptyCount = numEmptySpaces(freshBoard);            // keeping count of empty spaces left
            System.out.println();
            if (emptyCount==0){                                     // if there are no empty spaces make sure we dont loop
                cpuCheck = true;
            }

            int row = -1;                                           // making sure that we dont get stuck in a loop
            int column = -1;
            while (cpuCheck == false) {

                row = (int) (Math.random()*3);
                column = (int)(Math.random()*3);                    // generate a random number and make sure it isn't empty.
                cpuCheck = cpuSelectionChoice(freshBoard, row, column);

            }
            if (row != -1 & column !=-1){
                System.out.print("CPU's Turn    \n\n");             // play cpu choice.
                freshBoard[row][column] =String.valueOf(TicTacToe.Choice.O);
            }
        }

    }

    private static int numEmptySpaces(String[][] freshBoard) {
        //  In numEmpty Space we count the number of empty spaces and return the integer value.

        int emptyCount=0;                              // set count to zero
        for(int i = 0;i < freshBoard.length;i++){
            for(int j = 0; j< freshBoard.length;j++){
                if(freshBoard[i][j]=="EMPTY"){
                    emptyCount++;                       // loop through and count how many are empty.
                }
            }
        }
        return emptyCount;              // return number of empty spaces
    }

    private static boolean cpuSelectionChoice(String[][] freshBoard, int row, int column) {
        // In spuSelectionChoice we make sure that the choice the CPU has made to play is valid.
        String value = freshBoard[row][column];         // determine if it is a X, O, or EMPTY
        if (value == "EMPTY"){
            return true;                                // if EMPTY its true
        }
        else{
            return false;                               // if X or O its false.
        }
    }


    private static boolean checkForWinner(String[][] freshBoard) {
        // In CheckForWinner we determine after the user has placed their move if they have one by comparing
        // the board to all possible outcomes of winning. If any of the below match, we have a winner, if not,
        // no winner.
        boolean winner = false;

        if (freshBoard[0][0]== "X" & freshBoard[0][1]== "X" & freshBoard[0][2]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[1][0]== "X" & freshBoard[1][1]== "X" & freshBoard[1][2]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[2][0]== "X" & freshBoard[2][1]== "X" & freshBoard[2][2]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][0]== "X" & freshBoard[1][0]== "X" & freshBoard[2][0]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][1]== "X" & freshBoard[1][1]== "X" & freshBoard[2][1]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][2]== "X" & freshBoard[1][2]== "X" & freshBoard[2][2]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][0]== "X" & freshBoard[1][1]== "X" & freshBoard[2][2]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][2]== "X" & freshBoard[1][1]== "X" & freshBoard[2][0]== "X"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][0]== "O" & freshBoard[0][1]== "O" & freshBoard[0][2]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[1][0]== "O" & freshBoard[1][1]== "O" & freshBoard[1][2]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[2][0]== "O" & freshBoard[2][1]== "O" & freshBoard[2][2]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][0]== "O" & freshBoard[1][0]== "O" & freshBoard[2][0]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][1]== "O" & freshBoard[1][1]== "O" & freshBoard[2][1]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][2]== "O" & freshBoard[1][2]== "O" & freshBoard[2][2]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][0]== "O" & freshBoard[1][1]== "O" & freshBoard[2][2]== "O"){
            winner = true;
            return winner;
        }
        else if (freshBoard[0][2]== "O" & freshBoard[1][1]== "O" & freshBoard[2][0]== "O"){
            winner = true;
            return winner;
        }
        return winner;              // return false if none of the above are true
    }



    private static void playerMoveSelection(String[][] freshBoard, int moveCount) {
        /* In playerMoveSelection we determine whose turn it is and based on the move count we
           that player will be able to select where they want their move to be place.
         */

        if(moveCount%2 == 1){                                   // if the move count ahs a remainder of 1 its player 1
            System.out.println("\n\n PLAYER 1'S TURN! \n");
            boolean confirmMove = false;
            int row = 0;                                        // initialize row and column to zero
            int column = 0;
            while (confirmMove == false){                       // until we get a valid move from the user we continue.
                row = rowSelect();                              // call on rowSelect to obtain the row
                column = columnSelect();                        // call on columnSelect to obtain the column
                confirmMove= selectionCheck(freshBoard, row, column);       // Make sure it is valid
            }

            freshBoard[row][column] = String.valueOf(TicTacToe.Choice.X);   // place move for Player 1 and show board.
            currentBoard(freshBoard);

        }
        else{
            System.out.println("\n\n PLAYER 2'S TURN! \n");                 // same as above but for player 2
            boolean confirmMove = false;
            int row = 0;                                                    // initialize row and column
            int column = 0;
            while (confirmMove == false){                                   // loop til valid response.
                row = rowSelect();                                          // call rowSelect
                column = columnSelect();                                    // cal columnSelect
                confirmMove= selectionCheck(freshBoard, row, column);       // make sure its valid
            }
            freshBoard[row][column] = String.valueOf(TicTacToe.Choice.O);   // place move and show board.
            currentBoard(freshBoard);
        }

    }

    private static boolean selectionCheck(String[][] freshBoard, int row, int column) {
        // Same as CPU check but for the player, Should have just used one of the 2 and not both but running short
        // on time but this helped me integrate the 1v1 before i got the 1vCPU but checks to make sure the move
        // wanted is in an empty space
        String value = freshBoard[row][column];
        if (value == "EMPTY"){                          // if it is an empty space
            return true;                                // return true
        }
        else{
            return false;                               // if not return false.
        }

    }


    private static int columnSelect() {
        // In columnSelect the user is asked to pick a number 0-2 and will continue to be asked until they choose correct
        int column = -1;
        Scanner move2 = new Scanner(System.in);                     // be able to accept input and ask for input
        System.out.println("Which Column would you like your move to be in?: ");
        column = move2.nextInt();
        while(column<0 | column>2){                                 // if below 0 or greater the 2 they will be told invalid
            System.out.println("Invalid choice. Please Try Again");
            System.out.println("Which Column would you like your move to be in? (0, 1, or 2): ");
            column = move2.nextInt();
        }

        return column;                          // return column index
    }

    private static int rowSelect() {
        // In rowSelect the user is asked to select a row and it will be check if valid.
        int row = -1;
        Scanner move = new Scanner(System.in);          // expect input and ask for input
        System.out.println("Which Row would you like your move to be in? (0, 1, or 2): ");
        row = move.nextInt();
        while (row< 0 | row > 2){                       // if row is less than 0 and greater then 2 it is invalid
            System.out.println("Invalid choice. Please Try Again");
            System.out.println("Which Row would you like your move to be in? (0, 1, or 2): ");
            row = move.nextInt();
        }

        return row;                             // return row index





    }

    private static int playerNumber() {
        // In playerNumber the user is asked if they would like to be player 1 or player 2
        int playerNumber =0;                                // initialize player number
        Scanner whichPlayer = new Scanner(System.in);       // expect input
        System.out.println("\n"+                            // explain the recieve input
                "If you would like to be " +
                "Player 1 please type 1" +"\n"+
                "If you would like to be Player 2 please type 2:" );
        while (playerNumber != 1 | playerNumber!= 2){           // user must select either 1 or 2
            playerNumber = whichPlayer.nextInt();
            if (playerNumber == 1){
                System.out.println("\n"+"User has Requested to be Player 1!");
                return playerNumber;  // return player 1

            }
            else if (playerNumber == 2){
                System.out.println("\n"+"User has Requested to be Player 2!");
                return playerNumber;    // return player 2
            }
            else{
                System.out.println("\n"+"You MUST select 1 or 2 please: ");
                // error message
            }
        }
        return playerNumber;    // return player number
    }

    private static void currentBoard(String[][] board) {
        // In currentBoard we print out how the board looks in the current moment.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%30s",board[i][j] );             // loop and print out elements
                if (j == 2) {                               // once we get to the end of the row print new line.
                    System.out.println();
                }
            }
        }
    }
    private static int playerSelect() {
        // In playerSelect the user is asked if they would like to play 1v1 or against a computer.

        int count = 0;                                  // intitialize and then expect input
        Scanner playerCount = new Scanner(System.in);
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,"+"\n"+"|  Welcome to Tic-Tac-Toe  |"+"\n"+
                "''''''''''''''''''''''''''''"+"\n\n"+
                "Would you like to play 1v1 or 1vCPU?"+
                "\n\n"+"Press 1 for 1v1"+"\n"+                  // explain and receive input
                "or"+"\n"+
                "Press 2 to play against a computer: ");

        while (count != 1 | count !=2){
            count = playerCount.nextInt();                          // loop until user selects 1 or 2
            if (count == 1){
                System.out.println("\n"+"User selected 1v1!");
                return count;               // return 1v1
            }
            else if (count == 2){
                System.out.println("\n"+"User selected to play against the Computer!");
                return count;               // return 2v2
            }
            else{
                System.out.println("\n"+"You MUST select 1 or 2 please: ");
                // error message
            }
        }

        return count;                   // return game choice
    }


}
