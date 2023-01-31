package m_login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import m_dashboard.SalesNStockVO;
import m_dashboard.Statistics;
import m_edit_menu.Edit_Menu;
import m_prcs_order.Process_Order;
import m_search.Search_Orders;

public class Manager_Main extends JFrame {
	// 전달된 주문을 처리하는 스레드
	public WaitOrder waitOrder;
	// 신규주문, 조리중인 주문, 대기중인 주문들을 출력하는 화면 (주문 현황)
	public Process_Order procOrder;
	// 대시보드 화면
	//Statistics stat;
	
	JButton bOrder, bSrch, bEdit, bStat, bExit;	
	JPanel contentPane;
	 
	public Manager_Main(int mainWidth, int mainHeight) {
		super("매니저 메인");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(245, 245, 250));
		
		bExit = new JButton("종료");
		bExit.setBounds(792, 674, 119, 32);
		bExit.setFont(new Font("고딕", Font.BOLD, 13));
		contentPane.add(bExit);
		
		bOrder = new JButton("주문 현황");
		bOrder.setBounds(214, 251, 174, 32);
		//bOrder.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		bOrder.setFont(new Font("고딕", Font.BOLD, 12));
		bOrder.setBorderPainted(false);
		contentPane.add(bOrder);
		
		bSrch = new JButton("지난 주문 검색");
		bSrch.setBounds(600, 251, 174, 32);
		bSrch.setFont(new Font("고딕", Font.BOLD, 12));
		bSrch.setBorderPainted(false);
		contentPane.add(bSrch);
		
		bEdit = new JButton("메뉴 편집");
		bEdit.setBounds(214, 546, 174, 32);
		bEdit.setFont(new Font("고딕", Font.BOLD, 12));
		bEdit.setBorderPainted(false);
		contentPane.add(bEdit);
		
		bStat = new JButton("대시 보드");
		bStat.setBounds(600, 546, 174, 32);
		bStat.setFont(new Font("고딕", Font.BOLD, 12));
		bStat.setBorderPainted(false);
		contentPane.add(bStat);
		
		JLabel lbOrder = new JLabel("");
		lbOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lbOrder.setBounds(128, 89, 342, 245);
		lbOrder.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));	
		ImageIcon imgOrder = new ImageIcon("src/img_login/order-lb.jpg");
		lbOrder.setIcon(imgOrder);
		contentPane.add(lbOrder);
			
		JLabel lbSrch = new JLabel("");
		lbSrch.setHorizontalAlignment(SwingConstants.CENTER);
		lbSrch.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		lbSrch.setBounds(513, 89, 342, 245);	
		ImageIcon imgSrch = new ImageIcon("src/img_login/search-lb.jpg");
		lbSrch.setIcon(imgSrch);
		contentPane.add(lbSrch);
		
		JLabel lbEdit = new JLabel("");
		lbEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lbEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		lbEdit.setBounds(128, 376, 342, 245);
		ImageIcon imgEdit = new ImageIcon("src/img_login/edit-lb.jpg");
		lbEdit.setIcon(imgEdit);
		contentPane.add(lbEdit);
		
		JLabel lbStat = new JLabel("");
		lbStat.setHorizontalAlignment(SwingConstants.CENTER);
		lbStat.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		lbStat.setBounds(513, 376, 342, 245);
		ImageIcon imgStat = new ImageIcon("src/img_login/stat-lb.jpg");
		lbStat.setIcon(imgStat);
		contentPane.add(lbStat);	
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2 - mainWidth/2, ds.height/2 - mainHeight/2,
				  mainWidth, mainHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// 소켓생성, 주문 메시지 기다림
		startWaitingOrder();
				
		// '주문 현황' 화면을 생성
		procOrder = new Process_Order(this, mainWidth, mainHeight);
		
		// 오늘날짜로 생성된 재고수량DB 값이 없으면 초기값을 입력받는다.
		if (getSalesNStockToday() == null) {
			//stat = new Statistics(this, mainWidth, mainHeight);
			new Statistics(this, mainWidth, mainHeight, true);
			JOptionPane.showMessageDialog(getParent(), 
				"초기 재고수량을 입력하세요.", "초기 재고수량 없음", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		// 사진이나 버튼 클릭시 해당 화면 생성 
		bOrder.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				procOrder.setVisible(true);
				//setVisible(false);
			}
		});
		lbOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				procOrder.setVisible(true);
				//setVisible(false);
			}
		});
		
		bSrch.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				new Search_Orders(Manager_Main.this, mainWidth, mainHeight);
				//setVisible(false);
			}
		});
		lbSrch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Search_Orders(Manager_Main.this, mainWidth, mainHeight);
				//setVisible(false);
			}
		});
		
		bEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Edit_Menu(Manager_Main.this, mainWidth, mainHeight);
				//setVisible(false);
			}
		});
		lbEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Edit_Menu(Manager_Main.this, mainWidth, mainHeight);
				//setVisible(false);
			}
		});
		
		bStat.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Statistics(Manager_Main.this, mainWidth, mainHeight, false);
				//setVisible(false);
			}
		});
		lbStat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Statistics(Manager_Main.this, mainWidth, mainHeight, false);
				//setVisible(false);
			}
		});
			
		bExit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// int res = JOptionPane.showOptionDialog(getParent(),
				// 				"로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION,
				//              JOptionPane.Question_MESSAGE, icon, options, null);
				int res = JOptionPane.showConfirmDialog(getParent(), 
							"로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (res == 0) { // yes
					//소켓 닫음
					waitOrder.setLogout(true);
					waitOrder.closeServer();
					dispose();
					new Logout_Success(Manager_Main.this, mainWidth, mainHeight);
				}
			}
		});
	}
	
	void startWaitingOrder() {
		waitOrder = new WaitOrder(this);
		// Manager_Main 종료시 스레드 종료가 안됨..
		waitOrder.setDaemon(true);
		waitOrder.start();
	}
	
	public String getCurrDate() {
		Calendar curr = Calendar.getInstance();

		String yStr = "";
		String mStr = "";
		String dStr = "";

		yStr = String.valueOf(curr.get(Calendar.YEAR));
		int month = curr.get(Calendar.MONTH) + 1;
		int date = curr.get(Calendar.DATE);

		if (month >= 1 && month <= 9)
			mStr = "0" + month;
		else
			mStr = String.valueOf(month);

		if (date >= 1 && date <= 9)
			dStr = "0" + date;
		else
			dStr = String.valueOf(date);

		return (yStr + mStr + dStr);
	}
	
	public SalesNStockVO getSalesNStockToday() {
		List<SalesNStockVO> ssvoList = ManagerDAO.getSalesNStockList(
										"res", 
										getCurrDate()+" 00:00:00", 
										getCurrDate()+" 23:59:59");
		if (ssvoList.size() > 0) 
			return ssvoList.get(ssvoList.size()-1);
		else 
			return null;	
	}
}
