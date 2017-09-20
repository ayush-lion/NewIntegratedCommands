/**
 * 
 */
package com.app.instructions.compiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.app.instructions.beans.Action;
import com.app.instructions.beans.Col;
import com.app.instructions.beans.Component;
import com.app.instructions.beans.Font;
import com.app.instructions.beans.Layout;
import com.app.instructions.beans.Row;
import com.app.instructions.util.InstructionLogger;

/**
 * Class is responsible to validate instructions
 * 
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 06-Aug-2017
 */
public class InstructionsVerification {

	private Stack<String> tagStack;
	private List<String> listOfValidTags;
	private List<String> noClosingTags;
	private List<String> commands;
	private InstructionLogger logger;
	private List<String> errors;
	private List<Action> listOfActions;
	private List<String> specialCommands;
	
	/**
	 * Parameterized constructor to setup initial values
	 */
	public InstructionsVerification(InstructionLogger logger) {
		this.logger = logger;
	}
	
	/**
	 * Method is responsible to verify Special Actions
	 */
	public List<String> validateSpecialActions(String actions) {
		// Creating a list of Actions
		listOfActions = new ArrayList<Action>();
		
		// Initialize error
		errors = new ArrayList<>();
		
		// Parse Special Actions
		if (actions != null && actions.trim().length() > 0) {
			String[] actionList = actions.split("\n");
			
			for (String action : actionList) {
				String act = action.replaceAll("\\s", "");
				act = act.toLowerCase();
				boolean isFound = Boolean.FALSE;
				Action nAction = new Action();
				for (String command : specialCommands) {
					isFound = Boolean.FALSE;
					if(act.startsWith(command.toLowerCase()) || act.equalsIgnoreCase(command)) {
						isFound = Boolean.TRUE;
						nAction.setActionName(command);
						switch (command) {
							case "Font":
								parseFonts(actions, nAction, errors);
								break;
							case "Layout":
								parseLayout(actions, nAction, errors);
								break;
							case "Component":
								parseComponent(actions, nAction, errors);
								break;
							case "SubP":
								nAction.setActionName("SubjectName");
								validateSubjectDetailsCommand(act, errors);
								break;
						}
					}
					if(isFound)
						break;
				}
				listOfActions.add(nAction);
				break;
			}
		}
		
		// Print errors
		logger.logDebug(errors);
		
		// Return
		return errors;
	}
	
	/**
	 * Method is responsible to parse component details
	 */
	private void parseComponent(String actions, Action nAction, List<String> error) {
		Component component = new Component();
		if (actions != null && actions.trim().length() > 0) {
			String[] actionList = actions.split("\n");
			
			for (int i = 0; i < actionList.length; i++) {
				String act = actionList[i].replaceAll("\\s", "");
				act = act.toLowerCase();
				
				logger.logDebug("Layout Comp ==> " + act);
				if(i == 0) { //Parse Component
					String compArr[] = act.split("=");
					if(compArr.length != 2) {
						error.add(act + " ==> not a valid Component 001.");
						return;
					}
					String componentName = compArr[1].trim();
					if(!componentName.equalsIgnoreCase("Abacus") && !componentName.equalsIgnoreCase("Topic")
							&& !componentName.equalsIgnoreCase("Actor") && !componentName.equalsIgnoreCase("Image")) {
						error.add(act + " ==> not a valid Component 009.");
						return;
					}
					component.setName(compArr[1].trim());
				} else {
					if(component.getName() != null && component.getName().toLowerCase().equalsIgnoreCase("Abacus")) {
						if(!validateAbacusRodsCommand(act, errors)) {
							break;
						}
						
						// get Rod Number
						nAction.setRodNumber(Integer.valueOf(act.substring(10, 12)));
					} else if(component.getName() != null && component.getName().toLowerCase().equalsIgnoreCase("Topic")) {
						if(!act.toLowerCase().startsWith("align")) {
							error.add(act + " ==> not a valid Component 002.");
							return;
						}
						
						String alignArr[] = act.split("=");
						if(alignArr.length != 2) {
							error.add(act + " ==> not a valid Component 003.");
							return;
						}
						
						if(!alignArr[1].equalsIgnoreCase("left") && !alignArr[1].equalsIgnoreCase("right")
								&& !alignArr[1].equalsIgnoreCase("top") && !alignArr[1].equalsIgnoreCase("bottom")) {
							error.add(act + " ==> not a valid Component 009.");
							return;
						}
						
						component.setAlign(alignArr[1]);
					} else if(component.getName() != null && component.getName().toLowerCase().equalsIgnoreCase("Actor")) {
						if(!act.toLowerCase().startsWith("teacher") && !act.toLowerCase().startsWith("student")) {
							error.add(act + " ==> not a valid Component 004.");
							return;
						}
						
						if(act.toLowerCase().startsWith("teacher")) {
							String teacherArr[] = act.split("=");
							if(teacherArr.length != 2) {
								error.add(act + " ==> not a valid Component 005.");
								return;
							}
							if(!teacherArr[1].equalsIgnoreCase("left") && !teacherArr[1].equalsIgnoreCase("right")
									&& !teacherArr[1].equalsIgnoreCase("top") && !teacherArr[1].equalsIgnoreCase("bottom")) {
								error.add(act + " ==> not a valid Component 006.");
								return;
							}
							component.setTeacherAlign(teacherArr[1]);
						} else if(act.toLowerCase().startsWith("student")) {
							String studentArr[] = act.split("=");
							if(studentArr.length != 2) {
								error.add(act + " ==> not a valid Component 007.");
								return;
							}
							
							if(!studentArr[1].equalsIgnoreCase("left") && !studentArr[1].equalsIgnoreCase("right")
									&& !studentArr[1].equalsIgnoreCase("top") && !studentArr[1].equalsIgnoreCase("bottom")) {
								error.add(act + " ==> not a valid Component 008.");
								return;
							}
							
							component.setStudentAlign(studentArr[1]);
						}
						
					} 
				}
			}
		}
		nAction.setComponent(component);
	}
	
