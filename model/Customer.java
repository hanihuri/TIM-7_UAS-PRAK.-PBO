package model;

import java.util.ArrayList;

import driver.ShopData;

/**
 * Kelas Customer merepresentasikan akun pengguna yang dapat melakukan
 * aktivitas pembelian dalam aplikasi. Setiap Customer memiliki:
 * - Keranjang (Keranjang) untuk menyimpan barang sebelum checkout.
 * - Daftar invoice selesai (ArrayList<Invoice>) sebagai riwayat transaksi.
 * 
 * Customer mewarisi atribut dasar dari kelas Akun (id, username, password).
 * 
 * Kelas ini juga menyediakan method untuk:
 * - Menambah invoice selesai
 * - Mengambil transaksi yang masih berstatus DIBUAT (menunggu proses checkout)
 */

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    public Customer(String id, String username, String password) {
        super(id, username, password);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    public ArrayList<Invoice> getInvoiceSelesai() {
        return invoiceSelesai;
    }

    public void tambahInvoice(Invoice invoice) {
        invoiceSelesai.add(invoice);
    }

    public Transaksi getTransaksiMenunggu() {
    for (Transaksi t : ShopData.listTransaksi) {
        if (t.getCustomer() == this && t.getStatus() == StatusTransaksi.DIBUAT) {
            return t;
            }
        }
    return null;
    }

}
