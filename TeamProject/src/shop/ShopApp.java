package shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

public class ShopApp extends JFrame {
    private JPanel shopPanel;
    private JScrollPane scrollPane;

    public ShopApp() {
        setTitle("상점");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));

        addCategory("헤어", 3);
        addCategory("상의", 3);
        addCategory("하의", 3);
        addCategory("신발", 3);
        addCategory("악세서리", 3);

        scrollPane = new JScrollPane(shopPanel);
        add(scrollPane);

        setVisible(true);
    }

    private void addCategory(String categoryName, int itemCount) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout()); // FlowLayout으로 변경

        JLabel categoryLabel = new JLabel(categoryName);
        categoryPanel.add(categoryLabel);

        for (int i = 1; i <= itemCount; i++) {
            Item item = new Item(categoryName + " " + i, i * 10); 
            addItem(categoryPanel, item);
        }

        shopPanel.add(categoryPanel);
    }

    private void addItem(JPanel categoryPanel, Item item) {
        JButton itemButton = new JButton(item.getName() + " - $" + item.getPrice());

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ShopApp.this, item.getName() + "을(를) 구입했습니다! 가격: $" + item.getPrice());
            }
        });

        categoryPanel.add(itemButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShopApp();
            }
        });
    }
}

