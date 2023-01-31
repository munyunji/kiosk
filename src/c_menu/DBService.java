package c_menu;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBService {
	static private SqlSessionFactory factory;
	static String resource = "c_menu/config.xml";
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// DAO에서 factory를 호출할 메소드
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
