package m_login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import c_menu.Order;
import c_menu.SelectedMenu;

public class WaitOrder extends Thread {
	Manager_Main mngMain;

	ServerSocket ss = null;
	Socket socket = null;
	ObjectInputStream in;
	// ObjectOutputStream out;

	// * 전체 주문을 받은 순서대로 저장하는 리스트
	// orderList에서 index 0 은 '조리중'인 Order이며,  
	// 마지막 index는 신규 주문이다.
	// 조리완료 버튼이 눌러지면 index 0 order는 
	// DB(kioskorder)에 저장후, orderList에서 삭제된다.
	private ArrayList<Order> orderList;
	int orderNumber;
	
	// 시스템 로그아웃을 한 경우에 true
    boolean logout;
    
	public WaitOrder(Manager_Main mngMain) {
		this.mngMain = mngMain;
		orderList = new ArrayList<>();
		orderNumber = 0;
		logout = false;
	}
	
	public ArrayList<Order> getOrderList() {
		return orderList;
	}

//	public boolean isLogout() {
//		return logout;
//	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(7078);
			System.out.println("Server Start !");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			try {
				// 로그아웃 시에는 while문 탈출, 스레드 종료
				if(logout == true) {
					System.out.println("escape while~");
					break;
				} else System.out.println("logout is " + logout);
				
				socket = ss.accept();
				System.out.println("SS accepts");
				try {
					in = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Object obj = in.readObject();
					if (obj != null) {
						processOrder((Order)obj);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end of while
	}

	private void processOrder(Order order) {
		// 주문 접수 날짜와 시간을 저장한다
		Calendar curr = Calendar.getInstance();		
		String str = curr.get(Calendar.YEAR) + "/" +
                     (curr.get(Calendar.MONTH)+1) + "/" +
		             curr.get(Calendar.DATE) + " " +
		             curr.get(Calendar.HOUR_OF_DAY) + ":" +  
				     curr.get(Calendar.MINUTE) + ":" + 
				     curr.get(Calendar.SECOND);	
		order.setDate(str);  
		
		// 주문 번호 저장
		order.setOrderNumber(++orderNumber);
		
		// orderList에 추가 
		orderList.add(order);

		System.out.println("Received " + orderNumber + "th order !");

		// 지울것 -----------------
		System.out.println("=====================");
		System.out.println(order.getOrderNumber());
		int i = 0;
		for (SelectedMenu sm : order.getValue()) {
			System.out.println("* Menu: " + (++i));
			System.out.println(sm.getM_id());
			System.out.println(sm.getM_name());
			System.out.println(sm.getNumber());
			System.out.println(sm.getPrice());
		}
		System.out.println(order.getTakeaway());
		System.out.println(order.getPhone());
		System.out.println(order.getTotal());		
		// 지울것 -----------------

		// 신규 주문을 테이블에 출력한다 
		// orderList의 가장 마지막에 있는 Order가 신규 주문이다
		mngMain.procOrder.outputNewOrderTable(orderList.get(orderList.size() - 1));
		// 조리 중인 주문은 항상 orderList index 0 (가장 먼저 받은 주문)이다.
		mngMain.procOrder.outputCookingTable(orderList.get(0));
		// 대기 중인 주문들을 테이블에 출력한다
		mngMain.procOrder.outputWaitingTable(orderList);
		
		// '주문 현황' 화면이 나타나게 한다
		mngMain.procOrder.setVisible(true);
	}
	
	public void removeOrder(int index) {
		orderList.remove(index);
	}
	
	// logout 할 때, Manager_Main 화면 닫기 전에
	public void closeServer() {
		System.out.println("closeServer()");
		
		try {
			ss.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (socket == null)  
			return;
		
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
