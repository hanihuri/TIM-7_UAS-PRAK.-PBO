#  Fresh Fruit Store - Online Shopping with Java

Sistem perbelanjaan online "Fresh Fruit Store" menggunakan Java dengan interface GUI (Swing). Aplikasi toko buah segar ini menyediakan fitur lengkap untuk customer dan admin dalam mengelola transaksi belanja online.

## Requirements

Sistem ini memiliki 2 jenis akun:
1. Akun Pelanggan - untuk berbelanja buah segar
2. Akun Admin - untuk mengelola sistem toko

## Fitur

### Akun Admin
- Melakukan penambahan, penghapusan, dan pengeditan produk buah
- Melihat dan menerima transaksi dari pengguna
- Menyetujui atau menolak pesanan pelanggan

### Akun Pengguna
- Melihat katalog produk buah yang dijual
- Memasukkan produk ke dalam keranjang belanja
- Melakukan checkout barang dari keranjang
- Memilih metode pembayaran (Cash, Credit Card, E-Wallet, Bank Transfer)
- Melihat history belanja setelah transaksi disetujui admin

## Alur Aplikasi

1. Pengguna login ke sistem Fresh Fruit Store
2. Pengguna melihat barang (produk buah segar) yang tersedia
3. Pengguna memasukkan barang ke dalam keranjang belanja
4. Pengguna melakukan checkout/transaksi dari keranjang
5. Pengguna memilih metode pembayaran saat checkout
6. Admin menerima transaksi yang dilakukan pengguna
7. Invoice tercetak dan masuk ke dalam riwayat transaksi pengguna

## Teknologi

- Java SE - Bahasa pemrograman
- Java Swing - GUI Framework (bukan CLI)
- OOP - Object-Oriented Programming
- MVC Architecture - Pemisahan Model, View, Utility

## Cara Menjalankan
# Clone repository
git clone [https://github.com/username/fresh-fruit-store.git](https://github.com/hanihuri/TIM-7_UAS-PRAK.-PBO/new/main?filename=README.md)

# Compile
javac Main.java

# Jalankan
java Main

## 📁 Struktur Project
```
src/
├── model/
│   ├── UserAccount.java       # Model akun pengguna
│   ├── ProductItem.java       # Model produk buah
│   ├── ShoppingCartItem.java  # Model item keranjang
│   └── OrderData.java         # Model data pesanan
├── view/
│   ├── LoginFrame.java        # GUI Login
│   ├── RegisterFrame.java     # GUI Registrasi
│   ├── MainFrame.java         # GUI Utama (Dashboard)
│   └── CheckoutDialog.java    # GUI Checkout
├── util/
│   └── DataManager.java       # Manajemen data
└── Main.java                  # Entry point
```

## 🎨 Tampilan GUI

- Modern interface dengan gradient pink
- Card-based layout untuk produk
- Interactive tables untuk manajemen data
- Responsive buttons dengan hover effects
- Dialog checkout dengan pilihan pembayaran

## 📝 Catatan

- Sistem menggunakan GUI Swing (bukan CLI/Command Line)
- Data menggunakan in-memory storage
- Pastikan JDK 8 atau lebih tinggi terinstall

## 👨‍💻 Author

[Nama Anda]