	/**
	 * Method is responsible to parse Layout action and generate Action POJO
	 */
	private void parseLayout(String actions, Action nAction, List<String> error) {
		Layout layout = new Layout();
		if (actions != null && actions.trim().length() > 0) {
			String[] actionList = actions.split("\n");
			
			for (int i = 0; i < actionList.length; i++) {
				String act = actionList[i].replaceAll("\\s", "");
				act = act.toLowerCase();
				
				logger.logDebug("Layout act ==> " + act);
				
				if(i == 0) { //Parse Layout
					if(! (act.contains("rows") && act.contains("cols"))) {
						error.add(act + " ==> not a valid Layout 001.");
						return;
					}
					
					//Parse Number Of ROWs
					String numOfRowsAct = act.substring(act.indexOf("layout") + 6, act.indexOf("cols"));
					String numOfRowsArr[] = numOfRowsAct.split("=");
					
					if(numOfRowsArr.length != 2) {
						error.add(act + " ==> not a valid Layout 002.");
						return;
					}
					logger.logDebug("numOfRowsAct" + numOfRowsArr[1]);
					
					try {
						layout.setNumOfRow(Integer.parseInt(numOfRowsArr[1]));
					} catch (NumberFormatException nfe) {
						error.add(act + " ==> not a valid Layout 003.");
						return;
					}
					
					//Parse Number Of COLs
					String numOfColsAct = act.substring(act.indexOf("cols"));
					String numOfColsArr[] = numOfColsAct.split("=");
					
					if(numOfColsArr.length != 2) {
						error.add(act + " ==> not a valid Layout 002.");
						return;
					}
					logger.logDebug("numOfColsAct" + numOfColsArr[1]);
					
					try {
						layout.setNumOfCols(Integer.parseInt(numOfColsArr[1]));
					} catch (NumberFormatException nfe) {
						error.add(act + " ==> not a valid Layout 003.");
						return;
					}
					
					// Creating ROWs
					List<Row> listOfRows = new ArrayList<Row>();
					for (int r = 0; r < layout.getNumOfRow(); r++) {
						Row row = new Row();
						listOfRows.add(row);
					}
					
					//Creating COLs
					for (int r = 0; r < layout.getNumOfRow(); r++) {
						List<Col> listOfCols = new ArrayList<Col>();
						for(int c = 0; c < layout.getNumOfCols(); c++) {
							Col col = new Col();
							listOfCols.add(col);
						}
						listOfRows.get(r).setCols(listOfCols);;
					}
					
					layout.setRows(listOfRows);
					
				} else { //Parse Rows and Cols
					if(! (act.contains("row") && act.contains("col")
							&& act.contains("width") && act.contains("height")
							&& act.contains("component"))) {
						error.add(act + " ==> not a valid Layout 004.");
						return;
					}
					
					//Parse ROW
					Row row = getRowFromLayout(act, errors, layout);
					
					//Parse COL
					Col col = getColFromRow(act, errors, row);
					
					//Parse WIDTH
					int width = getWidthFromAction(act, errors);
					
					//Parse HEIGHT
					int height = getHeightFromAction(act, errors);
					
					//Parse Component
					String component = getComponentFromAction(act, errors);
					
					//System.out.println("Width ==> " + width + ", Height ==> " + height + ", Component ==> " + component);
					
					if(col != null) {
						row.setWidth(row.getWidth() + width);
						row.setHeight(row.getHeight() + height);
						
						col.setWidth(width);
						col.setHeight(height);
						col.setComponentName(component);
					}
				}
			}
		}
		nAction.setLayout(layout);
	}
	
