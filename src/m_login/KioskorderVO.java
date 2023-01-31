package m_login;

public class KioskorderVO {
	private int order_idx;
	private String order_date;
	private int order_no;
	private String order_list;
	private String phone;
	private int total;
	private String takeaway;
	
	public int getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(int order_idx) {
		this.order_idx = order_idx;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getOrder_list() {
		return order_list;
	}
	public void setOrder_list(String order_list) {
		this.order_list = order_list;
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
	public String getTakeaway() {
		return takeaway;
	}
	public void setTakeaway(String takeaway) {
		this.takeaway = takeaway;
	}
}
