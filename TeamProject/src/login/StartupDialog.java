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

        loginButton.setBounds(120, 650, 220, 60);
        signUpButton.setBounds(420, 650, 220, 60);
        
        loginButton.setBackground(Color.WHITE);
        signUpButton.setBackground(Color.WHITE);
        
        
        String fontFilePath = "img/neodgm.ttf";

        try {
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath));

            
            customFont = customFont.deriveFont(Font.PLAIN, 30);

            
            loginButton.setFont(customFont);
            signUpButton.setFont(customFont);
        } catch (Exception ex) {
            ex.printStackTrace();
            
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