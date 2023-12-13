package game;

import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.io.File;

public class Quiz {

    private JFrame frame;
    private JLabel questionLabel;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int correctAnswers;
    private JButton[] answerButtons;
    private JLabel feedbackLabel;

    public Quiz() {
        initializeQuestions();
        createUI();
    }

    private void initializeQuestions() {
        QuestionBank questionBank = new QuestionBank();
        questions = questionBank.getRandomQuestions(5); // 5개의 랜덤 퀴즈를 가져옴
    }


    private void createUI() {
        frame = new JFrame("상식 퀴즈 게임");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	frame.dispose();
                openGameSelectionWindow();
            }
        });
        
        frame.setLayout(new BorderLayout());
        
        // 폰트 설정
        Font font = new Font("NEO둥근모", Font.PLAIN, 30);

        questionLabel = new JLabel("<html><center>퀴즈가 여기에 표시됩니다.</center></html>", SwingConstants.CENTER);
        questionLabel.setFont(font);
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(2, 2));
        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton("<html><center>" + "답변 " + (i + 1) + "</center></html>");
            button.setFont(font);
            button.setActionCommand("답변 " + (i + 1)); // ActionCommand 설정
            button.addActionListener(this::checkAnswer);
            Color backgroundColor = new Color(255, 196, 235);
            button.setBackground(backgroundColor);
            button.setOpaque(true); // 버튼을 불투명하게 설정
            
            answerButtons[i] = button;
            answersPanel.add(button);
        }
        frame.add(answersPanel, BorderLayout.CENTER);

        feedbackLabel = new JLabel("<html><center>정답 혹은 오답을<br>여기에 표시합니다.</center></html>", SwingConstants.CENTER);
        feedbackLabel.setFont(font);
        frame.add(feedbackLabel, BorderLayout.SOUTH);
        
        frame.setSize(740, 790);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        nextQuestion();
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex++);
            questionLabel.setText("<html><center>" + question.getQuestion() + "</center></html>");

            for (int i = 0; i < question.getAnswers().length; i++) {
                answerButtons[i].setText("<html><center>" + question.getAnswers()[i] + "</center></html>");
            }
        } else {
        	showFinalResult();
        }
    }

    private void checkAnswer(ActionEvent e) {
        String selectedAnswer = ((JButton) e.getSource()).getText();
        // HTML 태그 제거
        selectedAnswer = selectedAnswer.replaceAll("<html><center>", "").replaceAll("</center></html>", "");
        
        Question currentQuestion = questions.get(currentQuestionIndex - 1);

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            feedbackLabel.setText("정답입니다!");
            correctAnswers++;
        } else {
            feedbackLabel.setText("오답입니다.");
        }

        nextQuestion();
    }
    
    private void showFinalResult() {
        String resultMessage = "<html><center>" + (correctAnswers >= 3 ? "성공!<br>" : "실패!<br>") +
                               "정답 개수: " + correctAnswers + " / 5";

        if (correctAnswers >= 3) {
            updateMoney(20);
            resultMessage += "<br>20원을 획득하였습니다!";
        }
        resultMessage += "</center></html>";
     
        JDialog dialog = new JDialog(frame, "퀴즈 종료", true);
        dialog.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(resultMessage);
        Font font = new Font("NEO둥근모", Font.PLAIN, 30);
        messageLabel.setFont(font);
        dialog.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("종료");
        closeButton.setFont(font);
        closeButton.addActionListener(e -> {
            dialog.dispose(); // 대화 상자를 닫음
            frame.dispose(); // 상식퀴즈 게임 창을 닫음
            openGameSelectionWindow(); // 게임 선택 창을 엶
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // 대화 상자 크기 조절
        dialog.setPreferredSize(new Dimension(280, 250)); // 원하는 크기로 설정
        dialog.pack(); // 대화 상자 크기를 내용에 맞게 조정
        dialog.setLocationRelativeTo(frame); // 부모 창에 대해 중앙에 위치
        dialog.setVisible(true);
    }


    private void updateMoney(int amount) {
        GameStart.increaseMoney(amount);
    }

    private void openGameSelectionWindow() {
    	GameStart.main(null);
    }

    public static void main(String[] args) {
        new Quiz();
    }
}

class Question {
    private String question;
    private String[] answers;
    private String correctAnswer;

    public Question(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
