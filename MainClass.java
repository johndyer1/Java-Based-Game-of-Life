package game;

/*******************************************************************
* This program reads a text file entered by the user and generates
* a display of generations based on the specifications of 'The Game
* of Life.'
*******************************************************************/

import java.io.*; 
import java.util.*; 


public class MainClass {
	
	
	/**  
	 * The main method is what begins when the user initiates the program.
	 *   
	 * The parameter "String[] args" are the central arguments of the code.
	 * Nothing is being returned.
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		String file;
		
		int r = 0;
		int d = 0;
		
		System.out.print("Enter initial configuration file name: "); 
		file = sc.nextLine(); 

		Scanner s = new Scanner(new File(file)); 
		
		int irow = Integer.parseInt(s.nextLine());
		int icol = Integer.parseInt(s.nextLine());
		int[][] cellArr = new int[irow][icol];
		
		while (s.hasNext()) {
			String line = s.nextLine();
			for ( int c = 0; c < line.length(); c++) {
				cellArr[r][c] = Character.getNumericValue(line.charAt(c));
			}
			r++;
	    }
		s.close();
		
		while ( d == 0 ) {
			System.out.println(boardDisplay(cellArr));
			try {   Thread.sleep(300);  }  
			catch (InterruptedException e) {}
			int[][] life = new int[irow][icol];
			for ( int ra = 0; ra < cellArr.length; ra++ ) {
				for ( int ca = 0; ca < cellArr[0].length; ca++ ) {
					life[ra][ca] = neighbors( cellArr, ra, ca);
				}
			}
			int[][] ding = update(life);
			
			for ( int ra = 0; ra < cellArr.length; ra++ ) {
				for ( int ca = 0; ca < cellArr[0].length; ca++ ) {
					if ( ding[ra][ca] == 2 && cellArr[ra][ca] == 1) {
						ding[ra][ca] = 1;
					}
					else if ( ding[ra][ca] == 2 && cellArr[ra][ca] == 0 ) {
						ding[ra][ca] = 0;
					}
				}
			}
			cellArr = ding;
		}
		
		
	}
	
	/**  
	 * The boardDisplay method converts numeric values of an array into user-friendly characters.
	 *   
	 * The parameter "int[][] cells" is an 2D array that contains values of zeros and ones.
	 * A block string of the cells is being returned.
	 */
	public static String boardDisplay(int[][] cells) {
		
		String boardDisp = "";
		for ( int k = 0; k < cells.length; k++ ) {
			for ( int c = 0; c < cells[0].length; c++ ) {
				if ( cells[k][c] == 0) {
					boardDisp = boardDisp + ".";
				}
				if ( cells[k][c] == 1) {
					boardDisp = boardDisp + "*";
				}
			}
			boardDisp = boardDisp + System.lineSeparator();
		}
		return boardDisp;
		
	}
	
	/**  
	 * The neighbors method finds the total number of nearby cells in a generation.
	 *   
	 * The parameter "int[][] cells" the location of cells.  "int row" is the number of the row requested.  "int col" is the number of the column requested.
	 * The integer returned is the number of neighbors of the requested cell.
	 */
	public static int neighbors(int[][] cells, int row, int col) {
		
		int life = 0;
		
		if ( col == 0 ) {
			if ( row == 0 ){
				if ( cells[row][col+1] == 1 ) {
					life++;
				}
				if ( cells[row+1][col] == 1 ) {
					life++;
				}
				if ( cells[row+1][col+1] == 1 ) {
					life++;
				}
			}
			else if ( row == cells.length - 1 ) {
				if ( cells[row-1][col] == 1 ) {
					life++;
				}
				if ( cells[row-1][col+1] == 1 ) {
					life++;
				}
				if ( cells[row][col+1] == 1 ) {
					life++;
				}
			}
			else {
				if ( cells[row-1][col] == 1 ) {
					life++;
				}
				if ( cells[row-1][col+1] == 1 ) {
					life++;
				}
				if ( cells[row][col+1] == 1 ) {
					life++;
				}
				if ( cells[row+1][col] == 1 ) {
					life++;
				}
				if ( cells[row+1][col+1] == 1 ) {
					life++;
				}
			}
		}
		else if ( col == cells[0].length - 1 ) {
			if ( row == 0 ) {
				if ( cells[row][col-1] == 1 ) {
					life++;
				}
				if ( cells[row+1][col] == 1 ) {
					life++;
				}
				if ( cells[row+1][col-1] == 1 ) {
					life++;
				}
			}
			else if ( row == cells.length - 1 ) {
				if ( cells[row-1][col] == 1 ) {
					life++;
				}
				if ( cells[row-1][col-1] == 1 ) {
					life++;
				}
				if ( cells[row][col-1] == 1 ) {
					life++;
				}
			}
			else {
				if ( cells[row-1][col] == 1 ) {
					life++;
				}
				if ( cells[row-1][col-1] == 1 ) {
					life++;
				}
				if ( cells[row][col-1] == 1 ) {
					life++;
				}
				if ( cells[row+1][col-1] == 1 ) {
					life++;
				}
				if ( cells[row+1][col] == 1 ) {
					life++;
				}
			}
		}
		else if ( row == 0 ) {
			if ( cells[row][col-1] == 1 ) {
				life++;
			}
			if ( cells[row][col+1] == 1 ) {
				life++;
			}
			if ( cells[row+1][col-1] == 1 ) {
				life++;
			}
			if ( cells[row+1][col] == 1 ) {
				life++;
			}
			if ( cells[row+1][col+1] == 1 ) {
				life++;
			}
		}
		else if ( row == cells.length - 1 ) {
			if ( cells[row][col-1] == 1 ) {
				life++;
			}
			if ( cells[row][col+1] == 1 ) {
				life++;
			}
			if ( cells[row-1][col-1] == 1 ) {
				life++;
			}
			if ( cells[row-1][col] == 1 ) {
				life++;
			}
			if ( cells[row-1][col+1] == 1 ) {
				life++;
			}
		}
		else {
			if ( cells[row][col-1] == 1 ) {
				life++;
			}
			if ( cells[row][col+1] == 1 ) {
				life++;
			}
			if ( cells[row-1][col-1] == 1 ) {
				life++;
			}
			if ( cells[row-1][col] == 1 ) {
				life++;
			}
			if ( cells[row-1][col+1] == 1 ) {
				life++;
			}
			if ( cells[row+1][col-1] == 1 ) {
				life++;
			}
			if ( cells[row+1][col] == 1 ) {
				life++;
			}
			if ( cells[row+1][col+1] == 1 ) {
				life++;
			}
		}
		return life;
		
	}

	/**  
	 * The update method determines whether the cells of the array given are alive or dead based off of the rules of the game
	 *   
	 * The parameter "int[][] cells" is a 2D array that contains the number of neighbors of the cell
	 * The array of alive and dead cells is returned
	 */
	public static int[][] update(int[][] cells) {
		for ( int ra = 0; ra < cells.length; ra++ ) {
			for ( int ca = 0; ca < cells[0].length; ca++ ) {
				if ( cells[ra][ca] < 2 || cells[ra][ca] > 3 ) {
					cells[ra][ca] = 0;
				}
				if ( cells[ra][ca] == 3 ) {
					cells[ra][ca] = 1;
				}
			}
		}
		return cells;
	}

	
}
