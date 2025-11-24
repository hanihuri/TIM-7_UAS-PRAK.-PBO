package model;

/**
 * Enum yang merepresentasikan status dari sebuah transaksi.
 * Setiap nilai menunjukkan tahapan proses transaksi dalam sistem.
 */
public enum StatusTransaksi {

    /**
     * Transaksi telah dibuat namun belum diproses lebih lanjut.
     */
    DIBUAT,

    /**
     * Transaksi menunggu konfirmasi dari admin sebelum dilanjutkan.
     */
    MENUNGGU_KONFIRMASI_ADMIN,

    /**
     * Transaksi telah selesai diproses dan dinyatakan berhasil.
     */
    SELESAI
}

