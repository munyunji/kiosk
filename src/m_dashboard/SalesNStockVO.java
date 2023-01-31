package m_dashboard;

import java.util.List;

import c_menu.CategoryVO;
import c_menu.MenuVO;
import m_login.ManagerDAO;

public class SalesNStockVO {
	private int sales_idx;
	private String sales_date;
	private String dataType; // "init", "res"
	private int day_total;

	private int c1_m101;
	private int c1_m102;
	private int c1_m103;
	private int c1_m104;
	private int c1_m105;
	private int c1_m106;
	private int c1_m107;
	private int c1_m108;

	private int c2_m201;
	private int c2_m202;
	private int c2_m203;
	private int c2_m204;
	private int c2_m205;
	private int c2_m206;
	private int c2_m207;
	private int c2_m208;

	private int c3_m301;
	private int c3_m302;
	private int c3_m303;
	private int c3_m304;
	private int c3_m305;
	private int c3_m306;
	private int c3_m307;
	private int c3_m308;

	private int c4_m401;
	private int c4_m402;
	private int c4_m403;
	private int c4_m404;
	private int c4_m405;
	private int c4_m406;
	private int c4_m407;
	private int c4_m408;

	private int c5_m501;
	private int c5_m502;
	private int c5_m503;
	private int c5_m504;
	private int c5_m505;
	private int c5_m506;
	private int c5_m507;
	private int c5_m508;

	public SalesNStockVO() {
	};

	public SalesNStockVO(int salesTotal, int stockQuantity) {
		dataType = "res";

		day_total = salesTotal;

		c1_m101 = stockQuantity;
		c1_m102 = stockQuantity;
		c1_m103 = stockQuantity;
		c1_m104 = stockQuantity;
		c1_m105 = stockQuantity;
		c1_m106 = stockQuantity;
		c1_m107 = stockQuantity;
		c1_m108 = stockQuantity;

		c2_m201 = stockQuantity;
		c2_m202 = stockQuantity;
		c2_m203 = stockQuantity;
		c2_m204 = stockQuantity;
		c2_m205 = stockQuantity;
		c2_m206 = stockQuantity;
		c2_m207 = stockQuantity;
		c2_m208 = stockQuantity;

		c3_m301 = stockQuantity;
		c3_m302 = stockQuantity;
		c3_m303 = stockQuantity;
		c3_m304 = stockQuantity;
		c3_m305 = stockQuantity;
		c3_m306 = stockQuantity;
		c3_m307 = stockQuantity;
		c3_m308 = stockQuantity;

		c4_m401 = stockQuantity;
		c4_m402 = stockQuantity;
		c4_m403 = stockQuantity;
		c4_m404 = stockQuantity;
		c4_m405 = stockQuantity;
		c4_m406 = stockQuantity;
		c4_m407 = stockQuantity;
		c4_m408 = stockQuantity;

		c5_m501 = stockQuantity;
		c5_m502 = stockQuantity;
		c5_m503 = stockQuantity;
		c5_m504 = stockQuantity;
		c5_m505 = stockQuantity;
		c5_m506 = stockQuantity;
		c5_m507 = stockQuantity;
		c5_m508 = stockQuantity;
	}

