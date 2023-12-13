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
                frame.dispose(); 
                openGameSelectionWindow(); 
            }
        });
        
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        int 버튼너비 = 220;
        int 버튼높이 = 220;

        
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
        
        JLabel computerChoiceLabel = new JLabel(); 
        computerChoiceLabel.setPreferredSize(new Dimension(200, 200)); 
        
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
                updateComputerChoiceImage(computerChoice, computerChoiceLabel); 
                
                String result = determineWinner(playerChoice, computerChoice);
                showResultDialog(result, frame); 
                frame.setVisible(false); 
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

        frame.pack(); 
        frame.setVisible(true);
        frame.setSize(740, 790);
        frame.setBackground(Color.WHITE);
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
                parentFrame.dispose(); 
                startGame(); 
            });
            resultDialog.add(retryButton); 
        } else {
            JButton closeButton = new JButton("종료");
            closeButton.addActionListener(e -> {
                resultDialog.dispose();
                parentFrame.setVisible(true); 
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
                return; 
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
        	GameStart.increaseMoney(10); 
            return "승리!";
        } else {
            return "패배!";
        }
    }
    
    
    private static void openGameSelectionWindow() {
        GameStart.main(null);
    }
}
