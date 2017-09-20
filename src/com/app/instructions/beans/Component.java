/**
 * 
 */
package com.app.instructions.beans;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 26-Aug-2017
 */
public class Component {

	private String name;
	private String align;
	private String teacherAlign; 
	private String studentAlign;
	private String numOfRods;
	private String image;
	private String rodnNmbers;
	
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	public String getRodnNmbers() {
		return rodnNmbers;
	}
	public void setRodnNmbers(String rodnNmbers) {
		this.rodnNmbers = rodnNmbers;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
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
	/**
	 * @return the teacherAlign
	 */
	public String getTeacherAlign() {
		return teacherAlign;
	}
	/**
	 * @param teacherAlign the teacherAlign to set
	 */
	public void setTeacherAlign(String teacherAlign) {
		this.teacherAlign = teacherAlign;
	}
	/**
	 * @return the studentAlign
	 */
	public String getStudentAlign() {
		return studentAlign;
	}
	/**
	 * @param studentAlign the studentAlign to set
	 */
	public void setStudentAlign(String studentAlign) {
		this.studentAlign = studentAlign;
	}
	/**
	 * @return the numOfRods
	 */
	public String getNumOfRods() {
		return numOfRods;
	}
	/**
	 * @param numOfRods the numOfRods to set
	 */
	public void setNumOfRods(String numOfRods) {
		this.numOfRods = numOfRods;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Component Name => ").append(name).append(" :: ");
		if(align != null)
			buf.append(" :: ").append("Align => ").append(align);
		if(teacherAlign != null)
			buf.append(" :: ").append("Teacher Align => ").append(teacherAlign);
		if(studentAlign != null)
			buf.append(" :: ").append("Student Align => ").append(studentAlign);
		if(numOfRods != "0")
			buf.append(" :: ").append("Number Of Rods => ").append(numOfRods);
		buf.append("\n");
		return buf.toString();
	}
	public String getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	
}
