package Challenge;

import javax.swing.*;

public class DateTry {

    public static void main(String[] args) {
        // 점수 생성
        int score = generateRandomScore();

        // 피드백 메시지 얻기
        String feedback = getFeedback(score);

        // 이미지 표시
        ImageIcon icon = getIcon(score);
        JLabel label = new JLabel(icon);

        // 결과 메시지와 이미지 표시
        JOptionPane.showMessageDialog(null, "점수: " + score + "\n농담곰 왈: " + feedback, "데이트 결과", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    // 랜덤으로 점수 생성
    private static int generateRandomScore() {
        return (int) (Math.random() * 101); // 0부터 100까지의 랜덤한 정수
    }

    // 점수에 따른 피드백 얻기
    private static String getFeedback(int score) {
        if (score <= 25) {
            return "너무 싫어!!";
        } else if (score <= 50) {
            return "별로야!";
        } else if (score <= 75) {
            return "음... 괜찮묘!";
        } else {
            return "나랑 사귀장!";
        }
    }

    // 점수에 따른 이미지 얻기
    private static ImageIcon getIcon(int score) {
        if (score <= 25) {
            return new ImageIcon("C://java_member//결과 1.jpg"); // 이미지 1 경로 지정
        } else if (score <= 50) {
            return new ImageIcon("C://java_member//결과 2.jpg"); // 이미지 2 경로 지정
        } else if (score <= 75) {
            return new ImageIcon("C://java_member//결과 3.jpg"); // 이미지 3 경로 지정
        } else {
            return new ImageIcon("C://java_member//결과 4.jpg"); // 이미지 4 경로 지정
        }
    }
}

