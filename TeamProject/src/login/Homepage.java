package login;

import javax.swing.*;

import Challenge.DateTry;
import game.GameStart;
import shop.Closet;
import shop.ClosetFrame;
import shop.ShopApp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setContentPane(new JLabel(backgroundImageIcon));
        
       

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
        challengeButton.setBorderPainted(false); // 버튼 테두리 없애기
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DateTry.main(new String[0]);
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
        
        // 옷장 버튼
        JButton closetButton = createButton("옷장", font);
        closetButton.setBounds(803, 310, 130, 50);
        closetButton.setBackground(Color.WHITE);
        closetButton.setBorderPainted(false); // 버튼 테두리 없애기
        closetButton.addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                 ClosetFrame closetFrame = new ClosetFrame(closet);
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
}
