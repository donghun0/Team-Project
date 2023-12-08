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
            	// 창이 닫힐 때 실행될 동작
            	frame.dispose(); // 현재 프레임을 닫습니다.
                openGameSelectionWindow(); // 게임 선택 창을 엽니다.
            }
        });
        
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel("<html><center>퀴즈가 여기에 표시됩니다.</center></html>", SwingConstants.CENTER);
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(2, 2));
        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton("답변 " + (i + 1));
            button.addActionListener(this::checkAnswer);
            answerButtons[i] = button;
            answersPanel.add(button);
        }
        frame.add(answersPanel, BorderLayout.CENTER);

        feedbackLabel = new JLabel("<html><center>정답 혹은 오답을<br>여기에 표시합니다.</center></html>", SwingConstants.CENTER);
        frame.add(feedbackLabel, BorderLayout.SOUTH);
        
        
        
     // 폰트 파일 경로
        String fontFilePath = "img/neodgm.ttf";

        try {
            // 폰트 파일 로드
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath));

            // 폰트 크기 설정
            customFont = customFont.deriveFont(Font.PLAIN, 50); // 혹시 필요시 폰트 크기 수정

            // 폰트 설정
            questionLabel.setFont(customFont);
            feedbackLabel.setFont(customFont);
            

            for (JButton button : answerButtons) {
                button.setFont(customFont);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // 폰트 로딩에 실패한 경우 기본 폰트를 사용하거나 에러 처리를 수행할 수 있습니다.
        }

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
        Question currentQuestion = questions.get(currentQuestionIndex - 1);

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            feedbackLabel.setText("정답입니다!");
            correctAnswers++;
        } else {
            feedbackLabel.setText("오답입니다.");
        }

        // 사용자가 답을 선택하면 다음 문제로 넘어감
        nextQuestion();
    }
    
    private void showFinalResult() {
        String resultMessage = (correctAnswers >= 3 ? "성공! " : "실패! ") + 
                               "정답 개수: " + correctAnswers + " / 5";
        
        if (correctAnswers >= 3) {
        	updateMoney(500); // Earn 500 won for 3 or more correct answers
            resultMessage += "\n500원을 획득하였습니다!";
        }
        
        Object[] options = {"종료"};
        int option = JOptionPane.showOptionDialog(frame, resultMessage, "퀴즈 종료",
                                                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                                                  null, options, options[0]);

        if (option == 0) {
            frame.dispose();
            openGameSelectionWindow();
        }
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
