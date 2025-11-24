package model;

/**
 * Implementasi metode pembayaran menggunakan QRIS.
 * Kelas ini mengatur proses pembayaran dan status transaksi
 * ketika metode pembayaran QRIS dipilih.
 */
public class QRIS implements Pembayaran {

    /**
     * Mengubah status transaksi menjadi MENUNGGU_KONFIRMASI_ADMIN
     * karena pembayaran melalui QRIS memerlukan konfirmasi dari admin.
     *
     * @param transaksi objek transaksi yang statusnya akan diperbarui
     */
    @Override
    public void bayar(Transaksi transaksi) {
        transaksi.setStatus(StatusTransaksi.MENUNGGU_KONFIRMASI_ADMIN);
    }

    /**
     * Memberikan informasi bahwa metode pembayaran yang digunakan adalah QRIS.
     *
     * @return nama metode pembayaran QRIS
     */
    @Override
    public String prosesPembayaran() {
        return "QRIS";
    }
}

