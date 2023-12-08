package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RockScissorsPaper {

    public static void startGame() {
        JFrame frame = new JFrame("가위바위보 게임");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 창이 닫힐 때 실행될 동작
                frame.dispose(); // 현재 프레임을 닫습니다.
                openGameSelectionWindow(); // 게임 선택 창을 엽니다.
            }
        });
        
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        int 버튼너비 = 220;
        int 버튼높이 = 220;

        // 이미지 크기를 조정하는 메소드
        ImageIcon rockIcon = new ImageIcon("img/game/rock.png");
        ImageIcon scissorsIcon = new ImageIcon("img/game/scissor.png");
        ImageIcon paperIcon = new ImageIcon("img/game/paper.png");

        Image rockImage = rockIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image scissorsImage = scissorsIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image paperImage = paperIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        
        JButton rockButton = new JButton(new ImageIcon(rockImage));
        rockButton.setPreferredSize(new Dimension(버튼너비, 버튼높이));
        JButton scissorsButton = new JButton(new ImageIcon(scissorsImage));
        scissorsButton.setPreferredSize(new Dimension(버튼너비, 버튼높이));
        JButton paperButton = new JButton(new ImageIcon(paperImage));
        paperButton.setPreferredSize(new Dimension(버튼너비, 버튼높이));
        
        JLabel computerChoiceLabel = new JLabel(); // 컴퓨터의 선택을 보여줄 라벨
        computerChoiceLabel.setPreferredSize(new Dimension(200, 200)); // 컴퓨터 선택 라벨의 초기 크기 설정
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerChoice = "";
                if (e.getSource() == scissorsButton) {
                    playerChoice = "가위";
                } else if (e.getSource() == rockButton) {
                    playerChoice = "바위";
                } else if (e.getSource() == paperButton) {
                    playerChoice = "보";
                }
                String computerChoice = getComputerChoice();
                updateComputerChoiceImage(computerChoice, computerChoiceLabel); // 컴퓨터의 선택에 따라 이미지 업데이트
                
                String result = determineWinner(playerChoice, computerChoice);
                showResultDialog(result, frame); // 결과 창 표시
                frame.setVisible(false); // 메인 프레임 숨기기
            }
        };
        
        rockButton.addActionListener(actionListener);
        scissorsButton.addActionListener(actionListener);
        paperButton.addActionListener(actionListener);

        JPanel computerChoicePanel = new JPanel();
        computerChoicePanel.add(computerChoiceLabel);
        frame.add(computerChoicePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(rockButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(paperButton);
        frame.add(buttonsPanel);

        frame.pack(); // 컴포넌트에 맞게 프레임 크기 조정
        frame.setVisible(true);
        frame.setSize(740, 790);
        frame.setLocationRelativeTo(null);
    }
    
    
  

    private static void showResultDialog(String result, JFrame parentFrame) {
        JDialog resultDialog = new JDialog(parentFrame, "결과", true);
        resultDialog.setLayout(new FlowLayout());
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel resultLabel = new JLabel(result);
        resultDialog.add(resultLabel);

        if (result.equals("재도전!")) {
            JButton retryButton = new JButton("다시하기");
            retryButton.addActionListener(e -> {
                resultDialog.dispose();
                parentFrame.dispose(); // 기존 프레임 닫기
                startGame(); // 새 게임 시작
            });
            resultDialog.add(retryButton); // 재도전 시 다시하기 버튼 추가
        } else {
            JButton closeButton = new JButton("종료");
            closeButton.addActionListener(e -> {
                resultDialog.dispose();
                parentFrame.setVisible(true); // 메인 게임 선택 창을 다시 보여줌
            });
            resultDialog.add(closeButton);
        }

        resultDialog.pack();
        resultDialog.setSize(270, 180);
        resultDialog.setLocationRelativeTo(parentFrame);
        resultDialog.setVisible(true);
    }

    private static void updateComputerChoiceImage(String choice, JLabel label) {
        ImageIcon icon;
        switch (choice) {
            case "가위":
                icon = new ImageIcon("img/game/scissor.png");
                break;
            case "바위":
                icon = new ImageIcon("img/game/rock.png");
                break;
            case "보":
                icon = new ImageIcon("img/game/paper.png");
                break;
            default:
                return; // 잘못된 선택인 경우 아무 것도 하지 않음
        }

        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }

    private static String getComputerChoice() {
        String[] choices = {"가위", "바위", "보"};
        return choices[new Random().nextInt(choices.length)];
    }


    private static String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "재도전!";
        } else if ((playerChoice.equals("가위") && computerChoice.equals("보")) ||
                   (playerChoice.equals("바위") && computerChoice.equals("가위")) ||
                   (playerChoice.equals("보") && computerChoice.equals("바위"))) { 
        	GameStart.increaseMoney(100); 
            return "승리!";
        } else {
            return "패배!";
        }
    }
    
    private static void openGameSelectionWindow() {
        // GameStart 클래스의 메인 메서드를 호출하여 게임 선택 창을 엽니다.
        GameStart.main(null);
    }
}
