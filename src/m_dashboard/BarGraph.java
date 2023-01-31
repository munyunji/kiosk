package m_dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BarGraph extends JPanel {
	final int MAX_BAR_NUM = 5;
	final int DAILY_DATA   = 0;
	final int MONTHLY_DATA = 1;
	
	ArrayList<BarInfo> data;
	int graphType; // 0: 일별 데이터, 1: 월별 데이터
	
	public BarGraph() {
		data = new ArrayList<>();
		graphType = DAILY_DATA;
	}

	public void setValues(ArrayList<BarInfo> graphData) {		
		data = graphData;
	}

	public void setGraphType(int graphType) {
		this.graphType = graphType;
	}

	@Override
	public void paint(Graphics g) {
		// x축 가로선
		g.drawLine(50, 250, 450, 250);

		for (int i = 1; i < 11; i++) {
			if (graphType == DAILY_DATA) // 일별 데이터
				g.drawString(i * 10 + "", 25, 255 - 20 * i);
			else  // 월별 데이터
				g.drawString(i * 300 + "", 15, 255 - 20 * i);
			
			g.drawLine(50, 250 - 20 * i, 450, 250 - 20 * i);
		}

		g.setFont(new Font("고딕", Font.BOLD, 12));
		g.drawString(" 단위 :", 15, 15);
		g.drawString(" 만원", 15, 30);
		
		// y축 세로선
		g.drawLine(50, 20, 50, 250);

		g.setColor(Color.RED);
		
		for (int i = 0; i < data.size(); i++) {
			// 일별 데이터
			if (graphType == DAILY_DATA) {
				int value = data.get(i).value/10000;
				g.fillRect(95+i*75, 250-value*2, 20, value*2);
			} 
			// 월별 데이터
			else {
				int value = data.get(i).value/10000;
				g.fillRect(95+i*75, 250 - value*2/30, 20, value*2/30);
			}
		}
	}
}

class BarInfo {
	String name;
	int value;
	
	public BarInfo(String name, int value) {
		this.name = name;
		this.value = value;
	}
}

