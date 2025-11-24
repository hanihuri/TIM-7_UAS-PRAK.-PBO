package model;

/**
 * Kelas Barang merepresentasikan sebuah item produk yang memiliki
 * nama, harga, stok, dan nama file gambar untuk kebutuhan GUI.
 * Class ini digunakan baik pada sisi CLI maupun GUI aplikasi.
 */

public class Barang {
    private String nama;
    private int harga;
    private int stok;
    private String imageName; // untuk menyimpan gambar buah

    // Constructor lengkap untuk GUI
    public Barang(String nama, int harga, int stok, String imageName) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.imageName = imageName;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public String getImageName() {
        return imageName;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return nama + " | Rp " + harga + " | Stok: " + stok;
    }
}
