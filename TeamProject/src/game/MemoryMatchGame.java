package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.ArrayList;

public class MemoryMatchGame extends JFrame implements ActionListener {
    private ArrayList<Card> cards;
    private Card selectedCard;
    private Card c1;
    private Card c2;
    private Timer t;
    private boolean isChecking = false;
    private int remainingTime = 40;
    private JLabel timeLabel;
    private Timer timer;
    private boolean gameEnded = false; // 게임 종료 여부를 나타내는 변수 추가
    
    public MemoryMatchGame() {
        this.cards = new ArrayList<>();
        this.selectedCard = null;
        this.t = new Timer(1000, this);
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timeLabel.setText("남은 시간: " + remainingTime + "초");

                if (remainingTime <= 0) {
                    timer.stop();
                    endGame();
                }
            }
        });

        setUpCards();

        setTitle("기억력 게임");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        timeLabel = new JLabel("남은 시간: " + remainingTime + "초");
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new GridLayout(4, 4));
        for (Card card : cards) {
            cardPanel.add(card);
            card.addActionListener(this);
        }
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);

        timer.start();

        // 창 닫기 이벤트 처리
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop(); // 창 닫을 때 타이머 중지
                returnToGameStart();
                dispose();
            }
        });
    }

    private void setUpCards() {
        // 이미지 크기 조정
        ImageIcon backIcon = new ImageIcon("img/game/back.png");
        backIcon.setImage(backIcon.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH));

        for (int i = 1; i <= 8; i++) {
            ImageIcon frontIcon = new ImageIcon("img/game/image" + i + ".png");
            // 이미지 크기 조정
            frontIcon.setImage(frontIcon.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH));

            Card c1 = new Card();
            c1.setId(i);
            c1.setFrontIcon(frontIcon);
            c1.setBackIcon(backIcon);
            c1.setFaceDown();

            Card c2 = new Card();
            c2.setId(i);
            c2.setFrontIcon(frontIcon);
            c2.setBackIcon(backIcon);
            c2.setFaceDown();

            cards.add(c1);
            cards.add(c2);
        }

        Collections.shuffle(cards);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {
            checkCards();
        } else if (!isChecking) {
            Card clickedCard = (Card) e.getSource();
            if (selectedCard == null && !clickedCard.isMatched() && !clickedCard.isFaceUp()) {
                selectedCard = clickedCard;
                selectedCard.flip();
            } else if (selectedCard != null && clickedCard != selectedCard && !clickedCard.isMatched() && !clickedCard.isFaceUp()) {
                c1 = selectedCard;
                c2 = clickedCard;
                c2.flip();

                isChecking = true;
                t.start();
                selectedCard = null;
            }
        }
    }

    private void checkCards() {
        if (c1.getId() != c2.getId()) {
            c1.flip();
            c2.flip();
        } else {
            c1.setMatched(true);
            c2.setMatched(true);
        }

        c1 = null;
        c2 = null;
        isChecking = false;
        t.stop();

        if (checkGameWon()) {
            endGame();
        }
    }

    private boolean checkGameWon() {
        for (Card card : cards) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    private void endGame() {
        timer.stop();

        // 게임 종료 여부를 확인하고 한 번만 결과를 표시
        if (!gameEnded) {
            gameEnded = true; // 게임 종료 상태로 설정

            if (checkGameWon()) {
                JOptionPane.showMessageDialog(this, "성공! 모든 카드를 맞췄습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "실패! 시간이 초과되었습니다.");
            }

            returnToGameStart();
            dispose();
        }
    }

    private void returnToGameStart() {
        GameStart.main(new String[0]);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MemoryMatchGame());
    }

    class Card extends JButton {
        private int id;
        private boolean matched = false;
        private boolean isFaceUp = false;
        private ImageIcon frontIcon;
        private ImageIcon backIcon;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setFrontIcon(ImageIcon frontIcon) {
            this.frontIcon = frontIcon;
        }

        public void setBackIcon(ImageIcon backIcon) {
            this.backIcon = backIcon;
        }

        public void flip() {
            if (isFaceUp) {
                setIcon(backIcon);
                isFaceUp = false;
            } else {
                setIcon(frontIcon);
                isFaceUp = true;
            }
        }

        public void setFaceDown() {
            setIcon(backIcon);
            isFaceUp = false;
        }

        public boolean isFaceUp() {
            return isFaceUp;
        }

        public boolean isMatched() {
            return matched;
        }

        public void setMatched(boolean matched) {
            this.matched = matched;
        }
    }
}
