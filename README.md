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

##  Struktur Project
```
TIM-7_UAS-PRAK.-PBO/
├── data/
│   ├── orders.txt            # Data pesanan
│   ├── products.txt          # Data produk
│   └── users.txt             # Data user
├── driver/
│   ├── AdminDriver.java      # Driver untuk admin
│   ├── CustomerDriver.java   # Driver untuk customer
│   ├── Driver.java           # Base driver
│   └── ShopData.java         # Handler data toko
├── model/
│   ├── Admin.java            # Model admin
│   ├── Akun.java             # Model akun (parent class)
│   ├── BankTransfer.java     # Model pembayaran bank
│   ├── Barang.java           # Model barang
│   ├── COD.java              # Model Cash on Delivery
│   ├── Customer.java         # Model customer
│   ├── Invoice.java          # Model invoice
│   ├── IPembayaran.java      # Interface pembayaran
│   ├── Keranjang.java        # Model keranjang belanja
│   ├── ListBarang.java       # List barang
│   ├── OrderData.java        # Model data pesanan
│   ├── Pembayaran.java       # Model pembayaran
│   ├── ProductItem.java      # Model item produk
│   ├── QRIS.java             # Model pembayaran QRIS
│   ├── ShoppingCartItem.java # Model item keranjang
│   ├── StatusTransaksi.java  # Enum status transaksi
│   ├── Transaksi.java        # Model transaksi
│   └── UserAccount.java      # Model akun user
├── util/
│   ├── DataManager.class     # Class manajemen data (compiled)
│   └── DataManager.java      # Source manajemen data
├── view/
│   ├── CheckoutDialog.java   # GUI Dialog checkout
│   ├── LoginFrame.java       # GUI Login
│   ├── MainFrame.java        # GUI Frame utama
│   └── RegisterFrame.java    # GUI Registrasi
└── Main.java                 # Entry point aplikasi
```

##  Tampilan GUI

- Modern interface dengan gradient pink
- Card-based layout untuk produk
- Interactive tables untuk manajemen data
- Responsive buttons dengan hover effects
- Dialog checkout dengan pilihan pembayaran

##  Author

- Hani Huriyah Ahmad
- Fitriani
- Fayla Syifa Rustam
