package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart {
    private static int money = 0; // 돈 축적을 위한 변수
    private static JLabel moneyLabel;
    private static JFrame frame; // 게임 선택 창을 위한 프레임 변수

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

        // Check if moneyLabel is null and initialize it if needed
        if (moneyLabel == null) {
            moneyLabel = new JLabel("현재 돈: " + money + "원");
            frame.add(moneyLabel);
            // Add moneyLabel to the frame or panel where it should be displayed
            // For example, frame.add(moneyLabel);
        } else {
            // Update the text if moneyLabel is already initialized
            moneyLabel.setText("현재 돈: " + money + "원");
        }
    }
}
