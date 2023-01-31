package m_login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Login extends JFrame {
	int mainWidth = 1000;
	int mainHeight = 800;

	private JPanel contentPane;
	private JTextField tfID;
	private JPasswordField tfPW;
	private JButton bLogin;

	public Login() {
		super(" 로그인 ");

		// contentPane = new JPanel();

		ImageIcon bg = new ImageIcon("src/img_login/bg.jpg");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		// contentPane.setBorder(new EmptyBorder(10, 5, 5, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbID = new JLabel("ID        :");
		lbID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbID.setFont(new Font("안성탕면체 ESG", Font.BOLD, 20));
		lbID.setBounds(170, 430, 148, 44);
		contentPane.add(lbID);

		JLabel lbPW = new JLabel("Password   :");
		lbPW.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPW.setFont(new Font("안성탕면체 ESG", Font.BOLD, 20));
		lbPW.setBounds(170, 501, 148, 44);
		contentPane.add(lbPW);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(339, 437, 391, 34);
		tfID.setBorder(new LineBorder(Color.DARK_GRAY, 0, true));
		tfID.setFont(new Font("고딕", Font.BOLD, 17));
		contentPane.add(tfID);

		tfPW = new JPasswordField();
		tfPW.setColumns(10);
		tfPW.setBounds(339, 508, 391, 34);
		tfPW.setBorder(new LineBorder(Color.BLACK, 0, true));
		tfPW.setFont(new Font("고딕", Font.BOLD, 17));
		tfPW.setEchoChar('●');
		contentPane.add(tfPW);

		JButton bLogin = new JButton("로그인");
		bLogin.setBounds(427, 581, 148, 39);
		bLogin.setFont(new Font("안성탕면체 ESG", Font.BOLD, 20));
		bLogin.setBorder(new LineBorder(new Color(139, 99, 49), 1, true));
		bLogin.setBackground(new Color(216, 190, 157));
		bLogin.setForeground(Color.BLACK);
		contentPane.add(bLogin);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2, mainWidth, mainHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		bLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		
		tfPW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					doLogin();
				}
			}
		});
	}

	private void doLogin() {
		ManagerVO manager = new ManagerVO();

		String get_id = tfID.getText().trim();
		String get_pw = "";

		// jpasswordfield 에서 암호화해 받은 값을 char[]에 담은 후
		// String 으로 캐스팅해 get_pw변수에 담아줌
		char[] s_pw = tfPW.getPassword();
		for (char pw : s_pw) {
			get_pw += Character.toString(pw);
			// get_pw += (get_pw.equals("")) ? ""+pw+"" : ""+pw+"";
		}
		
		// 
		if (get_id.length() > 0 && get_pw.length() > 0) {

			// 확인
			System.out.println(get_id);
			System.out.println(get_pw);

			// 중복확인에 사용할 변수
			int id_chk = 0;
			id_chk = ManagerDAO.getLoginChk(get_id);
			System.out.println(id_chk);
			if (id_chk > 0) { //
				// 중복됐으니까 비밀번호 확인
				String chk_pw = ManagerDAO.getManagerOne(get_id).getPw();
				System.out.println(chk_pw);
				if (chk_pw.equals(get_pw)) {
					// 로그인, 로그아웃타입, 로그인 시간 정보를 loginfo테이블에 저장한다.
					LogInfoVO logvo = new LogInfoVO();
					logvo.setLog_type("login");
					ManagerDAO.getLogInfoIns(logvo);
					
					/*
					 * List<LogInfoVO> list = DAO.getLogInforAll(); System.out.println("시퀀스idx : "+
					 * list.get(0).getLog_idx()); System.out.println("로그인 타입 : " +
					 * list.get(0).getLog_type()); System.out.println("날짜 암튼 날짜 : " +
					 * list.get(0).getDate());
					 */							
					
					new Login_Success(mainWidth, mainHeight);
					dispose();
				}else {
					JOptionPane.showMessageDialog(getParent(), "아이디 및 비밀번호를 확인해주세요.");
				}
			} else {
				JOptionPane.showMessageDialog(getParent(), "아이디 및 비밀번호를 확인해주세요.");
			}
		}else {
			JOptionPane.showMessageDialog(getParent(), "아이디 및 비밀번호 정보를 입력해주세요.");
		}
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
