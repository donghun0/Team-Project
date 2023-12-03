package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel("퀴즈가 여기에 표시됩니다.", SwingConstants.CENTER);
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

        feedbackLabel = new JLabel("정답 혹은 오답을 여기에 표시합니다.", SwingConstants.CENTER);
        frame.add(feedbackLabel, BorderLayout.SOUTH);

        frame.setSize(450, 150);
        frame.setVisible(true);

        nextQuestion();
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex++);
            questionLabel.setText(question.getQuestion());

            for (int i = 0; i < question.getAnswers().length; i++) {
                answerButtons[i].setText(question.getAnswers()[i]);
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
        String resultMessage = correctAnswers >= 3 ? "성공! " : "실패! ";
        resultMessage += "정답 개수: " + correctAnswers + " / 5";
        questionLabel.setText("퀴즈가 끝났습니다!");
        feedbackLabel.setText(resultMessage);
        for (JButton button : answerButtons) {
            button.setEnabled(false);
        }
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
