package game;

import javax.swing.*;

import login.Homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        frame.setSize(350, 100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 기본 닫기 작업 설정

        // 창이 닫힐 때 frame 변수를 null로 설정
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
        
        // 현재 있는 돈 표시
        moneyLabel = new JLabel("현재 돈: " + money + "원");
        frame.add(moneyLabel);

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

        frame.add(rockPaperScissorsButton);
        frame.add(quizButton);
        frame.add(memoryMatchGameButton);

        frame.setVisible(true);
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
