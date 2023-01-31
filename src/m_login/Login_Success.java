package m_login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

public class Login_Success extends JFrame {
	private JPanel contentPane;
	private JFormattedTextField tfLoginDate; // 날짜
	private JTextField tfLoginTime; // 시간
	private JFormattedTextField tfLogoutDate; // 로그아웃 날짜
	private JTextField tfLogoutTime; // 로그아웃 시간
	
	public Login_Success(int mainWidth, int mainHeight) {
		super("로그인 성공");
		
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(245, 245, 250));
		//contentPane.setBackground(Color.DARK_GRAY);
		//contentPane.setForeground(Color.WHITE);
		
		JLabel lbTitle = new JLabel("Welcome");
		lbTitle.setFont(new Font("굴림", Font.BOLD, 34));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(259, 142, 456, 91);
		//lbTitle.setForeground(Color.WHITE);
		contentPane.add(lbTitle);
		
		JLabel lblFirstLogin = new JLabel("현재 로그인    :");
		lblFirstLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstLogin.setFont(new Font("고딕", Font.BOLD, 17));
		lblFirstLogin.setBounds(156, 315, 173, 35);
		//lblFirstLogin.setForeground(Color.WHITE);
		contentPane.add(lblFirstLogin);
		
		// DB에 저장된 조건에 맞는 log_Date정보를 저장
		String loginTime = ManagerDAO.getLoginDateAll();
		// 공백문자로 String을 나눠 배열에 저장한다
		String[] login = loginTime.split(" ");
		
		//tfLoginDate = new JFormattedTextField(new DateFormatter());
		tfLoginDate = new JFormattedTextField();
		//tfLoginDate.setValue(new Date());	
		tfLoginDate.setColumns(10);
		tfLoginDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfLoginDate.setEditable(false);
		tfLoginDate.setBounds(341, 315, 214, 34);	
		tfLoginDate.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLoginDate);
		// String 배열로 나눠 저장한 로그인 날짜정보를 tf에 출력
		tfLoginDate.setText(login[0]);
		
		// DB에 저장된 최근 로그인 시간만 가져오기
		//String loginTime = ManagerDAO.getLogTime();
		
		tfLoginTime = new JTextField();
		tfLoginTime.setColumns(10);
		tfLoginTime.setHorizontalAlignment(SwingConstants.CENTER);
		tfLoginTime.setEditable(false);
		tfLoginTime.setBounds(567, 315, 214, 34);
		tfLoginTime.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLoginTime);
		
		// String 배열로 나눠 저장한 로그인 시간정보를 tf에 출력
		tfLoginTime.setText(login[1]);
		
		JLabel lbLastLogout = new JLabel("최종 로그아웃  :");
		lbLastLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lbLastLogout.setFont(new Font("고딕", Font.BOLD, 17));
		lbLastLogout.setBounds(156, 392, 173, 35);
		//lbLastLogout.setForeground(Color.WHITE);
		contentPane.add(lbLastLogout);
		
		
		//tfLogoutDate = new JFormattedTextField(new DateFormatter());
		tfLogoutDate = new JFormattedTextField();
		tfLogoutDate.setColumns(10);
		tfLogoutDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfLogoutDate.setEditable(false);
		tfLogoutDate.setBounds(341, 392, 214, 34);
		tfLogoutDate.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLogoutDate);
		
		tfLogoutTime = new JTextField();
		tfLogoutTime.setColumns(10);
		tfLogoutTime.setHorizontalAlignment(SwingConstants.CENTER);
		tfLogoutTime.setEditable(false);
		tfLogoutTime.setBounds(567, 392, 214, 34);
		tfLogoutTime.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLogoutTime);
		
		JButton bOk = new JButton("확인");
		bOk.setBounds(412, 506, 143, 35);
		bOk.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(bOk);
		
		int logout_chk = ManagerDAO.getLogoutChk();
		// 로그아웃 DB 가져오기
		String logoutinfo = ManagerDAO.getLogoutDateAll();
		
		 // 로그아웃 기록이 있는경우에 tf에 날짜와 시간을 출력해준다.
		if (logout_chk > 0) {
			String[] logout = logoutinfo.split(" ");
			tfLogoutDate.setText(logout[0]);
			tfLogoutTime.setText(logout[1]);
		}else {
			// 로그아웃 기록이 없는경우 로그아웃 날짜/시간이 나오는 tf를 빈값으로 세팅
			tfLogoutDate.setText("");
			tfLogoutTime.setText("");
		}
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2 - mainWidth/2, ds.height/2 - mainHeight/2,
				  mainWidth, mainHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		bOk.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Manager_Main(mainWidth, mainHeight);
			}
		});
	}
}
