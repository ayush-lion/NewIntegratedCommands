/**
 * 
 */
package com.app.callouts.panel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 13-Sept-2017
 */
public class CalloutsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CalloutPanel[] callouts ;
	private String stalign;
	private String ttalign;
	
	public CalloutsPanel(List<String> data,String stalign,String ttalign) {
		this.setLayout(new GridLayout(3,3));
		callouts = new CalloutPanel[data.size()*2];
		boolean isStudent = Boolean.TRUE;
		
		for (int i = 0; i < data.size(); i++) { 
			
			if(data.get(i).startsWith("ST")) 
			{ 
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			}	
		}
	}
	
	public CalloutsPanel(List<String> data,String stalign,String ttalign,String string) {
		this.setLayout(new GridLayout(3,3));
		callouts = new CalloutPanel[data.size()*2];
		boolean isStudent = Boolean.TRUE;
		
		for (int i = 0; i < data.size(); i++) { 
			
			if(data.get(i).startsWith("ST")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
		
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}	
		}
 }
	
	
	
	
	
	

	public String getStalign() {
		return stalign;
	}

	public void setStalign(String stalign) {
		this.stalign = stalign;
	}

	public String getTtalign() {
		return ttalign;
	}

	public void setTtalign(String ttalign) {
		this.ttalign = ttalign;
	}
}
