package m_search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import c_menu.MenuVO;
import m_login.KioskorderVO;
import m_login.Logout_Success;
import m_login.ManagerDAO;
import m_login.Manager_Main;

public class Search_Orders extends JFrame {
	Manager_Main mngMain;
	private JPanel contentPane;
	private JTextField tfStartDate;
	private JTextField tfStartTime;
	private JTextField tfEndDate;
	private JTextField tfEndTime;
	private JTable tbRes;
	private JTextField tfTotal;
	private JTextField tfSelectedTotal;

	private String[] columnNames = { "No.", "주문 번호", "주문 일시", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호" };
	private DefaultTableModel model;

	public Search_Orders(Manager_Main mngMain, int mainWidth, int mainHeight) {
		super("지난 주문 확인");
		
		this.mngMain = mngMain;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbSearchPastOrders = new JLabel("지난 주문 검색");
		lbSearchPastOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lbSearchPastOrders.setFont(new Font("굴림", Font.BOLD, 20));
		lbSearchPastOrders.setBounds(359, 24, 200, 33);
		contentPane.add(lbSearchPastOrders);

		JButton bMain = new JButton("<  메인");
		bMain.setBounds(842, 24, 102, 30);
		bMain.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bMain);

		JLabel lbStartDate = new JLabel("Start Date");
		lbStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbStartDate.setFont(new Font("굴림", Font.BOLD, 13));
		lbStartDate.setBounds(59, 86, 93, 25);
		contentPane.add(lbStartDate);

		tfStartDate = new JTextField(" " + mngMain.getCurrDate());
		tfStartDate.setColumns(10);
		tfStartDate.setBounds(152, 86, 157, 25);
		tfStartDate.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(tfStartDate);

		JLabel lbStartTime = new JLabel("Time");
		lbStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		lbStartTime.setFont(new Font("굴림", Font.BOLD, 13));
		lbStartTime.setBounds(337, 86, 71, 25);
		contentPane.add(lbStartTime);

		tfStartTime = new JTextField(" 00:00:00");
		tfStartTime.setColumns(10);
		tfStartTime.setBounds(408, 86, 157, 25);
		tfStartTime.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(tfStartTime);

		JLabel lbEndDate = new JLabel("End Date");
		lbEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbEndDate.setFont(new Font("굴림", Font.BOLD, 13));
		lbEndDate.setBounds(59, 134, 93, 25);
		contentPane.add(lbEndDate);

		tfEndDate = new JTextField(" " + mngMain.getCurrDate());
		tfEndDate.setColumns(10);
		tfEndDate.setBounds(152, 134, 157, 25);
		tfEndDate.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(tfEndDate);

		JLabel lbEndTime = new JLabel("Time");
		lbEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		lbEndTime.setFont(new Font("굴림", Font.BOLD, 13));
		lbEndTime.setBounds(337, 134, 71, 25);
		contentPane.add(lbEndTime);

		tfEndTime = new JTextField(" 23:59:59");
		tfEndTime.setColumns(10);
		tfEndTime.setBounds(408, 134, 157, 25);
		tfEndTime.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(tfEndTime);

		JLabel lbSelectMenu = new JLabel("Select Menu");
		lbSelectMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelectMenu.setFont(new Font("굴림", Font.BOLD, 13));
		lbSelectMenu.setBounds(604, 86, 113, 25);
		contentPane.add(lbSelectMenu);

		JComboBox cbMenu = new JComboBox(getMenuNameAll());
		cbMenu.setBounds(720, 87, 167, 24);
		contentPane.add(cbMenu);

		JButton bSearch = new JButton("검색");
		bSearch.setBounds(785, 135, 105, 30);
		bSearch.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bSearch);

		model = new DefaultTableModel(columnNames, 0); // 행 0개
		tbRes = new JTable(model);
		tbRes.setFillsViewportHeight(true);
		// tbRes.setBackground(Color.BLACK);
		tbRes.setRowHeight(20);
		// tbRes.setGridColor(Color.WHITE);
		tbRes.setShowGrid(false);
		tbRes.setIntercellSpacing(new Dimension(10, 0));
		tbRes.setFont(new Font("고딕", Font.BOLD, 12));

