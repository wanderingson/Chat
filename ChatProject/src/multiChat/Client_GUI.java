package multiChat;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client_GUI {
	public static void main(String[] args) {
		LoginGUI LG = new LoginGUI();
		LG.getContentPane().setLayout(null);
	}
}

class LoginGUI extends JFrame implements ActionListener {
	// 유저의 로그인 창
	private JPanel Login_GUIPanel = new JPanel();
	private JTextField NickName_Text = new JTextField(20);
	private JTextField ID_Text = new JTextField(20);
	private JTextField PW_Text = new JTextField(20);
	private JLabel NickName_Label = new JLabel("닉네임입력");
	private JLabel ID_Label = new JLabel("I D 입력  ");
	private JLabel PW_Label = new JLabel("P W 입력  ");
	private JButton Login_GUI_Button = new JButton("접속!");
	private JButton New_GUI_Button = new JButton("회원가입");

	public LoginGUI() {
		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setResizable(true);
		// === NickName ===
		NickName_Label.setBounds(24, 29, 27, 15);
		NickName_Text.setBounds(55, 29, 27, 20);
		
		// === ID ===
		ID_Label.setBounds(24, 54, 27, 15);
		ID_Text.setBounds(55, 54, 27, 20);
		
		// === PW ===
		PW_Label.setBounds(24, 29, 27, 15);
		PW_Text.setBounds(55, 54, 27, 20);
		
		
		// === 로그인 버튼 ===
		Login_GUI_Button.setSize(260, 40);
		Login_GUI_Button.addActionListener(this);
		
		New_GUI_Button.setSize(260, 40);
		New_GUI_Button.addActionListener(this);

		Login_GUIPanel.add(NickName_Label);
		Login_GUIPanel.add(NickName_Text);
		Login_GUIPanel.add(ID_Label);
		Login_GUIPanel.add(ID_Text);
		Login_GUIPanel.add(PW_Label);
		Login_GUIPanel.add(PW_Text);
		Login_GUIPanel.add(Login_GUI_Button);
		Login_GUIPanel.add(New_GUI_Button);

		add(Login_GUIPanel);
		setVisible(true);
		setLocationRelativeTo(null);


	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		UserData udata;
		UserDao udao;
		try {
			udata = new UserData();
			udao = new UserDao();
			if (e.getSource() == Login_GUI_Button) {
				String NickName = NickName_Text.getText().trim();
				String ID = ID_Text.getText().trim();
				String PW = PW_Text.getText().trim();
				int result1 = udao.select_id(ID);
				int result2 = udao.select_pw(PW);
				int result3 = udao.select_name(NickName);
				
				if(result1 != 0) {
					if(result2 != 0) {
						if(result3 != 0) {
							setVisible(false);
							new Client_ChatGUI(NickName,ID,PW);
						}else {
							JOptionPane.showMessageDialog(null, "없는 닉네임입니다.", "로그인실패", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "없는 PW입니다.", "로그인실패", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "없는 ID입니다.", "로그인실패", JOptionPane.WARNING_MESSAGE);
				}	
				
				
				
				
				
				
			}
		} catch (Exception a) {
			// 만약 올바르지 않는 값이 입력되면 팝업창을 띄워줍니다.
			JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
		}
		
		
			if(e.getSource()==New_GUI_Button) {
				setVisible(false);
				new Join().setVisible(true);
			}
		
	}
}

class Client_ChatGUI extends JFrame implements ActionListener, KeyListener {
	//클라이언트용 채팅창
	String NickName;
	Client_Back CB = new Client_Back();
	JPanel ClientGUIPanel = new JPanel();
	JLabel UserLabel = new JLabel("유저 목록");
	JLabel User = new JLabel(NickName);
	JTextField Chat = new JTextField(45);
	JButton Enter = new JButton("전송");
	TextArea ChatList = new TextArea(30, 50);
	TextArea UserList = new TextArea(30, 15);
	JButton Exit_Button = new JButton("홈으로");

	public Client_ChatGUI(String NickName, String ID, String PW) {
		this.NickName = NickName;

		setTitle(NickName+" 창");
		
		setSize(750, 650);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ChatList.setEditable(false);
		UserList.setEditable(false);
		Chat.addKeyListener(this);
		Enter.addActionListener(this);
		Exit_Button.addActionListener(this);
		
		ClientGUIPanel.add(User);
		ClientGUIPanel.add(ChatList);
		ClientGUIPanel.add(UserLabel);
		ClientGUIPanel.add(UserList);
		ClientGUIPanel.add(Chat);
		ClientGUIPanel.add(Enter);
		ClientGUIPanel.add(Exit_Button);
		add(ClientGUIPanel);
		setVisible(true);
		CB.setGui(this);
		CB.getUserInfo(NickName, ID, PW);
		CB.start(); // 채팅창이 켜짐과 동시에 접속을 실행해줍니다.
	}

	public void actionPerformed(ActionEvent e) { 
		// 전송 버튼을 누르고, 입력값이 1이상일때만 전송되도록 합니다.
		String Message = Chat.getText().trim();
		if (e.getSource() == Enter && Message.length() > 0) {
			CB.Transmit(NickName + " : " + Message + "\n");
			Chat.setText(null);
		}
		if(e.getSource()==Exit_Button) {
			setVisible(false);
			new Chatroom().setVisible(true);
		}
	}

	public void keyPressed(KeyEvent e) { 
		// 키보드 엔터키를 누르고, 입력값이 1이상일때만 전송되도록 합니다.
		String Message = Chat.getText().trim();
		if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
			CB.Transmit(NickName + " : " + Message + "\n");
			Chat.setText(null);
		}	
	}

	public void AppendMessage(String Message) {
		ChatList.append(Message);
	}

	public void AppendUserList(ArrayList NickName) {
		// 유저목록을 유저리스트에 띄워줍니다.
		String name;
		UserList.setText(null);
		for (int i = 0; i < NickName.size(); i++) {
			name = (String) NickName.get(i);
			UserList.append(name + "\n");
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}