package view;

/**
 * MainFrame adalah kelas utama untuk tampilan aplikasi Fresh Fruit Store.
 * Kelas ini mengelola seluruh antarmuka pengguna utama setelah login, termasuk
 * navigasi, tampilan produk, keranjang belanja, pesanan, dan panel admin.
 * 
 * <p>Fitur utama yang disediakan:</p>
 * <ul>
 *   <li>Tampilan beranda dengan produk unggulan</li>
 *   <li>Katalog produk lengkap</li>
 *   <li>Keranjang belanja interaktif</li>
 *   <li>Riwayat pesanan pengguna</li>
 *   <li>Profil pengguna</li>
 *   <li>Panel admin untuk mengelola produk dan pesanan (khusus admin)</li>
 * </ul>
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import model.*;
import util.DataManager;

public class MainFrame extends JFrame {
    private UserAccount currentUser;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private DataManager dataManager;
    private List<ShoppingCartItem> cart;
    
    public MainFrame(UserAccount user) {
        this.currentUser = user;
        this.dataManager = DataManager.getInstance();
        this.cart = new ArrayList<ShoppingCartItem>();
        
        setTitle("Fresh Fruit Store - Welcome " + user.getUsername());
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        add(createTopBar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);
        add(createMainContent(), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private JPanel createTopBar() {
        JPanel topBar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255, 105, 180),
                    getWidth(), 0, new Color(255, 182, 193)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topBar.setPreferredSize(new Dimension(0, 80));
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Fresh Fruit Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        leftPanel.add(titleLabel);
        
        topBar.add(leftPanel, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);
        
        JLabel userLabel = new JLabel("User: " + currentUser.getUsername() + (currentUser.isAdmin() ? " (Admin)" : ""));
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.BLACK);
        rightPanel.add(userLabel);
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        logoutBtn.setForeground(new Color(255, 105, 180));
        logoutBtn.setBackground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    MainFrame.this, 
                    "Are you sure you want to logout?", 
                    "Logout", 
                    JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame();
                }
            }
        });
        rightPanel.add(logoutBtn);
        
        topBar.add(rightPanel, BorderLayout.EAST);
        
        return topBar;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(new Color(248, 249, 250));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));
        
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        
        addMenuButton(sidebar, "Home", "home");
        
        if (!currentUser.isAdmin()) {
            addMenuButton(sidebar, "Products", "products");
            addMenuButton(sidebar, "My Cart", "cart");
            addMenuButton(sidebar, "Orders", "orders");
        }
        
        addMenuButton(sidebar, "Profile", "profile");
        
        if (currentUser.isAdmin()) {
            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
            JLabel adminLabel = new JLabel("  ADMIN PANEL");
            adminLabel.setFont(new Font("Arial", Font.BOLD, 12));
            adminLabel.setForeground(new Color(255, 69, 0));
            sidebar.add(adminLabel);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            addMenuButton(sidebar, "Manage Products", "admin_products");
            addMenuButton(sidebar, "All Orders", "admin_orders");
        }
        
        sidebar.add(Box.createVerticalGlue());
        
        return sidebar;
    }
    
    private void addMenuButton(JPanel sidebar, String text, final String cardName) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setForeground(new Color(70, 70, 70));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(250, 50));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(255, 240, 245));
                btn.setOpaque(true);
            }
            
            public void mouseExited(MouseEvent e) {
                btn.setOpaque(false);
            }
        });
        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, cardName);
                if (cardName.equals("cart")) {
                    refreshCart();
                } else if (cardName.equals("orders")) {
                    refreshOrders();
                }
            }
        });
        
        sidebar.add(btn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    private JPanel createMainContent() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        contentPanel.add(createHomePanel(), "home");
        contentPanel.add(createProductsPanel(), "products");
        contentPanel.add(createCartPanel(), "cart");
        contentPanel.add(createOrdersPanel(), "orders");
        contentPanel.add(createProfilePanel(), "profile");
        
        if (currentUser.isAdmin()) {
            contentPanel.add(createAdminProductsPanel(), "admin_products");
            contentPanel.add(createAdminOrdersPanel(), "admin_orders");
        }
        
        return contentPanel;
    }
    
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(255, 250, 240));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 182, 193), 2),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel welcomeTitle = new JLabel("Welcome back, " + currentUser.getUsername() + "!");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeTitle.setForeground(new Color(255, 69, 0));
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(welcomeTitle);
        
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel welcomeSubtitle = new JLabel("Fresh fruits delivered to your door");
        welcomeSubtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeSubtitle.setForeground(new Color(100, 100, 100));
        welcomeSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(welcomeSubtitle);
        
        panel.add(welcomePanel, BorderLayout.NORTH);
        
        JPanel featuredPanel = new JPanel(new GridLayout(2, 4, 20, 20));
        featuredPanel.setBackground(Color.WHITE);
        featuredPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        
        List<ProductItem> products = dataManager.getAllProducts();
        for (int i = 0; i < Math.min(8, products.size()); i++) {
            featuredPanel.add(createProductCard(products.get(i), false));
        }
        
        panel.add(featuredPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("All Products");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title, BorderLayout.NORTH);
        
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        List<ProductItem> products = dataManager.getAllProducts();
        for (ProductItem product : products) {
            gridPanel.add(createProductCard(product, true));
        }
        
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createProductCard(final ProductItem product, boolean showButton) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 240, 240), 1),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(255, 250, 240));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                    BorderFactory.createEmptyBorder(18, 13, 18, 13)
                ));
            }
            
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(240, 240, 240), 1),
                    BorderFactory.createEmptyBorder(20, 15, 20, 15)
                ));
            }
        });
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JLabel descLabel = new JLabel(product.getDescription());
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(120, 120, 120));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(descLabel);
        
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel priceLabel = new JLabel("Rp " + String.format("%.0f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 150, 0));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);
        
        JLabel stockLabel = new JLabel("Stock: " + product.getStock());
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        stockLabel.setForeground(new Color(150, 150, 150));
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(stockLabel);
        
        if (showButton) {
            card.add(Box.createRigidArea(new Dimension(0, 10)));
            
            JButton addBtn = new JButton("Add to Cart");
            addBtn.setFont(new Font("Arial", Font.BOLD, 12));
            addBtn.setBackground(new Color(255, 105, 180));
            addBtn.setForeground(Color.BLACK);
            addBtn.setFocusPainted(false);
            addBtn.setBorderPainted(false);
            addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addToCart(product);
                }
            });
            
            card.add(addBtn);
        }
        
        return card;
    }
    
    private void addToCart(ProductItem product) {
        if (product.getStock() <= 0) {
            JOptionPane.showMessageDialog(this, 
                "Sorry, " + product.getName() + " is out of stock!", 
                "Out of Stock", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        for (ShoppingCartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantity() >= product.getStock()) {
                    JOptionPane.showMessageDialog(this, 
                        "Cannot add more! Maximum stock reached.", 
                        "Stock Limit", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                item.setQuantity(item.getQuantity() + 1);
                JOptionPane.showMessageDialog(this, 
                    product.getName() + " quantity updated in cart!", 
                    "Cart Updated", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        cart.add(new ShoppingCartItem(product, 1));
        JOptionPane.showMessageDialog(this, 
            product.getName() + " added to cart!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private JPanel cartPanel;
    
    private JPanel createCartPanel() {
        cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        refreshCart();
        
        return cartPanel;
    }
    
    private void refreshCart() {
        cartPanel.removeAll();
        
        JLabel title = new JLabel("Shopping Cart");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        cartPanel.add(title, BorderLayout.NORTH);
        
        if (cart.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(emptyLabel);
            
            cartPanel.add(centerPanel, BorderLayout.CENTER);
        } else {
            String[] columns = {"Product", "Price", "Quantity", "Subtotal", "Action"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                public boolean isCellEditable(int row, int column) {
                    return column == 4;
                }
            };
            
            double total = 0;
            for (final ShoppingCartItem item : cart) {
                Object[] row = {
                    item.getProduct().getName(),
                    "Rp " + String.format("%.0f", item.getProduct().getPrice()),
                    item.getQuantity(),
                    "Rp " + String.format("%.0f", item.getSubtotal()),
                    "Remove"
                };
                model.addRow(row);
                total += item.getSubtotal();
            }
            
            JTable table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(40);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            
            table.getColumn("Action").setCellRenderer(new ButtonRenderer());
            table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), cart, table));
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(Color.WHITE);
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            
            JLabel totalLabel = new JLabel("Total: Rp " + String.format("%.0f", total));
            totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
            totalLabel.setForeground(new Color(255, 69, 0));
            bottomPanel.add(totalLabel, BorderLayout.WEST);
            
            JButton checkoutBtn = new JButton("Checkout");
            checkoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
            checkoutBtn.setBackground(new Color(255, 105, 180));
            checkoutBtn.setForeground(Color.BLACK);
            checkoutBtn.setPreferredSize(new Dimension(150, 50));
            checkoutBtn.setFocusPainted(false);
            checkoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            final double finalTotal = total;
            checkoutBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleCheckout(finalTotal);
                }
            });
            
            bottomPanel.add(checkoutBtn, BorderLayout.EAST);
            
            cartPanel.add(scrollPane, BorderLayout.CENTER);
            cartPanel.add(bottomPanel, BorderLayout.SOUTH);
        }
        
        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    private void handleCheckout(double total) {
        CheckoutDialog dialog = new CheckoutDialog(this, total);
        
        if (dialog.isConfirmed()) {
            String paymentMethod = dialog.getSelectedPayment();
            
            OrderData order = new OrderData(
                dataManager.getNextOrderId(),
                currentUser.getUsername(),
                new ArrayList<ShoppingCartItem>(cart),
                total,
                paymentMethod
            );
            
            dataManager.addOrder(order);
            
            for (ShoppingCartItem item : cart) {
                ProductItem product = item.getProduct();
                product.setStock(product.getStock() - item.getQuantity());
                dataManager.updateProduct(product);
            }
            
            cart.clear();
            refreshCart();
            
            JOptionPane.showMessageDialog(this, 
                "Order placed successfully!\nOrder ID: #" + order.getOrderId() + "\nPayment: " + paymentMethod + "\n\nWaiting for admin approval...", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private JPanel ordersPanel;
    
    private JPanel createOrdersPanel() {
        ordersPanel = new JPanel(new BorderLayout());
        ordersPanel.setBackground(Color.WHITE);
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        refreshOrders();
        
        return ordersPanel;
    }
    
    private void refreshOrders() {
        ordersPanel.removeAll();
        
        JLabel title = new JLabel("My Orders");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        ordersPanel.add(title, BorderLayout.NORTH);
        
        List<OrderData> orders = dataManager.getOrdersByUsername(currentUser.getUsername());
        
        if (orders.isEmpty()) {
            JLabel emptyLabel = new JLabel("No orders yet");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(emptyLabel);
            
            ordersPanel.add(centerPanel, BorderLayout.CENTER);
        } else {
            String[] columns = {"Order ID", "Date", "Items", "Total", "Payment", "Status"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for (OrderData order : orders) {
                StringBuilder items = new StringBuilder();
                for (ShoppingCartItem item : order.getItems()) {
                    items.append(item.getProduct().getName()).append(" x").append(item.getQuantity()).append(", ");
                }
                if (items.length() > 0) {
                    items.setLength(items.length() - 2);
                }
                
                Object[] row = {
                    "#" + order.getOrderId(),
                    order.getFormattedDate(),
                    items.toString(),
                    "Rp " + String.format("%.0f", order.getTotalAmount()),
                    order.getPaymentMethod(),
                    order.getStatus()
                };
                model.addRow(row);
            }
            
            JTable table = new JTable(model) {
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    if (column == 5) {
                        String status = (String) getValueAt(row, column);
                        if (status.equals("Approved")) {
                            c.setForeground(new Color(0, 150, 0));
                        } else if (status.equals("Rejected")) {
                            c.setForeground(Color.RED);
                        } else if (status.equals("Pending")) {
                            c.setForeground(new Color(255, 140, 0));
                        } else {
                            c.setForeground(Color.BLACK);
                        }
                        c.setFont(new Font("Arial", Font.BOLD, 14));
                    } else {
                        c.setForeground(Color.BLACK);
                        c.setFont(new Font("Arial", Font.PLAIN, 14));
                    }
                    return c;
                }
            };
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(40);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            
            ordersPanel.add(scrollPane, BorderLayout.CENTER);
        }
        
        ordersPanel.revalidate();
        ordersPanel.repaint();
    }
    
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("My Profile");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        addProfileField(formPanel, "Username:", currentUser.getUsername());
        addProfileField(formPanel, "Email:", currentUser.getEmail());
        addProfileField(formPanel, "Phone:", currentUser.getPhone());
        addProfileField(formPanel, "Account Type:", currentUser.isAdmin() ? "Admin" : "Customer");
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void addProfileField(JPanel panel, String label, String value) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Arial", Font.BOLD, 16));
        labelComp.setPreferredSize(new Dimension(150, 30));
        fieldPanel.add(labelComp);
        
        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.PLAIN, 16));
        fieldPanel.add(valueComp);
        
        panel.add(fieldPanel);
    }
    
    private JPanel createAdminProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel title = new JLabel("Manage Products");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(255, 69, 0));
        headerPanel.add(title, BorderLayout.WEST);
        
        JButton addBtn = new JButton("+ Add Product");
        addBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addBtn.setBackground(new Color(0, 150, 0));
        addBtn.setForeground(Color.BLACK);
        addBtn.setPreferredSize(new Dimension(150, 40));
        addBtn.setFocusPainted(false);
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddProductDialog();
            }
        });
        headerPanel.add(addBtn, BorderLayout.EAST);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        
        List<ProductItem> products = dataManager.getAllProducts();
        
        if (products.isEmpty()) {
            JLabel emptyLabel = new JLabel("No products in system");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(emptyLabel);
            
            panel.add(centerPanel, BorderLayout.CENTER);
        } else {
            String[] columns = {"ID", "Name", "Price", "Stock", "Description", "Actions"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                public boolean isCellEditable(int row, int column) {
                    return column == 5;
                }
            };
            
            for (ProductItem product : products) {
                Object[] row = {
                    product.getId(),
                    product.getName(),
                    "Rp " + String.format("%.0f", product.getPrice()),
                    product.getStock(),
                    product.getDescription(),
                    "Edit | Delete"
                };
                model.addRow(row);
            }
            
            JTable table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(40);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            table.getColumn("Actions").setMinWidth(180); 

            table.getColumn("Actions").setCellRenderer(new ProductActionRenderer());
            table.getColumn("Actions").setCellEditor(new ProductActionEditor(new JCheckBox(), dataManager, products));
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            
            panel.add(scrollPane, BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    private void showAddProductDialog() {
        JDialog dialog = new JDialog(this, "Add New Product", true);
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField descField = new JTextField();
        
        panel.add(createInputField("Product Name:", nameField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createInputField("Price:", priceField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createInputField("Stock:", stockField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createInputField("Description:", descField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(0, 150, 0));
        saveBtn.setForeground(Color.BLACK);
        saveBtn.setPreferredSize(new Dimension(100, 35));
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int stock = Integer.parseInt(stockField.getText().trim());
                    String desc = descField.getText().trim();
                    
                    if (name.isEmpty() || desc.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    ProductItem newProduct = new ProductItem(dataManager.getNextProductId(), name, price, desc, stock);
                    dataManager.addProduct(newProduct);
                    
                    JOptionPane.showMessageDialog(dialog, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    refreshAdminProductsPanel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid price or stock format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(saveBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(150, 150, 150));
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setPreferredSize(new Dimension(100, 35));
        cancelBtn.setFocusPainted(false);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(cancelBtn);
        
        panel.add(buttonPanel);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private JPanel createInputField(String label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(400, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);
        
        return panel;
    }
    
    private void refreshAdminProductsPanel() {
        contentPanel.remove(5);
        contentPanel.add(createAdminProductsPanel(), "admin_products", 5);
        cardLayout.show(contentPanel, "admin_products");
    }
    
    class ProductActionRenderer extends JPanel implements TableCellRenderer {
        private JButton editBtn;
        private JButton deleteBtn;
        
        public ProductActionRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            
            editBtn = new JButton("Edit");
            editBtn.setFont(new Font("Arial", Font.BOLD, 11));
            editBtn.setBackground(new Color(255, 165, 0));
            editBtn.setForeground(Color.BLACK);
            editBtn.setFocusPainted(false);
            editBtn.setPreferredSize(new Dimension(70, 28));
            
            deleteBtn = new JButton("Delete");
            deleteBtn.setFont(new Font("Arial", Font.BOLD, 11));
            deleteBtn.setBackground(Color.RED);
            deleteBtn.setForeground(Color.BLACK);
            deleteBtn.setFocusPainted(false);
            deleteBtn.setPreferredSize(new Dimension(70, 28));
            
            add(editBtn);
            add(deleteBtn);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    
    class ProductActionEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton editBtn;
        private JButton deleteBtn;
        private int editingRow;
        private DataManager dm;
        private List<ProductItem> productsList;
        
        public ProductActionEditor(JCheckBox checkBox, DataManager dataManager, List<ProductItem> products) {
            super(checkBox);
            this.dm = dataManager;
            this.productsList = products;
            
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            
            editBtn = new JButton("Edit");
            editBtn.setFont(new Font("Arial", Font.BOLD, 11));
            editBtn.setBackground(new Color(255, 165, 0));
            editBtn.setForeground(Color.BLACK);
            editBtn.setFocusPainted(false);
            editBtn.setPreferredSize(new Dimension(70, 28));
            editBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleEdit();
                }
            });
            
            deleteBtn = new JButton("Delete");
            deleteBtn.setFont(new Font("Arial", Font.BOLD, 11));
            deleteBtn.setBackground(Color.RED);
            deleteBtn.setForeground(Color.BLACK);
            deleteBtn.setFocusPainted(false);
            deleteBtn.setPreferredSize(new Dimension(70, 28));
            deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleDelete();
                }
            });
            
            panel.add(editBtn);
            panel.add(deleteBtn);
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            editingRow = row;
            return panel;
        }
        
        private void handleEdit() {
            if (editingRow >= 0 && editingRow < productsList.size()) {
                ProductItem product = productsList.get(editingRow);
                showEditProductDialog(product);
                fireEditingStopped();
            }
        }
        
        private void handleDelete() {
            if (editingRow >= 0 && editingRow < productsList.size()) {
                ProductItem product = productsList.get(editingRow);
                int result = JOptionPane.showConfirmDialog(panel, 
                    "Delete product: " + product.getName() + "?", 
                    "Confirm Delete", 
                    JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dm.deleteProduct(product.getId());
                    JOptionPane.showMessageDialog(panel, "Product deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    fireEditingStopped();
                    refreshAdminProductsPanel();
                }
            }
        }
        
        private void showEditProductDialog(final ProductItem product) {
            JDialog dialog = new JDialog(MainFrame.this, "Edit Product", true);
            dialog.setSize(450, 450);
            dialog.setLocationRelativeTo(MainFrame.this);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            
            JLabel titleLabel = new JLabel("Edit Product");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(titleLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            
            JTextField nameField = new JTextField(product.getName());
            JTextField priceField = new JTextField(String.valueOf(product.getPrice()));
            JTextField stockField = new JTextField(String.valueOf(product.getStock()));
            JTextField descField = new JTextField(product.getDescription());
            
            panel.add(createInputField("Product Name:", nameField));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(createInputField("Price:", priceField));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(createInputField("Stock:", stockField));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(createInputField("Description:", descField));
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.setBackground(Color.WHITE);
            
            JButton saveBtn = new JButton("Save");
            saveBtn.setBackground(new Color(0, 150, 0));
            saveBtn.setForeground(Color.BLACK);
            saveBtn.setPreferredSize(new Dimension(100, 35));
            saveBtn.setFocusPainted(false);
            saveBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        product.setName(nameField.getText().trim());
                        product.setPrice(Double.parseDouble(priceField.getText().trim()));
                        product.setStock(Integer.parseInt(stockField.getText().trim()));
                        product.setDescription(descField.getText().trim());
                        
                        dm.updateProduct(product);
                        
                        JOptionPane.showMessageDialog(dialog, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                        refreshAdminProductsPanel();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Invalid price or stock format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buttonPanel.add(saveBtn);
            
            JButton cancelBtn = new JButton("Cancel");
            cancelBtn.setBackground(new Color(150, 150, 150));
            cancelBtn.setForeground(Color.BLACK);
            cancelBtn.setPreferredSize(new Dimension(100, 35));
            cancelBtn.setFocusPainted(false);
            cancelBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            buttonPanel.add(cancelBtn);
            
            panel.add(buttonPanel);
            
            dialog.add(panel);
            dialog.setVisible(true);
        }
        
        public Object getCellEditorValue() {
            return "";
        }
    }
    
    private JPanel createAdminOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("All Orders (Admin)");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(255, 69, 0));
        panel.add(title, BorderLayout.NORTH);
        
        List<OrderData> orders = dataManager.getAllOrders();
        
        if (orders.isEmpty()) {
            JLabel emptyLabel = new JLabel("No orders in system");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(emptyLabel);
            
            panel.add(centerPanel, BorderLayout.CENTER);
        } else {
            String[] columns = {"Order ID", "User", "Date", "Total", "Payment", "Status", "Action"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                public boolean isCellEditable(int row, int column) {
                    return column == 6;
                }
            };
            
            for (OrderData order : orders) {
                Object[] row = {
                    "#" + order.getOrderId(),
                    order.getUsername(),
                    order.getFormattedDate(),
                    "Rp " + String.format("%.0f", order.getTotalAmount()),
                    order.getPaymentMethod(),
                    order.getStatus(),
                    order.getStatus().equals("Pending") ? "Actions" : "-"
                };
                model.addRow(row);
            }
            
            JTable table = new JTable(model) {
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    if (column == 5) {
                        String status = (String) getValueAt(row, column);
                        if (status.equals("Approved")) {
                            c.setForeground(new Color(0, 150, 0));
                        } else if (status.equals("Rejected")) {
                            c.setForeground(Color.RED);
                        } else if (status.equals("Pending")) {
                            c.setForeground(new Color(255, 140, 0));
                        } else {
                            c.setForeground(Color.BLACK);
                        }
                        c.setFont(new Font("Arial", Font.BOLD, 14));
                    } else {
                        c.setForeground(Color.BLACK);
                        c.setFont(new Font("Arial", Font.PLAIN, 14));
                    }
                    return c;
                }
            };
            table.setRowHeight(40);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            
            table.getColumn("Action").setCellRenderer(new AdminButtonRenderer());
            table.getColumn("Action").setCellEditor(new AdminButtonEditor(new JCheckBox(), dataManager, orders));
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            
            panel.add(scrollPane, BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    class AdminButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton approveBtn;
        private JButton rejectBtn;
        
        public AdminButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            
            approveBtn = new JButton("Approve");
            approveBtn.setFont(new Font("Arial", Font.BOLD, 11));
            approveBtn.setBackground(new Color(0, 150, 0));
            approveBtn.setForeground(Color.BLACK);
            approveBtn.setFocusPainted(false);
            approveBtn.setPreferredSize(new Dimension(80, 30));
            
            rejectBtn = new JButton("Reject");
            rejectBtn.setFont(new Font("Arial", Font.BOLD, 11));
            rejectBtn.setBackground(Color.RED);
            rejectBtn.setForeground(Color.BLACK);
            rejectBtn.setFocusPainted(false);
            rejectBtn.setPreferredSize(new Dimension(80, 30));
            
            add(approveBtn);
            add(rejectBtn);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            String status = (String) table.getValueAt(row, 5);
            if (status.equals("Pending")) {
                return this;
            } else {
                JLabel label = new JLabel("-");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        }
    }
    
    class AdminButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton approveBtn;
        private JButton rejectBtn;
        private int editingRow;
        private DataManager dm;
        private List<OrderData> ordersList;
        
        public AdminButtonEditor(JCheckBox checkBox, DataManager dataManager, List<OrderData> orders) {
            super(checkBox);
            this.dm = dataManager;
            this.ordersList = orders;
            
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            
            approveBtn = new JButton("Approve");
            approveBtn.setFont(new Font("Arial", Font.BOLD, 11));
            approveBtn.setBackground(new Color(0, 150, 0));
            approveBtn.setForeground(Color.BLACK);
            approveBtn.setFocusPainted(false);
            approveBtn.setPreferredSize(new Dimension(80, 30));
            approveBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleApprove();
                }
            });
            
            rejectBtn = new JButton("Reject");
            rejectBtn.setFont(new Font("Arial", Font.BOLD, 11));
            rejectBtn.setBackground(Color.RED);
            rejectBtn.setForeground(Color.BLACK);
            rejectBtn.setFocusPainted(false);
            rejectBtn.setPreferredSize(new Dimension(80, 30));
            rejectBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleReject();
                }
            });
            
            panel.add(approveBtn);
            panel.add(rejectBtn);
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            editingRow = row;
            return panel;
        }
        
        private void handleApprove() {
            if (editingRow >= 0 && editingRow < ordersList.size()) {
                OrderData order = ordersList.get(editingRow);
                dm.updateOrderStatus(order.getOrderId(), "Approved");
                JOptionPane.showMessageDialog(panel, 
                    "Order #" + order.getOrderId() + " approved!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                fireEditingStopped();
                refreshAdminPanel();
            }
        }
        
        private void handleReject() {
            if (editingRow >= 0 && editingRow < ordersList.size()) {
                OrderData order = ordersList.get(editingRow);
                dm.updateOrderStatus(order.getOrderId(), "Rejected");
                JOptionPane.showMessageDialog(panel, 
                    "Order #" + order.getOrderId() + " rejected!", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
                fireEditingStopped();
                refreshAdminPanel();
            }
        }
        
        public Object getCellEditorValue() {
            return "";
        }
    }
    
    private void refreshAdminPanel() {
        contentPanel.remove(6);
        contentPanel.add(createAdminOrdersPanel(), "admin_orders", 6);
        cardLayout.show(contentPanel, "admin_orders");
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private List<ShoppingCartItem> cartRef;
        private JTable tableRef;
        
        public ButtonEditor(JCheckBox checkBox, List<ShoppingCartItem> cart, JTable table) {
            super(checkBox);
            this.cartRef = cart;
            this.tableRef = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                int row = tableRef.getSelectedRow();
                if (row >= 0 && row < cartRef.size()) {
                    cartRef.remove(row);
                    refreshCart();
                }
            }
            isPushed = false;
            return label;
        }
        
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}
