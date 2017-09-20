/**
 * 
 */
package com.app.callouts.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 13-Sept-2017
 */
public class StudentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public StudentPanel() {
	}
	
	@Override
	public void paint(Graphics g) {
		Image image;
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("student.png"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
