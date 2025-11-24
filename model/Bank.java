package model;

public class Bank implements Pembayaran {

    @Override
    public void bayar(Transaksi transaksi) {
        transaksi.setStatus(StatusTransaksi.MENUNGGU_KONFIRMASI_ADMIN);
    }

    @Override
    public String prosesPembayaran() {
        return "Bank Transfer";
    }
}
