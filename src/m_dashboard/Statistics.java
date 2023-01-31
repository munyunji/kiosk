package m_dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import m_login.Logout_Success;
import m_login.ManagerDAO;
import m_login.Manager_Main;
import m_search.SearchVO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import c_menu.CategoryVO;
import c_menu.MenuVO;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Statistics extends JFrame {
	Manager_Main mngMain;

	private JComboBox cbPie;
	private String[] pieItems = { "  일별", "  월별" };
	PieChart pieChart;
	private JLabel lbS1, lbS2, lbS3, lbS4, lbS5, lbS6, lbS7, lbS8, lbS9, lbS10; 

	private JComboBox cbGraph;
	private String[] gItems = { "  일별", "  월별" };
	BarGraph barGraph;
	private JLabel lb1st, lb2nd, lb3rd, lb4th, lb5th;

	private JComboBox cbCategory;
	private String[] cItems;
	private JTable tbSales;
	private DefaultTableModel model;
	private String[] columnNames = { "No.", "ID", "메뉴 이름", "가격", "재고 수량" };

	private JComboBox cbAllOrCategory;
	private String[] sItems = { "  선택하세요", " 전체 메뉴 초기값", " 현재 카테고리 메뉴만" };
	private int allOrCategory = 0;
	private final int ALL_MENU = 1;
	private final int CATEGORY_MENU = 2;

	private JTextField tfPieBegin;
	private JTextField tfPieEnd;
	private JTextField tfGraphPeriod;

	private JTextField tfBatch;
	private JTextField tfOrderCnt;
	private JTextField tfSales;
	
	Color bgColor;

	public Statistics(Manager_Main mngMain, int mainWidth, int mainHeight, boolean isInit) {
		super("대시보드");

		this.mngMain = mngMain;
		
		getContentPane().setFont(new Font("굴림", Font.BOLD, 14));
		getContentPane().setLayout(null);
		
		bgColor = new Color(245, 245, 250);
		getContentPane().setBackground(bgColor);
		
		// 파이차트 영역
		JLabel lbSales = new JLabel("메뉴별 매출 현황");
		lbSales.setFont(new Font("굴림", Font.BOLD, 20));
		lbSales.setHorizontalAlignment(SwingConstants.CENTER);
		lbSales.setBounds(56, 67, 264, 46);
		getContentPane().add(lbSales);

		tfPieBegin = new JTextField("  " + mngMain.getCurrDate());
		tfPieBegin.setFont(new Font("굴림", Font.BOLD, 13));
		tfPieBegin.setBounds(80, 130, 102, 30);
		getContentPane().add(tfPieBegin);
		tfPieBegin.setColumns(10);

		tfPieEnd = new JTextField("  " + mngMain.getCurrDate());
		tfPieEnd.setFont(new Font("굴림", Font.BOLD, 13));
		tfPieEnd.setColumns(10);
		tfPieEnd.setBounds(214, 130, 102, 30);
		getContentPane().add(tfPieEnd);

		JLabel lbFromTo = new JLabel("~");
		lbFromTo.setHorizontalAlignment(SwingConstants.CENTER);
		lbFromTo.setFont(new Font("굴림", Font.BOLD, 15));
		lbFromTo.setBounds(182, 130, 32, 30);
		getContentPane().add(lbFromTo);

		cbPie = new JComboBox(pieItems);
		cbPie.setFont(new Font("굴림", Font.BOLD, 13));
		cbPie.setBackground(Color.WHITE);
		cbPie.setBounds(339, 130, 75, 30);
		getContentPane().add(cbPie);

		JButton bPieOk = new JButton("확인");
		bPieOk.setFont(new Font("굴림", Font.BOLD, 13));
		bPieOk.setBounds(339, 170, 75, 30);
		getContentPane().add(bPieOk);

		// Pie chart
		pieChart = new PieChart();
		pieChart.setBounds(56, 177, 259, 251);
		// pieChart.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(pieChart);
		
		lbS1 = new JLabel("메뉴 1");
		lbS1.setFont(new Font("고딕", Font.BOLD, 12));
		lbS1.setBounds(327, 238, 65, 30);
		getContentPane().add(lbS1);

		lbS2 = new JLabel("메뉴 2");
		lbS2.setFont(new Font("고딕", Font.BOLD, 12));
		lbS2.setBounds(327, 278, 65, 30);
		getContentPane().add(lbS2);
			
		lbS3 = new JLabel("메뉴 3");
		lbS3.setFont(new Font("고딕", Font.BOLD, 12));
		lbS3.setBounds(327, 318, 65, 30);
		getContentPane().add(lbS3);
		
		lbS4 = new JLabel("메뉴 4");
		lbS4.setFont(new Font("고딕", Font.BOLD, 12));
		lbS4.setBounds(327, 358, 65, 30);
		getContentPane().add(lbS4);
		
		lbS5 = new JLabel("메뉴 5");
		lbS5.setFont(new Font("고딕", Font.BOLD, 12));
		lbS5.setBounds(327, 398, 65, 30);
		getContentPane().add(lbS5);

		lbS6 = new JLabel("메뉴 6");
		lbS6.setFont(new Font("고딕", Font.BOLD, 12));
		lbS6.setBounds(404, 238, 65, 30);
		getContentPane().add(lbS6);
		
		lbS7 = new JLabel("메뉴 7");
		lbS7.setFont(new Font("고딕", Font.BOLD, 12));
		lbS7.setBounds(404, 278, 65, 30);
		getContentPane().add(lbS7);

		lbS8 = new JLabel("메뉴 8");
		lbS8.setFont(new Font("고딕", Font.BOLD, 12));
		lbS8.setBounds(404, 318, 65, 30);
		getContentPane().add(lbS8);
			
		lbS9 = new JLabel("메뉴 9");
		lbS9.setFont(new Font("고딕", Font.BOLD, 12));
		lbS9.setBounds(404, 358, 65, 30);
		getContentPane().add(lbS9);
		
		lbS10 = new JLabel("메뉴 10");
		lbS10.setFont(new Font("고딕", Font.BOLD, 12));
		lbS10.setBounds(404, 398, 65, 30);
		getContentPane().add(lbS10);	

		// 바 그래프 영역
		JLabel lbGrossSales = new JLabel("총매출 현황");
		lbGrossSales.setHorizontalAlignment(SwingConstants.CENTER);
		lbGrossSales.setFont(new Font("굴림", Font.BOLD, 20));
		lbGrossSales.setBounds(543, 67, 264, 46);
		getContentPane().add(lbGrossSales);

		// Bar graph
		barGraph = new BarGraph();
		barGraph.setBounds(479, 170, 450, 258);
		getContentPane().add(barGraph);

		lb1st = new JLabel("1 일");
		lb1st.setFont(new Font("굴림", Font.BOLD, 12));
		lb1st.setHorizontalAlignment(SwingConstants.CENTER);
		lb1st.setBounds(548, 428, 80, 30);
		getContentPane().add(lb1st);
		
		lb2nd = new JLabel("2 일");
		lb2nd.setHorizontalAlignment(SwingConstants.CENTER);
		lb2nd.setFont(new Font("굴림", Font.BOLD, 12));
		lb2nd.setBounds(623, 428, 80, 30);
		getContentPane().add(lb2nd);
		
		lb3rd = new JLabel("3 일");
		lb3rd.setHorizontalAlignment(SwingConstants.CENTER);
		lb3rd.setFont(new Font("굴림", Font.BOLD, 12));
		lb3rd.setBounds(698, 428, 75, 30);
		getContentPane().add(lb3rd);
		
		lb4th = new JLabel("4 일");
		lb4th.setHorizontalAlignment(SwingConstants.CENTER);
		lb4th.setFont(new Font("굴림", Font.BOLD, 12));
		lb4th.setBounds(773, 428, 75, 30);
		getContentPane().add(lb4th);
		
		lb5th = new JLabel("5 일");
		lb5th.setHorizontalAlignment(SwingConstants.CENTER);
		lb5th.setFont(new Font("굴림", Font.BOLD, 12));
		lb5th.setBounds(848, 428, 75, 30);
		getContentPane().add(lb5th);
		
		tfGraphPeriod = new JTextField(getInitDays());
		tfGraphPeriod.requestFocus();
		tfGraphPeriod.setFont(new Font("굴림", Font.BOLD, 13));
		tfGraphPeriod.setColumns(10);
		tfGraphPeriod.setBounds(528, 130, 312, 30);
		getContentPane().add(tfGraphPeriod);

		cbGraph = new JComboBox(gItems);
		cbGraph.setFont(new Font("굴림", Font.BOLD, 13));
		cbGraph.setBackground(Color.WHITE);
		cbGraph.setBounds(866, 130, 75, 30);
		getContentPane().add(cbGraph);

		JButton bGraphOk = new JButton("확인");
		bGraphOk.setFont(new Font("굴림", Font.BOLD, 13));
		bGraphOk.setBounds(866, 170, 75, 30);
		getContentPane().add(bGraphOk);

		JButton bMain = new JButton("<  메인");
		bMain.setFont(new Font("굴림", Font.BOLD, 13));
		bMain.setBounds(839, 27, 102, 30);
		getContentPane().add(bMain);

		JButton bExit = new JButton("종료");
		bExit.setFont(new Font("굴림", Font.BOLD, 13));
		bExit.setBounds(839, 705, 102, 30);
		getContentPane().add(bExit);

		// 전체 카테고리 정보를 콤보박스에 출력
		cbCategory = new JComboBox(getCategoryAll(cItems));
		cbCategory.setBounds(448, 495, 185, 35);
		cbCategory.setBackground(Color.WHITE);
		cbCategory.setFont(new Font("굴림", Font.BOLD, 14));
		getContentPane().add(cbCategory);

		// 재고 내역 출력 테이블
		model = new DefaultTableModel(columnNames, 0); // 행 0개
		tbSales = new JTable(model);
		tbSales.setFillsViewportHeight(true);
		// tbSales.setBackground(Color.BLACK);
		tbSales.setRowHeight(25);
		// tbSales.setGridColor(Color.WHITE);
		tbSales.setShowGrid(false);
		tbSales.setIntercellSpacing(new Dimension(10, 0));
		tbSales.setFont(new Font("고딕", Font.BOLD, 13));
		// "No.", "ID", "메뉴 이름", "가격", "재고 수량"
		tbSales.getColumn(columnNames[0]).setPreferredWidth(10); // No.
		tbSales.getColumn(columnNames[1]).setPreferredWidth(25); // ID
		tbSales.getColumn(columnNames[3]).setPreferredWidth(30); // 가격
		tbSales.getColumn(columnNames[4]).setPreferredWidth(30); // 재고수량

		JScrollPane jsp = new JScrollPane(tbSales, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(56, 482, 358, 235);
		// jsp.setBorder(new LineBorder(new Color(240, 240, 240)));
		getContentPane().add(jsp);

		// 주문수와 매출 출력
		JLabel lbTodayCnt = new JLabel(" 오늘 주문");
		lbTodayCnt.setFont(new Font("굴림", Font.BOLD, 16));
		lbTodayCnt.setBounds(448, 553, 183, 30);
		getContentPane().add(lbTodayCnt);

		tfOrderCnt = new JTextField();
		tfOrderCnt.setEditable(false);
		tfOrderCnt.setColumns(10);
		tfOrderCnt.setBorder(new LineBorder(new Color(171, 173, 179)));
		tfOrderCnt.setFont(new Font("굴림", Font.BOLD, 17));
		tfOrderCnt.setBackground(Color.WHITE);
		tfOrderCnt.setBounds(448, 585, 190, 35);
		getContentPane().add(tfOrderCnt);

		JLabel lbTodaySales = new JLabel(" 오늘 매출 ");
		lbTodaySales.setFont(new Font("굴림", Font.BOLD, 16));
		lbTodaySales.setBounds(450, 634, 183, 35);
		getContentPane().add(lbTodaySales);

		tfSales = new JTextField();
		tfSales.setEditable(false);
		tfSales.setColumns(10);
		tfSales.setBorder(new LineBorder(new Color(171, 173, 179)));
		tfSales.setFont(new Font("굴림", Font.BOLD, 17));
		tfSales.setBackground(Color.WHITE);
		tfSales.setBounds(450, 667, 190, 35);
		getContentPane().add(tfSales);

		// 재고 초기값 일괄 입력 패널
		JLabel lbInitSales = new JLabel("재고 초기값 일괄 입력");
		lbInitSales.setHorizontalAlignment(SwingConstants.CENTER);
		lbInitSales.setFont(new Font("Dialog", Font.BOLD, 16));
		lbInitSales.setBounds(697, 506, 232, 38);
		getContentPane().add(lbInitSales);

		JPanel panel = new JPanel();
		panel.setBackground(bgColor);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(697, 495, 232, 190);
		getContentPane().add(panel);
		panel.setLayout(null);

		cbAllOrCategory = new JComboBox(sItems);
		cbAllOrCategory.setBackground(Color.WHITE);
		cbAllOrCategory.setBounds(23, 67, 185, 35);
		panel.add(cbAllOrCategory);
		cbAllOrCategory.setFont(new Font("굴림", Font.BOLD, 13));

		tfBatch = new JTextField();
		tfBatch.setBounds(23, 124, 102, 35);
		panel.add(tfBatch);
		tfBatch.setFont(new Font("굴림", Font.BOLD, 13));
		tfBatch.setColumns(10);

		JButton bBatchOk = new JButton("입력");
		bBatchOk.setBounds(137, 124, 75, 35);
		panel.add(bBatchOk);
		bBatchOk.setFont(new Font("굴림", Font.BOLD, 14));
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2, 
				  mainWidth, mainHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	
		// 재고DB 초기값 설정도 되지 않은 경우는 출력하지 않는다.
		if (!isInit) {
			drawPieChart();
			drawBarGraph();
		}
		
		// '오늘 주문'와 '오늘 매출' 값 출력
		showOrderCntNSales();

		// 메인으로 가기
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

		// 메뉴별 매출 파이차트 출력 버튼
		bPieOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPieChart();
			}
		});

		// 전체 매출 바그래프 출력 버튼
		bGraphOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawBarGraph();
			}
		});

		// 카테코리 콤보박스에서 카테고리 선택 시 처리
		cbCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				String item = (String) jcb.getSelectedItem(); // " c_id c_mane "

				// '오늘 주문'와 '오늘 매출' 갱신
				showOrderCntNSales();

				// 현재 설정된 콤보박스의 카테고리의 메뉴 내용을 테이블에 출력
				showSelectedCategory(item);
			}
		});

		// 재고 초기값 설정 콤보박스 (메뉴전체 또는 현재 카테고리 메뉴만)
		cbAllOrCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				int index = (int) jcb.getSelectedIndex();
				switch (index) {
				case 1: // 메뉴전체
					allOrCategory = ALL_MENU;
					break;
				case 2: // 현재 카테고리 메뉴만
					allOrCategory = CATEGORY_MENU;
					break;
				}
			}
		});

		// 재고 초기값 일괄입력 버튼
		bBatchOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchVO svo = new SearchVO();
				svo.setStart_date(mngMain.getCurrDate()+" 00:00:00");
				svo.setEnd_date(mngMain.getCurrDate()+" 23:59:59");
				if (ManagerDAO.getOrderCnt(svo) > 0) {
					JOptionPane.showMessageDialog(getParent(), 
							"주문 시작 이후에는 초기값을 변경할 수 없습니다.", "초기값 수정 불가",
							JOptionPane.INFORMATION_MESSAGE);
					
					tfBatch.setText("");
					return;
				}
				
				String batch = tfBatch.getText().trim();
				if ((allOrCategory != ALL_MENU && allOrCategory != CATEGORY_MENU) || batch.length() < 1) {
					JOptionPane.showMessageDialog(getParent(), "'전체 메뉴' 또는 '카테고리 메뉴' 선택 후, 재고수량을 입력하세요.", "입력값 부족",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 모든 메뉴의 재고 초기값을 같은값으로 설정
				if (allOrCategory == ALL_MENU) {
					setAllMenuStock(Integer.parseInt(batch));

					// SalesNStock DB에 설정된 재고 초기값을 따로저장
					setSalesNStockCopy();
				}
				// 현재 선택된 카테고리의 모든 메뉴의 재고 초기값을 같은값으로 설정
				else if (allOrCategory == CATEGORY_MENU) {
					setCategoryMenuStock(Integer.parseInt(batch));

					// SalesNStock DB에 설정된 재고 초기값을 따로저장
					setSalesNStockCopy();
				}
			}
		});
	} // end of 생성자

	// Pie chart를 그린다.
	public void drawPieChart() {

		String beginDate = tfPieBegin.getText().trim();
		String endDate = tfPieEnd.getText().trim();

		if (cbPie.getSelectedIndex() == 0) { // 일별
			if (beginDate.length() != 8 || endDate.length() != 8) {
				JOptionPane.showMessageDialog(getParent(), " YYYYMMDD 로 입력해 주세요.", "잘못된 날짜 입력",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			beginDate = beginDate + " 00:00:00";
			endDate = endDate + " 23:59:59";
			
		} else { // 월별
			if (beginDate.length() < 6 || endDate.length() < 6) {
				JOptionPane.showMessageDialog(getParent(), " YYYYMM 으로 입력해 주세요.", "잘못된 날짜 입력",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			beginDate = beginDate.substring(0, 6) + "01 00:00:00";
							
			int yyyy = Integer.parseInt(endDate.substring(0, 4));
			int mm = Integer.parseInt(endDate.substring(4, 6));
			if (mm == 12) { 
				endDate = String.format("%04d%02d%s", yyyy+1, 1, "01 00:00:00");
			}
			else
				endDate = String.format("%04d%02d%s", yyyy, mm+1, "01 00:00:00");
			
			System.out.println("[Pie.월별] beginDate, endDate : " + beginDate + ", " + endDate);
		}

		// DB에서 기간 동안의 SalesNStock 정보를 가져옴
		List<SalesNStockVO> ssvoList = ManagerDAO.getSalesNStockList("res", beginDate, endDate);
		if (ssvoList.size() < 1) {
			JOptionPane.showMessageDialog(getParent(), "검색기간에 해당하는 정보가 없습니다.", "정보 없음",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// DB에서 기간 동안의 SalesNStock 초기값 정보를 가져옴
		List<SalesNStockVO> ssvoInitList = ManagerDAO.getSalesNStockList("init", beginDate, endDate);
		if (ssvoInitList.size() < 1) {
			JOptionPane.showMessageDialog(getParent(), "검색기간에 해당하는 초가값 정보가 없습니다.", "정보 없음",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (ssvoList.size() != ssvoInitList.size()) {
			JOptionPane.showMessageDialog(getParent(), "재고DB 개수가 일치하지 않습니다.", "정보 불일치",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		List<MenuVO> mvoList = ManagerDAO.getMenuAll();
		Slice[] sArr = new Slice[mvoList.size()];
		int sCnt = 0;
		for (MenuVO mvo : mvoList) {
			int c_id = mvo.getC_id();
			int m_id = mvo.getM_id();
			
			int saledPrice = 0;
			for (int i = 0; i < ssvoList.size(); i++) {
				int init = ssvoInitList.get(i).getStock(c_id, m_id);
				int res = ssvoList.get(i).getStock(c_id, m_id);
				
				if (init != 0xffff && res != 0xffff)
					saledPrice += (init - res) * mvo.getPrice();
				else 
					System.out.println("drawPieChart() : getStock 0xffff : "+ init + ", " + res);
			}
			if (saledPrice > 0) {
				Slice s = new Slice(mvo.getC_id(), mvo.getM_id(), mvo.getM_name(), saledPrice, null);
				s.setColorById();
				
				sArr[sCnt] = s;
				sCnt++;
			}
		}

		// 내림차순 정렬
		for (int i = 0; i < sCnt - 1; i++) {
			for (int j = i + 1; j < sCnt; j++) {
				if (sArr[i].value < sArr[j].value) {
					Slice tmp = sArr[i];
					sArr[i] = sArr[j];
					sArr[j] = tmp;
				}
			}
		}

		clearPieChartLegend();
		
		// 값이 큰 순서대로 10개 설정
		ArrayList<Slice> pie = new ArrayList<>();
		for (int i = 0; i < sCnt; i++) {
			if (i >= 10) break;
			
			setPieChartLegend(i+1, sArr[i].getName(), sArr[i].getColor());
			
			pie.add(sArr[i]);
		}

		pieChart.setValues(pie);
		repaint();
	}
	
	private void clearPieChartLegend() {
		lbS1.setText("");
		lbS1.setOpaque(true);
		lbS1.setBackground(bgColor);
		lbS2.setText("");
		lbS2.setOpaque(true);
		lbS2.setBackground(bgColor);
		lbS3.setText("");
		lbS3.setOpaque(true);
		lbS3.setBackground(bgColor);
		lbS4.setText("");
		lbS4.setOpaque(true);
		lbS4.setBackground(bgColor);
		lbS5.setText("");
		lbS5.setOpaque(true);
		lbS5.setBackground(bgColor);
		lbS6.setText("");
		lbS6.setOpaque(true);
		lbS6.setBackground(bgColor);
		lbS7.setText("");
		lbS7.setOpaque(true);
		lbS7.setBackground(bgColor);
		lbS8.setText("");
		lbS8.setOpaque(true);
		lbS8.setBackground(bgColor);
		lbS9.setText("");
		lbS9.setOpaque(true);
		lbS9.setBackground(bgColor);
		lbS10.setText("");
		lbS10.setOpaque(true);
		lbS10.setBackground(bgColor);
	}
	
	public void setPieChartLegend(int pos, String name, Color color) {
		switch (pos) {
		case 1 : 
			lbS1.setText(" 1." + name);
			lbS1.setOpaque(true);
			lbS1.setBackground(color);
			lbS1.setForeground(Color.WHITE);
			break;
		case 2 : 
			lbS2.setText(" 2." + name);
			lbS2.setOpaque(true);
			lbS2.setBackground(color);
			lbS2.setForeground(Color.WHITE);
			break;
		case 3 : 
			lbS3.setText(" 3." + name);
			lbS3.setOpaque(true);
			lbS3.setBackground(color);
			lbS3.setForeground(Color.WHITE);
			break;
		case 4 : 
			lbS4.setText(" 4." + name);
			lbS4.setOpaque(true);
			lbS4.setBackground(color);
			lbS4.setForeground(Color.WHITE);
			break;
		case 5 : 
			lbS5.setText(" 5." + name);
			lbS5.setOpaque(true);
			lbS5.setBackground(color);
			lbS5.setForeground(Color.WHITE);
			break;
		case 6 : 
			lbS6.setText(" 6." + name);
			lbS6.setOpaque(true);
			lbS6.setBackground(color);
			lbS6.setForeground(Color.WHITE);
			break;					
		case 7 :  
			lbS7.setText(" 7." + name);
			lbS7.setOpaque(true);
			lbS7.setBackground(color);
			lbS7.setForeground(Color.WHITE);
			break;
		case 8 : 
			lbS8.setText(" 8." + name);
			lbS8.setOpaque(true);
			lbS8.setBackground(color);
			lbS8.setForeground(Color.WHITE);
			break;
		case 9 : 
			lbS9.setText(" 9." + name);
			lbS9.setOpaque(true);
			lbS9.setBackground(color);
			lbS9.setForeground(Color.WHITE);
			break;
		case 10 : 
			lbS10.setText(" 10." + name);
			lbS10.setOpaque(true);
			lbS10.setBackground(color);
			lbS10.setForeground(Color.WHITE);
			break;				
		}
	}
	
	// 바그래프 검색기간 필드에 들어갈 초기값
	private String getInitDays() {
		String str = "";
		String[] days = new String[barGraph.MAX_BAR_NUM];
		
		LocalDate curr = LocalDate.now();
		
		for (int i = barGraph.MAX_BAR_NUM-1; i >= 0 ; i--) {
			String[] yymmdd = curr.toString().split("-");
			days[i] = yymmdd[0] + yymmdd[1] + yymmdd[2];
			
			curr = curr.minusDays(1);
		}
		
		for (int i = 0; i < barGraph.MAX_BAR_NUM; i++) {
			if (i == barGraph.MAX_BAR_NUM - 1)
				str += days[i];
			else
				str += days[i] + ", ";
		}
			
		return str;
	}
	
	// 단위별 총매출 바그래프를 그린다. 
	public void drawBarGraph() {
		ArrayList<BarInfo> graphData = new ArrayList<>();
		
		// 조회 시작일 또는 개별입력
		String input = tfGraphPeriod.getText().trim();
	
		if (cbGraph.getSelectedIndex() == 0) { // 일별
			if (input.length() < 8) {
				JOptionPane.showMessageDialog(getParent(),
						" YYYYMMDD, YYYYMMDD, ... 형식으로 입력해 주세요.", "잘못된 날짜 입력",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			clearBarGraphInfo();
			
			String[] days = input.split(",");
			
			for (int i = 0; i < days.length; i++) {
				if (i == barGraph.MAX_BAR_NUM) break;
				
				BarInfo bInfo;
				days[i] = days[i].trim();
				// DB에서 하루의 SalesNStock 정보를 가져옴
				List<SalesNStockVO> ssvoList = ManagerDAO.getSalesNStockList(
												"res", days[i]+" 00:00:00",
												       days[i]+" 23:59:59");
				if (ssvoList.size() < 1) // 데이터 없는 경우				
					bInfo = new BarInfo(days[i].substring(6)+"일", 0);	
				else 
					bInfo = new BarInfo(days[i].substring(6)+"일", 
										ssvoList.get(ssvoList.size()-1).getDay_total());
				
				// 바그래프 마다 날짜 표시
				setBarGraphInfo(i+1, days[i].substring(4, 6) + "." + days[i].substring(6)+"일");
											
				graphData.add(bInfo);
			}
			
			barGraph.setGraphType(barGraph.DAILY_DATA);  // 일별 데이터 출력
			barGraph.setValues(graphData);
			repaint();	
		} 
		else { // 월별
			if (input.length() < 6) {
				JOptionPane.showMessageDialog(getParent(),
						" YYYYMM, YYYYMM, ... 형식으로 입력해 주세요.", "잘못된 날짜 입력",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			clearBarGraphInfo();
		
			String[] months = input.split(",");
			
			for (int i = 0; i < months.length; i++) {
				if (i == barGraph.MAX_BAR_NUM) break;
				
				String str = months[i].trim();
				
				String beginDate = str.substring(0, 6) + "01 00:00:00";
				String endDate;				
				int yyyy = Integer.parseInt(str.substring(0, 4));
				int mm = Integer.parseInt(str.substring(4, 6));
				if (mm == 12) { 
					endDate = String.format("%04d%02d%s", yyyy+1, 1, "01 00:00:00");
				}
				else
					endDate = String.format("%04d%02d%s", yyyy, mm + 1, "01 00:00:00");
				
				System.out.println("[Bar.월별] beginDate, endDate : " + beginDate + ", " + endDate);
				
				BarInfo bInfo;
				// DB에서 한달간의 SalesNStock 정보를 가져옴
				List<SalesNStockVO> ssvoList = ManagerDAO.getSalesNStockList(
														"res", beginDate, endDate);
				if (ssvoList.size() < 1) // 데이터 없는 경우				
					bInfo = new BarInfo(beginDate.substring(4, 6)+"월", 0);		
				else {
					int total = 0;
					for (int j = 0; j < ssvoList.size(); j++)
						total += ssvoList.get(j).getDay_total();
						
					bInfo = new BarInfo(beginDate.substring(4, 6)+"월", total);
				}
					
				// 바그래프 마다 월 표시
				setBarGraphInfo(i+1, beginDate.substring(0, 4) + "." + beginDate.substring(4, 6)+"월");
				
				graphData.add(bInfo);
			}
			
			barGraph.setGraphType(barGraph.MONTHLY_DATA);  // 월별 데이터 출력
			barGraph.setValues(graphData);
			repaint();			
		}
	}
	
	private void clearBarGraphInfo() {
		lb1st.setText("");
		lb2nd.setText("");
		lb3rd.setText(""); 
		lb4th.setText(""); 
		lb5th.setText("");
	}
	
	private void setBarGraphInfo(int pos, String info) {
		switch (pos) {
		case 1 : lb1st.setText(info); break;
		case 2 : lb2nd.setText(info); break;
		case 3 : lb3rd.setText(info); break;
		case 4 : lb4th.setText(info); break;
		case 5 : lb5th.setText(info); break;
		}
	}

	// 현재 주문수와 매출(DB값)을 표시
	private void showOrderCntNSales() {
		// 현재 주문수
		SearchVO svo = new SearchVO();
		svo.setStart_date(mngMain.getCurrDate() + " 00:00:00");
		svo.setEnd_date(mngMain.getCurrDate() + " 23:59:59");
		int orderCnt = ManagerDAO.getOrderCnt(svo);
		tfOrderCnt.setText("  " + orderCnt);

		// 매출(DB값)을 표시
		SalesNStockVO ssvo = mngMain.getSalesNStockToday();
		// 생성전이면 0 표시
		if (ssvo == null)
			tfSales.setText("  0");
		else
			tfSales.setText("  " + ssvo.getDay_total());
	}

	// 모든 메뉴의 재고 초기값을 같은값으로 설정
	private void setAllMenuStock(int value) {
		int res = JOptionPane.showConfirmDialog(getParent(), "전체 메뉴의 초기 재고수량을 " + value + "개로 " + "입력하시겠습니까?",
				"전체 재고수량 입력", JOptionPane.YES_NO_OPTION);
		if (res != 0) { // yes 가 아닌 경우
			tfBatch.setText("");
			return;
		}

		SalesNStockVO ssvo = mngMain.getSalesNStockToday();
		// 처음은 INSERT
		if (ssvo == null) {
			// 매출 0, 전체 재고수량은 입력값으로 DB 저장
			ssvo = new SalesNStockVO(0, value);
			int result = ManagerDAO.getSalesNStockInsert(ssvo);
			if (result > 0) { // 삽입 성공
				JOptionPane.showMessageDialog(getParent(), "모든 메뉴의 재고수량이 " + value + "개로 설정되었습니다.", "재고값 설정 완료",
						JOptionPane.INFORMATION_MESSAGE);

				// 현재 설정된 콤보박스의 카테고리의 메뉴 내용을 테이블에 출력
				showSelectedCategory((String) cbCategory.getSelectedItem());
			} else {
				JOptionPane.showMessageDialog(getParent(), "재고 DB에 입력 실패하였습니다.", "재고 DB 입력 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// 두번째부터(존재하는 경우)는 UPDATE
		else {
			// DB에서 읽어온 값에 입력받은 값을 추가한다.
			ssvo.setSalesNStockVO(0, value);
			int result = ManagerDAO.getSalesNStockUpdate(ssvo);
			if (result > 0) { // update 성공
				showSelectedCategory((String) cbCategory.getSelectedItem());
			} else {
				JOptionPane.showMessageDialog(getParent(), "재고 DB에 입력 실패하였습니다.", "재고 DB 입력 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		tfBatch.setText("");
	}

	// 현재 선택된 카테고리의 모든 메뉴의 재고 초기값을 같은값으로 설정
	private void setCategoryMenuStock(int value) {
		// 선택한 카테고리가 없는 경우
		if (cbCategory.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(getParent(), "초기값을 입력할 카테고리를 선택하세요.", "필수 입력",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int res = JOptionPane.showConfirmDialog(getParent(), "현재 카테고리 메뉴의 초기 재고수량을 " + value + "개로 " + "입력하시겠습니까?",
				"카테고리별 재고수량 입력", JOptionPane.YES_NO_OPTION);
		if (res != 0) { // yes 가 아닌 경우
			tfBatch.setText("");
			return;
		}

		int c_id = getC_idFromComboBoxItem((String) cbCategory.getSelectedItem());

		// 매출 0, 현재 카테고리 메뉴의 재고수량은 입력값으로 DB 저장
		SalesNStockVO ssvo = mngMain.getSalesNStockToday();
		// 처음은 INSERT
		if (ssvo == null) {
			ssvo = new SalesNStockVO(0, c_id, value);
			int result = ManagerDAO.getSalesNStockInsert(ssvo);
			if (result > 0) { // 삽입 성공
				// 현재 설정된 콤보박스의 카테고리의 메뉴 내용을 테이블에 출력
				showSelectedCategory((String) cbCategory.getSelectedItem());
			} else {
				JOptionPane.showMessageDialog(getParent(), "재고 DB에 입력 실패하였습니다.", "재고 DB 입력 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// 두번째부터(존재하는 경우)는 UPDATE
		else {
			// DB에서 읽어온 값에 입력받은 값을 추가한다.
			ssvo.setSalesNStockVO(0, c_id, value);
			int result = ManagerDAO.getSalesNStockUpdate(ssvo);
			if (result > 0) { // update 성공
				showSelectedCategory((String) cbCategory.getSelectedItem());
			} else {
				JOptionPane.showMessageDialog(getParent(), "재고 DB에 입력 실패하였습니다.", "재고 DB 입력 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		tfBatch.setText("");
	}

	// SalesNStock DB에 설정된 재고 초기값을 따로저장
	private void setSalesNStockCopy() {
		// "res"로 설정된 값을 가져옴
		SalesNStockVO ssvo = mngMain.getSalesNStockToday();
		// "init"으로 설정된 값을 가져옴
		List<SalesNStockVO> ssvoList = ManagerDAO.getSalesNStockList("init", mngMain.getCurrDate() + " 00:00:00",
				mngMain.getCurrDate() + " 23:59:59");
		// 최초 생성 : "res"로 설정된 초기값을 "init" 값으로 생성
		if (ssvoList.size() < 1) {
			ssvo.setDataType("init");
			int result = ManagerDAO.getSalesNStockInsert(ssvo);
			if (result < 1) { // 삽입 실패
				JOptionPane.showMessageDialog(getParent(), "재고 DB(init) 입력에 실패하였습니다.", "재고 DB(init) 입력 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// 업데이트
		else {
			ssvo.setSales_idx(ssvoList.get(ssvoList.size() - 1).getSales_idx());
			ssvo.setDataType(ssvoList.get(ssvoList.size() - 1).getDataType());
			int result = ManagerDAO.getSalesNStockUpdate(ssvo);
			if (result < 1) { // Update 실패
				JOptionPane.showMessageDialog(getParent(), "재고 DB(init) Update에 실패하였습니다.", "재고 DB(init) Update 실패",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// 전체 카테고리 정보를 콤보박스에 출력
	public String[] getCategoryAll(String[] cItems) {

		List<CategoryVO> cList = ManagerDAO.getCategoryList();
		if (cList != null) {
			cItems = new String[cList.size() + 1];

			for (int i = 0; i < cList.size(); i++) {
				cItems[i + 1] = "  " + cList.get(i).getC_id() + "    " + cList.get(i).getC_name();
			}
		} else {
			System.out.println("getCategoryAll : ManagerDAO.getCategoryList() null");
			cItems = new String[1];
		}
		cItems[0] = "   메뉴 카테고리";

		return cItems;
	}

	// 콤보박스에서 선택된 카테고리의 c_id를 구함
	private int getC_idFromComboBoxItem(String item) {
		String[] cInfo = item.trim().split("  ");
		return Integer.parseInt(cInfo[0]);
	}

	// 현재 설정된 콤보박스의 카테고리의 메뉴 내용을 테이블에 출력
	private void showSelectedCategory(String item) {

		if (cbCategory.getSelectedIndex() == 0)
			return;

		List<MenuVO> mList = ManagerDAO.getMenuList(getC_idFromComboBoxItem(item));
		if (mList != null) {
			// 재고 수량을 가져와서,
			// 메뉴별 재고수량을 테이블에 출력
			outputStockTable(mList, mngMain.getSalesNStockToday());
		} else {
			JOptionPane.showMessageDialog(getParent(), "메뉴 DB에 값이 없습니다.", "DB 실패", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 메뉴별 재고수량을 테이블에 출력
	private void outputStockTable(List<MenuVO> mList, SalesNStockVO ssvo) {
		mngMain.procOrder.clearTable(model);

		String[] record = new String[columnNames.length];
		for (int i = 0; i < mList.size(); i++) {
			// "No.", "ID", "메뉴 이름", "가격", "재고 수량"
			record[0] = " " + (i + 1);
			record[1] = " " + String.valueOf(mList.get(i).getM_id());
			record[2] = " " + mList.get(i).getM_name();
			record[3] = " " + String.valueOf(mList.get(i).getPrice());
			if (ssvo == null)
				record[4] = " ";
			else
				record[4] = " " + ssvo.getStock(mList.get(i).getC_id(), mList.get(i).getM_id());

			model.addRow(record);
		}
	}
}
