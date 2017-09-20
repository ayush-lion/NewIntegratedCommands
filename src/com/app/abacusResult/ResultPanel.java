package com.app.abacusResult;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ResultPanel extends JPanel {

	private int inputRodValue;
	private int inputBeadValue;
	private int actionName;
	private int result;
	
	
	public int getInputRodValue() {
		return inputRodValue;
	}
	public void setInputRodValue(int inputRodValue) {
		this.inputRodValue = inputRodValue;
	}
	public int getInputBeadValue() {
		return inputBeadValue;
	}
	public void setInputBeadValue(int inputBeadValue) {
		this.inputBeadValue = inputBeadValue;
	}
	public int getActionName() {
		return actionName;
	}
	public void setActionName(int actionName) {
		this.actionName = actionName;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
