package m_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import c_menu.CategoryVO;
import c_menu.MenuVO;
import m_dashboard.SalesNStockVO;
import m_search.SearchVO;

public class ManagerDAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null)
			ss = ManagerDBService.getFactory().openSession();

		return ss;
	}

	// Kioskorder DB 삽입
	public static int getOrderInsert(KioskorderVO kvo) {
		int result = getSession().insert("manager.orderIns", kvo);
		ss.commit();
		return result;
	}

	// Kioskorder DB 검색
	public static List<KioskorderVO> getOrderList(SearchVO svo) {
//		public static List<KioskorderVO> getOrderList(String sDate, String eDate) {
//		Map<String, String> map = new HashMap<>();
//		map.put("start_date", sDate);
//		map.put("end_date", eDate);
		return getSession().selectList("manager.orderList", svo);
	}

	// Kioskorder DB 주문수 알아내기
	public static int getOrderCnt(SearchVO svo) {
		return getSession().selectOne("manager.orderCnt", svo);
	}

	// SalesNStock DB insert
	public static int getSalesNStockInsert(SalesNStockVO ssvo) {
		int result = getSession().insert("manager.salesnstockIns", ssvo);
		ss.commit();
		return result;
	}

	// 특정 기간동안의 SalesNStock DB 값을 가져옴
	public static List<SalesNStockVO> getSalesNStockList(String dataType, String start_date, String end_date) {
		Map<String, String> map = new HashMap<>();
		map.put("dataType", dataType);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		return getSession().selectList("manager.salesnstockList", map);
	}

	// SalesNStock DB Update (매출, 재고)
	public static int getSalesNStockUpdate(SalesNStockVO ssvo) {
		int result = getSession().update("manager.salesnstockUpdate", ssvo);
		ss.commit();
		return result;
	}

	// 모든 메뉴의 c_id, m_id, m_name, price 가져옴
	public static List<MenuVO> getMenuAll() {
		return getSession().selectList("manager.menuAll");
	}

	// m_id로 m_name, price를 가져옴
	public static MenuVO getMenuOne(String m_id) {
		return getSession().selectOne("manager.menuOne", m_id);
	}

	// 전체 카테고리 정보 가져옴
	public static List<CategoryVO> getCategoryList() {
		return getSession().selectList("manager.categoryList");
	}

	// c_name으로 c_id 가져옴
	public static int getCategoryId(String c_name) {
		return getSession().selectOne("manager.categoryId", c_name);
	}

	// c_id에 해당하는 모든 메뉴를 가져옴
	public static List<MenuVO> getMenuList(int c_id) {
		return getSession().selectList("manager.menuList", c_id);
	}

	// 삽입 !
	public static int getIns(MenuVO cvo) {
		int result = 0;
		result = getSession().insert("manager.menuIns", cvo);
		// commit;
		ss.commit();
		return result;
	}

	public static int getIns2(CategoryVO vo) {
		int result = 0;
		result = getSession().insert("manager.menuIns2", vo);
		// commit;
		ss.commit();
		return result;
	}

	// 삭제 !!
	public static int getDel(String s1) { // 삭제
		int result = 0;
		result = getSession().delete("manager.menuDel", s1);
		ss.commit();
		return result;
	}

	public static int getDel1(String vo1) {
		int result = 0;
		result = getSession().delete("manager.menuDel1", vo1);
		ss.commit();
		return result;
	}

	// 변경 !! 업데이트
	public static int getUpDate(MenuVO vo3) {
		int result = 0;
		result = getSession().update("manager.menuUpdate", vo3);
		ss.commit();
		return result;
	}

	public static int getUpDate1(MenuVO vo4) {
		int result = 0;
		result = getSession().update("manager.menuUpdate1", vo4);
		ss.commit();
		return result;
	}

	public static int getUpDate2(MenuVO vo5) {
		int result = 0;
		result = getSession().update("manager.menuUpdate2", vo5);
		ss.commit();
		return result;
	}

	public static int getUpDate3(MenuVO vo6) {
		int result = 0;
		result = getSession().update("manager.menuUpdate3", vo6);
		ss.commit();
		return result;
	}

	public static int getUpDate4(CategoryVO vo7) {
		int result = 0;
		result = getSession().update("manager.menuUpdate4", vo7);
		ss.commit();
		return result;
	}

	public static int getIdChk(int m_id) {
		int result = 0;
		try {
			result = getSession().selectOne("manager.idchk", m_id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public static int getIdChk2(int c_id) {
		int result = 0;
		try {
			result = getSession().selectOne("manager.idchk2", c_id);
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	// manager 정보 하나 검색
	public static ManagerVO getManagerOne(String id) {
		ManagerVO manager = null;
		manager = getSession().selectOne("manager.loginOne", id);
		return manager;
	}

	// 로그인 확인
	public static int getLoginChk(String id) {
		int result = 0;
		result = getSession().selectOne("manager.loginChk", id);
		return result;

	}

	public static List<LogInfoVO> getLogInforAll() {
		List<LogInfoVO> list2 = null;

		list2 = getSession().selectList("manager.logInforSelect");
		return list2;
	}

	// 로그인번호, 로그타입, 로그시간을 받아 loginfo 테이블에 insert
	public static int getLogInfoIns(LogInfoVO vo) {
		int result = 0;
		result = getSession().insert("manager.logInfoIns", vo);
		ss.commit();
		return result;
	}

	// 조건에 맞는 로그인 날짜/시간 모두 받아오는 쿼리
	public static String getLoginDateAll() {
		String logininfo;
		logininfo = getSession().selectOne("manager.loginDateAll");

		return logininfo;
	}

	// 조건에 맞는 로그아웃 날짜/시간 모두 받아오는 쿼리
	public static String getLogoutDateAll() {
		String logoutinfo;
		logoutinfo = getSession().selectOne("manager.logoutDateAll");

		return logoutinfo;
	}
		
	// 로그아웃 기록 있는지 확인
	public static int getLogoutChk() {
		int result = 0;
		result = getSession().selectOne("manager.logoutChk");
		
		return result;
	}
}
