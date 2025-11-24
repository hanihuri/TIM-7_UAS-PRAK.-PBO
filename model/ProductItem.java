package model;

import java.io.Serializable;

/**
 * Kelas ProductItem merepresentasikan satu produk dalam sistem.
 * 
 * Kelas ini menyimpan informasi dasar produk seperti:
 * - id unik produk
 * - nama produk
 * - harga produk
 * - deskripsi produk
 * - stok tersedia
 *
 * Kelas ini mengimplementasikan Serializable agar objek dapat disimpan ke file.
 *
 * Metode penting:
 * - toString() : mengubah objek menjadi format string CSV
 * - fromString() : membuat objek ProductItem dari satu baris CSV
 */

public class ProductItem implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private int stock;
    
    public ProductItem(int id, String name, double price, String description, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + description + "," + stock;
    }
    
    public static ProductItem fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new ProductItem(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                parts[3],
                Integer.parseInt(parts[4])
            );
        }
        return null;
    }
}
