package c_basketlist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import c_menu.DAO;
import c_menu.Menu_Main;

public class SavePoint extends JFrame {
	// DAO에서 받아온 DB VO
	// List<PointVO> list;
	PointVO one;

	// 최초 검색시 가지고 있던 포인트 (첫 검색시 가용 포인트 창에 뜨는 포인트)
	int point;
	// 포인트 사용했을때 사용후 남은 포인트를 업뎃하기 위해 받았던 번호를 따로 ㅈ저장해준것
	String num;
	// 포인트 사용 버튼에서 사용위해 전역 변수에 적립된 포인트 + 원래포인트 값을 저장한것
	int re_p;

	// SavePoint point ;
	// Menu_Main main;
	private JPanel contentPane;
	private JTextField phone_num, save_point, remaining_point, using_point;

	JLabel savepoint_Lb, phone_Lb, save_lb, remaining_lb2, remaining_lb, using_lb, msg_Lb;
	JButton back, chk_btn, using_btn, lookup;

	public SavePoint(BasketList bkList, int mainWidth, int mainHeight) {
		super("SavePoint");

		// 생성 먼저
		savepoint_Lb = new JLabel("Save Points");
		msg_Lb = new JLabel("**전화번호는 숫자로만 입력해 주세요.");

		phone_Lb = new JLabel("Phone Number :");
		save_lb = new JLabel("적립 포인트 :");
		remaining_lb2 = new JLabel("잔여 포인트 :");
		remaining_lb = new JLabel("가용 포인트 :");
		using_lb = new JLabel("사용할 포인트 :");

		phone_num = new JTextField();
		save_point = new JTextField();
		remaining_point = new JTextField();
		using_point = new JTextField();

		back = new JButton("<   Back");
		using_btn = new JButton("포인트 사용하기");
		lookup = new JButton("검색하기");
		chk_btn = new JButton("확인");

		// this.point = point;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 타이틀
		savepoint_Lb.setForeground(Color.WHITE);
		savepoint_Lb.setFont(new Font("굴림", Font.BOLD, 30));
		savepoint_Lb.setHorizontalAlignment(SwingConstants.CENTER);
		savepoint_Lb.setBounds(344, 109, 312, 46);
		contentPane.add(savepoint_Lb);

		// 전화번호 label, tf
		phone_Lb.setForeground(Color.WHITE);
		phone_Lb.setHorizontalAlignment(SwingConstants.CENTER);
		phone_Lb.setFont(new Font("굴림", Font.BOLD, 25));
		phone_Lb.setBounds(213, 227, 236, 53);
		contentPane.add(phone_Lb);
		// 직접 정보 받고 처리할 tf
		phone_num.setFont(new Font("굴림", Font.BOLD, 15));
		phone_num.setBounds(461, 234, 334, 45);
		contentPane.add(phone_num);
		phone_num.setColumns(10);

		// 적립될 포인트 label, 적립포인트 tf
		save_lb.setFont(new Font("굴림", Font.BOLD, 16));
		save_lb.setForeground(Color.WHITE);
		save_lb.setHorizontalAlignment(SwingConstants.CENTER);
		save_lb.setBounds(144, 330, 124, 39);
		contentPane.add(save_lb);
		// 확인용tf(변경불가)
		save_point.setColumns(10);
		save_point.setBounds(280, 328, 187, 45);
		save_point.setEditable(false);
		contentPane.add(save_point);

		// 잔여포인트label, 잔여포인트 tf
		// **포인트 사용후 변경 위해 label 2개만듦
		remaining_lb2.setHorizontalAlignment(SwingConstants.CENTER);
		remaining_lb2.setFont(new Font("굴림", Font.BOLD, 16));
		remaining_lb2.setForeground(Color.WHITE);
		remaining_lb2.setBounds(482, 330, 174, 39);
		contentPane.add(remaining_lb2);
		remaining_lb2.setVisible(false);

		remaining_lb.setHorizontalAlignment(SwingConstants.CENTER);
		remaining_lb.setFont(new Font("굴림", Font.BOLD, 16));
		remaining_lb.setForeground(Color.WHITE);
		remaining_lb.setBounds(482, 330, 174, 39);
		contentPane.add(remaining_lb);
		// 확인용 tf(변경불가)
		remaining_point.setColumns(10);
		remaining_point.setBounds(647, 326, 187, 45);
		remaining_point.setEditable(false);
		contentPane.add(remaining_point);

		// 사용할포인트 label, 사용포인트tf(사용자 입력)
		using_lb.setHorizontalAlignment(SwingConstants.CENTER);
		using_lb.setForeground(Color.WHITE);
		using_lb.setFont(new Font("굴림", Font.BOLD, 16));
		using_lb.setBounds(144, 422, 124, 39);
		contentPane.add(using_lb);
		// 포인트 검색전 정보숨기기
		using_lb.setVisible(false);
		// 직접 정보 받고 처리할 tf
		using_point.setColumns(10);
		using_point.setBounds(295, 420, 183, 45);
		contentPane.add(using_point);
		// 포인트 검색전 정보숨기기
		using_point.setVisible(false);

		// 칸나누기
		JSeparator separator = new JSeparator();
		separator.setBounds(159, 292, 686, 18);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(159, 394, 686, 18);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(159, 486, 686, 18);
		contentPane.add(separator_2);

		// 뒤로가기 버튼
		back.setBackground(Color.BLACK.gray);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("굴림", Font.BOLD, 13));
		back.setBounds(12, 22, 97, 39);
		contentPane.add(back);

