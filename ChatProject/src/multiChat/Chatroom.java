package multiChat;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Chatroom extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chatroom frame = new Chatroom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Chatroom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("코딩 친목방");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel.setBounds(89, 41, 243, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 로그인 버튼 누르면
				setVisible(false);
				new LoginGUI().setVisible(true);
			}
		});
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnLogin.setBounds(60, 510, 121, 34);
		contentPane.add(btnLogin);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 회원가입 버튼 누르면
				setVisible(false);
				new Join().setVisible(true);
			} 
		});
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnJoin.setBounds(244, 510, 121, 34);
		contentPane.add(btnJoin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Chatroom.class.getResource("/chatimg/coding.png")));
		lblNewLabel_1.setBounds(20, 87, 410, 399);
		contentPane.add(lblNewLabel_1);
	}
}
