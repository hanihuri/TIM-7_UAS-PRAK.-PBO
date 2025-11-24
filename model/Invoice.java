package model;

/**
 * Kelas Invoice merepresentasikan dokumen tagihan yang berisi informasi
 * transaksi dan metode pembayaran yang digunakan.  
 * 
 * Invoice ini dihasilkan setelah transaksi dilakukan dan berfungsi untuk
 * menampilkan detail transaksi seperti ID, customer, total harga, status,
 * serta metode pembayaran yang diproses.
 */

public class Invoice {
    private Transaksi transaksi;
    private Pembayaran pembayaran;

    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public String cetak() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== INVOICE =====\n");
        sb.append("ID Transaksi : ").append(transaksi.getIdTransaksi()).append("\n");
        sb.append("Customer     : ").append(transaksi.getCustomer().getUsername()).append("\n");
        sb.append("Total        : ").append(transaksi.getTotal()).append("\n");
        sb.append("Status       : ").append(transaksi.getStatus()).append("\n");
        sb.append("Metode Bayar : ").append(pembayaran.prosesPembayaran()).append("\n");
        sb.append("===================\n");
        return sb.toString();
    }
}
