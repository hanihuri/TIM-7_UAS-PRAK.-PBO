package model;

/**
 * Interface Pembayaran digunakan sebagai kontrak untuk semua metode pembayaran
 * yang tersedia dalam sistem. Setiap kelas yang mengimplementasikan interface ini
 * harus menyediakan:
 *
 * - proses pembayaran terhadap objek Transaksi melalui metode bayar()
 * - deskripsi metode pembayaran melalui prosesPembayaran(), yang nantinya
 *   ditampilkan dalam Invoice.
 *
 * Interface ini memastikan setiap metode pembayaran memiliki perilaku yang konsisten
 * dan dapat diproses oleh sistem, baik untuk transaksi maupun pencetakan invoice.
 */

public interface Pembayaran {
    void bayar(Transaksi transaksi);
    String prosesPembayaran();   // ← WAJIB untuk Invoice
}
