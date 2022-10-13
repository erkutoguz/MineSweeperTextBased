package erkut.java.MayinTarlasi;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

	boolean[][] mineBoard;
	String[][] board;
	int mineCount;
	Scanner sc = new Scanner(System.in);
	int digCount;
	int elementCount;

	MineSweeper(int row, int col) {
		mineBoard = new boolean[row][col];
		board = new String[row][col];
		mineCount = row * col / 4;
		digCount = 0;
		elementCount = row * col;
		fillMap();
		putMines();

	}

	void fillMap() {
		for (String[] s : board)
			Arrays.fill(s, "-");
	}

	void putMines() {
		Random rd = new Random();

		int placed = 0;
		int r = 0;
		int c = 0;
		while (placed < mineCount) {
			r = rd.nextInt(mineBoard.length);
			c = rd.nextInt(mineBoard[0].length);

			if (mineBoard[r][c] == false) {
				mineBoard[r][c] = true;
				placed++;
			}
		}


	}

	void printBoardWithMines() {
		for (int i = 0; i < mineBoard.length; i++) {
			for (int j = 0; j < mineBoard[i].length; j++) {
				if (mineBoard[i][j])
					System.out.print("*" + "\t");
				else
					System.out.print("-" + "\t");
			}
			System.out.println();
		}
	}

	void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("==================================");

	}

	boolean checkEmptySpaces() {
		return elementCount - mineCount == digCount;
	}

	String checkAround(int row, int col) {
		int minesAround = 0;

		// checks top left corner
		if (row == 0 && col == 0) {
			if (mineBoard[row + 1][col])
				minesAround++;
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row + 1][col + 1])
				minesAround++;
		}

		// checks top right corner
		if (row == 0 && col == mineBoard[0].length - 1) {
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col])
				minesAround++;
		}

		// checks bottom right corner
		if (row == mineBoard.length - 1 && col == 0) {
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row - 1][col])
				minesAround++;
			if (mineBoard[row - 1][col + 1])
				minesAround++;
		}

		// checks bottom left corner
		if (row == mineBoard.length && col == mineBoard[0].length - 1) {
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row - 1][col - 1])
				minesAround++;
			if (mineBoard[row][col])
				minesAround++;
		}

		// checks top edge
		if (row == 0 && row != mineBoard.length && col != 0 && col != mineBoard[0].length - 1) {
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row + 1][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col])
				minesAround++;
			if (mineBoard[row + 1][col + 1])
				minesAround++;
		}

		// checks bottom edge
		if (row == mineBoard.length - 1 && col != 0 && col != mineBoard[0].length - 1) {
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row - 1][col - 1])
				minesAround++;
			if (mineBoard[row - 1][col])
				minesAround++;
			if (mineBoard[row - 1][col + 1])
				minesAround++;
		}

		// checks left edge
		if (row != 0 && row != mineBoard.length - 1 && col == 0) {
			if (mineBoard[row - 1][col])
				minesAround++;
			if (mineBoard[row - 1][col + 1])
				minesAround++;
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row + 1][col])
				minesAround++;
			if (mineBoard[row + 1][col + 1])
				minesAround++;
		}

		// checks right edge
		if (row != 0 && row != mineBoard.length - 1 && col == mineBoard[0].length - 1) {
			if (mineBoard[row - 1][col - 1])
				minesAround++;
			if (mineBoard[row - 1][col])
				minesAround++;
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col])
				minesAround++;
		}

		// checks middles
		if (row != 0 && row != mineBoard.length - 1 && col != 0 && col != mineBoard[0].length - 1) {
			if (mineBoard[row - 1][col - 1])
				minesAround++;
			if (mineBoard[row - 1][col])
				minesAround++;
			if (mineBoard[row - 1][col + 1])
				minesAround++;
			if (mineBoard[row][col - 1])
				minesAround++;
			if (mineBoard[row][col + 1])
				minesAround++;
			if (mineBoard[row + 1][col - 1])
				minesAround++;
			if (mineBoard[row + 1][col])
				minesAround++;
			if (mineBoard[row + 1][col + 1])
				minesAround++;
		}
		return "" + minesAround;
	}

	boolean dig() {
		int row = 0;
		int col = 0;

		do {
			System.out.print("Satır giriniz: ");
			row = sc.nextInt() - 1;
			System.out.print("Sütun giriniz: ");
			col = sc.nextInt() - 1;
		} while ((row < 0 || row >= mineBoard.length) || (col < 0 || col >= mineBoard[0].length));

		if (mineBoard[row][col]) {
			System.out.println("Mayına bastın!");
			return true;
		} else {
			digCount++;
			board[row][col] = checkAround(row, col);
			if (checkEmptySpaces()) {
				System.out.println("Oyunu kazandın tebrikler.");
				return true;
			}
			printBoard();
			return false;
		}

	}

	void run() {
		System.out.println("Mayınların Konumu");

		printBoardWithMines();

		System.out.println("==================================");
		System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz.");

		printBoard();

		boolean isCountinue = true;
		while (isCountinue) {
			if (dig()) {
				break;
			}
		}



	}



}
