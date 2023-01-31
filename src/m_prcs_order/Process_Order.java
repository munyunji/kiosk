package m_prcs_order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import c_menu.Order;
import c_menu.SelectedMenu;
import m_dashboard.SalesNStockVO;
import m_login.KioskorderVO;
import m_login.Logout_Success;
import m_login.ManagerDAO;
import m_login.Manager_Main;

public class Process_Order extends JFrame {
	Manager_Main mngMain;
	
	private JPanel contentPane;
	private JFormattedTextField tfDate;
	private JTable tbNew;
	private JTable tbCook;
	private JTable tbWait;

	String[] columnsNew = { "주문 번호", "주문 일시", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호" };
	String[] columnsCook = { "O/N", "주문일시", "메뉴", "수량", "포장여부" };
	String[] columnsWait = { "O/N", "주문일시", "메뉴", "수량", "포장여부" };
	DefaultTableModel modelNew;
	DefaultTableModel modelCook;
	DefaultTableModel modelWait;

	public Process_Order(Manager_Main mngMain, int mainWidth, int mainHeight) {
		super("주문 현황");
		
		this.mngMain = mngMain;

		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.setBackground(new Color(245, 245, 250));

		JLabel lbNew = new JLabel("신규 주문");
		lbNew.setHorizontalAlignment(SwingConstants.CENTER);
		lbNew.setFont(new Font("굴림", Font.BOLD, 17));
		lbNew.setBounds(50, 32, 116, 33);
		contentPane.add(lbNew);

		//DateFormat format = DateFormat.FULL;
		tfDate = new JFormattedTextField(new DateFormatter());
		tfDate.setValue(new Date());
		tfDate.setEditable(false);
		tfDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfDate.setFont(new Font("굴림", Font.BOLD, 15));
		tfDate.setColumns(10);
		tfDate.setBounds(209, 32, 157, 33);
		contentPane.add(tfDate);

		JButton bMain = new JButton("< 메인");
		bMain.setBounds(836, 32, 102, 30);
		bMain.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bMain);

		JPanel pBgNew = new JPanel();
		pBgNew.setBackground(Color.WHITE);
		pBgNew.setBorder(new LineBorder(new Color(170, 170, 170), 1, true));
		pBgNew.setBounds(50, 87, 888, 285);
		pBgNew.setLayout(null);
		contentPane.add(pBgNew);

		// "주문번호", "주문일시", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호"
		modelNew = new DefaultTableModel(columnsNew, 0); // 행 0개
		tbNew = new JTable(modelNew);
		tbNew.setFillsViewportHeight(true);
		// tbNew.setBackground(Color.BLACK);
		tbNew.setRowHeight(25);
		// tbNew.setGridColor(Color.WHITE);
		tbNew.setShowGrid(false);
		tbNew.setIntercellSpacing(new Dimension(10, 0));
		tbNew.setFont(new Font("고딕", Font.BOLD, 13));

		tbNew.getColumn(columnsNew[0]).setPreferredWidth(20);
		tbNew.getColumn(columnsNew[1]).setPreferredWidth(110); // 주문일시
		tbNew.getColumn(columnsNew[3]).setPreferredWidth(30);  // 수량
		tbNew.getColumn(columnsNew[4]).setPreferredWidth(60);  // 가격
		tbNew.getColumn(columnsNew[5]).setPreferredWidth(50);  // 포장여부
		tbNew.getColumn(columnsNew[6]).setPreferredWidth(70);  // 합계

		JScrollPane jspNew = new JScrollPane(tbNew, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspNew.setBounds(12, 10, 864, 265);
		jspNew.setBorder(new LineBorder(new Color(240, 240, 240)));
		pBgNew.add(jspNew);

		JLabel lbCooking = new JLabel("조리 중..");
		lbCooking.setHorizontalAlignment(SwingConstants.CENTER);
		lbCooking.setFont(new Font("굴림", Font.BOLD, 17));
		lbCooking.setBounds(50, 402, 116, 33);
		contentPane.add(lbCooking);

		// "O/N", "주문일시", "메뉴", "수량", "포장여부"
		modelCook = new DefaultTableModel(columnsCook, 0); // 행 0개
		tbCook = new JTable(modelCook);
		tbCook.setFillsViewportHeight(true);
		// tbCook.setBackground(Color.BLACK);
		tbCook.setRowHeight(25);
		// tbCook.setGridColor(Color.WHITE);
		tbCook.setShowGrid(false);
		tbCook.setIntercellSpacing(new Dimension(10, 0));
		tbCook.setFont(new Font("고딕", Font.BOLD, 13));

		tbCook.getColumn(columnsCook[0]).setPreferredWidth(8);
		tbCook.getColumn(columnsCook[3]).setPreferredWidth(20);
		tbCook.getColumn(columnsCook[4]).setPreferredWidth(40);
		
		JScrollPane jspCook = new JScrollPane(tbCook,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspCook.setBounds(50, 452, 411, 184);
		jspCook.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(jspCook);

		JButton bFinish = new JButton("조리 완료");
		bFinish.setBounds(201, 646, 102, 30);
		bFinish.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bFinish);

		JLabel lbWaiting = new JLabel("조리 대기 목록");
		lbWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		lbWaiting.setFont(new Font("굴림", Font.BOLD, 17));
		lbWaiting.setBounds(507, 402, 150, 33);
		contentPane.add(lbWaiting);

		// "O/N", "주문일시", "메뉴", "수량", "포장여부"			
		modelWait = new DefaultTableModel(columnsWait, 0); // 행 0개
		tbWait = new JTable(modelWait);
		tbWait.setFillsViewportHeight(true);
		// tbWait.setBackground(Color.BLACK);
		tbWait.setRowHeight(25);
		// tbWait.setGridColor(Color.WHITE);
		tbWait.setShowGrid(false);
		tbWait.setIntercellSpacing(new Dimension(10, 0));
		tbWait.setFont(new Font("고딕", Font.BOLD, 13));

		tbWait.getColumn(columnsWait[0]).setPreferredWidth(8);
		tbWait.getColumn(columnsWait[3]).setPreferredWidth(30);
		tbWait.getColumn(columnsWait[4]).setPreferredWidth(40);

		JScrollPane jspWait = new JScrollPane(tbWait,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspWait.setBounds(507, 452, 431, 218);
		jspWait.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(jspWait);

		JButton bExit = new JButton("종료");
		bExit.setBounds(836, 700, 102, 30);
		bExit.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bExit);

		// 화면 출력
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2, 
				  mainWidth, mainHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);

		// 매니저 메인 화면으로 이동
		bMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// mngMain.setVisible(true);
				dispose();
			}
		});
		// 종료 - 로그아웃
		bExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showOptionDialog(getParent(), "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				// int res = JOptionPane.showConfirmDialog(getParent(),
				// "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (res == 0) { // yes
					dispose();
					//소켓 닫음
					mngMain.waitOrder.setLogout(true);
					mngMain.waitOrder.closeServer();
					mngMain.dispose();
					new Logout_Success(mngMain, mainWidth, mainHeight);
				}
			}
		});
		// 조리완료 버튼을 누르면 
		// 1. 해당 주문은 DB(kioskorder)에 저장
		// 1-1. 재고 감소, 매출 증가를 DB (SalesNStock)에 저장
		// 2. 해당 주문은 orderList에서 삭제
		// 3. 다음 대기 주문이 '조리 중'화면에 출력
		bFinish.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 조리 중인 order는 항상 index 0
				// 1. 해당 주문은 DB(kioskorder)에 저장
				KioskorderVO kvo = setKioskorderVO(mngMain.waitOrder.getOrderList().get(0));
				int result = ManagerDAO.getOrderInsert(kvo);
				if (result <= 0) { 
					JOptionPane.showMessageDialog(getParent(),  
							"완료주문 저장에 실패했습니다.", "DB INSERT failure", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// 1-1. 재고 감소, 매출 증가를 DB (SalesNStock)에 저장
				SalesNStockVO ssvo = setSalesNStock(mngMain.waitOrder.getOrderList().get(0));
				result = ManagerDAO.getSalesNStockUpdate(ssvo);
				if (result <= 0) { 
					JOptionPane.showMessageDialog(getParent(),  
							"매출과 재고수량 갱신에 실패했습니다.", "DB Update failure", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// 2. orderList에서 삭제
				mngMain.waitOrder.removeOrder(0);
				
				// 신규주문까지 조리완료된 경우
				if (mngMain.waitOrder.getOrderList().size() == 0) {
					clearTable(modelNew);
					clearTable(modelCook);				
				}
				else {
					// 3. 다음 대기 주문이 '조리 중'화면에 출력
					outputCookingTable(mngMain.waitOrder.getOrderList().get(0));
					//    대기 목록 출력
					outputWaitingTable(mngMain.waitOrder.getOrderList());
				}
			}
		});
		
		// 대기목록 테이블에서 하나의 주문을 더블클릭하면 상세내용을 새창에 출력한다
		tbWait.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String str = (String)modelWait.getValueAt(tbWait.getSelectedRow(), 0);				
				int orderNum = Integer.parseInt(str.trim());
				
				ArrayList<Order> orderList = mngMain.waitOrder.getOrderList();
				int orderListIdx = -1;
				
				for (int idx = 0; idx < orderList.size(); idx++) {
					if (orderList.get(idx).getOrderNumber() == orderNum) {
						orderListIdx = idx;
						break;
					}
				}
				
				if (orderListIdx < 0) {
					System.out.println("일치하는 Order Number가 Order List 에 없습니다.");
					return;
				}
				
				new Show_Order(mngMain, mainWidth-100, mainHeight-400, orderListIdx);
			}
		});
	} //end of 생성자
	
	// orderList에 있는 하나의 order를 DB에 저장하기 위해 KioskorderVO에 값을 설정
	public KioskorderVO setKioskorderVO(Order order) {
		KioskorderVO kvo = new KioskorderVO();
		
		kvo.setOrder_date(order.getDate());
		kvo.setOrder_no(order.getOrderNumber());
		kvo.setPhone(order.getPhone());
		kvo.setTotal(order.getTotal());
		kvo.setTakeaway(order.getTakeaway());
		
		String info = ""; 		
		ArrayList<SelectedMenu> sList = order.getValue();
		for (SelectedMenu sMenu : sList) {
			info = info.concat(sMenu.getM_id() + "-" + sMenu.getNumber() + ",");
		}
		kvo.setOrder_list(info);
		
		return kvo;
	}

	// 해당 주문의 메뉴 재고 감소와 매출 증가 저장을 위해 setSalesNStockVO에 값 설정
	private SalesNStockVO setSalesNStock(Order order) {
		// 매출과 재고 디비값을 가져옴 
		SalesNStockVO currVo = mngMain.getSalesNStockToday();
		
		// 매출액을 더함
		currVo.setDay_total(currVo.getDay_total() + order.getTotal());
		
		// 현재 주문된 메뉴의 m_id에 대한 재고수량을 주문수량만큼 감소하여 저장
		for (SelectedMenu sMenu : order.getValue()) {
			int m_id = sMenu.getM_id();
			int c_id = m_id/100;
			currVo.setStock(c_id, m_id, currVo.getStock(c_id, m_id) - sMenu.getNumber());
		}
		
		return currVo;
	}
	
	// 신규 주문 내용을 테이블에 출력한다
	public void outputNewOrderTable(Order order) {

		clearTable(modelNew);

		ArrayList<SelectedMenu> menuList = order.getValue();

		String[] record = new String[columnsNew.length];
		// 테이블에 출력
		for (int i = 0; i < menuList.size(); i++) {
			// "주문 번호", "주문 일시", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호"
			record[2] = menuList.get(i).getM_name();
			record[3] = " " + String.valueOf(menuList.get(i).getNumber());
			record[4] = " " + String.valueOf(menuList.get(i).getPrice());
			if (i == 0) {
				record[0] = " " + String.valueOf(order.getOrderNumber());
				record[1] = " " + order.getDate();
				record[5] = order.getTakeaway();
				record[6] = " " + String.valueOf(order.getTotal());
				record[7] = order.getPhone();
			} else {
				record[0] = " ";
				record[1] = " ";
				record[5] = " ";
				record[6] = " ";
				record[7] = " ";
			}

			modelNew.addRow(record);
		}
	}

	public void outputCookingTable(Order order) {
		clearTable(modelCook);

		String[] record = new String[columnsCook.length];
		ArrayList<SelectedMenu> menuList = order.getValue();

		for (int i = 0; i < menuList.size(); i++) {
			// "O/N", "주문일시", "메뉴", "수량", "포장여부"
			record[2] = menuList.get(i).getM_name();
			record[3] = " " + String.valueOf(menuList.get(i).getNumber());
			if (i == 0) {
				record[0] = " " + String.valueOf(order.getOrderNumber());
				record[1] = "" + order.getDate();
				record[4] = order.getTakeaway();
			} else {
				record[0] = " ";
				record[1] = " ";
				record[4] = " ";
			}

			modelCook.addRow(record);
		}
	}

	public void outputWaitingTable(ArrayList<Order> orderList) {

		clearTable(modelWait);
		String[] record = new String[columnsWait.length];
		Order order;

		for (int i = 0; i < orderList.size() - 1; i++) {
			order = orderList.get(i);
			ArrayList<SelectedMenu> menuList = order.getValue();

//				for (int j = 0; j < menuList.size(); j++) {
//					// "O/N", "주문일시", "메뉴", "수량", "포장여부"
//					record[2] = menuList.get(j).getM_name();
//					record[3] = " " + String.valueOf(menuList.get(j).getNumber());
//					if (j == 0) {
//						record[0] = " " + String.valueOf(order.getOrderNumber());
//						record[1] = " " + order.getDate();
//						record[4] = order.getTakeaway();						
//					} else {
//						record[0] = " ";
//						record[1] = " ";
//						record[4] = " ";
//					}
//					modelWait.addRow(record);
//				}

			// 첫번째 메뉴만 나타내기
			// "O/N", "주문일시", "메뉴", "수량", "포장여부"
			record[0] = " " + String.valueOf(order.getOrderNumber());
			record[1] = "" + order.getDate();
			if (menuList.size() > 1) {
				record[2] = menuList.get(0).getM_name() + " 외 " + (menuList.size() - 1);
				record[3] = " " + String.valueOf(menuList.get(0).getNumber()) + "  + ...";
			} else {
				record[2] = menuList.get(0).getM_name();
				record[3] = " " + String.valueOf(menuList.get(0).getNumber());
			}
			record[4] = order.getTakeaway();
			modelWait.addRow(record);
		} // end of for
	}

	public void clearTable(DefaultTableModel model) {
		// 한줄 삭제될때마다 RowCount는 변하므로 처음의 값을 써야한다.
		int rowCnt = model.getRowCount();
		// 테이블에 출력된 내용 모두 삭제
		for (int i = 0; i < rowCnt; i++)
			model.removeRow(0);
	}
}
