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

public class Logout_Success extends JFrame {
	private JPanel contentPane;
	private JFormattedTextField tfLoginDate;
	private JTextField tfLoginTime;
	private JFormattedTextField tfLogoutDate;
	private JTextField tfLogoutTime;
	
	public Logout_Success(Manager_Main mngMain, int mainWidth, int mainHeight) {		
		super("로그아웃 성공");
		
		
		// logout날짜/시간 시퀀스 타입을 테이블에 저장
		LogInfoVO logvo = new LogInfoVO();
		logvo.setLog_type("logout");
		ManagerDAO.getLogInfoIns(logvo);
		

	
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(245, 245, 250));
		
		JLabel lblLogout = new JLabel("Logged out");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setFont(new Font("고딕", Font.BOLD, 34));
		lblLogout.setBounds(274, 148, 456, 91);
		contentPane.add(lblLogout);
		
		JLabel lblThisLogin = new JLabel("최근 로그인 :");
		lblThisLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisLogin.setFont(new Font("고딕", Font.BOLD, 17));
		lblThisLogin.setBounds(171, 321, 173, 35);
		contentPane.add(lblThisLogin);
		
		// login 날짜/시간 구해 배열로 저장
		String logininfo = ManagerDAO.getLoginDateAll();
		String[] login = logininfo.split(" ");
		 
		//tfLoginDate = new JFormattedTextField(new DateFormatter());
		tfLoginDate = new JFormattedTextField();
		tfLoginDate.setColumns(10);
		tfLoginDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfLoginDate.setEditable(false);
		tfLoginDate.setBounds(356, 321, 214, 34);
		tfLoginDate.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLoginDate);
		//저장한 로그인배열의 날짜를 index번호로 출력
		tfLoginDate.setText(login[0]);
		
		tfLoginTime = new JTextField();
		tfLoginTime.setColumns(10);
		tfLoginTime.setHorizontalAlignment(SwingConstants.CENTER);
		tfLoginTime.setEditable(false);
		tfLoginTime.setBounds(582, 322, 214, 34);
		tfLoginTime.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLoginTime);
		//저장한 로그인배열의 시간를 index번호로 출력
		tfLoginTime.setText(login[1]);
		
		JLabel lblLogoutInfo = new JLabel("로그 아웃    :");
		lblLogoutInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoutInfo.setFont(new Font("고딕", Font.BOLD, 17));
		lblLogoutInfo.setBounds(171, 398, 173, 35);
		contentPane.add(lblLogoutInfo);
		
		String logoutinfo = ManagerDAO.getLogoutDateAll();
		String[] logout = logoutinfo.split(" ");
		
		//tfLogoutDate = new JFormattedTextField(new DateFormatter());
		tfLogoutDate = new JFormattedTextField();
		//tfLogoutDate.setValue(new Date());	
		tfLogoutDate.setColumns(10);
		tfLogoutDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfLogoutDate.setEditable(false);
		tfLogoutDate.setBounds(356, 398, 214, 34);
		tfLogoutDate.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLogoutDate);
		tfLogoutDate.setText(logout[0]);
		
		tfLogoutTime = new JTextField();
		tfLogoutTime.setColumns(10);
		tfLogoutTime.setHorizontalAlignment(SwingConstants.CENTER);
		tfLogoutTime.setEditable(false);
		tfLogoutTime.setBounds(582, 399, 214, 34);
		tfLogoutTime.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(tfLogoutTime);
		tfLogoutTime.setText(logout[1]);
		
		JButton bOk = new JButton("확인");
		bOk.setBounds(427, 512, 143, 35);
		bOk.setFont(new Font("고딕", Font.BOLD, 15));
		contentPane.add(bOk);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2 - mainWidth/2, ds.height/2 - mainHeight/2,
				mainWidth, mainHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// textfield 에 시간 출력 
		
		// 현재 시간을 logout 으로 db에 저장
		
		bOk.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();			
			}
		});
	}
}
