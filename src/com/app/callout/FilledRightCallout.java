/**
 * 
 */
package com.app.callout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 04-Sept-2017
 */
public class FilledRightCallout extends Callout {
	
	private static final long serialVersionUID = 1L;
	private Icon icon = null;
	boolean iconApplied;
	
	public FilledRightCallout(Color filledColor, Color foreColor) { 
		this.setBackground(filledColor);
		this.setForeground(foreColor);
		this.setOpaque(true);
		this.setBorder(new FilledRightCalloutBorder(filledColor));
		this.iconApplied = Boolean.FALSE;
	}
	
	@Override
	public void setText(String text) {
		StringBuffer txtToPrint = new StringBuffer("<html>");
		String txtArr[] = text.split("<br>");
		for (String sText : txtArr) {
			String txt = wordWrap(sText, 30, "Right");
			txtToPrint.append(txt).append("<br>");
		}
		txtToPrint.append("</html>");
		super.setText(txtToPrint.toString());
	}
	
	@Override
	public void displayIcon() {
		iconApplied = Boolean.TRUE;
		URL url = this.getClass().getClassLoader().getResource("teacher.gif");
		icon = new ImageIcon(url);
		this.setIcon(icon);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setIconTextGap(10);
	}
}
