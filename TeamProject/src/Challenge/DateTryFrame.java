package Challenge;

import javax.swing.*;
import java.awt.*;
import shop.Item;
import shop.Closet;

public class DateTryFrame extends JFrame {
    private DateTry dateTry;
    private JLabel scoreLabel;
    private JLabel feedbackLabel;

    public DateTryFrame(DateTry dateTry) {
        this.dateTry = dateTry;
        setSize(400, 400);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        scoreLabel = new JLabel();
        feedbackLabel = new JLabel();
        
        updateFeedback();

        add(scoreLabel);
        add(feedbackLabel);

        setVisible(true);
        
        
    }

    private void updateFeedback() {
        int score = dateTry.calculateScore();
        String feedback = dateTry.getFeedback(score);

        scoreLabel.setText("점수: " + score);
        feedbackLabel.setText("피드백: " + feedback);
    }

    public static void main(String[] args) {
        // 여기서 Closet 객체와 DateTry 객체를 생성하고 초기화해야 합니다.
        Closet closet = new Closet();
        DateTry dateTry = new DateTry(closet);

        // 예시를 위해 임시로 아이템을 추가합니다.
        // 실제 구현에서는 사용자의 선택에 따라 아이템이 추가됩니다.
        closet.addItem(new Item("옷1", 0, "옷"));
        closet.addItem(new Item("악세서리1", 0, "악세서리"));

        SwingUtilities.invokeLater(() -> new DateTryFrame(dateTry));
    }
}
