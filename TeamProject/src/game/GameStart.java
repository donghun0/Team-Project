package game;

import javax.swing.*;

import login.Homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class GameStart {
	public static int money = 0; 
    private static JLabel moneyLabel;
    private static JFrame frame; 
    private static Homepage homepage; 
    
    public static void setHomepage(Homepage homepage) {
        GameStart.homepage = homepage;
    }
    
    public static void main(String[] args) {
        
        if (frame != null) {
            frame.setState(Frame.NORMAL);
            frame.requestFocus(); 
            return;
        }

        frame = new JFrame("게임 선택");
        frame.setLayout(new FlowLayout());
        frame.setSize(740, 790);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 기본 닫기 작업 설정

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
        
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("img/neodgm.ttf")).deriveFont(50f);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.font", customFont);
            UIManager.put("Label.font", customFont);
        } catch (FontFormatException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame.setLayout(new BorderLayout());
        
        moneyLabel = new JLabel("현재 돈: " + money + "원");
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(moneyLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        

        
        JButton rockPaperScissorsButton = new JButton("가위바위보");
        rockPaperScissorsButton.setBackground(new Color(135, 206, 250));
        rockPaperScissorsButton.setOpaque(true);
        rockPaperScissorsButton.setBorderPainted(false);
        rockPaperScissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RockScissorsPaper.startGame(); //
            }
        });

        
        JButton quizButton = new JButton("상식 퀴즈");
        quizButton.setBackground(new Color(144, 238, 144));
        quizButton.setOpaque(true);
        quizButton.setBorderPainted(false);
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quiz(); 
            }
        });

        
        JButton memoryMatchGameButton = new JButton("기억력 게임");
        memoryMatchGameButton.setBackground(new Color(255, 182, 193));
        memoryMatchGameButton.setOpaque(true);
        memoryMatchGameButton.setBorderPainted(false); 
        memoryMatchGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoryMatchGame(); 
            }
        });

        buttonPanel.add(rockPaperScissorsButton);
        buttonPanel.add(quizButton);
        buttonPanel.add(memoryMatchGameButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
    public static void decreaseMoney(int amount) {
        money -= amount;

        if (money < 0) {
            money = 0; 
        }

        
        if (moneyLabel == null) {
            moneyLabel = new JLabel("현재 돈: " + money + "원");
            frame.add(moneyLabel);
            
        } else {
            
            moneyLabel.setText("현재 돈: " + money + "원");
        }

        
        if (homepage != null) {
            homepage.updateMoneyLabel();
        }
    }

    public static void increaseMoney(int amount) {
        money += amount;

        
        if (moneyLabel == null) {
            moneyLabel = new JLabel("현재 돈: " + money + "원");
            frame.add(moneyLabel);
            
        } else {
           
            moneyLabel.setText("현재 돈: " + money + "원");
        }

        
        if (homepage != null) {
            homepage.updateMoneyLabel();
        }
    }
    public static int getMoney() {
        return money;
    }
    
}
