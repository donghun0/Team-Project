package Challenge;

import javax.swing.*;
import java.awt.*;
import shop.Item;
import shop.Closet;

public class DateTryFrame extends JFrame {
    private DateTry dateTry;
    private JLabel scoreLabel;
    private JLabel feedbackLabel;
    private JLabel imageLabel;


    public DateTryFrame(DateTry dateTry) {
        this.dateTry = dateTry;
        setSize(600, 630);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        scoreLabel = new JLabel();
        feedbackLabel = new JLabel();
        imageLabel = new JLabel();
        
        updateFeedback();

        add(scoreLabel);
        add(feedbackLabel);
        add(imageLabel);

        // 폰트 설정
        Font font = new Font("NEO둥근모", Font.PLAIN, 50);
        scoreLabel.setFont(font);
        feedbackLabel.setFont(font);
        
        // 배경색 설정
        getContentPane().setBackground(new Color(255, 196, 235)); // 연핑크 색상
                
        setVisible(true);
    }

    private void updateFeedback() {
        int score = dateTry.calculateScore();
        String feedback = dateTry.getFeedback(score);

        scoreLabel.setText("점수: " + score);
        feedbackLabel.setText("피드백: " + feedback);
        
        // 이미지 파일 경로 업데이트
        String imagePath = "";
        if (score <= 25) {
            imagePath = "img/challenge/c1.jpg"; // 해당 결과에 맞는 이미지 경로
        } else if (score <= 50) {
            imagePath = "img/challenge/c2.jpg";
        } else if (score <= 75) {
            imagePath = "img/challenge/c3.jpg";
        } else {
            imagePath = "img/challenge/c4.jpg";
        }

        // 이미지 라벨 업데이트
        ImageIcon icon = new ImageIcon(imagePath);
        imageLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        // 여기서 Closet 객체와 DateTry 객체를 생성하고 초기화해야 합니다.
        Closet closet = new Closet();
        DateTry dateTry = new DateTry(closet);

        SwingUtilities.invokeLater(() -> new DateTryFrame(dateTry));
    }
}
