package multiChat;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Join extends JFrame implements ActionListener {
	
	UserData udata;
	
	Button btn1 = new Button("회원가입");
	Button btn2 = new Button("취소");

	Label lb1 = new Label("닉네임");
	Label lb2 = new Label("I d");
	Label lb3 = new Label("P W");
	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	JPasswordField tf3 = new JPasswordField();

	public Join() {

		this.setLayout(null);
		this.setBounds(350, 50, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lb1.setSize(50, 30);
		lb1.setLocation(170, 70);
		lb2.setSize(70, 30);
		lb2.setLocation(lb1.getX(), lb1.getY() + 30);
		lb3.setSize(50, 30);
		lb3.setLocation(lb2.getX(), lb2.getY() + 30);

		btn1.setSize(100, 30);
		btn1.setLocation(150, 170);
		btn2.setSize(100, 30);
		btn2.setLocation(btn1.getX() + 100, btn1.getY());
		tf1.setSize(120, 30);
		tf1.setLocation(lb1.getX() + 70, lb1.getY());
		tf2.setSize(120, 30);
		tf2.setLocation(lb2.getX() + 70, lb2.getY());
		tf3.setSize(120, 30);
		tf3.setLocation(lb3.getX() + 70, lb3.getY());

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		this.add(lb1);
		this.add(lb2);
		this.add(btn1);
		this.add(btn2);
		this.add(tf1);
		this.add(tf2);
		this.add(lb3);
		this.add(tf3);

		this.setVisible(true);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new Join();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		udata = new UserData();
		UserDao udao ;
		
		if (e.getSource() == btn1) {
			try {
				udao = new UserDao();
				udata.setName(tf1.getText());
				udata.setId(tf2.getText());
				udata.setPw(new String(tf3.getPassword()));

				int result1 = udao.insert_ud(udata);
				int result2 = udao.select_id(udata.getId());
				int result3 = udao.select_name(udata.getName());
				
				if (result1 == 0) {
					if (result2 == 1) { 
						JOptionPane.showMessageDialog(null, "이미 등록되어 있는 아이디입니다.", "회원가입 오류", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "값을 다시 입력하세요.", "회원가입 오류", JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "회원가입 되셨습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					new LoginGUI().setVisible(true);
				}

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}

		}
		if(e.getSource()==btn2) {
			setVisible(false);
			new LoginGUI().setVisible(true);
		}
	}
}