	/**
	 * Method to get row from layout
	 */
	private Row getRowFromLayout(String act, List<String> error, Layout layout) {
		//Parse ROW
		String numOfRowsAct = act.substring(0, act.indexOf("col"));
		String numOfRowsArr[] = numOfRowsAct.split("=");
		
		if(numOfRowsArr.length != 2) {
			error.add(act + " ==> not a valid Layout 005.");
			return null;
		}
		try {
			int rowNum = Integer.parseInt(numOfRowsArr[1]);
			logger.logDebug("numOfRowsAct" + rowNum);
			
			// Get Row
			return layout.getRows().get(rowNum - 1);
			
		} catch (NumberFormatException nfe) {
			error.add(act + " ==> not a valid Layout 006.");
			return null;
		} catch (IndexOutOfBoundsException iobe) {
			error.add(act + " ==> not a valid Layout 007.");
			return null;
		}
	}
	
	/**
	 * Method to get exact col from Row
	 */
	private Col getColFromRow(String act, List<String> error, Row row) {
		String numOfColsAct = act.substring(act.indexOf("col") + 3, act.indexOf("width"));
		String numOfColsArr[] = numOfColsAct.split("=");
		
		if(numOfColsArr.length != 2) {
			error.add(act + " ==> not a valid Layout 008.");
			return null;
		}
		
		try {	
			int colNum = Integer.parseInt(numOfColsArr[1]);
			return row.getCols().get(colNum - 1);
		} catch (NumberFormatException nfe) {
			error.add(act + " ==> not a valid Layout 009.");
			return null;
		} catch (IndexOutOfBoundsException iobe) {
			error.add(act + " ==> not a valid Layout 010.");
			return null;
		}
	}
	
	/**
	 * Method to get exact col from Row
	 */
	private int getWidthFromAction(String act, List<String> error) {
		String numOfWidthAct = act.substring(act.indexOf("width") + 5, act.indexOf("height"));
		String numOfWidthArr[] = numOfWidthAct.split("=");
		
		if(numOfWidthArr.length != 2) {
			error.add(act + " ==> not a valid Layout 011.");
			return -1;
		}
		
		try {	
			return Integer.parseInt(numOfWidthArr[1]);
		} catch (NumberFormatException nfe) {
			error.add(act + " ==> not a valid Layout 012.");
			return -1;
		} catch (IndexOutOfBoundsException iobe) {
			error.add(act + " ==> not a valid Layout 013.");
			return -1;
		}
	}
	
	/**
	 * Method to get exact col from Row
	 */
	private int getHeightFromAction(String act, List<String> error) {
		String numOfHeightAct = act.substring(act.indexOf("height") + 6, act.indexOf("component"));
		String numOfHeightArr[] = numOfHeightAct.split("=");
		
		if(numOfHeightArr.length != 2) {
			error.add(act + " ==> not a valid Layout 014.");
			return -1;
		}
		
		try {	
			return Integer.parseInt(numOfHeightArr[1]);
		} catch (NumberFormatException nfe) {
			error.add(act + " ==> not a valid Layout 015.");
			return -1;
		} catch (IndexOutOfBoundsException iobe) {
			error.add(act + " ==> not a valid Layout 016.");
			return -1;
		}
	}
	
	/**
	 * Method to get exact col from Row
	 */
	private String getComponentFromAction(String act, List<String> error) {
		String numOfComponentAct = act.substring(act.indexOf("component"));
		String numOfComponentArr[] = numOfComponentAct.split("=");
		
		if(numOfComponentArr.length != 2) {
			error.add(act + " ==> not a valid Layout 017.");
			return null;
		}
		
		try {	
			return numOfComponentArr[1];
		} catch (NumberFormatException nfe) {
			error.add(act + " ==> not a valid Layout 018.");
			return null;
		} catch (IndexOutOfBoundsException iobe) {
			error.add(act + " ==> not a valid Layout 019.");
			return null;
		}
	}
	
