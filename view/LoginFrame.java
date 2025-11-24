package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import model.UserAccount;
import util.DataManager;

/**
 * LoginFrame adalah tampilan login utama untuk aplikasi Fresh Fruit Store.
 * Kelas ini menggunakan Swing dan menggambar background custom dengan gradient
 * dan bentuk-bentuk dekoratif. Di tengah layar terdapat panel login yang berisi
 * input username, password, serta tombol login dan register.
 *
 * <p>Fitur utama:
 * <ul>
 *   <li>Antarmuka grafis modern dengan gradient dan efek rounded panel</li>
 *   <li>Input username dan password</li>
 *   <li>Tombol Login dan Register</li>
 *   <li>Terhubung dengan {@link util.DataManager} untuk validasi akun</li>
 * </ul>
 *
 * Kelas ini bertanggung jawab hanya untuk tampilan login. Logika autentikasi
 * ditangani melalui DataManager.
 *
 * @author (nama Anda)
 * @version 1.0
 */

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private DataManager dataManager;
    
    public LoginFrame() {
        dataManager = DataManager.getInstance();
        
        setTitle("Fresh Fruit Store - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255, 250, 240),
                    0, getHeight(), new Color(255, 228, 225)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                g2d.setColor(new Color(255, 182, 193, 100));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(getWidth() - 150, getHeight() - 150, 200, 200);
                
                g2d.setColor(new Color(152, 251, 152, 100));
                g2d.fillOval(getWidth() - 100, -50, 150, 150);
                g2d.fillOval(50, getHeight() - 100, 150, 150);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        
        JPanel loginPanel = createLoginPanel();
        mainPanel.add(loginPanel);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 30, 30);
                
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 30, 30);
                
                g2d.setColor(new Color(255, 105, 180, 50));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 30, 30);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 450));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        JLabel titleLabel = new JLabel("Fresh Fruit Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 69, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel subtitleLabel = new JLabel("Healthy & Fresh Every Day");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(100, 149, 237));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtitleLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(70, 70, 70));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(usernameLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        usernameField = createStyledTextField();
        usernameField.setMaximumSize(new Dimension(300, 40));
        panel.add(usernameField);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(70, 70, 70));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passwordLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        passwordField = createStyledPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        panel.add(passwordField);
        
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        loginButton = createStyledButton("Login", new Color(255, 105, 180), Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        panel.add(loginButton);
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        registerButton = createStyledButton("Register", new Color(152, 251, 152), new Color(50, 50, 50));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
        panel.add(registerButton);
        
        return panel;
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
            
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });
        
        return field;
    }
    
    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
            
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });
        
        return field;
    }
    
    private JButton createStyledButton(String text, final Color bgColor, final Color fgColor) {
        JButton button = new JButton(text) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(bgColor.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(bgColor.brighter());
                } else {
                    g2d.setColor(bgColor);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2d.setColor(fgColor);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(fgColor);
        button.setPreferredSize(new Dimension(300, 45));
        button.setMaximumSize(new Dimension(300, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password!", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        UserAccount user = dataManager.login(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, 
                "Login successful! Welcome " + username + "!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            new MainFrame(user);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password!", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRegister() {
        new RegisterFrame(this);
    }
}
