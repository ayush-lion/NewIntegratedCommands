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
public class TeacherPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public TeacherPanel() {
	}
	
	@Override
	public void paint(Graphics g) {
			Image image;
			try {
				image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("GTeacher.png"));
				g.drawImage(image, -20, 0, this.getWidth(), this.getHeight(), null);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
