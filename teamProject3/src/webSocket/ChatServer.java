package webSocket;

import java.util.Collections;
import java.io.IOException;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer")
// ServerEndpoint�ֳ����̼����� ������ ������ ��û���� ����, �ش� ��û������ �����ϴ� Ŭ���̾�Ʈ��
// ��Ŭ������ ó���ϰ� �Ѵ�.
// �����Ͽ� �����ϱ� ���� URL : (ws://ȣ��Ʈ:��Ʈ��ȣ/���ؽ�Ʈ��Ʈ/ChatingServer)


// ��, ���Ⱑ ä�ü���

public class ChatServer {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen // Ŭ���̾�Ʈ ���� �� ����޼���, clients�÷��ǿ� ������ �߰�
	public void onOpen(Session session) {
		
		clients.add(session); // �����߰�
		System.out.println("������ ���� : " + session.getId());
		
	} // onOpen-end
	
	@OnMessage // Ŭ���̾�Ʈ�κ��� �޼����� �޾��� �� �����ϴ� �޼���, Ŭ���̾�Ʈ�� ���� �޼����� ������ �Ű������� �Ѿ���� �ȴ�.
	public void onMessage(String message, Session session) throws IOException {
		
		System.out.println("�޼��� ���� : " + session.getId() + " : " + message);
		
		synchronized(clients) {
			
			for (Session client : clients) { // ���Ŭ���̾�Ʈ���� �޼��� ����
				
				if(!client.equals(session)) {
					// ��, �޼����� ���� Ŭ���̾�Ʈ�� ����
					client.getBasicRemote().sendText(message);					
				}
				
			} //for-end
			 
		}// synchronized-end
		
	}// onMessage-end
	
	
	
	
	@OnClose // Ŭ���̾�Ʈ�� ������ ����� ����Ǵ� �޼���
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("������ ���� : " + session.getId());
	} // onClose-end
	
	
	
	@OnError // �����߻� �� ����Ǵ� �޼���
	public void onError(Throwable e) {
		System.out.println("�����߻�");
		e.printStackTrace();
	} // onError-end
}
