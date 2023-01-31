package c_basketlist;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CardPayment extends JFrame {
	private JPanel contentPane ,jp1;
	private JTextField textField;
	private JTextField txtIc;
	private JButton ok;
	private JButton main;

	private JLabel jl1;
	public CardPayment(Payment pay, int mainWidth, int mainHeight) {
		super("카드결제");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Credit Card");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(336, 48, 298, 92);
		contentPane.add(lblNewLabel);
		
		
		txtIc = new JTextField();
		txtIc.setHorizontalAlignment(SwingConstants.CENTER);
		txtIc.setFont(new Font("굴림", Font.BOLD, 17));
		txtIc.setToolTipText("");
		txtIc.setText("카드를 IC카드 리더기에 꽂아주세요.\r\n\r\n");
		txtIc.setBounds(253, 566, 499, 68);
		contentPane.add(txtIc);
		txtIc.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(264, 542, 472, 14);
		contentPane.add(separator);
		
		ok = new JButton("확인\r\n");
		ok.setBackground(Color.BLACK.gray);
		ok.setForeground(Color.WHITE);
		ok.setFont(new Font("굴림", Font.BOLD, 15));
		ok.setBounds(831, 685, 115, 43);
		contentPane.add(ok);
		
		main = new JButton("< 돌아가기");
		main.setBackground(Color.BLACK.gray);
		main.setForeground(Color.WHITE);
		main.setFont(new Font("굴림", Font.BOLD, 15));
		main.setBounds(12, 10, 115, 39);
		contentPane.add(main);
		
		jl1= new JLabel();
		
		jl1.setBounds(278, 164, 457, 378);
		ImageIcon img1 = new ImageIcon("src/img_basket/image6.png");
		jl1.setIcon(img1);	
		contentPane.add(jl1);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2 - mainWidth/2, ds.height/2 - mainHeight/2, 
				  mainWidth, mainHeight);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		main.addActionListener(new ActionListener() {		
			@Override // 돌아가기를 선택하면 창을 끈다.
			public void actionPerformed(ActionEvent e) {
				dispose();
				pay.setVisible(true);
			}
		});
		
		// 확인버튼을 누르면 결제완료이므로, 주문이 전달되고
		// 키오스크 시작화면으로 돌아간다.
		ok.addActionListener(new ActionListener() {		
			@Override 
			public void actionPerformed(ActionEvent e) {
				
				// 영수증 내용 출력
				
				// manager 시스템에 주문 내역 전달
				pay.bkList.main.sendOrder(pay.bkList.getTakeaway(),
										  pay.bkList.getPhone(), pay.bkList.getTotal());
				
				JOptionPane.showMessageDialog(getParent(), 
						"결제가 완료되었습니다.", "결제완료",
						JOptionPane.INFORMATION_MESSAGE);
				
				dispose();
				pay.exit_Payment();
			}
		});
	}
}