	/**
	 * Method is responsible to parse Font action and generate Action POJO
	 */
	private void parseFonts(String actions, Action nAction, List<String> error) {
		String act = actions.replaceAll("\\s", "");
		act = act.toLowerCase();
		logger.logDebug("Font Action ==> " + act);
		
		if(! (act.contains("name") && act.contains("size"))) {
			error.add(act + " ==> not a valid Font 001.");
			return;
		}
		
		String fontNameAct = act.substring(act.indexOf("font") + 4, act.indexOf("size"));
		String fontNameArr[] = fontNameAct.split("=");
		
		if(fontNameArr.length != 2) {
			error.add(act + " ==> not a valid Font 002.");
			return;
		}
		logger.logDebug("fontNameAct" + fontNameArr[1]);
		
		Font font = new Font();
		font.setName(fontNameArr[1]);
		
		String fontSizeAct = act.substring(act.indexOf("size"));
		String fontSizeArr[] = fontSizeAct.split("=");
		
		if(fontSizeArr.length != 2) {
			error.add(act + " ==> not a valid Font 003.");
			return;
		}
		
		logger.logDebug("fontSizeAct" + fontSizeArr[1]);
		
		font.setSize(fontSizeArr[1]);
		nAction.setFont(font);
	}
	
	/**
	 * Method is responsible to verify tags
	 */
	public List<String> validateInstruction(String instruction) {
		// Prints Instruction
		logger.logDebug("Instruction ==> " + instruction);
		
		// Remove all the extra spaces
		instruction = instruction.replaceAll("\\s+", " ");
		
		// Initialize error list and stack
		errors = new ArrayList<String>();
		tagStack = new Stack<String>();
		
		// Start verifying tags
		LinkedList<InstructionTag> listOftags = InstructionTag.tokenize(instruction);
		//logger.logInfo(listOftags);
		for (InstructionTag instructionTag : listOftags) {
			String tag = instructionTag.toString();
			
			if(tag.charAt(0) == '<' && tag.charAt(1) == '/') {
				
				String closingTag = tag.substring(0, 1) + tag.substring(2);
				if(tagStack.isEmpty()) {
					errors.add(tag + " is not started!!!");
					continue;
				}
				String tagInStack = tagStack.pop();
				
				if(!tagInStack.equalsIgnoreCase(closingTag)) {
					errors.add(tagInStack + " is not properly closed!!!");
					continue;
				}
				
			} else if(tag.charAt(0) == '<') {
				String tagForCompare = tag.substring(1,tag.length() - 1);
				//logger.logInfo("tagForCompare ==> " + tagForCompare);
				if(!listOfValidTags.contains(tagForCompare) && !noClosingTags.contains(tagForCompare)) {
					errors.add(tag + " not a valid tag!!!");
					continue;
				}
				if(noClosingTags.contains(tagForCompare.toLowerCase()))
					continue;
				tagStack.push(tag);
			}
		}
		
		// Print errors
		logger.logDebug(errors);
		
		// Return
		return errors;
	}
	
	/**
	 * Method is responsible to verify tags
	 */
	public List<String> validateActions(String actions) {
		// Creating a list of Actions
		listOfActions = new ArrayList<Action>();
		
		// Prints Actions
		logger.logDebug("Actions ==> " + actions);
		
		// Parse Actions
		errors = parseActions(actions);
		
		// Print errors
		logger.logDebug(errors);
		
		// Return
		return errors;
	}
	
