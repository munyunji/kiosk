package c_menu;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
	ArrayList<SelectedMenu> value;
	String takeaway;
	String phone;
	int total; //주문 금액
	
	// manager system에서 설정하여 사용
	String date; 
	int orderNumber; //주문 번호

	public ArrayList<SelectedMenu> getValue() {
		return value;
	}

	public void setValue(ArrayList<SelectedMenu> value) {
		this.value = value;
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
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
