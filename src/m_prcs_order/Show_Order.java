package m_prcs_order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import c_menu.Order;
import c_menu.SelectedMenu;
import m_login.Manager_Main;

public class Show_Order extends JFrame {
	Manager_Main mngMain;
	private JPanel contentPane;
	private JTable tbOrder;
	
	String[] columnNames = { "주문번호", "주문 시간", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호" };
	DefaultTableModel model;
	
	public Show_Order(Manager_Main mngMain, int width, int height, int orderListIdx) {
		super("선택 주문 확인");
		
		this.mngMain = mngMain;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bOk = new JButton("확인");
		bOk.setBounds(749, 308, 102, 33);
		contentPane.add(bOk);
		
		model = new DefaultTableModel(columnNames, 0); // 행 0개
		tbOrder = new JTable(model);
		tbOrder.setFillsViewportHeight(true);
		// tbNew.setBackground(Color.BLACK);
		tbOrder.setRowHeight(25);
		// tbNew.setGridColor(Color.WHITE);
		tbOrder.setShowGrid(false);
		tbOrder.setIntercellSpacing(new Dimension(10, 0));
		tbOrder.setFont(new Font("고딕", Font.BOLD, 13));

		tbOrder.getColumn(columnNames[0]).setPreferredWidth(20);  // 주문번호
		tbOrder.getColumn(columnNames[1]).setPreferredWidth(110); // 주문시간
		tbOrder.getColumn(columnNames[3]).setPreferredWidth(30);  // 수량
		tbOrder.getColumn(columnNames[4]).setPreferredWidth(50);  // 가격
		tbOrder.getColumn(columnNames[5]).setPreferredWidth(40);  // 포장여부
		tbOrder.getColumn(columnNames[6]).setPreferredWidth(60);  // 합계

		JScrollPane jsp = new JScrollPane(tbOrder, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(31, 36, 820, 254);
		jsp.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(jsp);
		
		outputOrderTable(orderListIdx);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2 - width/2, ds.height/2 - height/2,
				  width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		bOk.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void outputOrderTable(int index) {
		Order order = mngMain.waitOrder.getOrderList().get(index);
		
		mngMain.procOrder.clearTable(model);
		
		ArrayList<SelectedMenu> menuList = order.getValue();

		String[] record = new String[columnNames.length];
		// 테이블에 출력
		for (int i = 0; i < menuList.size(); i++) {
			// "주문 번호", "주문 시간", "메뉴", "수량", "가격", "포장여부", "합계", "전화번호"
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

			model.addRow(record);
		}
	}
}
