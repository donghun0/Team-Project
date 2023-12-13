package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SignUp extends JDialog{
	 private JPanel signUpPanel = new JPanel(new GridLayout(11, 0));
	 private JTextField idText = new JTextField("");
	 private JPasswordField pwText = new JPasswordField();
	 private JPasswordField pwCheckText = new JPasswordField();
	 private JButton signUpbtn = new JButton("회원가입");
	 private JLabel idlabel = new JLabel("아이디");
	 private JLabel pwlabel = new JLabel("비밀번호");
	 private JLabel pwChecklabel = new JLabel("비밀번호 확인");
	  
	 private boolean membershipProgress = false;

	 public SignUp() {
		
		this.setTitle("회원가입");
		
		this.signUpPanel.add(idlabel);
		this.signUpPanel.add(idText);
		this.signUpPanel.add(pwlabel);
		this.signUpPanel.add(pwText);
		this.signUpPanel.add(pwChecklabel);
		this.signUpPanel.add(pwCheckText);
		
        signUpbtn.setBackground(new Color(255, 196, 235)); 

		this.signUpPanel.add(signUpbtn);
		
		this.setContentPane(signUpPanel);
		this.setSize(500,800);				
		this.setLocationRelativeTo(null);

		checkValue();
		
		
        String fontFilePath = "img/neodgm.ttf";

        try {
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath));

            
            customFont = customFont.deriveFont(Font.PLAIN, 30);

           
            idlabel.setFont(customFont);
            idText.setFont(customFont);
            pwlabel.setFont(customFont);
            pwText.setFont(customFont);
            pwChecklabel.setFont(customFont);
            pwCheckText.setFont(customFont);
            signUpbtn.setFont(customFont);
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
	}
	 
	
	 private void checkValue(){
		 signUpbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idText.getText().trim().length()==0 || idText.getText().trim().equals("아이디")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", "아이디 입력", JOptionPane.WARNING_MESSAGE);
					idText.grabFocus();
					return;
				}
				
				if(pwText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", "비밀번호 입력", JOptionPane.WARNING_MESSAGE);
					pwText.grabFocus();
					return;
				}
				
				if(pwCheckText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해 주세요.", "비밀번호 확인 입력", JOptionPane.WARNING_MESSAGE);
					pwCheckText.grabFocus();
					return;
				}
				
				if(!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
					JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.!!", "비밀번호 확인", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
                
				String txt = idText.getText()+"|"+pwText.getText();
				txt+="\n";
				
				String fileName = "C:\\java_member\\member.txt" ;
				
				
				try{
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
					
					
					fw.write(txt);
					fw.flush();

					
					fw.close();					
					
				}catch(Exception errmsg){
					errmsg.printStackTrace();
				}
				
				
							
				membershipProgress = true;				
				
				JOptionPane.showMessageDialog(null, "회원 가입이 완료 되었습니다.","회원 가입 완료.", JOptionPane.WARNING_MESSAGE);
								
				setVisible(false);
				
				 showStartupDialog();
			}
		});
	 }
	 
	 private void showStartupDialog() {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                StartupDialog startupDialog = new StartupDialog(null);
	                startupDialog.setVisible(true);
	            }
	        });
	 }
	 
	 
	public String getIdText() {
		return this.idText.getText().trim();
	}

	public String getPwText() {
		return this.pwText.getText().trim();
	}

	public String getPwCheckText() {
		return this.pwCheckText.getText().trim();
	}

	public boolean memberCheck() {
		return membershipProgress;
	}
	 
	 
	 
}