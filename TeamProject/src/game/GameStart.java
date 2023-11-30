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
        JButton otherGameButton1 = new JButton("농담곰 Quiz");
        JButton otherGameButton2 = new JButton("게임3");

        frame.add(rockPaperScissorsButton);
        frame.add(otherGameButton1);
        frame.add(otherGameButton2);

        frame.setVisible(true);
    }
}

