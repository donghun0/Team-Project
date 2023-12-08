package shop;

import javax.swing.*;

import game.GameStart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ShopApp extends JFrame {
    private JPanel shopPanel;
    private JScrollPane scrollPane;
    private static ShopApp instance;
    private Item selectedItem;
    private Closet closet; 

    private ShopApp(Closet closet) {
        this.closet = closet;

        setTitle("상점");
        setSize(1000, 800);
        
        setLocationRelativeTo(null);
        
        Font defaultFont = new Font("NEO둥근모", Font.BOLD, 30); // Adjust the font size and style as needed
        UIManager.put("Button.font", defaultFont);
        UIManager.put("Label.font", defaultFont);

        shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));

        addCategory("옷", 8);
        addCategory("악세서리", 8);

        scrollPane = new JScrollPane(shopPanel);
        add(scrollPane);

        addWindowListener(); 

        setVisible(true);

        selectedItem = null;
    }
    
    private void setFontRecursively(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                setFontRecursively((Container) component, font);
            } else if (component instanceof JButton || component instanceof JLabel || component instanceof JComponent) {
                ((JComponent) component).setFont(font);
            }
        }
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
    	String imagePath = getImagePath(item);

        ImageIcon icon = new ImageIcon(imagePath);
        JButton itemButton = new JButton();
        itemButton.setPreferredSize(new Dimension(270, 270)); // Adjust the size as needed

        itemButton.setBackground(Color.WHITE);
        
        Font defaultFont = new Font("NEO둥근모", Font.BOLD, 30); // Adjust the font size as needed
        itemButton.setFont(defaultFont);
        
        itemButton.setLayout(new BorderLayout());

        itemButton.add(new JLabel(icon), BorderLayout.CENTER);

        itemButton.add(new JLabel("  $" + item.getPrice(), SwingConstants.CENTER), BorderLayout.SOUTH);

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	itemButton.setBackground(Color.WHITE);
            	 itemButton.setFont(defaultFont);

                if (!item.isSoldOut()) {
                    if (GameStart.getMoney() >= item.getPrice()) {
                        GameStart.decreaseMoney(item.getPrice()); // 돈 차감
                        item.setSoldOut(true);
                        updateItemButton(itemButton, item);
                        selectedItem = item;
                        closet.addItem(item);
                        updateClosetFrame();
                        JOptionPane.showMessageDialog(ShopApp.this, item.getName() + "을(를) 구입했습니다! 가격: $" + item.getPrice());
                    } else {
                        JOptionPane.showMessageDialog(ShopApp.this, "돈이 부족합니다. 현재 돈: $" + GameStart.getMoney());
                    }
                }
            }
        });

        categoryPanel.add(itemButton);
    }
    
    private String getImagePath(Item item) {
        String category = item.getCategory();
        int itemNumber = Integer.parseInt(item.getName().replaceAll("[^0-9]", "")); 

        String imageName = category.toLowerCase() + itemNumber + ".png";
        return "img/shop/" + imageName;
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
            Font font = new Font("NEO둥근모", Font.BOLD, 300);
            itemButton.setFont(font);
            itemButton.setText("X");
            itemButton.setEnabled(false);
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