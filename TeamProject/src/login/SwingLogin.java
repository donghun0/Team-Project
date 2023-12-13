package login;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import shop.Closet;

public class SwingLogin extends JFrame {
    private JPanel loginPanel = new JPanel(new GridLayout(4, 4));
    private JLabel idLabel = new JLabel("  아이디 ");
    private JLabel pwLabel = new JLabel(" 비밀번호 ");
    private JTextField idText = new JTextField();
    private JPasswordField pwText = new JPasswordField();
    private JButton loginBtn = new JButton("로그인");
    private JButton memberbtn = new JButton("회원가입");
    private Closet closet = new Closet();

    public SwingLogin() {
        super("로그인 창!");
        
       
        loginBtn.setBackground(new Color(255, 196, 235)); // 연핑크 색상
        loginBtn.setOpaque(true);

       
        memberbtn.setBackground(new Color(255, 196, 235)); // 연핑크 색상
        memberbtn.setOpaque(true);


        this.setContentPane(loginPanel);
        loginPanel.add(idLabel);
        loginPanel.add(pwLabel);
        loginPanel.add(idText);
        loginPanel.add(pwText);
        loginPanel.add(loginBtn);
        loginPanel.add(memberbtn);

        
        idLabel.setHorizontalAlignment(NORMAL);
        pwLabel.setHorizontalAlignment(NORMAL);

        
        setSize(500, 200);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = idText.getText().trim();
                String pw = new String(pwText.getPassword());
                String login = "";

                if (id.length() == 0 || pw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!",
                            JOptionPane.DEFAULT_OPTION);
                    return;
                }

                
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\java_member\\member.txt"));

                    String str;
                    ArrayList<String> txtmember = new ArrayList<>();
                    while ((str = reader.readLine()) != null) {
                        txtmember.add(str);
                    }

                    reader.close();

                    
                    HashMap<String, String> memberlist = new HashMap<>();

                    for (int i = 0; i < txtmember.size(); i++) {
                        
                        String[] tempresult = txtmember.get(i).toString().split("\\|");
                        memberlist.put(tempresult[0], tempresult[1]);
                    }

                    
                    for (String key : memberlist.keySet()) {
                        if (id.equals(key.trim()) && pw.equals(memberlist.get(key))) {
                            
                            login = "성공";
                        }
                    }

                } catch (Exception errmsg) {
                    errmsg.printStackTrace();
                }

                if (login.equals("성공")) {
                    JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);

                    Homepage homepage = Homepage.getInstance(closet);
                    homepage.setVisible(true);

                    dispose(); 
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패", "계정 정보를 확인해 주세요.",
                            JOptionPane.DEFAULT_OPTION);
                }

            }
        });

        
        memberbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signup = new SignUp();
                signup.setVisible(true);
            }
        });
        
       
        String fontFilePath = "img/neodgm.ttf";

        try {
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath));

            
            customFont = customFont.deriveFont(Font.PLAIN, 30);

            
            idLabel.setFont(customFont);
            pwLabel.setFont(customFont);
            idText.setFont(customFont);
            pwText.setFont(customFont);
            loginBtn.setFont(customFont);
            memberbtn.setFont(customFont);
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    }
    
    public static void main(String[] args) {
        new SwingLogin();
    }
    
    
}
