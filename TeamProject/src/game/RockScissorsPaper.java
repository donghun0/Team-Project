package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockScissorsPaper {

    public static void startGame() {
        JFrame frame = new JFrame("가위바위보 게임");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // 이미지 크기를 조정하는 메소드
        ImageIcon scissorsIcon = new ImageIcon("img/scissors.png");
        ImageIcon rockIcon = new ImageIcon("img/rock.png");
        ImageIcon paperIcon = new ImageIcon("img/paper.png");

        Image scissorsImage = scissorsIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        Image rockImage = rockIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        Image paperImage = paperIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        
        JButton scissorsButton = new JButton(new ImageIcon(scissorsImage));
        JButton rockButton = new JButton(new ImageIcon(rockImage));
        JButton paperButton = new JButton(new ImageIcon(paperImage));
        
        JLabel resultLabel = new JLabel("결과가 여기에 표시됩니다.", SwingConstants.CENTER);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setPreferredSize(new Dimension(300, 30)); // 라벨의 선호 크기 설정
       
        JLabel computerChoiceLabel = new JLabel(); // 컴퓨터의 선택을 보여줄 라벨
        computerChoiceLabel.setPreferredSize(new Dimension(70, 70)); // 컴퓨터 선택 라벨의 초기 크기 설정
        
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
                resultLabel.setText(result);
            }
        };
        
        scissorsButton.addActionListener(actionListener);
        rockButton.addActionListener(actionListener);
        paperButton.addActionListener(actionListener);

        JPanel computerChoicePanel = new JPanel();
        computerChoicePanel.add(computerChoiceLabel);
        frame.add(computerChoicePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        frame.add(buttonsPanel);

        JPanel resultPanel = new JPanel(new BorderLayout()); // 결과를 가운데 정렬하기 위한 BorderLayout 사용
        resultPanel.add(resultLabel, BorderLayout.CENTER); // 결과 라벨을 가운데에 배치
        frame.add(resultPanel);

        frame.pack(); // 컴포넌트에 맞게 프레임 크기 조정
        frame.setVisible(true);
    }
    
    private static void updateComputerChoiceImage(String choice, JLabel label) {
        ImageIcon icon;
        switch (choice) {
            case "가위":
                icon = new ImageIcon("img/scissors.png");
                break;
            case "바위":
                icon = new ImageIcon("img/rock.png");
                break;
            case "보":
                icon = new ImageIcon("img/paper.png");
                break;
            default:
                return; // 잘못된 선택인 경우 아무 것도 하지 않음
        }

        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 이미지 크기를 50x50으로 조정
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
            return "승리!";
        } else {
            return "패배!";
        }
    }
}
