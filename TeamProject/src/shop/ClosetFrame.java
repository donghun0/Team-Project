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
        setSize(500, 400);
        setTitle("옷장");

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
                    JButton itemButton = new JButton(item.getName() + " - $" + item.getPrice());
                    
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

                    closetPanel.add(itemButton);
                }
            }
        }

        return closetPanel;
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
