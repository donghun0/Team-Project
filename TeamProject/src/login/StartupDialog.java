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
        ImageIcon icon = new ImageIcon("C:\\java_member\\농담곰.jpg");

        // Create a JLabel for the image
        JLabel imageLabel = new JLabel(icon);
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
        setSize(400, 400);
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