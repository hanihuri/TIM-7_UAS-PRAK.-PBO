package model;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Kelas OrderData merepresentasikan satu entri pesanan yang dibuat oleh
 * pengguna dalam aplikasi. Setiap pesanan menyimpan informasi seperti:
 * - ID pesanan,
 * - username pemesan,
 * - daftar item yang dibeli,
 * - total harga,
 * - metode pembayaran,
 * - status pesanan,
 * - tanggal dibuatnya pesanan.
 *
 * Kelas ini juga menyediakan metode bantu seperti pemformatan tanggal
 * dan representasi warna status untuk keperluan tampilan GUI.
 *
 * Objek OrderData digunakan dalam proses checkout, riwayat pesanan,
 * dan manajemen pesanan di sisi admin.
 */

public class OrderData {
    private int orderId;
    private String username;
    private List<ShoppingCartItem> items;
    private double totalAmount;
    private String status;
    private Date orderDate;
    private String paymentMethod;
    
    public OrderData(int orderId, String username, List<ShoppingCartItem> items, double totalAmount, String paymentMethod) {
        this.orderId = orderId;
        this.username = username;
        this.items = items;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = "Pending";
        this.orderDate = new Date();
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(orderDate);
    }
    
    public String getStatusColor() {
        if (status.equals("Approved")) {
            return "green";
        } else if (status.equals("Rejected")) {
            return "red";
        } else if (status.equals("Shipped")) {
            return "blue";
        } else if (status.equals("Completed")) {
            return "darkgreen";
        } else {
            return "orange";
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderId).append(",");
        sb.append(username).append(",");
        sb.append(totalAmount).append(",");
        sb.append(status).append(",");
        sb.append(paymentMethod).append(",");
        sb.append(orderDate.getTime()).append(",");
        
        for (int i = 0; i < items.size(); i++) {
            ShoppingCartItem item = items.get(i);
            sb.append(item.getProduct().getId()).append(":");
            sb.append(item.getQuantity());
            if (i < items.size() - 1) {
                sb.append(";");
            }
        }
        
        return sb.toString();
    }
}
