/**
 * 
 */
package com.app.callout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 04-Sept-2017
 */
public class FilledRightCalloutBorder extends AbstractBorder {
	
	private static final long serialVersionUID = 1L;
	private Insets insets = new Insets(20, 10, 10, 10);
	private Color borderColor;
	
	public FilledRightCalloutBorder(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	 @Override
    public Insets getBorderInsets(Component cmpnt) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component cmpnt, Insets insets) {
        insets.left = this.insets.left;
        insets.right = this.insets.right;
        insets.top = this.insets.top;
        insets.bottom = this.insets.bottom;
        return insets;
    }

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		int strokeThickness = 3;
		final Graphics2D g2d = (Graphics2D) g;
		
		// create the shape
		RoundRectangle2D rect = new RoundRectangle2D.Double(0, y + insets.top/2, width - 35, height-1-insets.top/2, 20, 20);

		// Draw Pointer
		Polygon arrow = new Polygon();
		arrow.addPoint(width - 35, 28);
		arrow.addPoint(width - 10, 35);
		arrow.addPoint(width - 35, 38);
		
		//Draw Area
		Area area = new Area(rect);
		area.add(new Area(arrow));
		
		//Color the border
		Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            Rectangle rect1 = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect1);
            borderRegion.subtract(area);
            g2d.setClip(borderRegion);
            g2d.setColor(bg);
            g2d.fillRect(0, 0, width, height);
            g2d.setClip(null);
        }
        
        g2d.setColor(this.borderColor);
        g2d.setStroke(new BasicStroke(2));
		g2d.draw(area);
	}
}
