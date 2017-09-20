package com.app.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

	String s = "Aayush \n vats";
	int count = 0;

	private void drawString(Graphics g, String text, int x, int y) {
		count++;
		System.out.println(s);
		System.out.println(count);
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		drawString(g, s, 20, 20);
		g.setFont(g.getFont().deriveFont(20f));
		drawString(g, s, 120, 120);
	}

	public static void main(String s[]) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Test());
		f.setSize(220, 220);
		f.setVisible(true);
	}
}

/*
private void drawString(Graphics g, String text, int x, int y) {
	for (String line : text.split("\n"))
		g.drawString(line, x, y += g.getFontMetrics().getHeight());
}
}
public void draw_instruction(Graphics g)

{
	if (isSwitchable()) { 
		int count=0;
		String sb = null;
		String str=getIns_text();
		String[] strArray = str.split(" ");
		StringBuffer sbuf = new StringBuffer();

		for (int i = 0; i < strArray.length; i++) {
			if (i != 0 && i % 5 == 0) {
				sbuf.append("\n");
			}
			sbuf.append(strArray[i]).append(" ");
		}
		System.out.print(sbuf);
		sb = sbuf.toString();
		count++;
		System.out.println(sb);
		System.out.println(count);
		
		g.drawImage(getImage(), getPosX(), getPosY(), getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);	
	        
		g.setFont(g.getFont().deriveFont(12f));
        drawString(g, sb, getPosX()+5, getPosY()+20);
		}
        
		else
		
		{
		//g.setColor(Color.TRANSLUCENT);
		g.setColor(Color.WHITE);
		g.drawRect(getPosX(), getPosY(), getWidth(), getHeight());
		}
	}*/