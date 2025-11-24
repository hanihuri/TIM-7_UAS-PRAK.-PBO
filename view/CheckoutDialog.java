package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Membuat satu opsi metode pembayaran dalam bentuk panel interaktif
 * yang berisi radio button, judul, dan deskripsi.
 * Panel dapat diklik untuk memilih metode pembayaran dan akan
 * mengubah tampilan (highlight) saat dipilih.
 *
 * @param parent   panel induk tempat opsi ditambahkan
 * @param index    indeks opsi dalam array kontrol
 * @param title    judul metode pembayaran (misalnya "Bank Transfer")
 * @param description deskripsi singkat metode pembayaran
 * @param group    ButtonGroup yang menampung radio button
 * @param selected apakah opsi ini dipilih secara default
 */

public class CheckoutDialog extends JDialog {
    private String selectedPayment;
    private boolean confirmed;
    private double totalAmount;
    private JPanel[] paymentPanels;
    private JRadioButton[] radioButtons;
    
    public CheckoutDialog(JFrame parent, double total) {
        super(parent, "Checkout - Select Payment Method", true);
        this.totalAmount = total;
        this.confirmed = false;
        this.paymentPanels = new JPanel[4];
        this.radioButtons = new JRadioButton[4];
        
        setSize(550, 700);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel titleLabel = new JLabel("Select Payment Method");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(255, 69, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel totalLabel = new JLabel("Total: Rp " + String.format("%,d", (int)totalAmount));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 22));
        totalLabel.setForeground(new Color(0, 150, 0));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(totalLabel);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        ButtonGroup paymentGroup = new ButtonGroup();
        
        createPaymentOption(mainPanel, 0, "🏦 Bank Transfer", "BCA, Mandiri, BRI", paymentGroup, true);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        
        createPaymentOption(mainPanel, 1, "💳 Credit/Debit Card", "Visa, Mastercard, JCB", paymentGroup, false);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        
        createPaymentOption(mainPanel, 2, "📱 E-Wallet", "GoPay, OVO, Dana, ShopeePay", paymentGroup, false);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        
        createPaymentOption(mainPanel, 3, "💰 Cash on Delivery", "Bayar saat barang tiba", paymentGroup, false);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton confirmBtn = new JButton("Confirm Order");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 16));
        confirmBtn.setBackground(new Color(255, 105, 180));
        confirmBtn.setForeground(Color.BLACK);
        confirmBtn.setPreferredSize(new Dimension(160, 50));
        confirmBtn.setFocusPainted(false);
        confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmBtn.setBorder(BorderFactory.createEmptyBorder());
        confirmBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                confirmBtn.setBackground(new Color(255, 120, 195));
            }
            public void mouseExited(MouseEvent e) {
                confirmBtn.setBackground(new Color(255, 105, 180));
            }
        });
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        buttonPanel.add(confirmBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBtn.setBackground(new Color(220, 220, 220));
        cancelBtn.setForeground(new Color(60, 60, 60));
        cancelBtn.setPreferredSize(new Dimension(130, 50));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.setBorder(BorderFactory.createEmptyBorder());
        cancelBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cancelBtn.setBackground(new Color(200, 200, 200));
            }
            public void mouseExited(MouseEvent e) {
                cancelBtn.setBackground(new Color(220, 220, 220));
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });
        buttonPanel.add(cancelBtn);
        
        mainPanel.add(buttonPanel);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void createPaymentOption(JPanel parent, final int index, String title, String description, ButtonGroup group, boolean selected) {
        final JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout(15, 0));
        optionPanel.setBackground(new Color(248, 249, 250));
        optionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
            BorderFactory.createEmptyBorder(18, 20, 18, 20)
        ));
        optionPanel.setMaximumSize(new Dimension(490, 75));
        optionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        paymentPanels[index] = optionPanel;
        
        final JRadioButton radio = new JRadioButton();
        radio.setBackground(new Color(248, 249, 250));
        radio.setFocusPainted(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        group.add(radio);
        radioButtons[index] = radio;
        
        if (selected) {
            radio.setSelected(true);
            selectedPayment = title.substring(title.indexOf(" ") + 1);
            optionPanel.setBackground(new Color(255, 245, 250));
            optionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)
            ));
        }
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(optionPanel.getBackground());
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(40, 40, 40));
        textPanel.add(titleLabel);
        
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descLabel.setForeground(new Color(120, 120, 120));
        textPanel.add(descLabel);
        
        optionPanel.add(radio, BorderLayout.WEST);
        optionPanel.add(textPanel, BorderLayout.CENTER);
        
        optionPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectPaymentOption(index, title);
            }
            
            public void mouseEntered(MouseEvent e) {
                if (!radio.isSelected()) {
                    optionPanel.setBackground(new Color(255, 250, 245));
                    textPanel.setBackground(new Color(255, 250, 245));
                    optionPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(255, 180, 200), 2),
                        BorderFactory.createEmptyBorder(18, 20, 18, 20)
                    ));
                }
            }
            
            public void mouseExited(MouseEvent e) {
                if (!radio.isSelected()) {
                    optionPanel.setBackground(new Color(248, 249, 250));
                    textPanel.setBackground(new Color(248, 249, 250));
                    optionPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                        BorderFactory.createEmptyBorder(18, 20, 18, 20)
                    ));
                }
            }
        });
        
        radio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectPaymentOption(index, title);
            }
        });
        
        parent.add(optionPanel);
    }
    
    private void selectPaymentOption(int index, String title) {
        radioButtons[index].setSelected(true);
        selectedPayment = title.substring(title.indexOf(" ") + 1);
        
        for (int i = 0; i < paymentPanels.length; i++) {
            if (i == index) {
                paymentPanels[i].setBackground(new Color(255, 245, 250));
                paymentPanels[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                    BorderFactory.createEmptyBorder(18, 20, 18, 20)
                ));
                Component textPanel = paymentPanels[i].getComponent(1);
                if (textPanel instanceof JPanel) {
                    textPanel.setBackground(new Color(255, 245, 250));
                }
            } else {
                paymentPanels[i].setBackground(new Color(248, 249, 250));
                paymentPanels[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                    BorderFactory.createEmptyBorder(18, 20, 18, 20)
                ));
                Component textPanel = paymentPanels[i].getComponent(1);
                if (textPanel instanceof JPanel) {
                    textPanel.setBackground(new Color(248, 249, 250));
                }
            }
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public String getSelectedPayment() {
        return selectedPayment;
    }
}
