package driver;

import java.util.ArrayList;
import model.*;

public class ShopData {

    public static ArrayList<Barang> listBarang = new ArrayList<>();
    public static ArrayList<Transaksi> listTransaksi = new ArrayList<>();
    public static ArrayList<Customer> listCustomer = new ArrayList<>();

    public static Admin admin;

    static {
        // admin default
        admin = new Admin("A1", "admin", "123");

        // customer default
        listCustomer.add(new Customer("C1", "user", "111"));

        // barang dengan gambar
        listBarang.add(new Barang("Apel", 10000, 50, "apple.png"));
        listBarang.add(new Barang("Jeruk", 8000, 40, "orange.png"));
        listBarang.add(new Barang("Mangga", 12000, 30, "mango.png"));
    }

    // mencari customer berdasarkan username
    public static Customer findCustomerByUsername(String username) {
        for (Customer c : listCustomer) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
}
