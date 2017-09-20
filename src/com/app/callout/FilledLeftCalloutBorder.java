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
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 04-Sept-2017
 */
public class FilledLeftCalloutBorder extends AbstractBorder {
	
	private static final long serialVersionUID = 1L;
	private Insets insets = new Insets(20, 10, 10, 10);
	private Color filledColor = null;
	
	public FilledLeftCalloutBorder(Color filledColor) {
		this.filledColor = filledColor;
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
		final Graphics2D g2d = (Graphics2D) g;
		
		// Initialize local variables
		int arrowSize = 12;
		int strokeThickness = 3;
		int padding = strokeThickness / 2;
		int x1 = padding + strokeThickness + arrowSize;
		
		// create the shape
		RoundRectangle2D rect = new RoundRectangle2D.Double(x1, y + insets.top/2, width-x1-10, height-1-insets.top/2, 20, 20);

		// Draw Pointer
		Polygon arrow = new Polygon();
		arrow.addPoint(20, 28);
		arrow.addPoint(0, 35);
		arrow.addPoint(20, 38);
		
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
            g2d.fillRect(0,0, width, height);
            g2d.setClip(null);
        }
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.filledColor);
        g2d.setStroke(new BasicStroke(2));
		g2d.draw(area);
	}
}
