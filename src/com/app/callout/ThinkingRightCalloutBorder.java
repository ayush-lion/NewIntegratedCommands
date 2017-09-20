package com.app.callout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import javax.swing.border.AbstractBorder;

class ThinkingRightCalloutBorder extends AbstractBorder {

	private static final long serialVersionUID = 1L;
	private Color borderColour;
	private int gap;

	public ThinkingRightCalloutBorder(Color colour, int g) {
		borderColour = colour;
		gap = g;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);
		Graphics2D g2d = null;
		//System.out.println(x + " :: " + y + " :: " + width + " :: " + height);
		if (g instanceof Graphics2D) {
			g2d = (Graphics2D) g;
			g2d.setColor(borderColour);

			// Top Border
			for (int i = 0; i < ((width - 40) / 20) + 1; i++) {
				int counter = x + (20 * i);
				g2d.draw(new Arc2D.Double(counter, y, 10, 10, 0, 180, Arc2D.OPEN));
				g2d.draw(new Arc2D.Double(counter + 10, y, 10, 10, 0, 180, Arc2D.OPEN));
			}

			// Right Border
			for (int i = 0; i < (height / 20) + 1; i++) {
				int counter = y + (20 * i);
				g2d.draw(new Arc2D.Double(width - 40, counter, 10, 10, 270, 180, Arc2D.OPEN));
				g2d.draw(new Arc2D.Double(width - 40, counter + 10, 10, 10, 270, 180, Arc2D.OPEN));
			}

			// Bottom Border
			for (int i = 0; i < ((width - 40) / 20) + 1; i++) {
				int counter = x + (20 * i);
				g2d.draw(new Arc2D.Double(counter, height - 10, 10, 10, 180, 180, Arc2D.OPEN));
				g2d.draw(new Arc2D.Double(counter + 10, height - 10, 10, 10, 180, 180, Arc2D.OPEN));
			}

			// Left Border
			for (int i = 0; i < (height / 20) + 1; i++) {
				int counter = y + (20 * i);
				g2d.draw(new Arc2D.Double(x, counter, 10, 10, 90, 180, Arc2D.OPEN));
				g2d.draw(new Arc2D.Double(x, counter + 10, 10, 10, 90, 180, Arc2D.OPEN));
			}
			
			// Draw Pointer
			Polygon arrow = new Polygon();
			arrow.addPoint(width - 35, 8);
			arrow.addPoint(width - 10, 25);
			arrow.addPoint(width - 35, 18);
			
			//Draw Arrow
			g2d.draw(arrow);
		}
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = insets.top = insets.right = insets.bottom = gap;
		return insets;
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}
}
