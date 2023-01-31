package c_menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Menu_Main extends JFrame {
	public int mainWidth, mainHeight;
	// 고객이 선택한 메뉴들을 저장하는 리스트
	private ArrayList<SelectedMenu> selectedMenuList = new ArrayList<>();
	
	Socket socket;
	ObjectOutputStream out;
	
	JTabbedPane jtab;
	Menu_Rice rice;
	Menu_Noodle noodle;
	Menu_Side side;
	Menu_Add add;
	Menu_Drink drink;

	public Menu_Main(int mainWidth, int mainHeight) {
		super("Select Menu");

		this.mainWidth = mainWidth;
		this.mainHeight = mainHeight;
		
		setBackground(Color.DARK_GRAY);
		
		jtab = new JTabbedPane();
		jtab.setBackground(Color.DARK_GRAY);
		jtab.setForeground(Color.WHITE);
		jtab.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
		// jtab.setBorder(new EmptyBorder(3,3,3,3));
		
		// category DB에서 정보(c_id, c_name)을 가져온다.
		List<CategoryVO> list = DAO.getCategoryList();
		int categoryCnt = list.size();
			
		if (categoryCnt >= 1) {
			rice = new Menu_Rice(this, list.get(0));
			jtab.addTab(" " + list.get(0).getC_name() + "     ", rice);
		}
		
		if (categoryCnt >= 2) {
			noodle = new Menu_Noodle(this, list.get(1));
			jtab.addTab(" " + list.get(1).getC_name() + "     ", noodle);
		}
		
		if (categoryCnt >= 3) {
			side = new Menu_Side(this, list.get(2));
			jtab.addTab(" " + list.get(2).getC_name() + "     ", side);
		}
		
		if (categoryCnt >= 4) {
			add = new Menu_Add(this, list.get(3));
			jtab.addTab(" " + list.get(3).getC_name() + "     ", add);
		}
		
		if (categoryCnt >= 5) {
			drink = new Menu_Drink(this, list.get(4));
			jtab.addTab(" " + list.get(4).getC_name() + "     ", drink);
		}
		
		jtab.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                   	
            }
        });

		add(jtab);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2, mainWidth, mainHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

//	public JPanel createTab(Color color) {
//	        JPanel p = new JPanel(new BorderLayout()) {
//	            private static final long serialVersionUID = 1L;
//
//	            @Override
//	            public Dimension getPreferredSize() {
//	                return new Dimension(400, 300);
//	            }
//	        };
//	        p.setBorder(BorderFactory.createLineBorder(color, 2));
//	        p.setBackground(Color.RED);
//	        return p;
//	    }

	public ArrayList<SelectedMenu> getSelectedMenuList() {
		return selectedMenuList;
	}
	
	public void setSelectedMenuList(ArrayList<SelectedMenu> selectedMenuList) {
		this.selectedMenuList = selectedMenuList;
	}
	
	public void exit_Manu_Main() {
		new Entrance();
		
		//closeClient();
		dispose();
	}

	public SelectedMenu getSelectedMenu(int m_id) {
		for (SelectedMenu selectedMenu : selectedMenuList) {
			if (selectedMenu.getM_id() == m_id)
				return selectedMenu;
		}
		return null;
	}
	
	public void addSelectedMenu(SelectedMenu selectedMenu, int m_id, String m_name, int price) {
		selectedMenu.setM_id(m_id);
		selectedMenu.setM_name(m_name);
		selectedMenu.setPrice(price);
		
		selectedMenuList.add(selectedMenu);
	}
	
	public void cancelSelectedMenu(SelectedMenu selectedMenu) {
		selectedMenuList.remove(selectedMenu);
	}
		
	public void sendOrder(String takeaway, String phone, int total) {
		System.out.println("Socket : " + socket);
		
		if (socket == null)
			connect();
		
		// 지울것 -----------------
		int i = 0;
		for (SelectedMenu sm : selectedMenuList) {
			System.out.println("<selectedMenuList> idx:" + (++i));
			System.out.println(sm.getM_id());
			System.out.println(sm.getM_name());
			System.out.println(sm.getNumber());
			System.out.println(sm.getPrice());
			System.out.println(takeaway);
			System.out.println(phone);
		}
		// 지울것 -----------------
		
		try {
			Order order = new Order();
			order.setValue(selectedMenuList);
			order.setTakeaway(takeaway);
			order.setPhone(phone);
			order.setTotal(total);
			
			out.writeObject(order);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 장바구니 비우기
		clearSelectedMenuList();
	}
	
	private void connect() {
		try {
			//socket = new Socket("192.168.0.40", 7078);
			socket = new Socket("localhost", 7078);
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("connect() : " + socket + out);
	}
	
	private void closeClient() {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clearSelectedMenuList() {
		selectedMenuList.clear();
	}
}
