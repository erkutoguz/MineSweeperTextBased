package erkut.java.MayinTarlasi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Satır sayısını giriniz: ");
		int row = sc.nextInt();
		System.out.print("Sütun sayısını giriniz: ");
		int col = sc.nextInt();

		MineSweeper game = new MineSweeper(row, col);

		game.run();

	}

}
