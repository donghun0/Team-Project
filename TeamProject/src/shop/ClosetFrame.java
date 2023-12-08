package shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosetFrame extends JFrame {

    private Closet closet;
    private static Map<String, Item> wornItems = new HashMap<>(); 
    private static ClosetFrame instance;

    private ClosetFrame(Closet closet) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setTitle("옷장");
        
        setLocationRelativeTo(null);

        this.closet = closet;

        updateCloset(closet);

        setVisible(true);
    }

    // 이미 생성된 인스턴스를 반환하는 정적 메서드
    public static ClosetFrame getInstance(Closet closet) {
        if (instance == null) {
            instance = new ClosetFrame(closet);
        }
        return instance;
    }

    public void updateCloset(Closet closet) {
        JPanel closetPanel = createClosetPanel(closet.getItems());

        JScrollPane scrollPane = new JScrollPane(closetPanel);

        setContentPane(scrollPane);

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
                    JPanel itemPanel = new JPanel();  // 패널을 추가하여 이미지와 텍스트를 가로로 배치
                    itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

                    // 이미지 표시를 위한 코드
                    String imagePath = getImagePath(item);
                    ImageIcon icon = new ImageIcon(imagePath);
                    JLabel imageLabel = new JLabel(icon);
                    itemPanel.add(imageLabel);

                    // 텍스트 표시를 위한 코드
                    JButton itemButton = new JButton(item.getName());

                    // Check if the item is worn and set the button text accordingly
                    if (isItemWorn(item)) {
                        itemButton.setText(item.getName() + " - 착용 중");
                    }

                    itemButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (isItemWorn(item)) {
                                wornItems.remove(item.getCategory());
                            } else {
                                wornItems.put(item.getCategory(), item);
                            }
                            updateCloset(closet);
                        }
                    });

                    itemPanel.add(itemButton);
                    closetPanel.add(itemPanel);
                }
            }
        }

        return closetPanel;
    }

    
    private String getImagePath(Item item) {
        // 이미지 경로를 생성하는 코드 (ShopApp 클래스의 getImagePath 메서드와 유사)
        String category = item.getCategory();
        int itemNumber = Integer.parseInt(item.getName().replaceAll("[^0-9]", ""));
        String imageName = category.toLowerCase() + itemNumber + ".png";
        return "img/shop/" + imageName;
    }


    public boolean isItemWorn(Item item) {
        return wornItems.containsValue(item);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Closet closet = new Closet();
            new ClosetFrame(closet);
        });
    }
}