	/**
	 * Parse Actions
	 */
	private List<String> parseActions(String actions) {
		List<String> error = new ArrayList<>();
		if (actions != null && actions.trim().length() > 0) {
			String[] actionList = actions.split("\n");
			
			for (String action : actionList) {
				String act = action.replaceAll("\\s", "");
				act = act.toLowerCase();
				boolean isFound = Boolean.FALSE;
				
				for (String command : commands) {
					isFound = Boolean.FALSE;
					if(act.startsWith(command.toLowerCase()) || act.equalsIgnoreCase(command)) {
						isFound = Boolean.TRUE;
						Action nAction = new Action();
						nAction.setActionName(command);
						boolean isAddRodCommand = Boolean.FALSE;
						//System.out.println(act + " :: Command is ==> " + command);
						switch (command) {
							case "Rod":
								if(!validateRodCommand(act, error)) {
									break;
								}
								String nRod = act.substring(command.length(), command.length() + 2);
								Integer iRod = Integer.valueOf(nRod.trim());
								nAction.setRodNumber(iRod);
								
								if(act.contains("bead")) {
									String beadNumber = act.substring(act.indexOf("bead") + 4, act.indexOf("bead") + 6);
									Integer bNum = Integer.valueOf(beadNumber.trim());
									nAction.setBeadNumber(bNum);
								}
								
								if(act.contains("use")) {
									String finger = act.substring(act.indexOf("use"));
									nAction.setFinger(finger);
								}
								break;
							case "AddRod":
								isAddRodCommand = Boolean.TRUE;
								if(!validateAddRodCommand(act, error)) {
									break;
								}
							case "MinusRod":
								if(!isAddRodCommand && !validateMinusRodCommand(act, error)) {
									break;
								}
								
								String rodNumber = act.substring(command.length(), act.indexOf("bead"));
								Integer rNum = Integer.valueOf(rodNumber.trim());
								nAction.setRodNumber(rNum);
								
								if(act.length() == 16 || act.length() == 14) { // IF NO FINGER
									String beadNumber = act.substring(act.indexOf("bead") + 4, act.indexOf("bead") + 6);
									Integer bNum = Integer.valueOf(beadNumber.trim());
									nAction.setBeadNumber(bNum);
								} else {
									String beadNumber = act.substring(act.indexOf("bead") + 4, act.indexOf("use"));
									Integer bNum = Integer.valueOf(beadNumber.trim());
									nAction.setBeadNumber(bNum);
									
									String finger = act.substring(act.indexOf("use"));
									nAction.setFinger(finger);
								}
								
								break;
							case "Wait":
								if(!validateWaitCommand(act, error)) {
									break;
								}
								String number = act.substring(act.indexOf("wait") + 4);
								Integer num = Integer.valueOf(number.trim());
								nAction.setNumber(num);
								break;
							case "HighlightRod":
								if(!validateHighlightRodCommand(act, error)) {
									break;
								}
								// set action Name
								nAction.setActionName("HighlightRod");
								
								// get Rod Number
								nAction.setRodNumber(Integer.valueOf(act.substring(12, 14)));
								
								// get bead Number
								int lenHighlightRod = act.length();
								if(lenHighlightRod == 20 || lenHighlightRod == 34 || lenHighlightRod == 35) {
									nAction.setBeadNumber(Integer.valueOf(act.substring(18, 20)));
								}
								
								// get finger
								if(lenHighlightRod > 20) {
									if(lenHighlightRod == 28 || lenHighlightRod == 29) {
										nAction.setFinger(act.substring(14));
									} else if(lenHighlightRod == 34 || lenHighlightRod == 35) {
										nAction.setFinger(act.substring(20));
									} 
								}
								
								break;
								
							case "HighlightFrame":
							case "HighlightRods":
							case "HighlightBeam":
							case "HighlightLowerBeads":
							case "HighlightUpperBeads":
							case "HighlightDots":
							case "StopBlink":
							case "ShowAllLabels":
							case "HideAllLabels":
							case "ShowLBL_Rod":
							case "ShowLBL_Lbeads":
							case "ShowLBL_Ubeads":
							case "ShowLBL_Frame":
							case "HidePlaceValue":
							case "Reset":
								validateSingleCommands(act, error);
								break;
								
							case "Answer":
								if(!validateDisplayCommand(act, error)) {
									break;
								}
								String display = act.substring(act.indexOf("display") + 7);
								Integer disp = Integer.valueOf(display.trim());
								nAction.setNumber(disp);
								break;
								
							case "BlinkRod":
								if(!validateBlinkRodCommand(act, error)) {
									break;
								}
								// set action Name
								nAction.setActionName("BlinkRod");
								
								// get Rod Number
								nAction.setRodNumber(Integer.valueOf(act.substring(8, 10)));
								
								// get bead Number
								int lenBlinkRod = act.length();
								if(lenBlinkRod == 16 || lenBlinkRod == 30 || lenBlinkRod == 31) {
									nAction.setBeadNumber(Integer.valueOf(act.substring(14, 16)));
								}
								
								// get finger
								if(lenBlinkRod > 16) {
									if(lenBlinkRod == 24 || lenBlinkRod == 25) {
										nAction.setFinger(act.substring(10));
									} else if(lenBlinkRod == 30 || lenBlinkRod == 31) {
										nAction.setFinger(act.substring(16));
									} 
								}
								break;
							
						}
						listOfActions.add(nAction);
					}
					if(isFound) {
						break;
					} 
				}
				if(!isFound) {
					error.add(act + " not a valid Action. Please validate (777) !!!");
				}
				
			}
		}
		return error;
	}
	
