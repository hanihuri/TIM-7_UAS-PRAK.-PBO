package util;

/**
 * DataManager adalah kelas Singleton yang mengelola data pengguna, produk,
 * dan pesanan pada aplikasi. Kelas ini bertanggung jawab untuk:
 * <ul>
 *   <li>Membuat folder data jika belum ada</li>
 *   <li>Memuat data dari file teks</li>
 *   <li>Menyimpan perubahan data ke file</li>
 *   <li>Menambahkan data awal saat pertama kali berjalan</li>
 *   <li>Melakukan operasi login/registrasi pengguna</li>
 *   <li>CRUD pada data produk</li>
 *   <li>Mengelola data pesanan</li>
 * </ul>
 *
 * Penyimpanan Data:
 * <ul>
 *   <li>data/users.txt</li>
 *   <li>data/products.txt</li>
 *   <li>data/orders.txt</li>
 * </ul>
 */

import model.*;
import java.io.*;
import java.util.*;

public class DataManager {
    private static final String USERS_FILE = "data/users.txt";
    private static final String PRODUCTS_FILE = "data/products.txt";
    private static final String ORDERS_FILE = "data/orders.txt";
    
    private static DataManager instance;
    private List<UserAccount> users;
    private List<ProductItem> products;
    private List<OrderData> orders;
    
    private DataManager() {
        users = new ArrayList<UserAccount>();
        products = new ArrayList<ProductItem>();
        orders = new ArrayList<OrderData>();
        
        createDataDirectory();
        loadData();
        initializeDefaultData();
    }
    
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    
    private void createDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }
    
    private void loadData() {
        loadUsers();
        loadProducts();
        loadOrders();
    }
    
    private void initializeDefaultData() {
        if (users.isEmpty()) {
            users.add(new UserAccount("admin", "admin123", "admin@fruitstore.com", "08123456789", true));
            users.add(new UserAccount("user", "user123", "user@email.com", "08987654321", false));
            saveUsers();
        }
        
        if (products.isEmpty()) {
            products.add(new ProductItem(1, "Apple", 15000, "Fresh red apples", 100));
            products.add(new ProductItem(2, "Orange", 12000, "Sweet oranges", 90));
            products.add(new ProductItem(3, "Banana", 8000, "Yellow bananas", 130));
            products.add(new ProductItem(4, "Kiwi", 20000, "Green kiwi fruit", 50));
            products.add(new ProductItem(5, "Strawberry", 25000, "Fresh strawberries", 70));
            products.add(new ProductItem(6, "Grape", 18000, "Purple grapes", 80));
            products.add(new ProductItem(7, "Watermelon", 30000, "Big watermelon", 50));
            products.add(new ProductItem(8, "Cherry", 35000, "Sweet cherries", 40));
            saveProducts();
        }
    }
    
    // USER METHODS
    private void loadUsers() {
        try {
            File file = new File(USERS_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    UserAccount user = UserAccount.fromString(line);
                    if (user != null) {
                        users.add(user);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveUsers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE));
            for (UserAccount user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public UserAccount login(String username, String password) {
        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean register(UserAccount user) {
        for (UserAccount u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        users.add(user);
        saveUsers();
        return true;
    }
    
    public UserAccount getUserByUsername(String username) {
        for (UserAccount user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    public void updateUser(UserAccount user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                saveUsers();
                break;
            }
        }
    }
    
    // PRODUCT METHODS
    private void loadProducts() {
        try {
            File file = new File(PRODUCTS_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    ProductItem product = ProductItem.fromString(line);
                    if (product != null) {
                        products.add(product);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveProducts() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE));
            for (ProductItem product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<ProductItem> getAllProducts() {
        return new ArrayList<ProductItem>(products);
    }
    
    public ProductItem getProductById(int id) {
        for (ProductItem product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    public void addProduct(ProductItem product) {
        products.add(product);
        saveProducts();
    }
    
    public void updateProduct(ProductItem product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                saveProducts();
                break;
            }
        }
    }
    
    public void deleteProduct(int productId) {
        Iterator<ProductItem> iterator = products.iterator();
        while (iterator.hasNext()) {
            ProductItem p = iterator.next();
            if (p.getId() == productId) {
                iterator.remove();
                break;
            }
        }
        saveProducts();
    }
    
    public int getNextProductId() {
        int maxId = 0;
        for (ProductItem p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }
    
    // ORDER METHODS
    private void loadOrders() {
        try {
            File file = new File(ORDERS_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    OrderData order = parseOrder(line);
                    if (order != null) {
                        orders.add(order);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private OrderData parseOrder(String line) {
        String[] parts = line.split(",", 7);
        if (parts.length >= 7) {
            int orderId = Integer.parseInt(parts[0]);
            String username = parts[1];
            double totalAmount = Double.parseDouble(parts[2]);
            String status = parts[3];
            String paymentMethod = parts[4];
            
            List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
            String[] itemParts = parts[6].split(";");
            for (String item : itemParts) {
                String[] itemData = item.split(":");
                if (itemData.length == 2) {
                    int productId = Integer.parseInt(itemData[0]);
                    int quantity = Integer.parseInt(itemData[1]);
                    ProductItem product = getProductById(productId);
                    if (product != null) {
                        items.add(new ShoppingCartItem(product, quantity));
                    }
                }
            }
            
            OrderData order = new OrderData(orderId, username, items, totalAmount, paymentMethod);
            order.setStatus(status);
            return order;
        }
        return null;
    }
    
    public void updateOrderStatus(int orderId, String newStatus) {
        for (OrderData order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(newStatus);
                saveOrders();
                break;
            }
        }
    }
    
    private void saveOrders() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE));
            for (OrderData order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addOrder(OrderData order) {
        orders.add(order);
        saveOrders();
    }
    
    public List<OrderData> getOrdersByUsername(String username) {
        List<OrderData> userOrders = new ArrayList<OrderData>();
        for (OrderData order : orders) {
            if (order.getUsername().equals(username)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
    
    public List<OrderData> getAllOrders() {
        return new ArrayList<OrderData>(orders);
    }
    
    public int getNextOrderId() {
        int maxId = 0;
        for (OrderData o : orders) {
            if (o.getOrderId() > maxId) {
                maxId = o.getOrderId();
            }
        }
        return maxId + 1;
    }
}
