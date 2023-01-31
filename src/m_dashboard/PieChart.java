package m_dashboard;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import c_menu.CategoryVO;
import c_menu.MenuVO;
import m_login.ManagerDAO;

public class PieChart extends JPanel {
	//ArrayList<Slice> pie = new ArrayList<>();
	ArrayList<Slice> pie;
	
	public PieChart() {
		pie = new ArrayList<>();
	}
	
	public void setValues(ArrayList<Slice> pie) {
		this.pie = pie;
	}
	
	//public void paintComponent(Graphics g) {
	@Override
	public void paint(Graphics g) {
		double total = 0.0;
		for (Slice s : pie) {
			total += s.value;
		}
		
		double currValue = 0.0;
		int start = 0;
		for (Slice s : pie) {
			start = (int) (currValue * 360 / total);
			int angle = (int) (s.value * 360 / total);
			
			g.setColor(s.color);
			g.fillArc(0, 0, 250, 250, start, angle);
			currValue += s.value;
		}
	}
}

class Slice {
	int c_id;
	int m_id;
	String name;
	double value;
	Color color;
	
	public Slice(int c_id, int m_id, String name, double value, Color color) {
		this.c_id = c_id;
		this.m_id = m_id;
		this.name = name;
		this.value = value;
		this.color = color;
	}
		
	public String getName() {
		return name;
	}

	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setColorById() {
		int cIndex, mIdx, pos;
		
		color = Color.WHITE;
		
		// c_id가 카테고리 디비의 몇번째인지 위치를 찾는다
		List<CategoryVO> cvoList = ManagerDAO.getCategoryList();
		if (cvoList.size() < 1) {	
			System.out.println("setColorById() : getCategoryList() null: " + m_id); 
			return;
		}

		for (cIndex = 0; cIndex < cvoList.size(); cIndex++) {
			if (cvoList.get(cIndex).getC_id() == c_id)
				break;
		}		
		if (cIndex >= cvoList.size()) {
			System.out.println("setColorById(): 일치하는 c_id가 DB에 없음: " + c_id);
			return;
		}

		// m_id가 해당 카테고리에서 몇번째 메뉴인지 위치를 찾는다
		List<MenuVO> mvoList = ManagerDAO.getMenuList(c_id);
		if (mvoList.size() < 1) {
			System.out.println("setColorById() : getMenuList(c_id) null: " + c_id); 
			return;
		}
		
		for (mIdx = 0; mIdx < mvoList.size(); mIdx++) {
			if (mvoList.get(mIdx).getM_id() == m_id)
				break;
		}
		if (mIdx >= mvoList.size()) {
			System.out.println("setColorById(): 일치하는 m_id가 DB에 없음: " + m_id);
			return;
		}
		
		pos = (cIndex+1) * 100 + (mIdx+1);
		
		switch (pos) {
		case 101 : color = new Color(237, 231, 246); break;
		case 102 : color = new Color(209, 196, 233); break;
		case 103 : color = new Color(179, 157, 219); break;
		case 104 : color = new Color(149, 117, 205); break;
		case 105 : color = new Color(126, 87, 194); break;
		case 106 : color = new Color(103, 58, 183); break;
		case 107 : color = new Color(94, 53, 177); break;
		case 108 : color = new Color(81, 45, 168); break;
		
		case 201 : color = new Color(225, 245, 254); break;
		case 202 : color = new Color(179, 229, 252); break;
		case 203 : color = new Color(129, 212, 250); break;
		case 204 : color = new Color(79, 195, 247); break;
		case 205 : color = new Color(41, 182, 246); break;
		case 206 : color = new Color(3, 169, 244); break;
		case 207 : color = new Color(3, 155, 229); break;
		case 208 : color = new Color(2, 136, 209); break;
		
		case 301 : color = new Color(175, 180, 43); break;
		case 302 : color = new Color(192, 202, 51); break;
		case 303 : color = new Color(205, 220, 57); break;
		case 304 : color = new Color(212, 225, 87); break;
		case 305 : color = new Color(220, 231, 117); break;
		case 306 : color = new Color(230, 238, 156); break;
		case 307 : color = new Color(240, 244, 195); break;
		case 308 : color = new Color(249, 251, 231); break;
		
		case 401 : color = new Color(239, 235, 233); break;
		case 402 : color = new Color(215, 204, 200); break;
		case 403 : color = new Color(188, 170, 164); break;
		case 404 : color = new Color(161, 136, 127); break;
		case 405 : color = new Color(141, 110, 99); break;
		case 406 : color = new Color(121, 85, 72); break;
		case 407 : color = new Color(109, 76, 65); break;
		case 408 : color = new Color(93, 64, 55); break;
		
		case 501 : color = new Color(236, 239, 241); break;
		case 502 : color = new Color(207, 216, 220); break;
		case 503 : color = new Color(176, 190, 197); break;
		case 504 : color = new Color(144, 164, 174); break;
		case 505 : color = new Color(120, 144, 156); break;
		case 506 : color = new Color(96, 125, 139); break;
		case 507 : color = new Color(84, 110, 122); break;
		case 508 : color = new Color(69, 90, 100); break;
		}
	}
}