	public SalesNStockVO(int salesTotal, int c_id, int stockQuantity) {
		dataType = "res";

		day_total = salesTotal;

		List<CategoryVO> cvoList = ManagerDAO.getCategoryList();
		if (cvoList.size() < 1) {
			System.out.println("SalesNStockVO(c_id) : getCategoryList() null: " + c_id);
			return;
		}

		int index;
		for (index = 0; index < cvoList.size(); index++) {
			if (cvoList.get(index).getC_id() == c_id)
				break;
		}

		if (index >= cvoList.size()) {
			System.out.println("SalesNStockVO(): 일치하는 c_id가 DB에 없음: " + c_id);
			return;
		}

		switch (index + 1) {
		case 1:
			c1_m101 = stockQuantity;
			c1_m102 = stockQuantity;
			c1_m103 = stockQuantity;
			c1_m104 = stockQuantity;
			c1_m105 = stockQuantity;
			c1_m106 = stockQuantity;
			c1_m107 = stockQuantity;
			c1_m108 = stockQuantity;
			break;

		case 2:
			c2_m201 = stockQuantity;
			c2_m202 = stockQuantity;
			c2_m203 = stockQuantity;
			c2_m204 = stockQuantity;
			c2_m205 = stockQuantity;
			c2_m206 = stockQuantity;
			c2_m207 = stockQuantity;
			c2_m208 = stockQuantity;
			break;

		case 3:
			c3_m301 = stockQuantity;
			c3_m302 = stockQuantity;
			c3_m303 = stockQuantity;
			c3_m304 = stockQuantity;
			c3_m305 = stockQuantity;
			c3_m306 = stockQuantity;
			c3_m307 = stockQuantity;
			c3_m308 = stockQuantity;
			break;

		case 4:
			c4_m401 = stockQuantity;
			c4_m402 = stockQuantity;
			c4_m403 = stockQuantity;
			c4_m404 = stockQuantity;
			c4_m405 = stockQuantity;
			c4_m406 = stockQuantity;
			c4_m407 = stockQuantity;
			c4_m408 = stockQuantity;
			break;

		case 5:
			c5_m501 = stockQuantity;
			c5_m502 = stockQuantity;
			c5_m503 = stockQuantity;
			c5_m504 = stockQuantity;
			c5_m505 = stockQuantity;
			c5_m506 = stockQuantity;
			c5_m507 = stockQuantity;
			c5_m508 = stockQuantity;
			break;
		}
	}

	public void setSalesNStockVO(int salesTotal, int stockQuantity) {
		dataType = "res";

		day_total = salesTotal;

		c1_m101 = stockQuantity;
		c1_m102 = stockQuantity;
		c1_m103 = stockQuantity;
		c1_m104 = stockQuantity;
		c1_m105 = stockQuantity;
		c1_m106 = stockQuantity;
		c1_m107 = stockQuantity;
		c1_m108 = stockQuantity;

		c2_m201 = stockQuantity;
		c2_m202 = stockQuantity;
		c2_m203 = stockQuantity;
		c2_m204 = stockQuantity;
		c2_m205 = stockQuantity;
		c2_m206 = stockQuantity;
		c2_m207 = stockQuantity;
		c2_m208 = stockQuantity;

		c3_m301 = stockQuantity;
		c3_m302 = stockQuantity;
		c3_m303 = stockQuantity;
		c3_m304 = stockQuantity;
		c3_m305 = stockQuantity;
		c3_m306 = stockQuantity;
		c3_m307 = stockQuantity;
		c3_m308 = stockQuantity;

		c4_m401 = stockQuantity;
		c4_m402 = stockQuantity;
		c4_m403 = stockQuantity;
		c4_m404 = stockQuantity;
		c4_m405 = stockQuantity;
		c4_m406 = stockQuantity;
		c4_m407 = stockQuantity;
		c4_m408 = stockQuantity;

		c5_m501 = stockQuantity;
		c5_m502 = stockQuantity;
		c5_m503 = stockQuantity;
		c5_m504 = stockQuantity;
		c5_m505 = stockQuantity;
		c5_m506 = stockQuantity;
		c5_m507 = stockQuantity;
		c5_m508 = stockQuantity;
	}

	public void setSalesNStockVO(int salesTotal, int c_id, int stockQuantity) {
		dataType = "res";
		
		day_total = salesTotal;
		
		List<CategoryVO> cvoList = ManagerDAO.getCategoryList();
		if (cvoList.size() < 1) {	
			System.out.println("setSalesNStockVO(c_id) : getCategoryList() null: " + c_id); 
			return;
		}

	    int index;
		for (index = 0; index < cvoList.size(); index++) {
			if (cvoList.get(index).getC_id() == c_id)
				break;
		}
		
		if (index >= cvoList.size()) {
			System.out.println("setSalesNStockVO(): 일치하는 c_id가 DB에 없음: " + c_id);
			return;
		}
		
		switch (index + 1) {
		case 1:
			c1_m101 = stockQuantity;
			c1_m102 = stockQuantity;
			c1_m103 = stockQuantity;
			c1_m104 = stockQuantity;
			c1_m105 = stockQuantity;
			c1_m106 = stockQuantity;
			c1_m107 = stockQuantity;
			c1_m108 = stockQuantity;
			break;

		case 2:
			c2_m201 = stockQuantity;
			c2_m202 = stockQuantity;
			c2_m203 = stockQuantity;
			c2_m204 = stockQuantity;
			c2_m205 = stockQuantity;
			c2_m206 = stockQuantity;
			c2_m207 = stockQuantity;
			c2_m208 = stockQuantity;
			break;

		case 3:
			c3_m301 = stockQuantity;
			c3_m302 = stockQuantity;
			c3_m303 = stockQuantity;
			c3_m304 = stockQuantity;
			c3_m305 = stockQuantity;
			c3_m306 = stockQuantity;
			c3_m307 = stockQuantity;
			c3_m308 = stockQuantity;
			break;

		case 4:
			c4_m401 = stockQuantity;
			c4_m402 = stockQuantity;
			c4_m403 = stockQuantity;
			c4_m404 = stockQuantity;
			c4_m405 = stockQuantity;
			c4_m406 = stockQuantity;
			c4_m407 = stockQuantity;
			c4_m408 = stockQuantity;
			break;

		case 5:
			c5_m501 = stockQuantity;
			c5_m502 = stockQuantity;
			c5_m503 = stockQuantity;
			c5_m504 = stockQuantity;
			c5_m505 = stockQuantity;
			c5_m506 = stockQuantity;
			c5_m507 = stockQuantity;
			c5_m508 = stockQuantity;
			break;
		}
	}

