package c_menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Entrance extends JFrame {
	int mainWidth = 1000;
	int mainHeight = 800;

	public Entrance() {
		super("Kiosk");

		JPanel jp1;
		JButton bEnt;
		ImageIcon bg1 = new ImageIcon("src/img_menu/bg1.jpg");

		jp1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bEnt = new JButton("둘러보기");
		bEnt.setBackground(new Color(216,190,157));
		bEnt.setForeground(Color.BLACK);
		bEnt.setFont(new Font("안성탕면체 Bold", Font.BOLD, 20));
		bEnt.setBorder(new LineBorder(new Color(139,99,49), 1, true));
		//bEnt.setBorderPainted(false);
		
		jp1.setLayout(null);

		jp1.add(bEnt);
		bEnt.setBounds(mainWidth / 2 - 130 / 2, 600, 130, 45);

		add(jp1);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - mainWidth / 2, ds.height / 2 - mainHeight / 2,
				  mainWidth, mainHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		// System.out.println(mainWidth +"x"+ mainHeight); 1000x800

		bEnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu_Main(mainWidth, mainHeight);
				dispose();
			}
		});
	}

//	public void paintComponent(Graphics g) {
//		g.drawImage(bg1.getImage(), 0, 0, null);
//		setOpaque(false);
//		super.paintComponent(g);
//	}
	public static void main(String[] args) {
		new Entrance();
	}
}
