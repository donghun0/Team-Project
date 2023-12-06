package login;

import javax.swing.*;

import game.GameStart;
import shop.ShopApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

    public Homepage() {
        // 프레임 초기화
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("농담곰 방");
        

        
       
        // 배경 이미지 설정

        ImageIcon backgroundImageIcon = new ImageIcon("C:\\Users\\user\\Desktop\\프방기말프로젝트\\nongdambang.png");
        Image backgroundImage = backgroundImageIcon.getImage();
        Image resizedBackgroundImage = backgroundImage.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        backgroundImageIcon = new ImageIcon(resizedBackgroundImage);
        setContentPane(new JLabel(backgroundImageIcon));
        
       

        // 폰트 설정
        Font font = new Font("NEO둥근모", Font.PLAIN, 20);

        // 도전 버튼
        JButton challengeButton = createButton("도전", font);
        challengeButton.setBounds(660, 100, 80, 30);
        challengeButton.setBackground(Color.WHITE);
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 도전 버튼이 클릭되었을 때 수행할 동작
            }
        });

        // 옷장 버튼
        JButton closetButton = createButton("옷장", font);
        closetButton.setBounds(660, 200, 80, 30);
        closetButton.setBackground(Color.WHITE);
        closetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 옷장 버튼이 클릭되었을 때 수행할 동작
            }
        });

        // 게임 버튼
        JButton gameButton = createButton("게임", font);
        gameButton.setBounds(660, 50, 80, 30);
        gameButton.setBackground(Color.WHITE);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 게임 버튼이 클릭되었을 때 수행할 동작
                GameStart.main(new String[0]);
            }
        });

        // 상점 버튼
        JButton shopButton = createButton("상점", font);
        shopButton.setBounds(660, 150, 80, 30);
        shopButton.setBackground(Color.WHITE);
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상점 버튼이 클릭되었을 때 수행할 동작
            	ShopApp.main(new String[0]);
            }
        });

        // 버튼을 프레임에 추가
        add(challengeButton);
        add(closetButton);
        add(gameButton);
        add(shopButton);

        // 프레임 표시
        setVisible(true);
    }

    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Homepage();
            }
        });
    }
}
