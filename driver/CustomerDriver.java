package driver;

import model.*;
import java.util.ArrayList;

public class CustomerDriver {

    public static void main(String[] args) {

        // 1. Buat customer
        Customer c = new Customer("C001", "keke", "123");

        // 2. Buat daftar barang dummy
        Barang apel = new Barang("Apel", 10000, 10, "apel.png");
        Barang jeruk = new Barang("Jeruk", 8000, 15, "jeruk.png");

        // 3. Masukkan barang ke keranjang
        c.getKeranjang().tambahBarang(apel);
        c.getKeranjang().tambahBarang(jeruk);

        System.out.println("=== ISI KERANJANG ===");
        for (Barang b : c.getKeranjang().getListBarang()) {
            System.out.println("- " + b.getNama() + " = Rp" + b.getHarga());
        }

        // 4. Buat transaksi (mengambil semua barang di keranjang)
        ArrayList<Barang> daftarBarang = new ArrayList<>(c.getKeranjang().getListBarang());
        Transaksi trx = new Transaksi(c, daftarBarang);

        // 5. Pilih metode pembayaran (contoh QRIS)
        Pembayaran bayar = new QRIS();
        trx.setPembayaran(bayar);

        // 6. Proses pembayaran
        bayar.bayar(trx);

        // 7. Buat invoice
        Invoice invoice = new Invoice(trx, bayar);

        System.out.println("\n=== INVOICE ===");
        System.out.println(invoice.cetak());

        // 8. Kosongkan keranjang setelah checkout
        c.getKeranjang().kosongkan();

        System.out.println("Keranjang setelah checkout: " + c.getKeranjang().getListBarang().size() + " item");
    }
}