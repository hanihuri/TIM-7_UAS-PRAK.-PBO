package model;

/**
 * Kelas yang merepresentasikan item dalam keranjang belanja.
 * Menyimpan informasi mengenai produk yang dipilih dan jumlah yang dibeli.
 */
public class ShoppingCartItem {

    /** Produk yang ditambahkan ke keranjang belanja */
    private ProductItem product;

    /** Jumlah produk yang dipilih */
    private int quantity;
    
    /**
     * Membuat objek ShoppingCartItem baru dengan produk dan jumlah tertentu.
     *
     * @param product objek produk yang akan dimasukkan ke keranjang
     * @param quantity jumlah produk yang dibeli
     */
    public ShoppingCartItem(ProductItem product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    /**
     * Mengambil informasi produk dari item keranjang.
     *
     * @return objek ProductItem yang tersimpan
     */
    public ProductItem getProduct() {
        return product;
    }
    
    /**
     * Mengatur atau mengganti produk pada item keranjang.
     *
     * @param product objek ProductItem baru
     */
    public void setProduct(ProductItem product) {
        this.product = product;
    }
    
    /**
     * Mengambil jumlah produk yang dibeli.
     *
     * @return jumlah produk
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Mengubah jumlah produk dalam item keranjang.
     *
     * @param quantity jumlah baru produk
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Menghitung subtotal harga item berdasarkan harga produk
     * dikalikan dengan jumlah yang dibeli.
     *
     * @return subtotal harga dalam bentuk double
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

