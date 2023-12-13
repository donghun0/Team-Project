package login;

import javax.swing.*;

import Challenge.DateTry;
import game.GameStart;
import shop.Closet;
import shop.ClosetFrame;
import shop.ShopApp;
import shop.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends JFrame{
	private Closet closet;
    private JLabel moneyValueLabel;

	public Homepage(Closet closet) {
        this.closet = closet;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setTitle("농담곰 방");
        setLocationRelativeTo(null);

        ImageIcon backgroundImageIcon = new ImageIcon("img//login//nongdambang.png");
        Image backgroundImage = backgroundImageIcon.getImage();
        Image resizedBackgroundImage = backgroundImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
        backgroundImageIcon = new ImageIcon(resizedBackgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, 1000, 750);
        setContentPane(backgroundLabel);

        Font font = new Font("NEO둥근모", Font.PLAIN, 30);

        JButton gameButton = createButton("게임", font);
        gameButton.setBounds(803, 100, 130, 50);
        gameButton.setBackground(Color.WHITE);
        gameButton.setBorderPainted(false);
        gameButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                GameStart.main(new String[0]);
            }
        });
        
        // 도전 버튼
        JButton challengeButton = createButton("도전", font);
        challengeButton.setBounds(803, 170, 130, 50);
        challengeButton.setBackground(Color.WHITE);
        challengeButton.setBorderPainted(false);
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChallenge();
            }
        });

        // 상점 버튼
        JButton shopButton = createButton("상점", font);
        shopButton.setBounds(803, 240, 130, 50);
        shopButton.setBackground(Color.WHITE);
        shopButton.setBorderPainted(false);
        shopButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                ShopApp shopApp = ShopApp.getInstance(closet);
                shopApp.setVisible(true);
            }
        });
        
        // 옷장 버튼
        JButton closetButton = createButton("옷장", font);
        closetButton.setBounds(803, 310, 130, 50);
        closetButton.setBackground(Color.WHITE);
        closetButton.setBorderPainted(false);
        closetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClosetFrame closetFrame = ClosetFrame.getInstance(closet);
                closetFrame.setVisible(true);
                closetFrame.updateCloset(closet);
            }
        });

        JLabel moneyTextLabel = new JLabel("현재 돈:");
        moneyTextLabel.setFont(new Font("NEO둥근모", Font.PLAIN, 25));
        moneyTextLabel.setForeground(Color.BLACK);
        moneyTextLabel.setBounds(820, 380, 300, 50);
        add(moneyTextLabel);

        moneyValueLabel = new JLabel(GameStart.getMoney() + "원");
        moneyValueLabel.setFont(new Font("NEO둥근모", Font.PLAIN, 30));
        moneyValueLabel.setForeground(Color.BLACK);
        moneyValueLabel.setBounds(840, 420, 300, 50);
        add(moneyValueLabel);
        
        GameStart.setHomepage(this);

        add(challengeButton);
        add(closetButton);
        add(gameButton);
        add(shopButton);

        setVisible(true);
    }
	
	private void startChallenge() {
	    List<Item> wornClothes = new ArrayList<>();
	    List<Item> wornAccessories = new ArrayList<>();

	    for (Item item : closet.getItems()) {
	        ClosetFrame closetFrame = ClosetFrame.getInstance(closet);
	        if (closetFrame.isItemWorn(item)) {
	            if ("옷".equals(item.getCategory())) {
	                wornClothes.add(item);
	            } else if ("악세서리".equals(item.getCategory())) {
	                wornAccessories.add(item);
	            }
	        }
	    }

	    if (!wornClothes.isEmpty() && !wornAccessories.isEmpty()) {
	        DateTry dateTry = new DateTry(closet);
	       
	        SwingUtilities.invokeLater(() -> new Challenge.DateTryFrame(dateTry));
	    } else {
	        JOptionPane.showMessageDialog(Homepage.this, "두 가지 카테고리의 아이템을 모두 착용해야 도전이 가능합니다.");
	    }
	}

	private void updateBackgroundImage(Item cloth, Item accessory) {
	    Component[] components = getContentPane().getComponents();
	    for (Component component : components) {
	        if (component instanceof JLabel) {
	            getContentPane().remove(component);
	        }
	    }

	    if (cloth != null) {
	        String clothImagePath = getImagePath(cloth);
	        if (clothImagePath != null) {
	            ImageIcon clothImageIcon = new ImageIcon(clothImagePath);
	            Image clothImage = clothImageIcon.getImage();
	            Image resizedClothImage = clothImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
	            clothImageIcon = new ImageIcon(resizedClothImage);

	            JLabel clothImageLabel = new JLabel(clothImageIcon);
	            clothImageLabel.setBounds(0, 0, 984, 757);
	            getContentPane().add(clothImageLabel, 0);
	        }
	    }

	    if (accessory != null) {
	        String accessoryImagePath = getImagePath(accessory);
	        if (accessoryImagePath != null) {
	            ImageIcon accessoryImageIcon = new ImageIcon(accessoryImagePath);
	            Image accessoryImage = accessoryImageIcon.getImage();
	            Image resizedAccessoryImage = accessoryImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
	            accessoryImageIcon = new ImageIcon(resizedAccessoryImage);

	            JLabel accessoryImageLabel = new JLabel(accessoryImageIcon);
	            accessoryImageLabel.setBounds(0, 0, 984, 757);
	            getContentPane().add(accessoryImageLabel, 0);
	        }
	    }

	    revalidate();
	    repaint();
	}


	private String getImagePath(Item item) {
	    String category = item.getCategory();
	    int itemNumber = Integer.parseInt(item.getName().replaceAll("[^0-9]", ""));
	    String imageName = category.toLowerCase() + itemNumber + ".png";
	    return "img/login/" + imageName;
	}
    
	public void updateMoneyLabel() {
        if (this.moneyValueLabel != null) {
            this.moneyValueLabel.setText(GameStart.getMoney() + "원");
        }
    }


    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        return button;
    }

    public static void main(String[] args) {
        Closet closet = new Closet();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Homepage(closet);
            }
        });
    }

    public void updateBackgroundImageOnItemSelection(Item cloth, Item accessory) {
        SwingUtilities.invokeLater(() -> {
            Component[] components = getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    getContentPane().remove(component);
                }
            }

            if (cloth != null) {
                String clothImagePath = getImagePath(cloth);
                if (clothImagePath != null) {
                    ImageIcon clothImageIcon = new ImageIcon(clothImagePath);
                    Image clothImage = clothImageIcon.getImage();
                    Image resizedClothImage = clothImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
                    clothImageIcon = new ImageIcon(resizedClothImage);

                    JLabel clothImageLabel = new JLabel(clothImageIcon);
                    clothImageLabel.setBounds(0, 0, 984, 757);
                    getContentPane().add(clothImageLabel, 0);
                }
            }

            if (accessory != null) {
                String accessoryImagePath = getImagePath(accessory);
                if (accessoryImagePath != null) {
                    ImageIcon accessoryImageIcon = new ImageIcon(accessoryImagePath);
                    Image accessoryImage = accessoryImageIcon.getImage();
                    Image resizedAccessoryImage = accessoryImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
                    accessoryImageIcon = new ImageIcon(resizedAccessoryImage);

                    JLabel accessoryImageLabel = new JLabel(accessoryImageIcon);
                    accessoryImageLabel.setBounds(0, 0, 984, 757);
                    getContentPane().add(accessoryImageLabel, 0);
                }
            }

            revalidate();
            repaint();
        });
    }


}