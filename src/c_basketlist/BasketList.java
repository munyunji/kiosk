package c_basketlist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import c_menu.Menu_Main;
import c_menu.SelectedMenu;

public class BasketList extends JFrame {
	Menu_Main main;
	private String takeaway = ""; // 포장여부 저장
	private String phone = ""; // 포인트를 저장하는 전화번호
	private int total = 0; // 주문금액

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tfTotal;
	private JTextField textField_3;
	JButton main1;
	JButton point;
	JButton food;
	JButton pay;
	ImageIcon img;
	
	private JTable table;
	String[] columnNames = { "No.", "메뉴", "수량", "가격"};
	DefaultTableModel model;

	public BasketList(Menu_Main main) {
		super("장바구니");

		this.main = main;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Basket List");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 22));
		lblNewLabel.setBounds(338, 10, 303, 97);
		contentPane.add(lblNewLabel);

		JButton box = new JButton("New button1");
		img = new ImageIcon("src/img_basket/in2.jpg");
		box.setIcon(img);

		box.setBounds(464, 147, 221, 229);
		contentPane.add(box);
//		contentPane.setBackground(Color.green);

		point = new JButton("New button2");
		ImageIcon img1 = new ImageIcon("src/img_basket/image3.png");
		point.setIcon(img1);

		point.setBounds(464, 443, 472, 163);
		contentPane.add(point);

		food = new JButton("New button3");
		ImageIcon img2 = new ImageIcon("src/img_basket/ing3.png");
		food.setIcon(img2);

		food.setBounds(715, 147, 221, 229);
		contentPane.add(food);

		pay = new JButton("결제 하기");
		pay.setBackground(Color.BLACK.gray);
		pay.setForeground(Color.WHITE);
		pay.setFont(new Font("굴림", Font.BOLD, 15));
		pay.setToolTipText("");

		pay.setBounds(631, 658, 138, 37);
		contentPane.add(pay);

		JLabel lblNewLabel_3 = new JLabel("포장(1회 용기 제공)");
		lblNewLabel_3.setBackground(Color.RED);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(505, 386, 158, 23);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("매장(다회 용기 제공)");
		lblNewLabel_3_1.setBackground(Color.RED);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(750, 386, 147, 24);
		contentPane.add(lblNewLabel_3_1);

		// "No.", "메뉴", "수량", "가격"
		model = new DefaultTableModel(columnNames, 0); // 행 0개
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		// tbNew.setBackground(Color.BLACK);
		table.setRowHeight(35);
		// tbNew.setGridColor(Color.WHITE);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setFont(new Font("고딕", Font.BOLD, 15));

		table.getColumn(columnNames[0]).setPreferredWidth(50); // No.
		//table.getColumn(columnNames[2]).setPreferredWidth(40); // 수량
		//table.getColumn(columnNames[3]).setPreferredWidth(70); // 가격

		JScrollPane jsp = new JScrollPane(table, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(48, 147, 355, 459);
		//jsp.setBorder(new LineBorder(new Color(240, 240, 240)));
		
		contentPane.add(jsp);

		JSeparator separator = new JSeparator();
		separator.setBounds(163, 147, 0, 533);
		contentPane.add(separator);

		main1 = new JButton("< 돌아가기");
		main1.setBackground(Color.BLACK.gray);
		main1.setForeground(Color.WHITE);
		main1.setFont(new Font("굴림", Font.BOLD, 15));
		main1.setToolTipText("");
		main1.setBounds(28, 24, 115, 39);
		contentPane.add(main1);

		JLabel lblNewLabel_1 = new JLabel("  주문 내역");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(48, 114, 110, 23);
		contentPane.add(lblNewLabel_1);

//		JLabel lblNewLabel_1_1 = new JLabel("Pcs");
//		lblNewLabel_1_1.setForeground(Color.WHITE);
//		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 15));
//		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1.setBounds(190, 114, 57, 23);
//		contentPane.add(lblNewLabel_1_1);
//
//		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
//		lblNewLabel_1_1_1.setForeground(Color.WHITE);
//		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 15));
//		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1_1.setBounds(348, 114, 57, 23);
//		contentPane.add(lblNewLabel_1_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(486, 693, 1, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(464, 420, 472, 28);
		contentPane.add(separator_2);

		JLabel lblNewLabel_2 = new JLabel("Total");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(63, 624, 60, 37);
		contentPane.add(lblNewLabel_2);

		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(135, 624, 268, 37);
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(tfTotal);

		JLabel lblNewLabel_3_2 = new JLabel("포인트 적립");
		lblNewLabel_3_2.setBackground(Color.RED);
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setBounds(631, 616, 133, 23);
		contentPane.add(lblNewLabel_3_2);

		// 주문 개수가 0 인 메뉴는 삭제한다.
		arrangeSelectedMenuList();
		// 주문 내역을 테이블에 출력한다.
		outputSelectedMenuList();
		// 주문 합계 금액을 구하고, 출력한다.
		outputTotal();
		// 구매 내역이 없으면 포인트 화면 안열리게 함
		if (getTotal() == 0)
			point.setEnabled(false);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - main.mainWidth / 2, ds.height / 2 - main.mainHeight / 2, 
				  main.mainWidth, main.mainHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		main1.addActionListener(new ActionListener() { // 돌아가기
			@Override // 돌아가기를 선택하면 장바구니 창을 끈다.
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				img = new ImageIcon("src/images/imag8.png");
//				box.setIcon(img);

				setTakeaway("포장");
				box.setBorder(new LineBorder(Color.red, 10));
				food.setBorder(new LineBorder(Color.BLACK, 4));
				JOptionPane.showMessageDialog(getParent(), "포장선택.", "포장여부 선택", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		food.addActionListener(new ActionListener() { // 매장 (다회용기 제공 )
			@Override
			public void actionPerformed(ActionEvent e) {
				setTakeaway("매장식사");
				food.setBorder(new LineBorder(Color.red, 10));
				box.setBorder(new LineBorder(Color.black, 4));
				JOptionPane.showMessageDialog(getParent(), "매장선택.", "포장여부 선택", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		point.addActionListener(new ActionListener() { // 포인트적립
			@Override
			public void actionPerformed(ActionEvent e) {
				new SavePoint(BasketList.this, main.mainWidth, main.mainHeight);
				setVisible(false);
			}
		});

		pay.addActionListener(new ActionListener() { // 결제하기
			@Override // 결재하기를 선택한 경우
			public void actionPerformed(ActionEvent e) {
				// 주문한 메뉴가 없는 경우
				if (main.getSelectedMenuList().size() < 1) {
					JOptionPane.showMessageDialog(getParent(), 
							"주문하실 메뉴를 선택해 주세요.", "주문 내역 없음", JOptionPane.INFORMATION_MESSAGE);
				} else if (getTakeaway().length() < 1) {
					JOptionPane.showMessageDialog(getParent(), 
							"포장여부를 선택해 주세요.", "포장여부 없음", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					new Payment(BasketList.this);
					setVisible(false);
				}
			}
		});
	}
	
	// 주문 개수가 0 인 메뉴는 삭제한다.
	private void arrangeSelectedMenuList() { 
		ArrayList<SelectedMenu> mList = main.getSelectedMenuList();
		
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getNumber() < 1) {
				main.cancelSelectedMenu(mList.get(i));
				i--;
			}
		}
	}
			
	// 테이블에 선택한 메뉴들을 출력한다.
	private void outputSelectedMenuList() {
		clearTable(model);
		
		String[] record = new String[columnNames.length];
		ArrayList<SelectedMenu> mList = main.getSelectedMenuList();
		
		for (int i = 0; i < mList.size(); i++) {
			// "No.", "메뉴", "수량", "가격"
			record[0] = " " + String.valueOf(i+1);
			record[1] = mList.get(i).getM_name();
			record[2] = " " + String.valueOf(mList.get(i).getNumber());
			record[3] = " " + String.valueOf(mList.get(i).getPrice()*mList.get(i).getNumber());

			model.addRow(record);
		}
	}
	
	public void clearTable(DefaultTableModel model) {
		// 한줄 삭제될때마다 RowCount는 변하므로 처음의 값을 써야한다.
		int rowCnt = model.getRowCount();
		// 테이블에 출력된 내용 모두 삭제
		for (int i = 0; i < rowCnt; i++)
			model.removeRow(0);
	}
	
	// 주문 합계 금액을 출력한다.
	private void outputTotal() {
		ArrayList<SelectedMenu> mList = main.getSelectedMenuList();
		int total = 0;
		
		for (SelectedMenu sMenu : mList) {
			total += sMenu.getPrice() * sMenu.getNumber();
		}
		
		tfTotal.setText(String.valueOf(total)+ "  ");
		setTotal(total);
	}
	
	// 주문 합계 금액에 포인트 사용내역을 표시한다.
	public void updateTotal(int usedPoint) {
		String str = "";
		int newTotal = getTotal() - usedPoint;
		
		str = getTotal() + " - (" + usedPoint + ") = " + newTotal + "  ";
		
		tfTotal.setText(str);
	}
	
	public void makePointButtonDisable() {
		point.setEnabled(false);
		main1.setEnabled(false);
		
	}
	
	public String getTakeaway() {
		return takeaway;
	}

	public void setTakeaway(String takeaway) {
		this.takeaway = takeaway;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void exit_BasketList() {
		dispose();
		main.exit_Manu_Main();
	}
}
