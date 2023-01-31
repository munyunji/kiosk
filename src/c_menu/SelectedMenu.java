package c_menu;

import java.io.Serializable;

public class SelectedMenu implements Serializable {
	private int m_id = 0;
	private String m_name = "";
	private int number = 0; //주문한 메뉴 개수
	private int price = 0;

	public int getM_id() {
		return m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public int getNumber() {
		return number;
	}
	public int getPrice() {
		return price;
	}
	
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