		// 검색 버튼
		lookup.setBackground(Color.BLACK.gray);
		lookup.setForeground(Color.WHITE);
		lookup.setFont(new Font("굴림", Font.BOLD, 15));
		lookup.setBounds(228, 581, 124, 39);
		contentPane.add(lookup);

		// 포인트 사용 버튼
		using_btn.setForeground(Color.WHITE);
		using_btn.setFont(new Font("굴림", Font.BOLD, 15));
		using_btn.setBackground(Color.GRAY);
		using_btn.setBounds(585, 420, 197, 45);
		contentPane.add(using_btn);
		// 포인트 검색전 버튼 숨기기
		using_btn.setVisible(false);

		// 확인 버튼
		chk_btn.setBackground(Color.BLACK.gray);
		chk_btn.setForeground(Color.WHITE);
		chk_btn.setFont(new Font("굴림", Font.BOLD, 15));
		chk_btn.setBounds(658, 581, 124, 39);
		contentPane.add(chk_btn);
		// 포인트 검색 전 버튼 비활성화
		chk_btn.setEnabled(false);

		// 뒤로가기 버튼 이벤트
		back.addActionListener(new ActionListener() { // 백 메인화면
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				bkList.setVisible(true);
			}
		});

		// 검색 버튼 이벤트
		lookup.addActionListener(new ActionListener() { // 검색하기

			@Override
			public void actionPerformed(ActionEvent e) {
				// 새로 쌓일 포인트 => 총 결제금액의 2%
				int newpoint = (int) (bkList.getTotal() * 0.02);

				String phoneNum = phone_num.getText().trim();

				// 받은 번호가 0이상 12보다 작은 경우에만 번호 검색가능
				// "-" 없이 번호만 받기
				if (phoneNum.length() > 0 && phoneNum.length() < 12) {
					// 번호 중복 확인
					int res = DAO.getPhoneNumChk(phoneNum);
					System.out.println("DAO.getPhoneNumChk(phoneNum) : " + res);
					if (res > 0) { // 체크한 값이 0보다 큰경우 => 이미 등록된 고객
						// 받은 번호로 DB에서 검색한 전화번호와 포인트를 PointVO one에 저장
						// one = DAO.getPointOne(phoneNum);
						// point = one.getPoint(); // 원래 가지고 있던 포인트
						point = DAO.getPointOne(phoneNum).getPoint(); // 원래 가지고 있던 포인트
						bkList.setPhone(phoneNum);

						// 번호 있을 때
						remaining_point.setText(String.valueOf(point));// 가용 포인트 창에 원래 가지고 있던 포인트를 출력
						save_point.setText(String.valueOf(newpoint)); // 적립 포인트 창에 적립될 포인트를 출력

						// 잔여포인트 + new포인트 => upvo에 저장후 DB에 업데이트
						PointVO upvo = new PointVO();
						upvo.setPhone(phoneNum); // 조건식에 사용하기 위해 받은번호를 담는다
						upvo.setPoint(point + newpoint); // 포인트 정보를 업데이트 하기위해 새로 저장될 포인트 정보를 담는다
						int result = DAO.getPointUpdate(upvo); // 위의 정보를 보내 DAO의 업데이트 메서드 실행 성공시 0이상의 숫자로 결과 반환

						// 포인트 사용하기 위한 ui 숨김 해제
						using_lb.setVisible(true);
						using_point.setVisible(true);
						using_btn.setVisible(true);

						// 재적립 방지위해 검색버튼과 번호 입력하는 tf를 비활성화 시킨다.
						lookup.setEnabled(false);
						phone_num.setEditable(false);

						// 적립 후 확인버튼 활성화, 재적립 방지 위해 back버튼 숨기기
						// **확인버튼 이용 시 장바구니의 포인트적립버튼 사용 불가**
						back.setVisible(false);
						chk_btn.setEnabled(true);

						// 포인트 사용 버튼에서 사용위해 전역 변수에 값 저장
						num = upvo.getPhone();
						re_p = upvo.getPoint();

						if (result > 0) {
							// 업데이트 성공시
							// JOptionPane.showMessageDialog(getParent(), "적립후 포인트는 " + re_p + "point
							// 입니다.");
							JOptionPane.showMessageDialog(getParent(), "포인트 적립이 완료되었습니다.");
						} // 실패 경우 있나?

					} else { // 번호 없을 때
						int option = JOptionPane.showConfirmDialog(getParent(), "저장된 번호가 없습니다.\n전화번호를 등록 후 적립하시겠습니까?",
								"저장확인", JOptionPane.YES_NO_OPTION);
						if (option == 0) {
							// 새로운 번호를저장할 vo생성
							PointVO vo = new PointVO();
							// 생성한 vo안에 입력받은 번호와 새로 쌓인 newpoint 저장
							vo.setPhone(phoneNum);
							vo.setPoint(newpoint);
							// DAO에 있는 insert메서드를 이용해 vo에 저장한 정보를 DB에 집어넣는다.
							DAO.getPhoneNumIns(vo);
							// order클래스(= protocol)에 넣어 사장님 패키지로 보내기 위해 받은 번호정보를 저장해준다
							bkList.setPhone(phoneNum);

							// vo에 저장된 전화번호를 이용해 PointVO를 검색한뒤 one에 저장
							one = DAO.getPointOne(vo.getPhone());
							// one에 저장된 포인트를 잔여포인트 창에 넣어 새로운 가입자에게 적립을 확인시켜준다.
							remaining_point.setText("0");
							save_point.setText(String.valueOf(one.getPoint()));

							// 포인트 사용하기 위한 ui 숨김 해제
							using_lb.setVisible(true);
							using_point.setVisible(true);
							using_btn.setVisible(true);

							// 재적립방지
							lookup.setEnabled(false);
							phone_num.setEditable(false);

							// 확인버튼 활성화, 뒤로가기 비활성화
							back.setVisible(false);
							chk_btn.setEnabled(true);

							// 새로운 가입자 포인트 사용방지 => 기존 유저 적립시만 포인트사용 tf, 버튼등 뜨게 만든다
							// using_btn.setEnabled(false);

							JOptionPane.showMessageDialog(getParent(), "번호 등록 및 포인트 적립이 완료되었습니다.\n적립 후 포인트는 "
									+ String.valueOf(one.getPoint()) + "point 입니다.");
							// 적립버튼 => 확인버튼으로 바꾸기

						} else if (option == 1) {
							JOptionPane.showMessageDialog(getParent(), "포인트 적립이 취소되었습니다.");
							phone_num.setText("");
							// dispose();
							// bkList.setVisible(true);
						} else {
							phone_num.setText("");

						}
					} // 번호 없는 경우 else 끝
				} else {
					JOptionPane.showMessageDialog(getParent(), "번호를 정확히 입력해주세요");
				}
			}
		});

		// 포인트 사용 버튼 이벤트
		// 포인트 사용 후 잔액 MessageDialog로 알려주기
		using_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String up = using_point.getText().trim();

				if (up.length() > 0) { // 사용 포인트에서 받은 텍스트의 길이가 0보다 작을 때

					// 계산을 위해 tf에서 받은 값을 Integer로 캐스팅후 저장
					int using_P = Integer.parseInt(up);

					if (point >= 1000) { // 가용 포인트가 1000점 이상인경우 사용가능
						if (using_P <= point) { // 사용할 포인트가 가용 포인트이하인 경우 사용가능

							if (bkList.getTotal() < using_P) {
								using_P = bkList.getTotal();
								JOptionPane.showMessageDialog(getParent(), "결제금액 만큼의 포인트만 차감되었습니다.");
							}
							// 포인트 이용 결제 후 장바구니에 출력될 메서드
							bkList.updateTotal(using_P);

							// 포인트 사용후 잔여 포인트를 DB에 저장
							int end_p = (re_p - using_P);
							PointVO pvo = new PointVO();
							pvo.setPhone(num);
							pvo.setPoint(end_p);
							DAO.getPointUpdate(pvo);
							// 가용포인트 label을 잔여포인트 라벨로 변경
							remaining_lb2.setVisible(true);
							remaining_lb.setVisible(false);
							// 잔여포인트 tf에 업데이트될 포인트정보를 출력
							remaining_point.setText("");
							remaining_point.setText(String.valueOf(DAO.getPointOne(num).getPoint()));
							save_point.setText("");

							// 포인트 사용 ui 숨김
							using_lb.setVisible(false);
							using_point.setVisible(false);
							using_btn.setVisible(false);

							JOptionPane.showMessageDialog(getParent(), "포인트 사용이 완료되었습니다.");

						} else // 사용하려는 포인트가 가용포인트를 초과하는 경우
							JOptionPane.showMessageDialog(getParent(), "포인트 결제는 가용포인트 만큼만 가능합니다.");
						using_point.setText("");
					} else // 가용 포인트가 3000포인트 미만인경우
						JOptionPane.showMessageDialog(getParent(), "가용포인트가 1000point 이상인 경우 사용 가능합니다.");
				}
			}
		});

		// 확인 버튼 이벤트
		chk_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 버튼을 누르면 장바구니로
				dispose();
				bkList.setVisible(true);
				// 장바구니로 돌아갔을때 포인트적립 버튼과 메뉴선택창 돌아가기 버튼 비활시키는 메서드
				bkList.makePointButtonDisable();
			}
		});

		// 알림lable
		msg_Lb.setFont(new Font("굴림", Font.PLAIN, 15));
		msg_Lb.setForeground(new Color(255, 255, 255));
		msg_Lb.setBounds(504, 195, 252, 29);
		contentPane.add(msg_Lb);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2, 1000, 800);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}
}
