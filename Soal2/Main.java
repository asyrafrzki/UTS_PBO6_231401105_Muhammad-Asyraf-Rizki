package Soal2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tambahKendaraan = "";
        int totalKendaraan = 0;
        double totalBiaya = 0;

        System.out.println("====== Welcome to ParkingChan ======");

        do {
            System.out.print("\nEnter vehicle type (Motor/Mobil/Truk) : ");
            String jenis = input.nextLine();

            //objek baru
            Vehicle Vehicle = new Vehicle(jenis);

            System.out.print("Enter Duration (Manual/Time): ");
            String metode = input.nextLine();

            if (metode.equalsIgnoreCase("Manual")) {
                System.out.print("Enter Duration (in hour): ");
                int durasi = input.nextInt();
                input.nextLine();
                Vehicle.hitungBiayaParkir(durasi);
            } else if (metode.equalsIgnoreCase("Time")) {
                System.out.print("Enter entry time : ");
                int masuk = input.nextInt();
                System.out.print("Enter exit time  : ");
                int keluar = input.nextInt();
                input.nextLine();
                Vehicle.hitungBiayaParkir(masuk, keluar);
            } else {
                System.out.println("Invalid input method!");
                continue;
            }

            Vehicle.tampilRingkasan();
            totalKendaraan++;
            totalBiaya += Vehicle.getBiayaParkir();

            System.out.print("\nAdd another vehicle? (y/n): ");
            tambahKendaraan = input.nextLine();

        } while (tambahKendaraan.equalsIgnoreCase("y"));

        System.out.println("\n===== FINAL REPORT =====");
        System.out.println("Total Vehicle Final  : " + totalKendaraan);
        System.out.println("Total Parking Fees Final : Rp" + String.format("%.0f", totalBiaya));
        System.out.println("Thank you!");
    }
}
