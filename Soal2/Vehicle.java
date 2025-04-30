package Soal2;

//atribute
public class Vehicle {
    private String jenisKendaraan;
    private int lamaParkir;
    private double biayaParkir;

    //constructor
    public Vehicle(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    // Overloading biaya parkir dengan durasi manual
    public void hitungBiayaParkir(int lamaParkir) {
        this.lamaParkir = lamaParkir;
        biayaParkir = lamaParkir * tarifPerJam();
        applyDiskon();
    }

    // Overloading dari jam masuk dan keluar
    public void hitungBiayaParkir(int jamMasuk, int jamKeluar) {
        this.lamaParkir = jamKeluar - jamMasuk;
        if (this.lamaParkir < 0) {
            this.lamaParkir += 24;
        }
        biayaParkir = lamaParkir * tarifPerJam();
        applyDiskon();
    }

    //biaya parkir per jam berdasarkan jenis kendaraan
    private double tarifPerJam() {
        switch (jenisKendaraan.toLowerCase()) {
            case "motor":
                return 2000;
            case "mobil":
                return 5000;
            case "truk":
                return 10000;
            default:
                return 0;
        }
    }

    //diskon 10 persen jika lama parkir diatas 5 jam
    private void applyDiskon() {
        if (lamaParkir > 5) {
            biayaParkir = biayaParkir * 0.9;
        }
    }

    //ringkasan berisi jenis kendaraan, lama parkir, dan biaya parkir
    public void tampilRingkasan() {
        System.out.println("==== PARKING SUMMARY ====");
        System.out.println("Vehicle Type   : " + jenisKendaraan);
        System.out.println("Parking Time   : " + lamaParkir + " hour(s)");
        System.out.println("Total Fee      : Rp" + String.format("%.0f", biayaParkir));
    }

    //getter untuk mengambil nilai dari biayaparkir
    public double getBiayaParkir() {
        return biayaParkir;
    }
}
