package fr.nicolas.TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	public static String[][] board = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " }, };
	public static boolean turnO = true;
	public static int availableCase = 9;

	public static void main(String[] args) {
		System.out.println("Bienvenue au TicTacToe");
		display();

		while (!checkWin() && availableCase > 0) {
			if (turnO) {
				play();
			} else {
				bot();
			}
			display();
		}

		if (checkWin()) {
			System.out.println("Bien jouer !");
		} else {
			System.out.println("Egaliter !");
		}
	}

	public static void display() {
		String[] letters = { "A", "B", "C" };
		System.out.println("   1 2 3");
		for (int i = 0; i < board.length; i++) {
			System.out.println(letters[i] + " |" + board[i][0] + "|" + board[i][1] + "|" + board[i][2] + "|");
		}
	}

	public static void play() {
		System.out.println("Veuillez indiquer une case :");

		String choice = scan.nextLine();
		String lineText = choice.substring(0, 1);
		String columnText = choice.substring(1, 2);

		int line = 0;

		// if (lineText == "B") {
		if (lineText.equals("B")) {
			line = 1;
		} else if (lineText.equals("C")) {
			line = 2;
		}

		int column = 0;
		try {
			column = Integer.parseInt(columnText) - 1;
		} catch (Exception e) {
			System.out.println("Colonne incorrect");
			play();
			return;
		}

		if (board[line][column] == " ") {
			// board[line][column] = turnO ? "O" : "X";
			if (turnO) {
				board[line][column] = "O";
			} else {
				board[line][column] = "X";
			}
			availableCase--;
			turnO = !turnO;
		} else {
			System.out.println("Case deja utilisé");
			play();
		}
	}

	public static boolean checkWin() {
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] != " " && board[i][0] == board[i][1] && board[i][0] == board[i][2]
					|| board[0][i] != " " && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
				return true;
			}
		}
		if (board[0][0] != " " && board[0][0] == board[1][1] && board[0][0] == board[2][2]
				|| board[0][2] != " " && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return true;
		}

		return false;
	}

	public static void bot() {
		Random rand = new Random();
		int choice = rand.nextInt(availableCase) + 1;

		int index = 0; // index
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == " ") {
					index++;
				}
				if (index == choice) {
					board[x][y] = "X";
					turnO = !turnO;
					return;
				}
			}
		}
	}
}
