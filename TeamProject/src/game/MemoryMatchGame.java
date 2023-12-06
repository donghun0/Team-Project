package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryMatchGame {
    private JFrame frame;
    private List<JButton> cardButtons;
    private List<String> cardValues;
    private JButton firstSelectedButton;
    private String firstSelectedValue;
    private int matchesFound = 0;
    private final int timeLimit = 30; // 30초 시간 제한
    private Timer timer;
    private JLabel timeLabel;

    public MemoryMatchGame() {
        initializeGame();
        createUI();
        startGameTimer();
    }

    private void initializeGame() {
        cardValues = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String imageName = "image" + i;
            cardValues.add(imageName);
            cardValues.add(imageName);
        }
        Collections.shuffle(cardValues);
    }

    private void createUI() {
        frame = new JFrame("메모리 매칭 게임");
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 4x4 그리드 패널 생성
        cardButtons = new ArrayList<>();
        Dimension buttonSize = new Dimension(100, 150);
        for (int i = 0; i < 16; i++) {
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(buttonSize);
            cardButton.setActionCommand(cardValues.get(i));
            cardButton.addActionListener(this::cardClicked);
            cardButtons.add(cardButton);
            gridPanel.add(cardButton);
        }

        frame.add(gridPanel, BorderLayout.CENTER); // 그리드 패널을 중앙에 추가

        timeLabel = new JLabel("남은 시간: " + timeLimit, SwingConstants.CENTER);
        frame.add(timeLabel, BorderLayout.SOUTH); // 타이머 레이블을 하단에 추가

        frame.pack();
        frame.setVisible(true);
    }


    private void cardClicked(ActionEvent e) {
        JButton selectedButton = (JButton) e.getSource();
        String selectedValue = selectedButton.getActionCommand();

        if (firstSelectedButton == selectedButton) {
            return; // 같은 카드를 두 번 클릭하는 경우 무시
        }

        // 선택한 카드에 이미지 설정
        String imagePath = "img/" + selectedValue + ".jpg"; // 'img' 폴더 내의 상대 경로
        ImageIcon icon = new ImageIcon(imagePath);
        
        // 이미지 크기 조절
        Image scaledImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        selectedButton.setIcon(new ImageIcon(scaledImage));


        if (firstSelectedButton == null) {
            firstSelectedButton = selectedButton;
            firstSelectedValue = selectedValue;
        } else {
            if (selectedValue.equals(firstSelectedValue)) {
                firstSelectedButton.setEnabled(false);
                selectedButton.setEnabled(false);
                matchesFound++;
                firstSelectedButton = null;
                if (matchesFound == 8) {
                    timer.stop();
                    endGame(true);
                }
            } else {
                Timer pause = new Timer(1000, ae -> {
                    firstSelectedButton.setIcon(null);
                    selectedButton.setIcon(null);
                    firstSelectedButton = null;
                });
                pause.setRepeats(false);
                pause.start();
            }
        }
    }

    private void startGameTimer() {
        timer = new Timer(1000, new ActionListener() {
            int remainingTime = timeLimit;

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timeLabel.setText("남은 시간: " + remainingTime);
                if (remainingTime <= 0) {
                    timer.stop();
                    endGame(matchesFound == 8);
                }
            }
        });
        timer.start();
    }


    private void endGame(boolean isWin) {
        String message = isWin ? "성공! 모든 카드를 맞췄습니다!" : "실패! 시간 초과!";
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
    }

    public static void main(String[] args) {
        new MemoryMatchGame();
    }
}