	/**
	 * ROD04 = 5, ROD04UseLeftPointer = 19, ROD04UseRightPointer = 20
	 * ROD04Bead03 = 11, ROD04Bead03UseLeftPointer = 25, ROD04Bead03UseRightPointer = 26
	 */
	private boolean validateRodCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 5 || len == 11 || len == 19 || len == 20 || len == 25 || len == 26) {
			
			String rodNumber = action.substring(3, 5);
			
			try {
				Integer.valueOf(rodNumber);
				if(action.contains("bead")) {
					String beadNumber = action.substring(9, 11);
					Integer.valueOf(beadNumber);
				}
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod or bead number. Please validate!!!");
				return Boolean.FALSE;
			}
			
			String command = "";
			
			// Get the command
			if(len == 5) {
				command = action.substring(0, 3);
			} else if(len == 11) {
				command = action.substring(0, 3) + action.substring(5, 9);
			} else if(len == 19 || len == 20) {
				command = action.substring(0, 3) + action.substring(5);
			} else {
				command = action.substring(0, 3) + action.substring(5, 9) + action.substring(11);
			}
			logger.logDebug("Extracted Command : " + command);
			
			
			if(command.equalsIgnoreCase("Rod") || command.equalsIgnoreCase("RodBead")
					|| command.equalsIgnoreCase("RODBeadUseRightPointer") || command.equalsIgnoreCase("RODBeadUseLeftPointer")
					|| command.equalsIgnoreCase("RODUseRightPointer") || command.equalsIgnoreCase("RODUseLeftPointer")
					//|| command.equalsIgnoreCase("AddRODBeadUseRightPointer") || command.equalsIgnoreCase("AddRODBeadUseLeftPointer")
					) {
				logger.logDebug(action + " is Good");
			} else {
				errors.add(action + " not a valid action. Please validate (001)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (002)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * AddROD04Bead03 = 14, AddROD04Bead03UseLeftThumb = 26, AddROD04Bead03UseRightThumb = 27, AddROD04Bead03UseLeftThumbIndex = 31,
	 * AddROD04Bead03UseRightThumbIndex = 32, AddROD04Bead03UseLeftIndex = 26, AddROD04Bead03UseRightIndex = 27, AddROD04Bead03UseRightPointer = 29,
	 * AddROD04Bead03UseLeftPointer = 28
	 */
	private boolean validateAddRodCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 14 || len == 26 || len == 27 || len == 28 || len == 29 || len == 31 || len == 32) {
			
			// Verify rod and bead numbers
			//System.out.println("action ==> " + action);
			String rodNumber = action.substring(6, 8);
			String beadNumber = action.substring(12, 14);
			try {
				Integer.valueOf(rodNumber);
				Integer.valueOf(beadNumber);
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod or bead number. Please validate!!!");
				return Boolean.FALSE;
			}
			
			String command = "";
			
			// Get the command
			if(len == 14) {
				command = action.substring(0, 6) + action.substring(8, 12);
			} else {
				command = action.substring(0, 6) + action.substring(8, 12) + action.substring(14);
			}
			logger.logDebug("Extracted Command : " + command);
			
			
			if(command.equalsIgnoreCase("AddRODBead") || command.equalsIgnoreCase("AddRODBeadUseLeftThumb")
					|| command.equalsIgnoreCase("AddRODBeadUseRightThumb") || command.equalsIgnoreCase("AddRODBeadUseLeftThumbIndex")
					|| command.equalsIgnoreCase("AddRODBeadUseRightThumbIndex") || command.equalsIgnoreCase("AddRODBeadUseLeftIndex")
					|| command.equalsIgnoreCase("AddRODBeadUseRightIndex") 
					//|| command.equalsIgnoreCase("AddRODBeadUseRightPointer") || command.equalsIgnoreCase("AddRODBeadUseLeftPointer")
					) {
				logger.logDebug(action + " is Good");
			} else {
				errors.add(action + " not a valid action. Please validate (001)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (002)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * MinusROD04Bead03 = 16, MinusROD04Bead03UseLeftThumb = 28, MinusROD04Bead03UseRightThumb = 29, MinusROD04Bead03UseLeftThumbIndex = 33,
	 * MinusROD04Bead03UseRightThumbIndex = 34, MinusROD04Bead03UseLeftIndex = 28, MinusROD04Bead03UseRightIndex = 29, MinusROD04Bead03UseRightPointer = 31,
	 * MinusROD04Bead03UseLeftPointer = 27
	 */
	private boolean validateMinusRodCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 16 || len == 29 || len == 27 || len == 28 || len == 31 || len == 33 || len == 34) {
			
			// Verify rod and bead numbers
			String rodNumber = action.substring(8, 10);
			String beadNumber = action.substring(14, 16);
			try {
				Integer.valueOf(rodNumber);
				Integer.valueOf(beadNumber);
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod or beab number. Please validate!!!");
				return Boolean.FALSE;
			}
			
			String command = "";
			
			// Get the command
			if(len == 15) {
				command = action.substring(0, 8) + action.substring(10, 14);
			} else {
				command = action.substring(0, 8) + action.substring(10, 14) + action.substring(16);
			}
			logger.logDebug("Extracted Command : " + command);
			
			if(command.equalsIgnoreCase("MinusRODBead") || command.equalsIgnoreCase("MinusRODBeadUseLeftThumb")
					|| command.equalsIgnoreCase("MinusRODBeadUseRightThumb") || command.equalsIgnoreCase("MinusRODBeadUseLeftThumbIndex")
					|| command.equalsIgnoreCase("MinusRODBeadUseRightThumbIndex") || command.equalsIgnoreCase("MinusRODBeadUseLeftIndex")
					|| command.equalsIgnoreCase("MinusRODBeadUseRightIndex") 
					//|| command.equalsIgnoreCase("MinusRODBeadUseRightPointer") || command.equalsIgnoreCase("MinusRODBeadUseLeftPointer")
					) {
				logger.logDebug(action + " is Good");
			} else {
				errors.add(action + " not a valid action. Please validate (003)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (004)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * Validate Wait Command
	 */
	private boolean validateWaitCommand(String action, List<String> errors) {
		
		int len = action.length();
		if(len != 5) {
			errors.add(action + " is not valid. Please validate!!!.");
			return Boolean.FALSE;
		}
		
		String waitNum = action.substring(4, 5);
		try {
			Integer.valueOf(waitNum);
		} catch (NumberFormatException nfe) {
			errors.add(action + " doesn't contains valid wait time. Please validate (005)!!!");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * Validate Single Commands
	 */
	private boolean validateSingleCommands(String action, List<String> errors) {
		if(action.equalsIgnoreCase("HighlightFrame") || action.equalsIgnoreCase("HighlightRods")
				|| action.equalsIgnoreCase("HighlightBeam") || action.equalsIgnoreCase("HighlightLowerBeads")
				|| action.equalsIgnoreCase("HighlightUpperBeads") || action.equalsIgnoreCase("HighlightDots")
				|| action.equalsIgnoreCase("Reset") || action.equalsIgnoreCase("StopBlink")
				|| action.equalsIgnoreCase("ShowAllLabels") || action.equalsIgnoreCase("HideAllLabels")
				|| action.equalsIgnoreCase("ShowLBL_Rod") || action.equalsIgnoreCase("ShowLBL_Lbeads")
				|| action.equalsIgnoreCase("ShowLBL_Ubeads") || action.equalsIgnoreCase("ShowLBL_Frame")
				|| action.equalsIgnoreCase("HidePlaceValue")) {
			logger.logDebug(action + " is valid command");
		} else {
			errors.add(action + " not a Valid command. Please validate (001)!!!");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * Validate HightlightRod command
	 * HighlightRod04 = 14, HighlightRod04UseRightPointer = 29, HighlightRod04Bead05 = 20, HighlightRod04Bead03UseLeftPointer = 34
	 * HighlightRod04UseLeftPointer = 28, HighlightRod04Bead03UseRightPointer = 35
	 */
	private boolean validateHighlightRodCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 14 || len == 28 || len == 29 || len == 20 || len == 34 || len == 35) {
			// Validate Rod Number
			String number = action.substring(12, 14);
			try {
				Integer.valueOf(number);
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod number. Please validate (006)!!!");
				return Boolean.FALSE;
			}
			
			// Validate Bead Number
			if(len == 20 || len == 34 || len == 35) {
				number = action.substring(18, 20);
				try {
					Integer.valueOf(number);
				} catch (NumberFormatException nfe) {
					errors.add(action + " doesn't contains valid bead number. Please validate (007)!!!");
					return Boolean.FALSE;
				}
			}
			
			String command = "";
			// Get the command
			if(len == 14) {
				command = action.substring(0, 12);
			} else if(len == 20){
				command = action.substring(0, 12) + action.substring(14, 18);
			} else if(len == 28 || len == 29){
				command = action.substring(0, 12) + action.substring(14);
			} else {
				command = action.substring(0, 12) + action.substring(14, 18) + action.substring(20);
			}
			logger.logDebug("Extracted Command : " + command);
			
			if(command.equalsIgnoreCase("HighlightRod") || command.equalsIgnoreCase("HighlightRodUseRightPointer")
					|| command.equalsIgnoreCase("HighlightRodBead") || command.equalsIgnoreCase("HighlightRodBeadUseLeftPointer")
					|| command.equalsIgnoreCase("HighlightRodUseLeftPointer") || command.equalsIgnoreCase("HighlightRodBeadUseRightPointer")) {
				logger.logDebug(command + " is valid command");
			} else {
				errors.add(action + " not a Valid command. Please validate (002)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (006)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * Validate Answer Command
	 * Answer4
	 */
	private boolean validateDisplayCommand(String action, List<String> errors) {
		String displayNum = action.substring(6);
		try {
			Integer.valueOf(displayNum);
		} catch (NumberFormatException nfe) {
			errors.add(action + " doesn't contains valid Answer count. Please validate (007)!!!");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * Validate BlinkRod command
	 * BlinkRod04 = 10, BlinkRod04UseRightPointer = 25, BlinkRod04Bead05 = 16, BlinkRod04Bead03UseLeftPointer = 30
	 * BlinkRod04UseLeftPointer = 24, BlinkRod04Bead03UseRightPointer = 31
	 */
	private boolean validateBlinkRodCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 10 || len == 25 || len == 16 || len == 30 || len == 24 || len == 31) {
			// Validate Rod Number
			String number = action.substring(8, 10);
			try {
				Integer.valueOf(number);
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod number. Please validate (008)!!!");
				return Boolean.FALSE;
			}
			
			// Validate Bead Number
			if(len == 16 || len == 30 || len == 31) {
				number = action.substring(14, 16);
				try {
					Integer.valueOf(number);
				} catch (NumberFormatException nfe) {
					errors.add(action + " doesn't contains valid bead number. Please validate (009)!!!");
					return Boolean.FALSE;
				}
			}
			
			String command = "";
			// Get the command
			if(len == 10) {
				command = action.substring(0, 8);
			} else if(len == 16){
				command = action.substring(0, 8) + action.substring(10, 14);
			} else if(len == 24 || len == 25){
				command = action.substring(0, 8) + action.substring(10);
			} else {
				command = action.substring(0, 8) + action.substring(10, 14) + action.substring(16);
			}
			logger.logDebug("Extracted Command : " + command);
			
			if(command.equalsIgnoreCase("BlinkRod") || command.equalsIgnoreCase("BlinkRodBead")
					|| command.equalsIgnoreCase("BlinkRodBeadUseRightPointer") || command.equalsIgnoreCase("BlinkRodBeadUseLeftPointer")
					|| command.equalsIgnoreCase("BlinkRodUseRightPointer") || command.equalsIgnoreCase("BlinkRodUseLeftPointer")) {
				logger.logDebug(action + " is valid command");
			} else {
				errors.add(action + " not a Valid command. Please validate (002)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (006)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * AbacusRods10 = 12
	 */
	private boolean validateAbacusRodsCommand(String action, List<String> errors) {
		int len = action.length();
		if(len == 12) {
			// Validate Rod Number
			String number = action.substring(10, 12);
			try {
				Integer.valueOf(number);
			} catch (NumberFormatException nfe) {
				errors.add(action + " doesn't contains valid rod number. Please validate (011)!!!");
				return Boolean.FALSE;
			}
			
			// Get the command
			String command = action.substring(0, 10);
			logger.logDebug("Extracted Command : " + command);
			
			if(command.equalsIgnoreCase("AbacusRods")) {
				logger.logDebug(action + " is valid command");
			} else {
				errors.add(action + " not a Valid command. Please validate (012)!!!");
				return Boolean.FALSE;
			}
		} else {
			errors.add(action + " not a valid action. Please validate (013)!!!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * Validate Subject Details
	 */
	private boolean validateSubjectDetailsCommand(String action, List<String> errors) {
		
		int subPPos = action.indexOf("subp");
		int gradePos = action.indexOf("grade");
		int topicPos = action.indexOf("topic");
		int chapter = action.indexOf("chapter");
		int section = action.indexOf("section");
		
		if(subPPos == -1 || gradePos == -1 || topicPos == -1 || chapter == -1 || section == -1) {
			errors.add(action + " Not a valid statement. Sequence should be => SubP -> Grade -> Topic -> Chapter -> Section. Please validate (013)!!!");
			return Boolean.FALSE;
		}
		
		if(!(section > chapter && chapter > topicPos && topicPos > gradePos && gradePos > subPPos)) {
			errors.add(action + " Not a valid statement. Sequence should be => SubP -> Grade -> Topic -> Chapter -> Section. Please validate (013)!!!");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	/**
	 * @return the listOfValidTags
	 */
	public List<String> getListOfValidTags() {
		return listOfValidTags;
	}

	/**
	 * @param listOfValidTags the listOfValidTags to set
	 */
	public void setListOfValidTags(List<String> listOfValidTags) {
		this.listOfValidTags = listOfValidTags;
	}

	/**
	 * @param noClosingTags the noClosingTags to set
	 */
	public void setNoClosingTags(List<String> noClosingTags) {
		this.noClosingTags = noClosingTags;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	/**
	 * @return the listOfActions
	 */
	public List<Action> getListOfActions() {
		return listOfActions;
	}

	/**
	 * @param specialCommands the specialCommands to set
	 */
	public void setSpecialCommands(List<String> specialCommands) {
		this.specialCommands = specialCommands;
	}
}
