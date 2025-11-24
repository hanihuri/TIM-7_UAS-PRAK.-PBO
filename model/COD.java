package model;

/**
 * Kelas COD merupakan implementasi dari interface Pembayaran
 * yang merepresentasikan metode pembayaran Cash On Delivery (COD).
 * Pada metode ini, transaksi tidak langsung dibayar, tetapi 
 * status transaksi akan diatur menjadi "MENUNGGU_KONFIRMASI_ADMIN".
 * 
 * Kelas ini digunakan dalam proses checkout baik pada CLI maupun GUI.
 */

public class COD implements Pembayaran {

    @Override
    public void bayar(Transaksi transaksi) {
        transaksi.setStatus(StatusTransaksi.MENUNGGU_KONFIRMASI_ADMIN);
    }

    @Override
    public String prosesPembayaran() {
        return "COD (Bayar di tempat)";
    }
}
