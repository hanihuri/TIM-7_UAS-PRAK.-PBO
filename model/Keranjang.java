package model;

import java.util.ArrayList;

/**
 * Kelas Keranjang merepresentasikan keranjang belanja yang dimiliki
 * oleh seorang customer. Keranjang ini menyimpan daftar barang yang
 * ingin dibeli, menyediakan fungsi untuk menambah barang, menghitung
 * total harga, mengecek apakah keranjang kosong, serta mengosongkan
 * keranjang setelah proses checkout.
 *
 * Kelas ini berfungsi sebagai komponen utama dalam alur transaksi
 * sebelum pembuatan invoice dan pembayaran.
 */

public class Keranjang {

    private ArrayList<Barang> listBarang;

    public Keranjang() {
        listBarang = new ArrayList<>();
    }

    // Tambah barang ke keranjang
    public void tambahBarang(Barang b) {
        listBarang.add(b);
    }

    // Ambil semua barang (untuk checkout, riwayat)
    public ArrayList<Barang> getListBarang() {
        return listBarang;
    }

    // Hitung total harga
    public int hitungTotal() {
        int total = 0;
        for (Barang b : listBarang) {
            total += b.getHarga();
        }
        return total;
    }

    // Cek apakah keranjang kosong
    public boolean isKosong() {
        return listBarang.isEmpty();
    }

    // Kosongkan keranjang setelah checkout
    public void kosongkan() {
        listBarang.clear();
    }
}
