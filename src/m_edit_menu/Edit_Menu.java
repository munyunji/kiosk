package m_edit_menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import c_menu.CategoryVO;
import c_menu.DAO;
import c_menu.MenuVO;
import m_login.Logout_Success;
import m_login.ManagerDAO;
import m_login.Manager_Main;

public class Edit_Menu extends JFrame {

	Manager_Main mngMain;
	private JTable table;
	DefaultTableModel model;
	// 컬럼 아이디
	String[] columnNames = { "No.", "ID", "메뉴 이름", "가격", "이미지" }; // table columns

	String[] cItems;
	// 카테 ㅗ리 메뉴
	String[] sItems = { "  선택하세요 (카테고리, 메뉴)", "   메뉴 카테고리", "   메뉴" };

	int editMode = 0; // 카테고리 편집(1), 메뉴 편집(2)
	final int EDIT_TAB = 1; // 카테고리 편집일때
	final int EDIT_MENU = 2; // 메뉴 편집일때
	private JTextField textField;

	public Edit_Menu(Manager_Main mngMain, int mainWidth, int mainHeight) {
		super("메뉴 편집");

		this.mngMain = mngMain;

		JPanel jp1;
		getContentPane().setLayout(null);

		jp1 = new JPanel();
		jp1.setBackground(Color.LIGHT_GRAY);
		jp1.setBounds(0, 0, 984, 761);

		getContentPane().add(jp1);
		jp1.setLayout(null);

		JLabel e_menu = new JLabel("Edit Menu");
		e_menu.setFont(new Font("고딕", Font.PLAIN, 22));
		e_menu.setBounds(40, 29, 132, 40);

		// 카테고리 콤보박스
		// category DB에서 정보(c_id, c_name)을 가져온다.
		List<CategoryVO> list = DAO.getCategoryList();
		int categoryCnt = list.size();

		cItems = new String[categoryCnt + 1];
		cItems[0] = "  메뉴 카테고리";
		for (int i = 0; i < categoryCnt; i++) {
			cItems[i + 1] = "   " + list.get(i).getC_id() + "    " + list.get(i).getC_name();
		}

		JComboBox category_cb = new JComboBox(cItems);
		category_cb.setBounds(40, 107, 250, 32);
		category_cb.setEditable(false);
		category_cb.setFont(new Font("고딕", Font.BOLD, 13));

		// 콤보박스 리스트 내용 가운데 정렬
//		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
//		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
//		category_cb.setRenderer(listRenderer);

		jp1.add(category_cb);

		// 메뉴 정보 (No.,ID,이름,가격,이미지) 출력 테이블
		model = new DefaultTableModel(columnNames, 0); // 행 0개
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setRowHeight(35);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setFont(new Font("고딕", Font.BOLD, 13));

		table.getColumn(columnNames[0]).setPreferredWidth(20);
		table.getColumn(columnNames[1]).setPreferredWidth(50);
		// table.getColumn(columnNames[3]).setPreferredWidth(70);

		JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(40, 187, 500, 493);
		// jsp.setBorder(new LineBorder(new Color(240, 240, 240)));

		// 카테고리 편집 or 메뉴 편집 결정 콤보박스
		JComboBox select_cb = new JComboBox(sItems);
		select_cb.setBounds(610, 112, 335, 32);
		select_cb.setEditable(false);
		select_cb.setFont(new Font("고딕", Font.BOLD, 13));
		jp1.add(select_cb);

		Label id_label = new Label("ID :");
		id_label.setFont(new Font("Dialog", Font.PLAIN, 16));
		id_label.setBounds(610, 212, 57, 30);
		jp1.add(id_label);

		JTextField id_tf = new JTextField();
		id_tf.setBounds(670, 212, 151, 35);
		jp1.add(id_tf);

		Label n_label = new Label("Name :");
		n_label.setFont(new Font("Dialog", Font.PLAIN, 16));
		n_label.setBounds(610, 289, 57, 30);
		jp1.add(n_label);

		JTextField name_tf = new JTextField();
		name_tf.setBounds(670, 289, 275, 35);
		jp1.add(name_tf);

		Label p_label = new Label("Price :");
		p_label.setFont(new Font("Dialog", Font.PLAIN, 16));
		p_label.setBounds(610, 362, 57, 30);
		jp1.add(p_label);

		JTextField price_tf = new JTextField();
		price_tf.setBounds(670, 362, 275, 35);
		jp1.add(price_tf);

		JButton in_btn = new JButton("생성하기");
		in_btn.setBackground(Color.DARK_GRAY);
		in_btn.setForeground(Color.WHITE);
		in_btn.setFont(new Font("고딕", Font.BOLD, 16));
		in_btn.setBounds(610, 510, 335, 45);

		JButton u_btn = new JButton("변경하기");
		u_btn.setBackground(Color.DARK_GRAY);
		u_btn.setForeground(Color.WHITE);
		u_btn.setFont(new Font("고딕", Font.BOLD, 16));
		u_btn.setBounds(610, 572, 335, 45);

		JButton d_btn = new JButton("삭제하기");
		d_btn.setBackground(Color.DARK_GRAY);
		d_btn.setForeground(Color.WHITE);
		d_btn.setFont(new Font("고딕", Font.BOLD, 16));
		d_btn.setBounds(610, 635, 335, 45);

		jp1.add(e_menu);
		jp1.add(jsp);
		jp1.add(in_btn);
		jp1.add(u_btn);
		jp1.add(d_btn);

		JButton main_btn = new JButton("< 메인");
		main_btn.setBackground(Color.DARK_GRAY);
		main_btn.setForeground(Color.WHITE);
		main_btn.setFont(new Font("고딕", Font.BOLD, 15));
		main_btn.setBounds(830, 37, 115, 30);
		jp1.add(main_btn);

		JButton exit_btn = new JButton("종료");
		exit_btn.setBackground(Color.DARK_GRAY);
		exit_btn.setForeground(Color.WHITE);
		exit_btn.setFont(new Font("고딕", Font.BOLD, 15));
		exit_btn.setBounds(830, 700, 115, 30);
		jp1.add(exit_btn);

		Label p_label_1 = new Label("Image :");
		p_label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		p_label_1.setBounds(610, 435, 57, 30);
		jp1.add(p_label_1);

		textField = new JTextField();
		textField.setBounds(670, 435, 275, 35);
		jp1.add(textField);

		JButton check_btn = new JButton("중복 확인");
		check_btn.setBackground(Color.DARK_GRAY);
		check_btn.setForeground(Color.WHITE);
		check_btn.setForeground(new Color(255, 255, 255));
		check_btn.setFont(new Font("Dialog", Font.BOLD, 13));
		check_btn.setBounds(833, 212, 112, 35);
		jp1.add(check_btn);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2,
 				  mainWidth, mainHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		// 콤보박스에서 카테고리를 선택하면, 테이블에 해당 카테고리의 메뉴정보 출력
		// 현재 카테고리(메뉴 탭) 이름을 보여주는 콤보박스
		category_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				
				if (combo.getSelectedIndex() == 0) return;
				
				String c_name = (String) combo.getSelectedItem();
				String[] items = c_name.trim().split("   ");
				
				int c_id = Integer.parseInt(items[0].trim());
				c_name = items[1].trim();
					
				// 탭이름에 해당하는 c_id를 찾는다.
				//int c_id = ManagerDAO.getCategoryId(c_name);
				// c_id에 해당하는 메뉴들을 테이블에 출력한다.
				outputMenuList(c_id);
			}
		});

		// 카테고리 or 메뉴 선택 콤보박스
		select_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 콤보 박스에 자료를 담는다
				JComboBox combo = (JComboBox) e.getSource();
				// item 에 String 으로 콤보박스 안에있는 값을 가져온다 아이템.
				String item = (String) combo.getSelectedItem();

				// 메뉴 탭일떄
				switch (item.trim()) {
				case "메뉴":
					editMode = EDIT_MENU;

					price_tf.setEditable(true);
					textField.setEditable(true);
					break;
				// 카테고리 탭일떄
				case "메뉴 카테고리":
					editMode = EDIT_TAB;

					// 안보이게 하겠다.
					price_tf.setEditable(false);
					textField.setEditable(false);

					break;

				default:
					editMode = 0;
				}
			}
		});

		// 중복 버튼 체크 액션 주기
		check_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String menu = null;

				// 내용을 입력받기 위해서는 받는형이 int 형이기 떄문에 int => String 형으로
				// 바꿔주기위해서 결과값을 Integer 으로 해서 바꿔줬다.

				String s1 = id_tf.getText().trim();
				int s1_1 = Integer.parseInt(s1);
				// 카테고리가 메뉴일때!
				if (editMode == EDIT_MENU) {
					// s1 의 길이가 0 보다 크면 중복검사!
					if (s1.length() > 0) {

						// MenuVo vo 안에 m_ id 값이 들어있다.
						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);

						// 중복검사 하기 콤보박스 안에 있는값이 (0) 안들어가있을 경우
						// 중복없음 들어가있을경우 중복 있음! 그러고 내용이 없을경우 값
						// 초 기화 해주기 m_id 검사
						int res = ManagerDAO.getIdChk(vo.getM_id());

						// 값이랑 0일경우

						if (res == 0) {
							// int a 에다가 카테고리 콬보박스 값을 가져온다 인덱스값으로 가져온다.

							int a = category_cb.getSelectedIndex();
							if (a == 0) {
								JOptionPane.showMessageDialog(null, "카테고리를 선택하세요.");
								return;
							}

							// category DB에서 정보(c_id, c_name)을 포함한 값 배열안에
//						// a 를넣어주고 값을 가져오고 뒤에는 공백처리를 해준다 
							
							// 그러고 c_id 는 DAO 카테고리 아이디 에서name 을가져오고
//						// vo. setc_id()  괄호안에 c_id 값을 넣어준다 
							//int c_id = ManagerDAO.getCategoryId(c_name);	
							
							// info[0]: c_id, info[1]: c_main
							String[] info = cItems[a].trim().split("   ");
							int c_id = Integer.parseInt(info[0].trim());
							
							vo.setC_id(c_id);
							// 중복 메세지 알림

							JOptionPane.showMessageDialog(null, "존재 하지 않는 ID 입니다.");
						} else {
							// 중복 메세지 알림

							JOptionPane.showMessageDialog(null, "존재하는 ID 입니다.");
							id_tf.setText("");
							name_tf.setText("");
							price_tf.setText("");
							textField.setText("");

						}

					}
					// 카테고리 탭일때 중복검사
				} else if (editMode == EDIT_TAB) {
					// 똑같이 인트형으로 바꿔주고!

					String c_id = id_tf.getText().trim();
					int s1_3 = Integer.parseInt(c_id);
					// c_id 길이가 0보다 클때
					if (c_id.length() > 0) {
						// categoryVO 객체 만들어주고
						CategoryVO vo3 = new CategoryVO();
						// 값설정 숫자
						vo3.setC_id(s1_3);

						// res = 중복검사 한다 카테고리 아이디
						int res = ManagerDAO.getIdChk2(vo3.getC_id());

						// 값이 0이면 값이 없으므로 존재하지않는 id
						if (res == 0) {

							JOptionPane.showMessageDialog(null, "존재하지않는 ID 입니다.");

							// 값이 1이면 값이 있으므로 존재하는 id
						} else if (res == 1) {
							// 중복 메세지 알림
							JOptionPane.showMessageDialog(null, "존재하는 ID 입니다.");
							id_tf.setText("");
						}
					}
				}
			}
		});

		in_btn.addActionListener(new ActionListener() {// 생성
			@Override
			public void actionPerformed(ActionEvent e) {
				// 카테고리 가 메뉴탭일떄
				if (editMode == EDIT_MENU) {

					String menu = null;

					// 텍스트 필드! 3가지의 값 똑같이 String 을 int 으로변환

					String s1 = id_tf.getText().trim();
					int s1_1 = Integer.parseInt(s1);
					String s2 = name_tf.getText().trim();
					String s3 = price_tf.getText().trim();
					int s1_3 = Integer.parseInt(s3);
					String s4 = textField.getText().trim();

					// 0보다 길이가 클떄! 일어나는 상황
					if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0 && s4.length() > 0) {

						// menuVO 가져와서 객체화시켜주기
						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);
						vo.setM_name(s2);
						vo.setPrice(s1_3);
						vo.setImage(s4);

						int res = 0;

						if (res == 0) {

							int a = category_cb.getSelectedIndex();
							//String c_name = cItems[a].trim();
							//int c_id = ManagerDAO.getCategoryId(c_name);
							
							// info[0]: c_id, info[1]: c_main
							String[] info = cItems[a].trim().split("   ");
							int c_id = Integer.parseInt(info[0].trim());
							
							vo.setC_id(c_id);

							// if 문 res = 0이면 Ins 추가한다 vo 값을 M_id 와 M_name, price
							// 값을 넣어준다 그러고 난뒤 다이얼로그 메세지 창 을 띄어준다
							int res1 = ManagerDAO.getIns(vo);
							if (res1 > 0) {
								int cet1 = JOptionPane.showConfirmDialog(getParent(), "메뉴를 생성하시겠습니까?", "생성확인",
										JOptionPane.YES_NO_OPTION);
								outputMenuList(c_id);
								id_tf.setText("");
								name_tf.setText("");
								price_tf.setText("");
								textField.setText("");
							}
						}
					}
				} else if (editMode == EDIT_TAB) {
					mngMain.procOrder.clearTable(model);

					String menu = null;

					// s2 있는 이름 텍스트 필드 값
					String s1 = id_tf.getText().trim();
					int s1_1 = Integer.parseInt(s1);
					String s2 = name_tf.getText().trim();

					// s2 길이가 0보다 클떄!
					if (s1.length() > 0 && s2.length() > 0) {

						// 카테고리 vo 안에 있는 c_id , c_name 값
						CategoryVO vo = new CategoryVO();

						vo.setC_id(s1_1);
						vo.setC_name(s2);

						int res = 0;
						if (res == 0) {

//							int a = category_cb.getSelectedIndex();
//							String c_name = cItems[a].trim();
//							int c_id = ManagerDAO.getCategoryId(c_name);
//							vo.setC_id(c_id);
//							vo.setC_name(c_name);

							int res1 = ManagerDAO.getIns2(vo);
							if (res1 > 0) {
								int cet1 = JOptionPane.showConfirmDialog(getParent(), "카테고리를 생성하겠습니까?", "생성확인",
										JOptionPane.YES_NO_OPTION);

//								outputCategorylist(c_id1);
								id_tf.setText("");
								name_tf.setText("");

							}
						}

					}

				}
			}
		});

		u_btn.addActionListener(new ActionListener() { // 업데이트
			@Override
			public void actionPerformed(ActionEvent e) {
				String menu = null;
				// 업데이트는 3가지 에 필드 값 이 필요 하다 .
				// 똑같이 필드 마다 입력값 이 문자열 숫자가 있으니
				// 값을변경시켜준다

				String s1 = id_tf.getText().trim();
				int s1_1 = Integer.parseInt(s1);

				// s2 가 널인지 체크
				String s2 = name_tf.getText().trim();
				if (editMode == EDIT_MENU) {
					if (s2.length() > 0) {

						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);
						vo.setM_name(s2);

						name_tf.setText("");
						int change = JOptionPane.showConfirmDialog(getParent(), "메뉴를 수정하시겠습니까?", "편집 확인",
								JOptionPane.YES_NO_OPTION);
						if (change == 0) {
							// 수정 버튼 누르면 값이 업데이트 된다
							int result = ManagerDAO.getUpDate1(vo);
							// 그러고 메뉴 가 수정되었다 다이얼로그 출력

							JOptionPane.showMessageDialog(getParent(), "메뉴가 수정되었습니다.");
							// 수정되지 않았으면 수정실패

						} else {
							JOptionPane.showMessageDialog(null, "수정 실패 ");
						}

						int a = category_cb.getSelectedIndex();
						//String c_name = cItems[a].trim();
						//int c_id = ManagerDAO.getCategoryId(c_name);
						
						// info[0]: c_id, info[1]: c_main
						String[] info = cItems[a].trim().split("   ");
						int c_id = Integer.parseInt(info[0].trim());
						
						vo.setC_id(c_id);

						outputMenuList(c_id);
					}

					String s3 = price_tf.getText().trim();
					int s1_3 = 0;
					if (s3.length() > 0) {
						s1_3 = Integer.parseInt(s3);

						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);
						vo.setPrice(s1_3);

						int change = JOptionPane.showConfirmDialog(getParent(), "메뉴를 수정하시겠습니까?", "편집 확인",
								JOptionPane.YES_NO_OPTION);
						if (change == 0) {
							// 메뉴 수정 기능
							int result = ManagerDAO.getUpDate2(vo);
							JOptionPane.showMessageDialog(getParent(), "메뉴가 수정되었습니다.");

						} else {
							JOptionPane.showMessageDialog(null, "수정 실패 ");
						}
						int a = category_cb.getSelectedIndex();
						//String c_name = cItems[a].trim();
						//int c_id = ManagerDAO.getCategoryId(c_name);
						
						// info[0]: c_id, info[1]: c_main
						String[] info = cItems[a].trim().split("   ");
						int c_id = Integer.parseInt(info[0].trim());
						
						vo.setC_id(c_id);

						outputMenuList(c_id);
						price_tf.setText("");
					}

					// s4 가 널인지 체크

					String s4 = textField.getText().trim();

					if (s4.length() > 0) {

						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);
						vo.setImage(s4);

						int change = JOptionPane.showConfirmDialog(getParent(), "메뉴를 수정하시겠습니까?", "편집 확인",
								JOptionPane.YES_NO_OPTION);
						if (change == 0) {
							int result = ManagerDAO.getUpDate3(vo);
							// 메뉴 수정 기능
							JOptionPane.showMessageDialog(getParent(), "메뉴가 수정되었습니다.");

						} else {
							JOptionPane.showMessageDialog(null, "수정 실패 ");
						}
						int a = category_cb.getSelectedIndex();
						//String c_name = cItems[a].trim();
						//int c_id = ManagerDAO.getCategoryId(c_name);
						
						// info[0]: c_id, info[1]: c_main
						String[] info = cItems[a].trim().split("   ");
						int c_id = Integer.parseInt(info[0].trim());
						
						vo.setC_id(c_id);

						outputMenuList(c_id);
						textField.setText("");
					}

					// 만약 s1, s2,s3 필드값이 0보다 클경우!

					if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0 && s4.length() > 0) {

						// MenuVO 값 m_id , m_name ,price 값 이 저장되어있다.
						MenuVO vo = new MenuVO();

						vo.setM_id(s1_1);
						vo.setM_name(s2);
						vo.setPrice(s1_3);
						vo.setImage(s4);

						// int result DAO.getUpDate(vo) < 해서 vo 입력값을 저장해준다
						// result 값이 0보다 크면 업데이트 하겠다 .

						int change = JOptionPane.showConfirmDialog(getParent(), "전체 수정을 다시 하시겠습니까?", "편집 확인",
								JOptionPane.YES_NO_OPTION);
						if (change == 0) {

							// 메뉴 수정 기능
							JOptionPane.showMessageDialog(getParent(), "메뉴 수정완료");
							int result = ManagerDAO.getUpDate(vo);

						} else {
							JOptionPane.showMessageDialog(null, "부분 수정해주세요! ");

						}

						// 메뉴 vo 생성 해주기 그리고 카테고리 콤보박스 값을 찾아서 인덱스
						// 값을 받고 인트형으로 a 에 저장 그리고 String c_name = 해주고
						// 카테고리 메뉴별 a 에넣어주고 공백제거
						// 그리고 c_id = 카테고리 아이디 , 이름을 가져와서 VO.setC_id 로 값넣어주기
						// 그리고 나면 이제 메뉴 리스트가 출력 되서 테이블 값에 보인다 .
						int a = category_cb.getSelectedIndex();
						
						//String c_name = cItems[a].trim();
						//int c_id = ManagerDAO.getCategoryId(c_name);
						
						// info[0]: c_id, info[1]: c_main
						String[] info = cItems[a].trim().split("   ");
						int c_id = Integer.parseInt(info[0].trim());
						
						vo.setC_id(c_id);

						outputMenuList(c_id);
					}

				} else if (editMode == EDIT_TAB) {
					String c_id = id_tf.getText().trim();
					int s1_2 = Integer.parseInt(c_id);
					String s3 = name_tf.getText().trim();

					if (c_id.length() > 0 && s3.length() > 0) {
						CategoryVO vo = new CategoryVO();
						vo.setC_id(s1_2);
						vo.setC_name(s3);

						int change = JOptionPane.showConfirmDialog(getParent(), "전체 수정을 다시 하시겠습니까?", "편집 확인",
								JOptionPane.YES_NO_OPTION);
						id_tf.setText("");
						name_tf.setText("");
						if (change == 0) {

							// 메뉴 수정 기능
							JOptionPane.showMessageDialog(getParent(), "메뉴 수정완료");
							int result = ManagerDAO.getUpDate4(vo);

						} else {
							JOptionPane.showMessageDialog(null, "부분 수정해주세요! ");
						}

					}

				}

			}

		});

		d_btn.addActionListener(new ActionListener() { // 삭제

			@Override
			public void actionPerformed(ActionEvent e) {
				String menu = null;
				// String 형으로 텍스트 배열 을 받는다 뒤에 는 공백 처리
				// 하지만 m_id 는 int 형이니까 int 형으로바꿔준다
				// 메서드를 써서
				String m_id = id_tf.getText().trim();
				int s1_1 = Integer.parseInt(m_id);

				// m_id 입력하는 길이가 o 보다 크면 삭제한다 m_id를
				if (editMode == EDIT_MENU) {

					if (m_id.length() > 0) {
						int result = ManagerDAO.getDel(m_id);

						// or 삭제하시겠습니까? Y/N
						int delete = JOptionPane.showConfirmDialog(getParent(), "메뉴를 삭제하시겠습니까?", "삭제확인",
								JOptionPane.YES_NO_OPTION);

						if (delete == 0) {
							// 메뉴삭제기능

							JOptionPane.showMessageDialog(getParent(), "메뉴가 삭제되었습니다.");
							// 메뉴가 삭제 다이얼로그가 종료 되면은 삭제 되었다는 말과 함께
							// 초기화 되서 안에있는 입력값이 초기화됨 필드 값
							id_tf.setText("");

							// 메뉴 vo 생성 해주기 그리고 카테고리 콤보박스 값을 찾아서 인덱스
							// 값을 받고 인트형으로 a 에 저장 그리고 String c_name = 해주고
							// 카테고리 메뉴별 a 에넣어주고 공백제거
							// 그리고 c_id = 카테고리 아이디 , 이름을 가져와서 VO.setC_id 로 값넣어주기
							// 그리고 나면 이제 메뉴 리스트가 출력 되서 테이블 값에 보인다 .
							MenuVO vo = new MenuVO();
							int a = category_cb.getSelectedIndex();
							
							//String c_name = cItems[a].trim();
							//int c_id = ManagerDAO.getCategoryId(c_name);
							
							// info[0]: c_id, info[1]: c_main
							String[] info = cItems[a].trim().split("   ");
							int c_id = Integer.parseInt(info[0].trim());
							
							vo.setC_id(c_id);

							outputMenuList(c_id);
						}
					}

				} else if (editMode == EDIT_TAB) {

					String c_id = id_tf.getText().trim();
					int s1_2 = Integer.parseInt(c_id);

					if (c_id.length() > 0) {
						//int result1 = ManagerDAO.getDel1(c_id);
						int delete = JOptionPane.showConfirmDialog(getParent(), "메뉴를 삭제하시겠습니까?", "삭제확인",
								JOptionPane.YES_NO_OPTION);

						if (delete == 0) {
							// 메뉴삭제기능
							// 삭제 카테고리의 메뉴들 모두 삭제
							List<MenuVO> mvoList = ManagerDAO.getMenuList(s1_2);
							for(int i = 0; i < mvoList.size(); i++) {
								ManagerDAO.getDel(String.valueOf(mvoList.get(i).getM_id()));
							}
							
							int result1 = ManagerDAO.getDel1(c_id);
							JOptionPane.showMessageDialog(getParent(), "메뉴가 삭제되었습니다.");
							// 메뉴가 삭제 다이얼로그가 종료 되면은 삭제 되었다는 말과 함께
							// 초기화 되서 안에있는 입력값이 초기화됨 필드 값
							id_tf.setText("");

//							outputCategorylist(c_id);
						}

					}

				}
			}
		});

		main_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// mngMain.setVisible(true);
				dispose();
			}
		});

		exit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// int res = JOptionPane.showOptionDialog(getParent(), "로그아웃 하시겠습니까?", "로그아웃",
				// JOptionPane.YES_NO_OPTION,
				// JOptionPane.QUESTION_MESSAGE, null, null, null);
				int res = JOptionPane.showConfirmDialog(getParent(), "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (res == 0) { // yes
					new Logout_Success(mngMain, mainWidth, mainHeight);
					dispose();
				}
			}
		});

	}

	// c_id에 해당하는 메뉴들을 테이블에 출력한다.
	public void outputMenuList(int c_id) {
		// 메뉴 디비에서 해당 c_id의 메뉴들을 가져온다.
		List<MenuVO> list = ManagerDAO.getMenuList(c_id);
		int menuCnt = list.size();

		String[] record = new String[columnNames.length];

		// 테이블에 출력된 내용 모두 삭제 (화면 클리어)
		mngMain.procOrder.clearTable(model);

		// 테이블에 출력
		for (int i = 0; i < menuCnt; i++) {
			// "No.", "ID", "메뉴 이름", "가격", "이미지"
			record[0] = " " + String.valueOf(i + 1);
			record[1] = " " + String.valueOf(list.get(i).getM_id());
			record[2] = list.get(i).getM_name();
			record[3] = " " + String.valueOf(list.get(i).getPrice());
			record[4] = list.get(i).getImage();
			model.addRow(record);
		}
	}
}

//  이거는  테이블 자체에에서 다시  내용을 삭제하고 다시 화면을 만드는거다
// 다른생각을해보자
//	public void outputCategorylist(int c_id) {
//		List<CategoryVO> list = ManagerDAO.getCategoryList1(c_id);
//		int categoryCnt = list.size();
//
//		String[] record = new String[columnNames.length];
//		mngMain.procOrder.clearTable(model);
//
//		for (int i = 0; i < categoryCnt; i++) {
//			record[0] = " " + String.valueOf(i + 1);
//			record[1] = " " + String.valueOf(list.get(i).getC_id());
//			record[2] = " " + String.valueOf(list.get(i).getC_name());
//			record[3] = list.get(i).getC_name();
//
//			model.addRow(record);
//		}
//
//	}
//
//}