		tbRes.getColumn(columnNames[0]).setPreferredWidth(10); // No.
		tbRes.getColumn(columnNames[1]).setPreferredWidth(25); // 주문번호		
		tbRes.getColumn(columnNames[2]).setPreferredWidth(105);// 주문일시
		tbRes.getColumn(columnNames[4]).setPreferredWidth(30); // 수량
		tbRes.getColumn(columnNames[5]).setPreferredWidth(50); // 가격
		tbRes.getColumn(columnNames[6]).setPreferredWidth(50); // 포장여부
		tbRes.getColumn(columnNames[7]).setPreferredWidth(60); // 합계

		JScrollPane jsp = new JScrollPane(tbRes, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(39, 188, 905, 440);
		// jsp.setBorder(new LineBorder(new Color(240, 240, 240)));
		contentPane.add(jsp);

		JLabel lbTotal = new JLabel("매출 합계 :");
		lbTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbTotal.setFont(new Font("굴림", Font.BOLD, 15));
		lbTotal.setBounds(629, 644, 102, 33);
		contentPane.add(lbTotal);

		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(743, 645, 200, 33);
		tfTotal.setEditable(false);
		tfTotal.setFont(new Font("굴림", Font.BOLD, 15));
		tfTotal.setBackground(Color.WHITE);
		contentPane.add(tfTotal);
		
		JLabel lbSelectedTotal = new JLabel("선택 메뉴 매출 합계 :");
		lbSelectedTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelectedTotal.setFont(new Font("굴림", Font.BOLD, 15));
		lbSelectedTotal.setBounds(246, 644, 180, 33);
		contentPane.add(lbSelectedTotal);
		
		tfSelectedTotal = new JTextField();
		tfSelectedTotal.setColumns(10);
		tfSelectedTotal.setBounds(425, 644, 180, 33);
		tfSelectedTotal.setEditable(false);
		tfSelectedTotal.setFont(new Font("굴림", Font.BOLD, 15));
		tfSelectedTotal.setBackground(Color.WHITE);
		contentPane.add(tfSelectedTotal);
		
		JButton bExit = new JButton("종료");
		bExit.setBounds(842, 702, 102, 30);
		bExit.setFont(new Font("굴림", Font.BOLD, 13));
		contentPane.add(bExit);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2,
				  mainWidth, mainHeight);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		// 메인으로 가기 (뒤로가기)
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
				// int res = JOptionPane.showOptionDialog(getParent(), "로그아웃 하시겠습니까?", "로그아웃",
				// JOptionPane.YES_NO_OPTION,
				// JOptionPane.QUESTION_MESSAGE, null, null, null);
				int res = JOptionPane.showConfirmDialog(getParent(), "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (res == 0) { // yes
					dispose();
					// 소켓 닫음
					mngMain.waitOrder.setLogout(true);
					mngMain.waitOrder.closeServer();
					mngMain.dispose();
					new Logout_Success(mngMain, mainWidth, mainHeight);
				}
			}
		});
		// 검색
		bSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] menuInfo = {"", ""};
				int salesTotal = 0;
				
				String sDate = tfStartDate.getText().trim();
				String sTime = tfStartTime.getText().trim();
				String eDate = tfEndDate.getText().trim();
				String eTime = tfEndTime.getText().trim();

				if (sDate.length() != 8 || eDate.length() != 8) {
					JOptionPane.showMessageDialog(getParent(), "날짜는 8자리로 입력해 주세요", "날짜입력 확인",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (sTime.length() == 0)
					sTime = "00:00:00";
				else if (sTime.length() != 8) {
					JOptionPane.showMessageDialog(getParent(), "비우거나 시간 입력 양식에 맞춰주세요", "시간입력 확인",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (eTime.length() == 0)
					eTime = "23:59:59";
				else if (eTime.length() != 8) {
					JOptionPane.showMessageDialog(getParent(), "비우거나 시간 입력 양식에 맞춰주세요", "시간입력 확인",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				SearchVO svo = new SearchVO();
				svo.setStart_date(sDate + " " + sTime);
				svo.setEnd_date(eDate + " " + eTime);

				// 검색 기간에 해당하는 Order들을 가져온다.
				List<KioskorderVO> kvoList = ManagerDAO.getOrderList(svo);
				if (kvoList.size() < 1) {
					JOptionPane.showMessageDialog(getParent(), 
							"검색기간에 해당하는 주문정보가 없습니다.", "주문정보 없음",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// 검색할 메뉴가 있는 경우
				if (cbMenu.getSelectedIndex() > 0) {
					String item = (String) cbMenu.getSelectedItem();
					menuInfo = item.trim().split("   ");

					// order_list에서 m_id (mInfo[0])을 포함하는 order들만 찾는다.				
					List<KioskorderVO> list = new ArrayList<>();
					for (KioskorderVO kvo : kvoList) {
						if (kvo.getOrder_list().contains(menuInfo[0])) {
							list.add(kvo);
						}
					}
			
					System.out.println("m_id: "+menuInfo[0]+"(" + menuInfo[1]+ "), num: "+list.size());
					
					// 테이블에 출력
					salesTotal = outputSearchedOrder(list, menuInfo[0]);
				}
				else // 테이블에 출력
					salesTotal = outputSearchedOrder(kvoList, "");
				
				// 매출합계 출력
				tfTotal.setText("  " + String.valueOf(salesTotal));
			}
		});
	}

	// MENU DB의 모든 m_id, m_name 을 가져온다.
	private String[] getMenuNameAll() {

		List<MenuVO> list = ManagerDAO.getMenuAll();
		String[] mInfo = new String[list.size() + 1];

		mInfo[0] = "  메뉴를 선택하세요";
		for (int i = 0; i < list.size(); i++) {
			mInfo[i + 1] = "  " + list.get(i).getM_id() + "   " + list.get(i).getM_name();
		}

		return mInfo;
	}

	private int outputSearchedOrder(List<KioskorderVO> kvoList, String m_id) {
		
		mngMain.procOrder.clearTable(model);
		
		// "No.", "주문 번호", "주문 일시", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호"
		String[] record = new String[columnNames.length];
		
		int num = 0;
		int salesTotal = 0, menuTotal = 0;
		
		for (KioskorderVO kvo : kvoList) {	
			String[] sMenu = kvo.getOrder_list().split(",");

			int i = 0;
			for (String menu : sMenu) { 
				// menu : 'm_id'-'개수'
				String[] mInfo = menu.split("-"); //m_id,개수로 구분하여 저장
				
				int mPrice = 0;
				
				// m_id로 해당 메뉴정보 가져옴				
				MenuVO mvo = ManagerDAO.getMenuOne(mInfo[0]);
				if (mvo != null) {
					record[3] = mvo.getM_name();      //메뉴
					record[5] = " " + mvo.getPrice(); //가격
					
					if (m_id.length() > 0 && mInfo[0].equals(m_id)) {
						mPrice = mvo.getPrice();
					}
				}
				menuTotal += mPrice * Integer.parseInt(mInfo[1]); // 가격 * 주문 개수
				
				record[4] = " " + mInfo[1]; // 수량(메뉴의 주문 개수)
						
				if (i == 0) {
					num++;
					salesTotal += kvo.getTotal();
					
					record[0] = " " + num;
					record[1] = " " + String.valueOf(kvo.getOrder_no()); //주문 번호
					record[2] = " " + kvo.getOrder_date();               //주문 일시
					record[6] = kvo.getTakeaway();                       //포장여부
					record[7] = " " + String.valueOf(kvo.getTotal());    //합계
					record[8] = kvo.getPhone();                          //전화번호
				} else {
					record[0] = " ";
					record[1] = " ";
					record[2] = " ";
					record[6] = " ";
					record[7] = " ";
					record[8] = " ";
				}

				model.addRow(record);
				i++;
			} // end of for(mInfo) : 하나의 주문에서 메뉴들
		} // end of for(kvoList) : 주문리스트
		
		// 선택 메뉴의 가격 합계 출력
		if (m_id.length() > 0)
			tfSelectedTotal.setText("  " + String.valueOf(menuTotal));
		else 
			tfSelectedTotal.setText("");
		
		return salesTotal;
	} 
}
