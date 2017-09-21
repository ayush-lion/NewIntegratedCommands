/**
 * 
 */
package com.app.callouts.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 13-Sept-2017
 */
public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private StudentPanel sPanel;
	private TeacherPanel tPanel;
	private CalloutsPanel cPanel;
	private List<String> data;
	private String stdalign;
	private String tuteralign;

	public String getStdalign() {
		return stdalign;
	}

	public void setStdalign(String stdalign) {
		this.stdalign = stdalign;
	}

	public String getTuteralign() {
		return tuteralign;
	}

	public void setTuteralign(String tuteralign) {
		this.tuteralign = tuteralign;
	}

	/**
	 * @return the data
	 */
	public List<String> getData() {
		return data;
	}

	/**
	 * @param tosend the data to set
	 */
	public void setData(List<String> tosend) {
		this.data = tosend;
	}

	public MainPanel() 
	{
		this.setLayout(new GridBagLayout());
		
		
		sPanel = new StudentPanel();
		tPanel = new TeacherPanel();
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("test");
			cPanel = new CalloutsPanel(temp,"right","left","");
		
		//Add constraints
		GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.fill = GridBagConstraints.BOTH;
		constraints.ipadx = 100;
		
		// Add Student Panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.add(sPanel, constraints);
		
		// Add Callout panel
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.weightx = 0.60;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		this.add(cPanel, constraints);
		
		
		
		// Add teacher Panel
		constraints.gridx = 2; 
		constraints.gridy = 0;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.add(tPanel, constraints);
		
	}
	
	public void ChangeInstructions(List<String> data, MainPanel main)
	{
		
		
		main.remove(cPanel);
		main.remove(tPanel);
		main.remove(sPanel);
		//cPanel = new CalloutsPanel(data);
		System.out.println(""+data.size());
		
	
		
		
		sPanel = new StudentPanel();
		tPanel = new TeacherPanel();
		
		if(main.getTuteralign().equals("left")) 
		{
		cPanel = new CalloutsPanel(data,main.getStdalign(),main.getTuteralign(),"");
		}
		else 
		{
		cPanel = new CalloutsPanel(data,main.getStdalign(),main.getTuteralign());	
		}
		
		main.setLayout(new GridBagLayout());
		//Add constraints
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.ipadx = 100;
		
		// Add Student Panel
		
		if(main.getStdalign().equals("left")) 
		{
			constraints.gridx = 0;	
		}
		else 
		{
			constraints.gridx = 3;	
		}
		
		constraints.gridy = 0;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		main.add(sPanel, constraints);
		
		// Add Callout panel
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.weightx = 0.60;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		main.add(cPanel, constraints);
		
		
		
		// Add teacher Panel
		
		System.out.println("tuter alignhhhh:"+getTuteralign());
		
		if(main.getTuteralign().equals("left")) 
		{
			constraints.gridx = 0; 	
		}
		else 
		{
			constraints.gridx = 3; 	
		}
		
		
		constraints.gridy = 0;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		main.add(tPanel, constraints);
		
		
		
	}
	
	
	
}
