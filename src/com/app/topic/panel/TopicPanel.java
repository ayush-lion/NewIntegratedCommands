package com.app.topic.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.app.integrated.main.Performer;
import com.app.topic.Topic;

public class TopicPanel extends JPanel {

	public TopicPanel() {
		// TODO Auto-generated constructor stub
	}

	private int texttopicposX;
	private int texttopicposY;
	private int texttopicheight;
	private int texttopicweidth;
	private String text;
	private Topic topic;
	private String align;
	
	/**
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	private String fontName ;
	private String topicName;
	private int fontSize;
	
	private Performer performer;

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Dimension d = this.getPreferredSize();
		g.setFont(new Font(getFontName(), Font.BOLD, getFontSize()));
		g.setColor(Color.black);
		if(getAlign().equals("left"))
		{
			g.drawString(getTopicName(), g.getClipBounds().x+5, g.getClipBounds().height/2);
			g.drawLine(g.getClipBounds().x+5, g.getClipBounds().height/2+2, getTopicName().length()*10, g.getClipBounds().height/2+2);
		}
		else if(getAlign().equals("center"))
		{
			g.drawString(getTopicName(), g.getClipBounds().width/3, g.getClipBounds().height/2);
			g.drawLine(g.getClipBounds().x+5 + g.getClipBounds().width/3,  g.getClipBounds().height/2+2, getTopicName().length()*22,  g.getClipBounds().height/2+2);
		}
		else if(getAlign().equals("right"))
		{
			g.drawString(getTopicName(), g.getClipBounds().width-getTopicName().length()*10, g.getClipBounds().height/2);
			g.drawLine(g.getClipBounds().x+5 + g.getClipBounds().width/3 + getTopicName().length()*24,
		    g.getClipBounds().height/2+2,g.getClipBounds().x+5 + g.getClipBounds().width/3 + getTopicName().length()*15,g.getClipBounds().height/2+2);
		}
	}

	public int getTexttopicposX() {
		return texttopicposX;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setTexttopicposX(int texttopicposX) {
		this.texttopicposX = texttopicposX;
	}

	public int getTexttopicposY() {
		return texttopicposY;
	}

	public void setTexttopicposY(int texttopicposY) {
		this.texttopicposY = texttopicposY;
	}

	public int getTexttopicheight() {
		return texttopicheight;
	}

	public void setTexttopicheight(int texttopicheight) {
		this.texttopicheight = texttopicheight;
	}

	public int getTexttopicweidth() {
		return texttopicweidth;
	}

	public void setTexttopicweidth(int texttopicweidth) {
		this.texttopicweidth = texttopicweidth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
