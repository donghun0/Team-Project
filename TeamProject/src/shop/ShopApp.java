package shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Item {
    private String name;
    private int price;
    private boolean soldOut;
    private String category;
    private boolean worn;

    public Item(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.soldOut = false;
        this.category = category;
        this.worn = false;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public boolean isWorn() {
        return worn;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public void setWorn(boolean worn) {
        this.worn = worn;
    }
}

public class ShopApp extends JFrame {
    private JPanel shopPanel;
    private JScrollPane scrollPane;
    private static ShopApp instance;
    private Item selectedItem;
    private Closet closet; 

    private ShopApp(Closet closet) {
        this.closet = closet;

        setTitle("상점");
        setSize(500, 400);

        shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));

        addCategory("헤어", 3);
        addCategory("상의", 3);
        addCategory("하의", 3);
        addCategory("신발", 3);
        addCategory("악세서리", 3);

        scrollPane = new JScrollPane(shopPanel);
        add(scrollPane);

        addWindowListener(); 

        setVisible(true);

        selectedItem = null;
    }

    public static ShopApp getInstance(Closet closet) {
        if (instance == null) {
            instance = new ShopApp(closet);
        }
        return instance;
    }

    private void addWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }

    private void addCategory(String categoryName, int itemCount) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout());

        JLabel categoryLabel = new JLabel(categoryName);
        categoryPanel.add(categoryLabel);

        for (int i = 1; i <= itemCount; i++) {
            Item item = new Item(categoryName + " " + i, i * 10, categoryName);
            addItem(categoryPanel, item);
        }

        shopPanel.add(categoryPanel);
    }

    private void addItem(JPanel categoryPanel, Item item) {
        JButton itemButton = new JButton(item.getName() + " - $" + item.getPrice());

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!item.isSoldOut()) {
                    item.setSoldOut(true);
                    updateItemButton(itemButton, item);
                    selectedItem = item;
                    closet.addItem(item);
                    updateClosetFrame();
                    JOptionPane.showMessageDialog(ShopApp.this, item.getName() + "을(를) 구입했습니다! 가격: $" + item.getPrice());
                } else {
                    JOptionPane.showMessageDialog(ShopApp.this, item.getName() + "은(는) 이미 품절되었습니다.");
                }
            }
        });

        categoryPanel.add(itemButton);
    }

    private void updateClosetFrame() {
        for (Window window : Window.getWindows()) {
            if (window instanceof ClosetFrame) {
                ClosetFrame closetFrame = (ClosetFrame) window;
                closetFrame.updateCloset(closet); 
            }
        }
    }

    private void updateItemButton(JButton itemButton, Item item) {
        if (item.isSoldOut()) {
            itemButton.setText(item.getName() + " - 품절");
        }
    }

    public static void main(String[] args) {
        Closet closet = new Closet();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShopApp(closet);
            }
        });
    }
}
