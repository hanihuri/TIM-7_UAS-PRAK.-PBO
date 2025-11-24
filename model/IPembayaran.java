package model;

/**
 * Interface IPembayaran mendefinisikan kontrak untuk berbagai jenis metode
 * pembayaran.  
 * 
 * Setiap kelas yang mengimplementasikan interface ini wajib menyediakan
 * implementasi metode { prosesPembayaran()}, yang akan mengembalikan
 * informasi mengenai proses atau jenis pembayaran yang digunakan.
 */

public interface IPembayaran {
    String prosesPembayaran();
}
