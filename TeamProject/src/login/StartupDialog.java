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
        ImageIcon originalIcon = new ImageIcon("img/image-start.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(720, 720, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        // Add components to the main panel
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
                new SwingLogin(); // Open the login window
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
                new SignUp().setVisible(true); // Open the signup window
            }
        });

        getContentPane().add(panel);
        setSize(720, 720);
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