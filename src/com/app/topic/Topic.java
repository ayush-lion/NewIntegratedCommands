package com.app.topic;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;


import com.app.integrated.main.Performer;

public class Topic {

	private int posX;
	private int posY;
	private String text;
	private Performer performer;
	private Topic topic;

	public Topic(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
	}

	public Topic() {
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
