package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupDialog extends JDialog {
    private JButton loginButton = new JButton("로그인");
    private JButton signUpButton = new JButton("회원가입");

    public StartupDialog(JFrame parent) {
    	super(parent, "농담곰의 우당탕탕 데이트 대작전", true);

        JPanel panel = new JPanel(new BorderLayout());
        
        ImageIcon originalIcon = new ImageIcon("img//login//image-start.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(720, 720, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setLayout(null);

        loginButton.setBounds(100, 640, 250, 80);
        signUpButton.setBounds(400, 640, 250, 80);
        
        loginButton.setBackground(Color.WHITE);
        signUpButton.setBackground(Color.WHITE);
        
     // 사용할 폰트 파일 경로
        String fontFilePath = "img/neodgm.ttf";

        try {
            // 폰트 파일 로드
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath));

            // 폰트 크기 설정
            customFont = customFont.deriveFont(Font.PLAIN, 50);

            // 폰트 설정
            loginButton.setFont(customFont);
            signUpButton.setFont(customFont);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 폰트 로딩에 실패한 경우 기본 폰트를 사용하거나 에러 처리를 수행할 수 있습니다.
        }

        imageLabel.add(loginButton);
        imageLabel.add(signUpButton);

        panel.add(imageLabel);
        panel.setSize(720, 720); 
   

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new SwingLogin(); 
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new SignUp().setVisible(true); 
            }
        });

        getContentPane().add(panel);
        setSize(740, 790);
        setLocationRelativeTo(parent);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartupDialog startupDialog = new StartupDialog(null);
                startupDialog.setVisible(true);
            }
        });
    }
}