package com.app.abacusResult;

import java.awt.Graphics;

public class RodValue {

	private long RodValue;
	
	private boolean switchable;
	
	private int posX;
	private int ePosY;
	private int hPosY;
	private String text;
	
	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public long getRodValue() {
		return RodValue;
	}
	public void setRodValue(long rodValue) {
		RodValue = rodValue;
	}
	public boolean isSwitchable() {
		return switchable;
	}
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getePosY() {
		return ePosY;
	}
	public void setePosY(int ePosY) {
		this.ePosY = ePosY;
	}
	public int gethPosY() {
		return hPosY;
	}
	public void sethPosY(int hPosY) {
		this.hPosY = hPosY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	private int width;
	private int height;

	

}
