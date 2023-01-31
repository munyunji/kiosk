package m_login;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ManagerDBService {
	static private SqlSessionFactory factory;
	static String resource = "m_login/config.xml";
	
	static {
		try {
			InputStream inStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// ManagerDAO에서 factory를 호출할 메소드
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
