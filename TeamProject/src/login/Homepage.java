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
        

       
       
        // 배경 이미지 설정

        ImageIcon backgroundImageIcon = new ImageIcon("img//login//nongdambang.png");
        Image backgroundImage = backgroundImageIcon.getImage();
        Image resizedBackgroundImage = backgroundImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
        backgroundImageIcon = new ImageIcon(resizedBackgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, 1000, 750);
        setContentPane(backgroundLabel);

//        // 추가 이미지 설정-악세서리로 설정
//        ImageIcon additionalImageIcon = new ImageIcon("img//login//목도리-메인용.png");
//        Image additionalImage = additionalImageIcon.getImage();
//        Image resizedAdditionalImage = additionalImage.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
//        additionalImageIcon = new ImageIcon(resizedAdditionalImage);
//        JLabel additionalImageLabel = new JLabel(additionalImageIcon);
//        additionalImageLabel.setBounds(0, 0, 984, 757);
//        backgroundLabel.add(additionalImageLabel);
////        
////        //두번째 이미지-옷으로 설정
//        ImageIcon additionalImageIcon2 = new ImageIcon("img//login//옷1.png");
//        Image additionalImage2 = additionalImageIcon2.getImage();
//        Image resizedAdditionalImage2 = additionalImage2.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
//        additionalImageIcon2 = new ImageIcon(resizedAdditionalImage2);
//        JLabel additionalImageLabel2 = new JLabel(additionalImageIcon2);
//        additionalImageLabel2.setBounds(0, 0, 984, 757);
//        backgroundLabel.add(additionalImageLabel2);

        // 폰트 설정
        Font font = new Font("NEO둥근모", Font.PLAIN, 30);

        // 게임 버튼
        JButton gameButton = createButton("게임", font);
        gameButton.setBounds(803, 100, 130, 50);
        gameButton.setBackground(Color.WHITE);
        gameButton.setBorderPainted(false); // 버튼 테두리 없애기
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
                // 옷장 프레임을 열지 않고 바로 도전 시작
                startChallenge();
            }
        });

        // 상점 버튼
        JButton shopButton = createButton("상점", font);
        shopButton.setBounds(803, 240, 130, 50);
        shopButton.setBackground(Color.WHITE);
        shopButton.setBorderPainted(false); // 버튼 테두리 없애기
        shopButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                ShopApp shopApp = ShopApp.getInstance(closet);
                shopApp.setVisible(true);
            }
        });
        
     // 옷장 버튼 클릭 이벤트 수정
        JButton closetButton = createButton("옷장", font);
        closetButton.setBounds(803, 310, 130, 50);
        closetButton.setBackground(Color.WHITE);
        closetButton.setBorderPainted(false);
        closetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 이미 생성된 ClosetFrame 인스턴스를 가져와서 보이도록 함
                ClosetFrame closetFrame = ClosetFrame.getInstance(closet);
                closetFrame.setVisible(true);
                closetFrame.updateCloset(closet);
            }
        });
        
        // "현재 돈:" 라벨 생성 및 설정
        JLabel moneyTextLabel = new JLabel("현재 돈:");
        moneyTextLabel.setFont(new Font("NEO둥근모", Font.PLAIN, 25));
        moneyTextLabel.setForeground(Color.BLACK);
        moneyTextLabel.setBounds(820, 380, 300, 50);
        add(moneyTextLabel);

        // 실제 금액을 표시하는 라벨 생성 및 설정
        moneyValueLabel = new JLabel(GameStart.getMoney() + "원"); // 클래스 변수를 사용
        moneyValueLabel.setFont(new Font("NEO둥근모", Font.PLAIN, 30));
        moneyValueLabel.setForeground(Color.BLACK);
        moneyValueLabel.setBounds(840, 420, 300, 50); // 위치 조정
        add(moneyValueLabel);
        
        GameStart.setHomepage(this);

        // 버튼을 프레임에 추가
        add(challengeButton);
        add(closetButton);
        add(gameButton);
        add(shopButton);

        // 프레임 표시
        setVisible(true);
    }
	
	private void startChallenge() {
	    // 옷장에서 착용 중인 옷과 악세서리를 가져오기
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

	    // 두 카테고리 모두에 아이템을 착용했는지 확인
	    if (!wornClothes.isEmpty() && !wornAccessories.isEmpty()) {
	        // 두 카테고리에 모두 아이템을 착용한 경우 도전 시작
	        DateTry dateTry = new DateTry(closet);
	       
	        SwingUtilities.invokeLater(() -> new Challenge.DateTryFrame(dateTry));
	    } else {
	        // 한 카테고리 이상에서 아이템을 착용하지 않은 경우 메시지 표시
	        JOptionPane.showMessageDialog(Homepage.this, "두 가지 카테고리의 아이템을 모두 착용해야 도전이 가능합니다.");
	    }
	}

	private void updateBackgroundImage(Item cloth, Item accessory) {
	    // 기존에 추가된 이미지가 있다면 제거
	    Component[] components = getContentPane().getComponents();
	    for (Component component : components) {
	        if (component instanceof JLabel) {
	            getContentPane().remove(component);
	        }
	    }

	    // 착용한 옷이 있으면 이미지 추가
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

	    // 착용한 악세서리가 있으면 이미지 추가
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

	    // 갱신된 내용을 반영
	    revalidate();
	    repaint();
	}


	private String getImagePath(Item item) {
	    // 이미지 경로를 생성하는 코드 (ShopApp 클래스의 getImagePath 메서드와 유사)
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
            // 기존에 추가된 이미지가 있다면 제거
            Component[] components = getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    getContentPane().remove(component);
                }
            }

            // 착용한 옷이 있으면 이미지 추가
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

            // 착용한 악세서리가 있으면 이미지 추가
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

            // 갱신된 내용을 반영
            revalidate();
            repaint();
        });
    }


}