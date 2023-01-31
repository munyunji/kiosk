package c_menu;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import c_basketlist.PointVO;

public class DAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null)
			ss = DBService.getFactory().openSession();

		return ss;
	}

	public static List<CategoryVO> getCategoryList() {
		return getSession().selectList("menu.categoryList");
	}

	public static int getCategoryId(String c_name) {
		return getSession().selectOne("menu.categoryId", c_name);
	}

	public static List<MenuVO> getMenuList(int c_id) {
		return getSession().selectList("menu.menuList", c_id);
	}

	// 전체검색
	public static List<PointVO> getAllList() {
		List<PointVO> list1 = null;

		list1 = getSession().selectList("menu.allLisst");
		return list1;
	}

	// 하나검색
	public static PointVO getPointOne(String phone) {
		PointVO list = null;

		list = getSession().selectOne("menu.pointOne", phone);
		return list;
	}

	// 중복체크
	public static int getPhoneNumChk(String phone) {
		int result = 0;
		result = getSession().selectOne("menu.phoneNumChk", phone);
		return result;
	}

	// 번호, 포인트 insert
	public static int getPhoneNumIns(PointVO vo) {
		int result = 0;
		result = getSession().insert("menu.phoneNumIns", vo);
		ss.commit();
		return result;
	}

	// 번호 검색후에 번호에 따른 포인트 업데이트
	public static int getPointUpdate(PointVO vo) {
		int result = 0;
		result = getSession().update("menu.pointUpdate", vo);
		ss.commit();
		return result;
	}
}
