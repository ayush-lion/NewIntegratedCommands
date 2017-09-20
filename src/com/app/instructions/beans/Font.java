/**
 * 
 */
package com.app.instructions.beans;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 25-Aug-2017
 */
public class Font {

	private String name;
	private String size;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Font Name => ").append(name).append(" :: ");
		buf.append("Font Size => ").append(size);
		return buf.toString();
	}
	
}
