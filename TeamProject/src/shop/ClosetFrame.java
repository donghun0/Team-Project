package shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosetFrame extends JFrame {

    private Closet closet;

    public ClosetFrame(Closet closet) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setTitle("옷장");

        this.closet = closet;

        updateCloset(closet);

        setVisible(true);
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

        for (String category : Arrays.asList("헤어", "상의", "하의", "신발", "악세서리")) {
            List<Item> categoryItems = categorizedItems.get(category);

            if (categoryItems != null) {
                JButton categoryButton = new JButton(category);
                categoryButton.setEnabled(false);
                closetPanel.add(categoryButton);

                for (Item item : categoryItems) {
                    JButton itemButton = new JButton(item.getName() + " - $" + item.getPrice());
                    itemButton.setEnabled(true);

                    if (item.isWorn()) {
                        itemButton.setText(item.getName() + " - 착용 중");
                    }

                    itemButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (item.isWorn()) {
                                item.setWorn(false);
                            } else {
                                item.setWorn(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Closet closet = new Closet();
            new ClosetFrame(closet);
        });
    }
}
