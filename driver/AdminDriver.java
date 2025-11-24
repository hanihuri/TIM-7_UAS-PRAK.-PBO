package driver;

import java.util.ArrayList;
import java.util.Scanner;
import model.Barang;
import model.Transaksi;
import model.*;

public class AdminDriver extends Driver {
    private Admin admin;
    private ArrayList<Barang> listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver(Admin admin, ArrayList<Barang> listBarang, ArrayList<Transaksi> listTransaksi, Scanner scanner) {
        super(scanner);
        this.admin = admin;
        this.listBarang = listBarang;
        this.listTransaksi = listTransaksi;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka. Coba lagi.");
            }
        }
    }

    @Override
    public void menu() {
        int pilih;
        do {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Lihat stok barang");
            System.out.println("2. Edit barang");
            System.out.println("3. Hapus barang");
            System.out.println("4. Lihat semua transaksi");
            System.out.println("5. Terima transaksi (konfirmasi pembayaran)");
            System.out.println("6. Logout");
            pilih = readInt("Pilih: ");

            switch (pilih) {
                case 1:
                    lihatStokBarang();
                    break;
                case 2:
                    editBarang();
                    break;
                case 3:
                    hapusBarang();
                    break;
                case 4:
                    lihatTransaksi();
                    break;
                case 5:
                    terimaTransaksi();
                    break;
                case 6:
                    System.out.println("Logout admin...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 6);
    }

    private void lihatStokBarang() {
        System.out.println("\n===== STOK BARANG =====");
        if (listBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
        } else {
            int i = 1;
            for (Barang b : listBarang) {
                System.out.println(i++ + ". " + b);
            }
        }
        System.out.println("0. Back");
        readInt("Input 0 untuk kembali: ");
    }

    private void editBarang() {
        System.out.println("\n===== EDIT BARANG =====");
        if (listBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        int i = 1;
        for (Barang b : listBarang) {
            System.out.println(i++ + ". " + b);
        }
        System.out.println("0. Back");
        int pilih = readInt("Pilih barang yang ingin di-edit: ");
        if (pilih == 0) return;
        if (pilih < 1 || pilih > listBarang.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Barang b = listBarang.get(pilih - 1);
        System.out.print("Nama baru (" + b.getNama() + "): ");
        String namaBaru = scanner.nextLine();
        int hargaBaru = readInt("Harga baru (" + b.getHarga() + "): ");
        int stokBaru = readInt("Stok baru (" + b.getStok() + "): ");

        if (!namaBaru.isBlank()) b.setNama(namaBaru);
        b.setHarga(hargaBaru);
        b.setStok(stokBaru);

        System.out.println("Barang berhasil di-update!");
    }

    private void hapusBarang() {
        System.out.println("\n===== HAPUS BARANG =====");
        if (listBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        int i = 1;
        for (Barang b : listBarang) {
            System.out.println(i++ + ". " + b);
        }
        System.out.println("0. Back");
        int pilih = readInt("Pilih barang yang ingin dihapus: ");
        if (pilih == 0) return;
        if (pilih < 1 || pilih > listBarang.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Barang b = listBarang.remove(pilih - 1);
        System.out.println("Barang " + b.getNama() + " dihapus.");
    }

    private void lihatTransaksi() {
        System.out.println("\n===== DAFTAR TRANSAKSI =====");
        if (listTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaksi t : listTransaksi) {
                System.out.println(t.ringkas());
            }
        }
        System.out.println("0. Back");
        readInt("Input 0 untuk kembali: ");
    }

    private void terimaTransaksi() {
        System.out.println("\n===== TERIMA TRANSAKSI =====");
        ArrayList<Transaksi> menunggu = new ArrayList<>();
        for (Transaksi t : listTransaksi) {
            if (t.getStatus() == StatusTransaksi.MENUNGGU_KONFIRMASI_ADMIN) {
                menunggu.add(t);
            }
        }
        if (menunggu.isEmpty()) {
            System.out.println("Tidak ada transaksi yang menunggu konfirmasi.");
            return;
        }

        int i = 1;
        for (Transaksi t : menunggu) {
            System.out.println(i++ + ". " + t.ringkas());
        }
        System.out.println("0. Back");
        int pilih = readInt("Pilih transaksi yang akan diterima: ");
        if (pilih == 0) return;
        if (pilih < 1 || pilih > menunggu.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Transaksi t = menunggu.get(pilih - 1);
        t.setStatus(StatusTransaksi.SELESAI);

        if (t.getPembayaran() != null) {
            Invoice inv = new Invoice(t, t.getPembayaran());
            t.getCustomer().tambahInvoice(inv);
            System.out.println("Transaksi diterima. Invoice tercetak:");
            System.out.println(inv.cetak());
        } else {
            System.out.println("Transaksi diterima tanpa data pembayaran.");
        }
    }
}
