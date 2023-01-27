package multiChat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server_Contents extends JFrame {

	private JPanel contentPane;
	private JScrollPane sc;
	private JTextArea ta;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_Contents frame = new Server_Contents();
					frame.setVisible(true);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Server_Contents() throws Exception{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		
		//contentPane = new JPanel();
		ta = new JTextArea(20,50);
		ta.setEditable(false);
		sc = new JScrollPane(ta); //TextArea 객체를 ScrollPane 에 넣어줘서 적용!

		contentPane = new JPanel();
		
		//컴포넌트 속성 세팅
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 안쓰게함.
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ta.setLineWrap(true);                                           //꽉차면 다음줄로 가게 해줌.
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT));      //글자수 나오는칸 오른쪽으로 가게 해줌.

		getContentPane().add(sc, BorderLayout.CENTER);
		getContentPane().add(contentPane, BorderLayout.SOUTH);
		this.pack(); // 윈도우크기를 조절해줌
		this.setVisible(true);

		
		UserDao udao = new UserDao();
		List<String> rcvContents = udao.selectContents();
		for(String str : rcvContents) {
			System.out.println(str);
			ta.append(str+"\n");
			
		}
		
	}
}