	public int getStock(int c_id, int m_id) {
		int stock = 0xffff;
		int cIndex, mIdx, pos;
		
		// c_id가 카테고리 디비의 몇번째인지 위치를 찾는다
		List<CategoryVO> cvoList = ManagerDAO.getCategoryList();
		if (cvoList.size() < 1) {	
			System.out.println("getStock() : getCategoryList() null: " + m_id); 
			return stock;
		}

		for (cIndex = 0; cIndex < cvoList.size(); cIndex++) {
			if (cvoList.get(cIndex).getC_id() == c_id)
				break;
		}		
		if (cIndex >= cvoList.size()) {
			System.out.println("getStock(): 일치하는 c_id가 DB에 없음: " + c_id);
			return stock;
		}

		// m_id가 해당 카테고리에서 몇번째 메뉴인지 위치를 찾는다
		List<MenuVO> mvoList = ManagerDAO.getMenuList(c_id);
		if (mvoList.size() < 1) {
			System.out.println("getStock() : getMenuList(c_id) null: " + c_id); 
			return stock;
		}
		
		for (mIdx = 0; mIdx < mvoList.size(); mIdx++) {
			if (mvoList.get(mIdx).getM_id() == m_id)
				break;
		}
		if (mIdx >= mvoList.size()) {
			System.out.println("getStock(): 일치하는 m_id가 DB에 없음: " + m_id);
			return stock;
		}
		
		pos = (cIndex+1) * 100 + (mIdx+1);
		
		switch (pos) {
		case 101:
			stock = c1_m101;
			break;
		case 102:
			stock = c1_m102;
			break;
		case 103:
			stock = c1_m103;
			break;
		case 104:
			stock = c1_m104;
			break;
		case 105:
			stock = c1_m105;
			break;
		case 106:
			stock = c1_m106;
			break;
		case 107:
			stock = c1_m107;
			break;
		case 108:
			stock = c1_m108;
			break;

		case 201:
			stock = c2_m201;
			break;
		case 202:
			stock = c2_m202;
			break;
		case 203:
			stock = c2_m203;
			break;
		case 204:
			stock = c2_m204;
			break;
		case 205:
			stock = c2_m205;
			break;
		case 206:
			stock = c2_m206;
			break;
		case 207:
			stock = c2_m207;
			break;
		case 208:
			stock = c2_m208;
			break;

		case 301:
			stock = c3_m301;
			break;
		case 302:
			stock = c3_m302;
			break;
		case 303:
			stock = c3_m303;
			break;
		case 304:
			stock = c3_m304;
			break;
		case 305:
			stock = c3_m305;
			break;
		case 306:
			stock = c3_m306;
			break;
		case 307:
			stock = c3_m307;
			break;
		case 308:
			stock = c3_m308;
			break;

		case 401:
			stock = c4_m401;
			break;
		case 402:
			stock = c4_m402;
			break;
		case 403:
			stock = c4_m403;
			break;
		case 404:
			stock = c4_m404;
			break;
		case 405:
			stock = c4_m405;
			break;
		case 406:
			stock = c4_m406;
			break;
		case 407:
			stock = c4_m407;
			break;
		case 408:
			stock = c4_m408;
			break;

		case 501:
			stock = c5_m501;
			break;
		case 502:
			stock = c5_m502;
			break;
		case 503:
			stock = c5_m503;
			break;
		case 504:
			stock = c5_m504;
			break;
		case 505:
			stock = c5_m505;
			break;
		case 506:
			stock = c5_m506;
			break;
		case 507:
			stock = c5_m507;
			break;
		case 508:
			stock = c5_m508;
			break;
		}

		return stock;
	}

