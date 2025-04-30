package Soal3;

import java.util.Random;

public class LotreBoard {
    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private final int rows = 4;
    private final int cols = 5;

    //papan lotre 4 baris 5 kolom
    public LotreBoard() {
        board = new char[rows][cols];
        revealed = new boolean[rows][cols];
        data = new int[rows][cols];
        generateBoard();
    }

    //bikin papan bom acak
    public void generateBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '*';
                revealed[i][j] = false;
                data[i][j] = 0;
            }
        }

        Random rand = new Random();
        int bombsPlaced = 0;
        while (bombsPlaced < 2) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (data[r][c] == 0) {
                data[r][c] = 1;
                bombsPlaced++;
            }
        }
    }

    //nampilin papan
    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //tebakan user
    public int guess(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Tebakan di luar papan! Coba lagi.");
            return -1;
        }
        if (revealed[row][col]) {
            System.out.println("Kotak sudah dibuka sebelumnya!");
            return -1;
        }

        revealed[row][col] = true;
        if (data[row][col] == 1) {
            board[row][col] = 'X';
            return 0;
        } else {
            board[row][col] = 'O';
            return 1;
        }
    }
    //game selesai jika sisa 2 papan bom dan semua papan yg aman sudah terbuka semua
    public boolean isGameOver() {
        int safeOpened = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j] && data[i][j] == 0) {
                    safeOpened++;
                }
            }
        }
        return safeOpened == 18; // total aman = 4x5 - 2 = 18, 2 bom
    }
}
