package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart {

    public static void main(String[] args) {
        // 게임 선택 창을 위한 프레임 생성
        JFrame frame = new JFrame("게임 선택");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 100);

        // 가위바위보 게임 버튼
        JButton rockPaperScissorsButton = new JButton("가위바위보");
        rockPaperScissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RockScissorsPaper.startGame(); // 가위바위보 게임 시작
            }
        });

        // 다른 게임 버튼 추가 예시
        JButton quizButton = new JButton("상식 퀴즈");
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quiz(); // 퀴즈 게임 시작
            }
        });
        
        JButton otherGameButton = new JButton("게임3");

        frame.add(rockPaperScissorsButton);
        frame.add(quizButton);
        frame.add(otherGameButton);

        frame.setVisible(true);
    }
}

