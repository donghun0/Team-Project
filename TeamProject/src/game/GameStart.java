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
	public static int money = 0; // 돈 축적을 위한 변수
    private static JLabel moneyLabel;
    private static JFrame frame; // 게임 선택 창을 위한 프레임 변수
    private static Homepage homepage; 
    
    public static void setHomepage(Homepage homepage) {
        GameStart.homepage = homepage;
    }
    
    public static void main(String[] args) {
        // 이미 열려 있는 게임 선택 창이 있다면, 추가로 창을 생성하지 않고 반환
        if (frame != null) {
            frame.setState(Frame.NORMAL); // 최소화된 창을 정상 상태로 변경
            frame.requestFocus(); // 창에 포커스를 줌
            return;
        }

        // 게임 선택 창을 위한 프레임 생성
        frame = new JFrame("게임 선택");
        frame.setLayout(new FlowLayout());
        frame.setSize(740, 790);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 기본 닫기 작업 설정

        // 창이 닫힐 때 frame 변수를 null로 설정
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
        
        // 글꼴 로드
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("img/neodgm.ttf")).deriveFont(50f);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.font", customFont);
            UIManager.put("Label.font", customFont);
        } catch (FontFormatException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 레이아웃 설정
        frame.setLayout(new BorderLayout());
        
        // 현재 돈 표시
        moneyLabel = new JLabel("현재 돈: " + money + "원");
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(moneyLabel, BorderLayout.NORTH);
        
        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1)); // 3줄로 버튼 배치

        // 가위바위보 게임 버튼
        JButton rockPaperScissorsButton = new JButton("가위바위보");
        rockPaperScissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RockScissorsPaper.startGame(); // 가위바위보 게임 시작
            }
        });

        // 상식 퀴즈 게임 버튼
        JButton quizButton = new JButton("상식 퀴즈");
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quiz(); // 퀴즈 게임 시작
            }
        });

        // 기억력 게임 버튼
        JButton memoryMatchGameButton = new JButton("기억력 게임");
        memoryMatchGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoryMatchGame(); // 기억력 게임 시작
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
            money = 0; // 돈이 음수가 되지 않도록 보정
        }

        // moneyLabel이 null인지 확인하고 필요한 경우 초기화
        if (moneyLabel == null) {
            moneyLabel = new JLabel("현재 돈: " + money + "원");
            frame.add(moneyLabel);
            // moneyLabel을 표시해야 하는 프레임 또는 패널에 추가
            // 예: frame.add(moneyLabel);
        } else {
            // moneyLabel이 이미 초기화된 경우 텍스트 업데이트
            moneyLabel.setText("현재 돈: " + money + "원");
        }

        // Homepage이 사용 가능한 경우 Homepage에서 돈 라벨을 업데이트
        if (homepage != null) {
            homepage.updateMoneyLabel();
        }
    }

    public static void increaseMoney(int amount) {
        money += amount;

        // moneyLabel이 null인지 확인하고 필요한 경우 초기화
        if (moneyLabel == null) {
            moneyLabel = new JLabel("현재 돈: " + money + "원");
            frame.add(moneyLabel);
            // moneyLabel을 표시해야 하는 프레임 또는 패널에 추가
            // 예: frame.add(moneyLabel);
        } else {
            // moneyLabel이 이미 초기화된 경우 텍스트 업데이트
            moneyLabel.setText("현재 돈: " + money + "원");
        }

        // Homepage이 사용 가능한 경우 Homepage에서 돈 라벨을 업데이트
        if (homepage != null) {
            homepage.updateMoneyLabel();
        }
    }
    public static int getMoney() {
        return money;
    }
    
}
