import java.util.*;

public class GabdrakhmanovaRenata_ExhaustedKnight {
	
	/* Renata Gabdrakhmanova 
	 * Pd 3 
	 * 10/14/20
	 * 
	 * Exhausted Knight- the program has a board with size of 8x8, the figure starts in the top-left corner and moves in random direction
	 * There are 8 different ways a figure can move, if there are multiple possible ways to move, the program should chose one of the directions randomly
	 * The figure should continue to move until all of the spots are filled with a number or there is no where to go
	 * At the end the program should print the board with locations of where the figure has been.
	 * It should also count the number of locations it has visited. */

	
	
	
	//creates the board and sets all the values inside to be 0, except the starting point
	//will call a function to find if the figure has anywhere to go
	//each time the function will return an array of numbers that must contain the position of the knight
	//the positions from the function will be set as new positions
	// the function can be called many times until it returns an array containing -1, which means there are no more steps to take
	//when there are no more steps left another function is called to print out the board and the knight's locations
	
	public static void main(String[] args) {

		int[][] board = new int[8][8];
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = 0;
			}
		}
		board[0][0] = 1;

		int currentRow = 0;
		int currentCol = 0;
		int location[] = new int[2];

		while (location[0] != -1) {

			location = determineMoves(board, currentRow, currentCol);
			currentCol = location[1];
			currentRow = location[0];
		}

		printBoard(board);
	}

	
	//takes in a board and the current location of the knight
	//contains two arrays which are for calculating the steps knight can take
	//there are 8 possible ways a knight can move, if it's move is out of the board then that location is excluded and will not be saved
	//or if the knight has already been in that spot, then that location won't be saved either
	//the for loop checks what possible ways there are for knight to move
	//an if statement sorts the movements of the knight and saves the ones that are accurate
	//after the loop is over, the function checks if there were any accurate locations
	//if there were then the program makes a copy of that saved array with a correct size, and calls another function to select a random location based on a random number
	//after it returned the location from the saved array will be returned to main
	//if there were no accurate locations the function will return an array containing-1
	public static int[] determineMoves(int[][] board, int currentRow, int currentCol) {
		int horzDisp[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
		int vertDisp[] = { -2, -1, 1, 2, 2, 1, -1, -2 };

		int location[] = new int[2];
		int row[] = new int[8];
		int col[] = new int[8];
		int count = 0;
		

		for (int moves = 0; moves < 8; moves++) {
			int newRow = currentRow - vertDisp[moves];
			int newCol = currentCol + horzDisp[moves];

			if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board[0].length
					&& board[newRow][newCol] == 0) {

				row[count] = newRow;
				col[count] = newCol;
				count++;
			}
		}
		
		if (count != 0) {
			int copyRow[] = new int[count];
			int copyCol[] = new int[count];

			for (int i = 0; i < count; i++) {
				copyRow[i] = row[i];
				copyCol[i] = col[i];
			}
			
			int random = RandomTurn(copyRow, copyCol);
			location[0] = copyRow[random];
			location[1] = copyCol[random];
			board[copyRow[random]][copyCol[random]] = board[currentRow][currentCol] + 1;

			return location;
		}

		location[0] = -1;
		return location;
	}

	//called from determineMoves function
	//takes in two array with accurate locations of the knight
	//from those locations, based on a random number it will return place of one of the locations
	public static int RandomTurn(int[] row, int[] col) {
		int length = row.length;
		int random = (int) (Math.random() * length + 0);

		return random;
	}

	//called from main
	//prints out the board with the locations of where the knight has been
	//also counts the total number of locations and prints it out
	public static void printBoard(int[][] board) {
		int location = 0;
		
		for (int col = 0; col < board[0].length; col++) {
			System.out.print("\t" + (col + 1));
		}

		System.out.println("\n");

		for (int row = 0; row < board.length; row++) {
			System.out.print((row + 1) + "\t");
			
			for (int col = 0; col < board[row].length; col++) {
				
				System.out.print(board[row][col] + "\t");
				
				if (board[row][col] != 0) {
					location++;

				}
			}
			System.out.print("\n");
		}
		System.out.print(location + " locations were visited");
	}
}
