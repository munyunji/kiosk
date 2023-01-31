package c_basketlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Payment extends JFrame {
	BasketList bkList;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtCradetCard;
	private JTextField textField_3;
	private JTextField txtCash;
	private JButton main1;
	private JButton crdit;

	public Payment(BasketList bkList) {
		super("결제하기");

		this.bkList = bkList;

		/*
		 * JPanel jpTop = new JPanel(); JPanel jpBtm = new JPanel();
		 * 
		 * JButton bBack = new JButton(" < 돌아가기 "); JButton bCard = new
		 * JButton(" 카드결제 ");
		 * 
		 * jpTop.add(bBack); jpBtm.add(bCard);
		 * 
		 * add(jpTop, BorderLayout.NORTH); add(jpBtm, BorderLayout.CENTER);
		 */

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		main1 = new JButton("< 돌아가기");
		main1.setBackground(Color.BLACK.gray);
		main1.setForeground(Color.WHITE);
		main1.setFont(new Font("굴림", Font.BOLD, 15));
		main1.setBounds(12, 24, 115, 39);
		contentPane.add(main1);

		JLabel lblNewLabel = new JLabel("Pyament");

		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(335, 49, 286, 66);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.BOLD, 20));
		textField.setText("      결제 완료가된 후에는 주문 취소가 불가능합니다.");
		textField.setBounds(213, 125, 568, 52);
		contentPane.add(textField);
		textField.setColumns(10);

		crdit = new JButton("Credit Card");
		ImageIcon img = new ImageIcon("src/img_basket/image4.png");
		crdit.setIcon(img);
		crdit.setBounds(181, 228, 286, 269);
		contentPane.add(crdit);

		JButton btnNewButton_1_1 = new JButton("Cash");
		ImageIcon img1 = new ImageIcon("src/img_basket/image5.jpg");
		btnNewButton_1_1.setIcon(img1);
		btnNewButton_1_1.setBounds(538, 228, 286, 269);
		contentPane.add(btnNewButton_1_1);

		txtCradetCard = new JTextField();
		txtCradetCard.setFont(new Font("굴림", Font.BOLD, 15));
		txtCradetCard.setHorizontalAlignment(SwingConstants.CENTER);
		txtCradetCard.setText("Credit Card");
		txtCradetCard.setBounds(264, 508, 116, 21);
		contentPane.add(txtCradetCard);
		txtCradetCard.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("굴림", Font.BOLD, 20));
		textField_3.setText(" 현금 결제는 사장님에게 문의해주세요!");
		textField_3.setBounds(310, 593, 392, 46);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		txtCash = new JTextField();
		txtCash.setText("Cash");
		txtCash.setHorizontalAlignment(SwingConstants.CENTER);
		txtCash.setFont(new Font("굴림", Font.BOLD, 15));
		txtCash.setColumns(10);
		txtCash.setBounds(637, 507, 116, 21);
		contentPane.add(txtCash);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - bkList.main.mainWidth / 2, ds.height / 2 - bkList.main.mainHeight / 2,
				  bkList.main.mainWidth, bkList.main.mainHeight);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		main1.addActionListener(new ActionListener() {
			@Override // 돌아가기를 선택하면 결제창을 끈다.
			public void actionPerformed(ActionEvent e) {
				dispose();
				bkList.setVisible(true);
			}
		});

		crdit.addActionListener(new ActionListener() {
			@Override // 카드결제를 선택하면 카드결제창 생성.
			public void actionPerformed(ActionEvent e) {
				crdit.setBorder(new LineBorder(Color.red, 10));
				new CardPayment(Payment.this, bkList.main.mainWidth, bkList.main.mainHeight);
				setVisible(false);
			}
		});
	}

	public void exit_Payment() {
		dispose();
		bkList.exit_BasketList();
	}
}
