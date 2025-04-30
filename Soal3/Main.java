package Soal3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LotreBoard lotre = new LotreBoard(); // objek baru dari class LotreBoard

        System.out.println("Welcome to E-Lottery Gosok");

        boolean mulai = true;
        // iterasi selama permainan belum selesai
        while (mulai) {
            lotre.displayBoard();

            System.out.print("Masukkan tebakan anda (baris dan kolom) : ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            // menebak jawaban dari baris dan kolom inputan
            int status = lotre.guess(row, col);

            if (status == -1) {
                continue;
            } else if (status == 0) {
                System.out.println("BOOM! Anda menemukan bom! Permainan berakhir.");
                lotre.displayBoard();
                mulai = false;
            } else {
                System.out.println("Kotak Aman!");
                if (lotre.isGameOver()) {
                    System.out.println("Selamat! Anda berhasil membuka semua kotak aman!");
                    lotre.displayBoard();
                    mulai = false;
                }
            }
        }

        scan.close();
    }
}
