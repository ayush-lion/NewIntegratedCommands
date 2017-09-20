/**
 * 
 */
package com.app.callouts.panel;

import java.awt.Color;

import javax.swing.JPanel;

import com.app.callout.Callout;
import com.app.callout.FilledLeftCallout;
import com.app.callout.FilledRightCallout;
import com.app.callout.ThinkingLeftCallout;
import com.app.callout.ThinkingRightCallout;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 13-Sept-2017
 */
public class CalloutPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private Callout callout; 
	
	public CalloutPanel(String align, String text, boolean isthinking) {
		
		this.setBackground(Color.WHITE);
		if(align.equals("left"))
		{
			if(isthinking)
			{
				callout = new ThinkingLeftCallout(Color.black);
			}
			else
			{
				callout = new FilledLeftCallout(Color.blue, Color.yellow);
			}
		} 
		else if(align.equals("right"))
		{
			if(isthinking)
			{
				callout = new ThinkingRightCallout(Color.PINK);
			}
			else
			{
				callout = new FilledRightCallout(Color.GREEN, Color.BLACK);
			}
		}
		this.add(callout);
	}

	/**
	 * @return the callout
	 */
	public Callout getCallout() {
		return callout;
	}
}
