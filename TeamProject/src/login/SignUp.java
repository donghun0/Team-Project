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
		this.signUpPanel.add(signUpbtn);
		
		this.setContentPane(signUpPanel);
		this.setSize(300,500);				
		this.setLocationRelativeTo(null);

		checkValue();
		
		// 폰트 파일 경로
        String fontFilePath = "img/neodgm.ttf";

        try {
            // 폰트 파일 로드
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath));

            // 폰트 크기 설정
            customFont = customFont.deriveFont(Font.PLAIN, 15);

            // 폰트 설정
            idlabel.setFont(customFont);
            idText.setFont(customFont);
            pwlabel.setFont(customFont);
            pwText.setFont(customFont);
            pwChecklabel.setFont(customFont);
            pwCheckText.setFont(customFont);
            signUpbtn.setFont(customFont);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 폰트 로딩에 실패한 경우 기본 폰트를 사용하거나 에러 처리를 수행할 수 있습니다.
        }
	}
	 
	 //회원 가입할때 모든 값이 입력되었는지 체크하기 위한 메소드
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
				
				
                /** 컴퓨터 C드라이브에 java_member 폴더 만들고 member.txt 파일 만드시면 됩니다. **/
				//회원가입시 텍스트 파일을 하나 만들어서 아이디와 비번을 저장 하자. 한줄 뛰고 아이디|비밀번호 텍스트 파일에 차곡 차곡 저장하기~ | 는 아이디랑 패스워드 구분하기 위해 사용
				String txt = idText.getText()+"|"+pwText.getText();
				txt+="\n";
				
				String fileName = "C:\\java_member\\member.txt" ;
				
				
				try{
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
					
					// 파일안에 문자열 쓰기
					fw.write(txt);
					fw.flush();

					// 객체 닫기
					fw.close();					
					
				}catch(Exception errmsg){
					errmsg.printStackTrace();
				}
				
				
				//여기까지 왔다면 모든 값을 입력하고 선택하는 것이 완료되었으니 회원가입 과정이 완료.				
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
	 
	 //메인 클래스에서 다이얼로그 회원가입 창 데이터를 가져오기 위한 get 메소드 선언
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