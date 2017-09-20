/**
 * 
 */
package com.app.instructions.beans;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 08-Aug-2017
 */
public class Action {

	// Data members
	private String actionName;
	private Font font;
	private Layout layout;
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align; 
	}
	private Component component;
	private Integer rodNumber;
	private Integer beadNumber;
	private Integer[] beadNum;
	private String align;
	
	
	public Integer[] getBeadNum() {
		return beadNum;
	}
	public void setBeadNum(Integer[] beadNum) {
		this.beadNum = beadNum;
	}
	private Integer number;
	private String finger;
	
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the rodNumber
	 */
	public Integer getRodNumber() {
		return rodNumber;
	}
	/**
	 * @param rodNumber the rodNumber to set
	 */
	public void setRodNumber(Integer rodNumber) {
		this.rodNumber = rodNumber;
	}
	/**
	 * @return the beadNumber
	 */
	public Integer getBeadNumber() {
		return beadNumber;
	}
	/**
	 * @param beadNumber the beadNumber to set
	 */
	public void setBeadNumber(Integer beadNumber) {
		this.beadNumber = beadNumber;
	}
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * @return the finger
	 */
	public String getFinger() {
		return finger;
	}
	/**
	 * @param finger the finger to set
	 */
	public void setFinger(String finger) {
		this.finger = finger;
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}
	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}
	/**
	 * @param layout the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	/**
	 * @return the component
	 */
	public Component getComponent() {
		return component;
	}
	/**
	 * @param component the component to set
	 */
	public void setComponent(Component component) {
		this.component = component;
	}
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Action Name=> ").append(actionName);
		
		if(rodNumber != null)
			buf.append(" :: ").append("RodNumber=> ").append(rodNumber);
		
		if(beadNumber != null)
			buf.append(" :: ").append("BeadNumber=> ").append(beadNumber);
		
		if(number != null)
			buf.append(" :: ").append("Number=> ").append(number);
		
		if(finger != null)
			buf.append(" :: ").append("Finger=> ").append(finger);
		
		if(font != null) 
			buf.append(" :: ").append(font.toString());
		
		if(layout != null) 
			buf.append(" :: ").append(layout.toString());
		
		if(component != null) 
			buf.append(" :: ").append(component.toString());
		
		
		buf.append("\n");
		return buf.toString();
	}

}
