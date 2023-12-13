package shop;

import javax.swing.*;
import login.Homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosetFrame extends JFrame {

    private Closet closet;
    private Map<String, Item> wornItems = new HashMap<>();
    private static ClosetFrame instance;
    private Homepage homepage;

    private ClosetFrame(Closet closet) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setTitle("옷장");
        setLocationRelativeTo(null);

        this.closet = closet;
        
        this.homepage = Homepage.getInstance(closet);

        updateCloset(closet);

        setVisible(true);
    }

    public void setHomepage(Homepage homepage) {
        this.homepage = homepage;
    }

    public static ClosetFrame getInstance(Closet closet) {
        if (instance == null) {
            instance = new ClosetFrame(closet);
        }
        return instance;
    }

    public void updateBackgroundImage(Item cloth, Item accessory) {
        if (homepage != null) {
            homepage.updateBackgroundImageOnItemSelection(cloth, accessory);
        }
    }


    public void updateCloset(Closet closet) {
        JPanel closetPanel = createClosetPanel(closet.getItems());
        JScrollPane scrollPane = new JScrollPane(closetPanel);
        setContentPane(scrollPane);
        
        Item lastWornCloth = getLastWornCloth();
        Item lastWornAccessory = getLastWornAccessory();

        // Homepage 인스턴스를 통해 메서드 호출
        if (homepage != null) {
            homepage.updateBackgroundImageOnItemSelection(lastWornCloth, lastWornAccessory);
        }
        revalidate();
        repaint();
    }

    private JPanel createClosetPanel(List<Item> items) {
        JPanel closetPanel = new JPanel();
        closetPanel.setLayout(new BoxLayout(closetPanel, BoxLayout.Y_AXIS));

        Map<String, List<Item>> categorizedItems = new HashMap<>();

        for (Item item : items) {
            categorizedItems.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
        }

        for (String category : categorizedItems.keySet()) {
            List<Item> categoryItems = categorizedItems.get(category);

            if (categoryItems != null) {
                JButton categoryButton = new JButton(category);
                categoryButton.setEnabled(false);
                closetPanel.add(categoryButton);

                for (Item item : categoryItems) {
                    JPanel itemPanel = new JPanel();
                    itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

                    String imagePath = getImagePath(item);
                    ImageIcon icon = new ImageIcon(imagePath);
                    JLabel imageLabel = new JLabel(icon);
                    itemPanel.add(imageLabel);

                    JButton itemButton = new JButton(item.getName());

                    if (isItemWorn(item)) {
                        itemButton.setText(item.getName() + " - 착용 중");
                        updateBackgroundImage(item, null);
                    }

                    itemButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            boolean wornBefore = isItemWorn(item);

                            if (wornBefore) {
                                item.setWorn(false);
                                wornItems.remove(item.getCategory());
                            } else {
                                item.setWorn(true);
                                wornItems.put(item.getCategory(), item);
                            }

                            updateCloset(closet);

                            // 착용 전 상태와 착용 후 상태가 다를 때만 이미지 업데이트
                            if (wornBefore != isItemWorn(item)) {
                                updateBackgroundImage(getLastWornCloth(), getLastWornAccessory());
                            }
                        }
                    });


                    itemPanel.add(itemButton);
                    closetPanel.add(itemPanel);
                }
            }
        }

        return closetPanel;
    }
    
    private Item getLastWornCloth() {
        List<Item> wornClothes = new ArrayList<>();
        for (Item item : closet.getItems()) {
            if (isItemWorn(item) && "옷".equals(item.getCategory())) {
                wornClothes.add(item);
            }
        }
        return wornClothes.isEmpty() ? null : wornClothes.get(wornClothes.size() - 1);
    }
    public void updateBackgroundImageOnItemPurchase() {
        SwingUtilities.invokeLater(() -> {
            updateBackgroundImage(getLastWornCloth(), getLastWornAccessory());
        });
    }
    
    private Item getLastWornAccessory() {
        List<Item> wornAccessories = new ArrayList<>();
        for (Item item : closet.getItems()) {
            if (isItemWorn(item) && "악세서리".equals(item.getCategory())) {
                wornAccessories.add(item);
            }
        }
        return wornAccessories.isEmpty() ? null : wornAccessories.get(wornAccessories.size() - 1);
    }

    private String getImagePath(Item item) {
        String category = item.getCategory();
        int itemNumber = Integer.parseInt(item.getName().replaceAll("[^0-9]", ""));
        String imageName = category.toLowerCase() + itemNumber + ".png";
        return "img/shop/" + imageName;
    }

    public boolean isItemWorn(Item item) {
        return wornItems.containsValue(item);
    }

    public static void main(String[] args) {
    	Closet closet = new Closet();
        SwingUtilities.invokeLater(() -> {
            Homepage homepage = Homepage.getInstance(closet);
            homepage.setVisible(true);
        });
    }
}