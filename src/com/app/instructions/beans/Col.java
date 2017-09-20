/**
 * 
 */
package com.app.instructions.beans;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 25-Aug-2017
 */
public class Col {

	private int width;
	private int height;
	private String componentName;
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}
	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Component => ").append(componentName).append(" :: ");
		buf.append("Width => ").append(width).append(" :: ");
		buf.append("Height => ").append(height);
		return buf.toString();
	}
	
}
