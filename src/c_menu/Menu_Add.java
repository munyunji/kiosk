package c_menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import c_basketlist.BasketList;

public class Menu_Add extends JPanel {
	Menu_Main main;
	final String IMG_PATH = "src/images/";
	List<MenuVO> list;
	int menuCnt;

	JButton add_btn1, add_btn2, add_btn3, add_btn4;
	JButton add_btn5, add_btn6, add_btn7, add_btn8;

	SpinnerNumberModel numModel1;
	SpinnerNumberModel numModel2;
	SpinnerNumberModel numModel3;
	SpinnerNumberModel numModel4;
	SpinnerNumberModel numModel5;
	SpinnerNumberModel numModel6;
	SpinnerNumberModel numModel7;
	SpinnerNumberModel numModel8;

	JSpinner spinner1, spinner2, spinner3, spinner4;
	JSpinner spinner5, spinner6, spinner7, spinner8;

	JButton c_btn1, c_btn2, c_btn3, c_btn4;
	JButton c_btn5, c_btn6, c_btn7, c_btn8;

	public Menu_Add(Menu_Main main, CategoryVO cvo) {

		list = DAO.getMenuList(cvo.getC_id());

		// 가져온 menu 개수 구하기
		menuCnt = list.size();

		setBackground(Color.DARK_GRAY);
		setLayout(null);

		// add menu1
		if (menuCnt >= 1) {
			add_btn1 = new JButton("차슈");
			add_btn1.setBackground(Color.DARK_GRAY);
			ImageIcon img1 = new ImageIcon(IMG_PATH + list.get(0).getImage());
			add_btn1.setIcon(img1);
			add_btn1.setBounds(30, 35, 200, 200);
			add(add_btn1);

			JLabel menu1 = new JLabel(list.get(0).getM_name());
			menu1.setForeground(Color.WHITE);
			menu1.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu1.setBounds(30, 240, 80, 30);
			add(menu1);

			JLabel price1 = new JLabel(String.valueOf(list.get(0).getPrice() + "원"));
			price1.setForeground(Color.WHITE);
			price1.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price1.setBounds(116, 240, 80, 30);
			add(price1);

			numModel1 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner1 = new JSpinner(numModel1);
			spinner1.setBounds(190, 240, 40, 30);
			spinner1.setEnabled(false);	
			add(spinner1);

			c_btn1 = new JButton("장바구니");
			c_btn1.setBackground(Color.DARK_GRAY);
			c_btn1.setForeground(Color.WHITE);
			c_btn1.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn1.setBounds(30, 280, 200, 35);
			add(c_btn1);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(0).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn1.setBorder(new LineBorder(Color.RED, 7));
						spinner1.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(0).getM_id(), list.get(0).getM_name(), list.get(0).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn1.setBorder(new LineBorder(Color.BLACK, 1));
						spinner1.setValue(0);
						spinner1.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});

			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner1.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner1.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(0).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu2
		if (menuCnt >= 2) {
			add_btn2 = new JButton("새우튀김");
			add_btn2.setBackground(Color.DARK_GRAY);
			ImageIcon img2 = new ImageIcon(IMG_PATH + list.get(1).getImage());
			add_btn2.setIcon(img2);
			add_btn2.setBounds(270, 35, 200, 200);
			add(add_btn2);

			JLabel menu2 = new JLabel(list.get(1).getM_name());
			menu2.setForeground(Color.WHITE);
			menu2.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu2.setBounds(270, 240, 90, 30);
			add(menu2);

			JLabel price2 = new JLabel(String.valueOf(list.get(1).getPrice() + "원"));
			price2.setForeground(Color.WHITE);
			price2.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price2.setBounds(360, 239, 80, 30);
			add(price2);

			numModel2 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner2 = new JSpinner(numModel2);
			spinner2.setBounds(430, 240, 40, 30);
			spinner2.setEnabled(false);	
			add(spinner2);

			c_btn2 = new JButton("장바구니");
			c_btn2.setBackground(Color.DARK_GRAY);
			c_btn2.setForeground(Color.WHITE);
			c_btn2.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn2.setBounds(270, 280, 200, 35);
			add(c_btn2);
			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(1).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn2.setBorder(new LineBorder(Color.RED, 7));
						spinner2.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(1).getM_id(), list.get(1).getM_name(), list.get(1).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn2.setBorder(new LineBorder(Color.BLACK, 1));
						spinner2.setValue(0);
						spinner2.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner2.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner2.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(1).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu3
		if (menuCnt >= 3) {
			add_btn3 = new JButton("믹스튀김");
			add_btn3.setBackground(Color.DARK_GRAY);
			ImageIcon img3 = new ImageIcon(IMG_PATH + list.get(2).getImage());
			add_btn3.setIcon(img3);
			add_btn3.setBounds(510, 35, 200, 200);
			add(add_btn3);

			JLabel menu3 = new JLabel(list.get(2).getM_name());
			menu3.setForeground(Color.WHITE);
			menu3.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu3.setBounds(510, 240, 80, 30);
			add(menu3);

			JLabel price3 = new JLabel(String.valueOf(list.get(2).getPrice() + "원"));
			price3.setForeground(Color.WHITE);
			price3.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price3.setBounds(590, 240, 80, 30);
			add(price3);

			numModel3 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner3 = new JSpinner(numModel3);
			spinner3.setBounds(670, 240, 40, 30);
			spinner3.setEnabled(false);	
			add(spinner3);

			JButton c_btn3 = new JButton("장바구니");
			c_btn3.setBackground(Color.DARK_GRAY);
			c_btn3.setForeground(Color.WHITE);
			c_btn3.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn3.setBounds(510, 280, 200, 35);
			add(c_btn3);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(2).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn3.setBorder(new LineBorder(Color.RED, 7));
						spinner3.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(2).getM_id(), list.get(2).getM_name(), list.get(2).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn3.setBorder(new LineBorder(Color.BLACK, 1));
						spinner3.setValue(0);
						spinner3.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner3.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner3.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(2).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}
		// add menu4
		if (menuCnt >= 4) {
			add_btn4 = new JButton("반숙계란");
			add_btn4.setBackground(Color.DARK_GRAY);
			ImageIcon img4 = new ImageIcon(IMG_PATH + list.get(3).getImage());
			add_btn4.setIcon(img4);
			add_btn4.setBounds(750, 35, 200, 200);
			add(add_btn4);

			JLabel menu4 = new JLabel(list.get(3).getM_name());
			menu4.setForeground(Color.WHITE);
			menu4.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu4.setBounds(750, 240, 80, 30);
			add(menu4);

			JLabel price4 = new JLabel(String.valueOf(list.get(3).getPrice() + "원"));
			price4.setForeground(Color.WHITE);
			price4.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price4.setBounds(841, 239, 80, 30);
			add(price4);

			numModel4 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner4 = new JSpinner(numModel4);
			spinner4.setBounds(910, 240, 40, 30);
			spinner4.setEnabled(false);	
			add(spinner4);

			c_btn4 = new JButton("장바구니");
			c_btn4.setBackground(Color.DARK_GRAY);
			c_btn4.setForeground(Color.WHITE);
			c_btn4.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn4.setBounds(750, 280, 200, 35);
			add(c_btn4);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(3).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn4.setBorder(new LineBorder(Color.RED, 7));
						spinner4.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(3).getM_id(), list.get(3).getM_name(), list.get(3).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn4.setBorder(new LineBorder(Color.BLACK, 1));
						spinner4.setValue(0);
						spinner4.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner4.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner4.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(3).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu5
		if (menuCnt >= 5) {
			add_btn5 = new JButton("밥");
			add_btn5.setBackground(Color.DARK_GRAY);
			ImageIcon img5 = new ImageIcon(IMG_PATH + list.get(4).getImage());
			add_btn5.setIcon(img5);
			add_btn5.setBounds(30, 365, 200, 200);
			add(add_btn5);

			JLabel menu5 = new JLabel(list.get(4).getM_name());
			menu5.setForeground(Color.WHITE);
			menu5.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu5.setBounds(30, 575, 80, 30);
			add(menu5);

			JLabel price5 = new JLabel(String.valueOf(list.get(4).getPrice() + "원"));
			price5.setForeground(Color.WHITE);
			price5.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price5.setBounds(122, 575, 80, 30);
			add(price5);

			numModel5 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner5 = new JSpinner(numModel5);
			spinner5.setBounds(190, 575, 40, 30);
			spinner5.setEnabled(false);	
			add(spinner5);

			c_btn5 = new JButton("장바구니");
			c_btn5.setBackground(Color.DARK_GRAY);
			c_btn5.setForeground(Color.WHITE);
			c_btn5.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn5.setBounds(30, 615, 200, 35);
			add(c_btn5);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(4).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn5.setBorder(new LineBorder(Color.RED, 7));
						spinner5.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(4).getM_id(), list.get(4).getM_name(), list.get(4).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn5.setBorder(new LineBorder(Color.BLACK, 1));
						spinner5.setValue(0);
						spinner5.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner5.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner5.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(4).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu6
		if (menuCnt >= 6) {
			add_btn6 = new JButton("면");
			add_btn6.setBackground(Color.DARK_GRAY);
			ImageIcon img6 = new ImageIcon(IMG_PATH + list.get(5).getImage());
			add_btn6.setIcon(img6);
			add_btn6.setBounds(270, 365, 200, 200);
			add(add_btn6);

			JLabel menu6 = new JLabel(list.get(5).getM_name());
			;
			menu6.setForeground(Color.WHITE);
			menu6.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu6.setBounds(270, 575, 80, 30);
			add(menu6);

			JLabel price6 = new JLabel(String.valueOf(list.get(5).getPrice() + "원"));
			price6.setForeground(Color.WHITE);
			price6.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price6.setBounds(360, 575, 80, 30);
			add(price6);

			numModel6 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner6 = new JSpinner(numModel6);
			spinner6.setBounds(430, 575, 40, 30);
			spinner6.setEnabled(false);	
			add(spinner6);

			c_btn6 = new JButton("장바구니");
			c_btn6.setBackground(Color.DARK_GRAY);
			c_btn6.setForeground(Color.WHITE);
			c_btn6.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn6.setBounds(270, 615, 200, 35);
			add(c_btn6);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(5).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn6.setBorder(new LineBorder(Color.RED, 7));
						spinner6.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(5).getM_id(), list.get(5).getM_name(), list.get(5).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn6.setBorder(new LineBorder(Color.BLACK, 1));
						spinner6.setValue(0);
						spinner6.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner6.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner6.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(5).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu7
		if (menuCnt >= 7) {
			add_btn7 = new JButton("매운소스");
			add_btn7.setBackground(Color.DARK_GRAY);
			ImageIcon img7 = new ImageIcon(IMG_PATH + list.get(6).getImage());
			add_btn7.setIcon(img7);
			add_btn7.setBounds(510, 365, 200, 200);
			add(add_btn7);

			JLabel menu7 = new JLabel(list.get(6).getM_name());
			menu7.setForeground(Color.WHITE);
			menu7.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu7.setBounds(510, 575, 80, 30);
			add(menu7);

			JLabel price7 = new JLabel(String.valueOf(list.get(6).getPrice() + "원"));
			price7.setForeground(Color.WHITE);
			price7.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price7.setBounds(602, 575, 80, 30);
			add(price7);

			numModel7 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner7 = new JSpinner(numModel7);
			spinner7.setBounds(670, 575, 40, 30);
			spinner7.setEnabled(false);	
			add(spinner7);

			c_btn7 = new JButton("장바구니");
			c_btn7.setBackground(Color.DARK_GRAY);
			c_btn7.setForeground(Color.WHITE);
			c_btn7.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn7.setBounds(510, 615, 200, 35);
			add(c_btn7);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(6).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn7.setBorder(new LineBorder(Color.RED, 7));
						spinner7.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(6).getM_id(), list.get(6).getM_name(), list.get(6).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn7.setBorder(new LineBorder(Color.BLACK, 1));
						spinner7.setValue(0);
						spinner7.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner7.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner7.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(6).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		// add menu8
		if (menuCnt >= 8) {
			add_btn8 = new JButton("청양고추");
			add_btn8.setBackground(Color.DARK_GRAY);
			ImageIcon img8 = new ImageIcon(IMG_PATH + list.get(7).getImage());
			add_btn8.setIcon(img8);
			add_btn8.setBounds(750, 365, 200, 200);
			add(add_btn8);

			JLabel menu8 = new JLabel(list.get(7).getM_name());
			menu8.setForeground(Color.WHITE);
			menu8.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			menu8.setBounds(750, 575, 80, 30);
			add(menu8);

			JLabel price8 = new JLabel(String.valueOf(list.get(7).getPrice() + "원"));
			price8.setForeground(Color.WHITE);
			price8.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			price8.setBounds(830, 575, 80, 30);
			add(price8);

			numModel8 = new SpinnerNumberModel(0, 0, 100, 1);
			spinner8 = new JSpinner(numModel8);
			spinner8.setBounds(910, 575, 40, 30);
			spinner8.setEnabled(false);	
			add(spinner8);

			c_btn8 = new JButton("장바구니");
			c_btn8.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
			c_btn8.setBackground(Color.DARK_GRAY);
			c_btn8.setForeground(Color.WHITE);
			c_btn8.setBounds(750, 615, 200, 35);
			add(c_btn8);

			// 메뉴 사진을 누르면 해당 메뉴가 선택된다.
			add_btn8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 고객이 선택한 메뉴리스트에 존재하는지 여부를 확인한다.
					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(7).getM_id());
					if (selectedMenu == null) { // 최초 선택한 경우
						add_btn8.setBorder(new LineBorder(Color.RED, 7));
						spinner8.setEnabled(true);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에 추가한다.
						selectedMenu = new SelectedMenu();
						main.addSelectedMenu(selectedMenu,
								list.get(7).getM_id(), list.get(7).getM_name(), list.get(7).getPrice());
					} else { // 선택을 해제하는 경우
						add_btn8.setBorder(new LineBorder(Color.BLACK, 1));
						spinner8.setValue(0);
						spinner8.setEnabled(false);

						// 해당 메뉴를 선택한 메뉴리스트(selectedMenuList)에서 삭제한다.
						main.cancelSelectedMenu(selectedMenu);
					}
				}
			});
			// 고객이 해당 메뉴의 수량을 결정하면 이것을 저장한다.
			spinner8.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int val = (int) spinner8.getValue();

					SelectedMenu selectedMenu = main.getSelectedMenu(list.get(7).getM_id());
					selectedMenu.setNumber(val);
				}
			});
		}

		JButton main_bt = new JButton("< 처음화면");
		main_bt.setBackground(Color.RED);
		main_bt.setForeground(Color.WHITE);
		main_bt.setFont(new Font("안성탕면체 Bold", Font.BOLD, 17));
		main_bt.setBounds(30, 683, 120, 35);
		add(main_bt);

		JButton cart_bt = new JButton("장바구니 >");
		cart_bt.setBackground(Color.RED);
		cart_bt.setForeground(Color.WHITE);
		cart_bt.setFont(new Font("안성탕면체 Bold", Font.BOLD, 17));
		cart_bt.setBounds(830, 683, 120, 35);
		add(cart_bt);

		main_bt.addActionListener(new ActionListener() {
			@Override // 종료버튼을 누르면 메뉴화면을 종료한다.
			public void actionPerformed(ActionEvent e) {
				main.exit_Manu_Main();
			}
		});

		cart_bt.addActionListener(new ActionListener() {
			@Override // 장바구니 버튼을 누르면 장바구니 화면을 생성한다.
			public void actionPerformed(ActionEvent e) {
				new BasketList(main);
			}
		});		
	}
}