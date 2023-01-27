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
// ServerEndpoint애너테이션으로 웹소켓 서버의 요청명을 지정, 해당 요청명으로 접속하는 클라이언트를
// 이클래스가 처리하게 한다.
// 웹소켓에 접속하기 위한 URL : (ws://호스트:포트번호/컨텍스트루트/ChatingServer)


// 즉, 여기가 채팅서버

public class ChatServer {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen // 클라이언트 접속 시 실행메서드, clients컬렉션에 세션을 추가
	public void onOpen(Session session) {
		
		clients.add(session); // 세션추가
		System.out.println("웹소켓 연결 : " + session.getId());
		
	} // onOpen-end
	
	@OnMessage // 클라이언트로부터 메세지를 받았을 때 실행하는 메서드, 클라이언트가 보낸 메세지와 세션이 매개변수로 넘어오게 된다.
	public void onMessage(String message, Session session) throws IOException {
		
		System.out.println("메세지 전송 : " + session.getId() + " : " + message);
		
		synchronized(clients) {
			
			for (Session client : clients) { // 모든클라이언트에게 메세지 전달
				
				if(!client.equals(session)) {
					// 단, 메세지를 보낸 클라이언트는 제외
					client.getBasicRemote().sendText(message);					
				}
				
			} //for-end
			 
		}// synchronized-end
		
	}// onMessage-end
	
	
	
	
	@OnClose // 클라이언트와 연결이 끊기면 실행되는 메서드
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	} // onClose-end
	
	
	
	@OnError // 에러발생 시 실행되는 메서드
	public void onError(Throwable e) {
		System.out.println("에러발생");
		e.printStackTrace();
	} // onError-end
}
