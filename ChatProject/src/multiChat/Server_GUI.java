package multiChat;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Server_GUI {

	public static void main(String[] args) {
		new Server_ChatGUI(9999);
	}
}



	
class Server_ChatGUI extends JFrame implements ActionListener, KeyListener {
	// 서버용 채팅창
	JPanel ServerGUI_Panel = new JPanel();
	JLabel ServerLabel = new JLabel("Main Server");
	JLabel UserLabel = new JLabel("유저 목록");
	JTextField Chat = new JTextField(45);
	JButton Enter = new JButton("전송");
	JButton ContentsRead = new JButton("대화내역");
	TextArea ServerChatList = new TextArea(30, 50);
	TextArea UserList = new TextArea(30, 15);
	Server_Back SB = new Server_Back();
	UserDao udao;
	
	public Server_ChatGUI(int Port) {
		
		setTitle("메인 서버");
		
		setSize(750, 650);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫았을 때 메모리에서 제거되도록 설정합니다.

		ServerChatList.setEditable(false);
		UserList.setEditable(false);
		Chat.addKeyListener(this);
		Enter.addActionListener(this);
		ContentsRead.addActionListener(this);

		ServerGUI_Panel.add(ServerLabel);
		ServerGUI_Panel.add(ServerChatList);
		ServerGUI_Panel.add(UserLabel);
		ServerGUI_Panel.add(UserList);
		ServerGUI_Panel.add(Chat);
		ServerGUI_Panel.add(Enter);
		ServerGUI_Panel.add(ContentsRead);
		add(ServerGUI_Panel);
		setVisible(true);
		setLocationRelativeTo(null);

		UserList.append("관리자\n"); // 실행과 동시에 서버주인(Admin)을 유저목록에 추가하도록 합니다.
		SB.setGUI(this);
		SB.Start_Server(9999);
		SB.start(); // 서버 채팅창이 켜짐과 동시에 서버소켓도 함께 켜집니다.
	}
	
	public void actionPerformed(ActionEvent e) { // 전송 버튼을 누르고, 입력값이 1이상일때만 전송되도록 합니다.
		String Message = Chat.getText().trim();
		if (e.getSource() == Enter && Message.length() > 0) {
			AppendMessage("서버 : " + Message + "\n");
			SB.Transmitall("서버 : " + Message + "\n");
			Chat.setText(null); // 채팅창 입력값을 초기화 시켜줍니다.
		}
		
		if(e.getSource() == ContentsRead) {
			try { 
				new Server_Contents().setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
		}
		
	}

	public void keyPressed(KeyEvent e) { // 키보드 엔터키를 누르고, 입력값이 1이상일때만 전송되도록 합니다.
		String Message = Chat.getText().trim();
		if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
			AppendMessage("서버 : " + Message + "\n");
			SB.Transmitall("서버 : " + Message + "\n");
			Chat.setText(null); // 채팅창 입력값을 초기화 시켜줍니다.
		}
		
	}

	public void AppendMessage(String Message) {
		try {	
			udao = new UserDao();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ServerChatList.append(Message);
		
		 try {
	         int answer1 = udao.insertContents(Message);
	         ServerChatList.append(Message);
	         if(answer1 != 0) 
	            System.out.println("DB에 Message 성공!!");
	         else
	            System.out.println("DB 저장 실패!!");
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	}

	public void AppendUserList(ArrayList NickName) {
		String name;
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
