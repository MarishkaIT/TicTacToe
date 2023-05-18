package TicTacToe1;

import java.util.Scanner;

public class TicTacToe {
    public static final String EMPTY = "   ";
    public static final String CROSS = " X ";
    public static final String ZERO = " O ";
    public static String activePlayer;

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    public static String[][] grid = new String[ROWS][COLUMNS];
    public static int statusGame;
    public static final int STATUS_GAME_CONTINUES = 0, STATUS_DRAW = 1,
                                STATUS_WON_X = 3, STATUS_WON_O = 4;
    public static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        startPlay();
        do {
            getPlayerAction();
            gridAnalysis();
            displayGrid();
            if (statusGame ==STATUS_WON_X){
                System.out.println("'X' won! Congratulations!");
            }else if (statusGame == STATUS_WON_O){
                System.out.println("'0' won! Congratulations!");
            }else if (statusGame == STATUS_DRAW){
                System.out.println("Draw!");
            }

            activePlayer = (activePlayer==CROSS?ZERO:CROSS);

        }while (statusGame == STATUS_GAME_CONTINUES);
    }

    public static void startPlay(){
        for (int row = 0; row < ROWS; row++){
            for (int column = 0; column < COLUMNS; column++){
                grid[row][column] = EMPTY;
            }
        }
        activePlayer = CROSS;
        displayGrid();
    }
    public static void getPlayerAction(){
        boolean inputEffective = false;
        do {
            System.out.println("Player " + activePlayer + ", input row (1-3) and column (1-3) through a space");
            int row = in.nextInt()-1;
            int column = in.nextInt()-1;
            if (row >=0 && row < ROWS && column >=0 && column<COLUMNS && grid[row][column]==EMPTY){
                grid[row][column] = activePlayer;
                inputEffective = true;
            }else {
                System.out.println("Try once more...");
            }
        }
        while (!inputEffective);
    }
    public static void gridAnalysis(){
         String winner = findWinner();
         if (winner.equals(CROSS)){
             statusGame = STATUS_WON_X;
         }else if (winner.equals(ZERO)){
             statusGame = STATUS_WON_O;
         }else if (cellsFilled()){
             statusGame = STATUS_DRAW;
         }else {
             statusGame = STATUS_GAME_CONTINUES;
         }
    }
    public static boolean cellsFilled (){
        for (int row =0; row<ROWS;row++){
            for (int column = 0; column<COLUMNS; column++){
                if (grid[row][column]==EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    public static String findWinner (){
        int quantityIsTheSome;
        for (int row = 0; row<ROWS; row++){
            quantityIsTheSome=0;
            for (int column = 0; column < COLUMNS; column++){
                if (grid[row][0] != EMPTY && grid[row][0]== grid[row][column]){
                    quantityIsTheSome++;
                }
            }
            if (quantityIsTheSome==3){
                return grid[row][0];
            }
        }
        for (int column = 0; column<COLUMNS; column++){
            quantityIsTheSome=0;
            for (int row = 0; row<ROWS; row++){
                if (grid[0][column] != EMPTY && grid[0][column]==grid[row][column]){
                    quantityIsTheSome++;
                }
            }
            if (quantityIsTheSome == 3 ){
                return grid[0][column];
            }
        }
        if (grid[0][0] != EMPTY && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]){
            return grid[0][0];
        }
        if (grid[0][2] !=EMPTY && grid[1][1]==grid[0][2] && grid[2][0]==grid[0][2]){
            return grid[0][2];
        }
      return EMPTY;
    }
    public static void displayGrid (){
        for (int row = 0; row < ROWS; row++){
            for (int column = 0; column < COLUMNS; column++){
                System.out.print(grid[row][column]);
                if (column != COLUMNS-1){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row != ROWS-1){
                System.out.println("----------------");
            }
            System.out.println();
        }
    }

}
