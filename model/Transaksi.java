package model;

import java.util.ArrayList;

/**
 * Kelas yang merepresentasikan transaksi pembelian dalam sistem.
 * Menyimpan informasi mengenai customer, daftar barang, metode pembayaran, 
 * serta status transaksi.
 */
public class Transaksi {

    /** Counter untuk menghasilkan ID transaksi secara otomatis */
    private static int COUNTER = 1;

    /** ID unik dari transaksi */
    private String idTransaksi;

    /** Customer yang melakukan transaksi */
    private Customer customer;

    /** Daftar barang yang dibeli dalam transaksi */
    private ArrayList<Barang> barang;

    /** Status transaksi saat ini */
    private StatusTransaksi status;

    /** Metode pembayaran yang dipilih untuk transaksi ini */
    private Pembayaran pembayaran;

    /**
     * Konstruktor untuk membuat transaksi baru dengan informasi customer dan barang.
     * Status awal transaksi adalah {@link StatusTransaksi#DIBUAT}.
     *
     * @param customer customer yang melakukan transaksi
     * @param barang daftar barang yang dibeli
     */
    public Transaksi(Customer customer, ArrayList<Barang> barang) {
        this.idTransaksi = "TRX" + COUNTER++;
        this.customer = customer;
        this.barang = barang;
        this.status = StatusTransaksi.DIBUAT;
    }

    /**
     * Mengambil ID transaksi.
     *
     * @return ID transaksi
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * Mengambil informasi customer yang terkait dengan transaksi.
     *
     * @return objek {@link Customer}
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Mengambil daftar barang yang dibeli.
     *
     * @return list barang dalam transaksi
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    /**
     * Mengambil status transaksi.
     *
     * @return status transaksi
     */
    public StatusTransaksi getStatus() {
        return status;
    }

    /**
     * Mengubah status transaksi.
     *
     * @param status status baru transaksi
     */
    public void setStatus(StatusTransaksi status) {
        this.status = status;
    }

    /**
     * Mengambil metode pembayaran yang digunakan.
     *
     * @return metode pembayaran
     */
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    /**
     * Menetapkan metode pembayaran untuk transaksi.
     *
     * @param pembayaran metode pembayaran yang ingin digunakan
     */
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    /**
     * Menghitung total biaya transaksi berdasarkan harga setiap barang.
     *
     * @return total harga seluruh barang
     */
    public int getTotal() {
        int total = 0;
        for (Barang b : barang) {
            total += b.getHarga();
        }
        return total;
    }

    /**
     * Memberikan ringkasan transaksi dalam bentuk string,
     * berisi ID transaksi, username customer, total harga dan status.
     *
     * @return string ringkasan transaksi
     */
    public String ringkas() {
        return idTransaksi + " | " + customer.getUsername() + " | Total: " + getTotal() +
                " | Status: " + status;
    }
}

