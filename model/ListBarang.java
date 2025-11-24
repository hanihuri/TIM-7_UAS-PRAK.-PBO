package model;

/**
 * Kelas ListBarang merepresentasikan entitas data sebuah barang
 * yang tersedia di toko. Setiap barang memiliki ID, nama, harga,
 * dan jumlah stok yang dapat diakses melalui getter.
 *
 * Kelas ini digunakan sebagai objek dasar dalam penyimpanan dan
 * penampilan daftar barang, serta mendukung operasi seperti
 * menampilkan detail barang dalam format teks.
 */

public class ListBarang {
    private int id;
    private String nama;
    private double harga;
    private int stok;

    public ListBarang(int id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    
    @Override
    public String toString() {
        return id + " - " + nama + " (Rp " + harga + ", stok: " + stok + ")";
    }
}