	public void setStock(int c_id, int m_id, int value) {
		int cIndex, mIdx, pos;
		
		// c_id가 카테고리 디비의 몇번째인지 위치를 찾는다
		List<CategoryVO> cvoList = ManagerDAO.getCategoryList();
		if (cvoList.size() < 1) {	
			System.out.println("setStock() : getCategoryList() null: " + m_id); 
			return;
		}

		for (cIndex = 0; cIndex < cvoList.size(); cIndex++) {
			if (cvoList.get(cIndex).getC_id() == c_id)
				break;
		}		
		if (cIndex >= cvoList.size()) {
			System.out.println("setStock(): 일치하는 c_id가 DB에 없음: " + c_id);
			return;
		}

		// m_id가 해당 카테고리에서 몇번째 메뉴인지 위치를 찾는다
		List<MenuVO> mvoList = ManagerDAO.getMenuList(c_id);
		if (mvoList.size() < 1) {
			System.out.println("setStock() : getMenuList(c_id) null: " + c_id); 
			return;
		}
		
		for (mIdx = 0; mIdx < mvoList.size(); mIdx++) {
			if (mvoList.get(mIdx).getM_id() == m_id)
				break;
		}
		if (mIdx >= mvoList.size()) {
			System.out.println("setStock(): 일치하는 m_id가 DB에 없음: " + m_id);
			return;
		}
		
		pos = (cIndex+1) * 100 + (mIdx+1);
		
		switch (pos) {
		case 101:
			c1_m101 = value;
			break;
		case 102:
			c1_m102 = value;
			break;
		case 103:
			c1_m103 = value;
			break;
		case 104:
			c1_m104 = value;
			break;
		case 105:
			c1_m105 = value;
			break;
		case 106:
			c1_m106 = value;
			break;
		case 107:
			c1_m107 = value;
			break;
		case 108:
			c1_m108 = value;
			break;

		case 201:
			c2_m201 = value;
			break;
		case 202:
			c2_m202 = value;
			break;
		case 203:
			c2_m203 = value;
			break;
		case 204:
			c2_m204 = value;
			break;
		case 205:
			c2_m205 = value;
			break;
		case 206:
			c2_m206 = value;
			break;
		case 207:
			c2_m207 = value;
			break;
		case 208:
			c2_m208 = value;
			break;

		case 301:
			c3_m301 = value;
			break;
		case 302:
			c3_m302 = value;
			break;
		case 303:
			c3_m303 = value;
			break;
		case 304:
			c3_m304 = value;
			break;
		case 305:
			c3_m305 = value;
			break;
		case 306:
			c3_m306 = value;
			break;
		case 307:
			c3_m307 = value;
			break;
		case 308:
			c3_m308 = value;
			break;

		case 401:
			c4_m401 = value;
			break;
		case 402:
			c4_m402 = value;
			break;
		case 403:
			c4_m403 = value;
			break;
		case 404:
			c4_m404 = value;
			break;
		case 405:
			c4_m405 = value;
			break;
		case 406:
			c4_m406 = value;
			break;
		case 407:
			c4_m407 = value;
			break;
		case 408:
			c4_m408 = value;
			break;

		case 501:
			c5_m501 = value;
			break;
		case 502:
			c5_m502 = value;
			break;
		case 503:
			c5_m503 = value;
			break;
		case 504:
			c5_m504 = value;
			break;
		case 505:
			c5_m505 = value;
			break;
		case 506:
			c5_m506 = value;
			break;
		case 507:
			c5_m507 = value;
			break;
		case 508:
			c5_m508 = value;
			break;
		}
	}

	public int getSales_idx() {
		return sales_idx;
	}

	public void setSales_idx(int sales_idx) {
		this.sales_idx = sales_idx;
	}

	public String getSales_date() {
		return sales_date;
	}

	public void setSales_date(String sales_date) {
		this.sales_date = sales_date;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getDay_total() {
		return day_total;
	}

	public void setDay_total(int day_total) {
		this.day_total = day_total;
	}
}
