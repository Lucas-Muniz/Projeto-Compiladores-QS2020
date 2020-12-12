package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class NumericExpressionBuilder extends AbstractExpression{
	//private String operatorBuilder = null;
	/*Legend: 0 -> first part, 1 -> second part, 2 -> operator and 3 -> storaged operator */
	private int lastChange = 0;
	//private String buffer = "";
	private String opBuffer = "";
	
	public NumericExpressionBuilder() {
		super(IsiTypes.NUMBER);
	}

	@Override
	public void addOperator(String op) {
		if (op != null) {
			if (operator == null) {
				if (IsiOperator.isNumericOperator(op)) {
					operator = op;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a numeric operator, but got '"+operatorBuilder+"'");
				}
			} else {
				operatorBuilder = op;
				lastChange = 3;
			}
		} else {
			throw new IsiSemanticException("ERROR: expecting a numeric operator, but got a null string.");
		}

	}

	@Override
	public void addElement(String elem, int type) {
		//checkElement(elem, type);
		if (isSignedNumber(elem)) {
			elem = "("+elem+")";
		}
		if (operatorBuilder == null) {
			if (!firstComplete) {
				firstTerm = new IsiTerm(firstTerm.getContent()+buffer+elem, type);
				firstComplete = true;
				lastChange = 0;
			} else if (!secondComplete) {
				secondTerm = new IsiTerm(secondTerm.getContent()+buffer+elem, type);
				secondComplete = true;
				lastChange = 1;
			} else {
				throw new IsiSemanticException("ERROR: bad formed expression.");
			}
			buffer = opBuffer;
		} else {
			if (operator != null) {
				int expressionType = getExpressionType(firstTerm.getType(), operator, secondTerm.getType());
				firstTerm.addToContent(space+operator+space+buffer+secondTerm.getContent());
				buffer = opBuffer;
				firstTerm.setType(expressionType);
				operator = operatorBuilder;
				secondTerm = new IsiTerm(elem, type); 
				secondComplete = true;
				lastChange = 1;
			} else {
				if (IsiOperator.isNumericOperator(operatorBuilder)) {
					operator = operatorBuilder;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a numeric operator, but got '"+operatorBuilder+"'");
				}
				
			}
			operatorBuilder = null;
			opBuffer = "";
		}
		
	}
	
	@Override
	public void checkElement(String elem, int type) {
		boolean isNumber = isNumeric(elem);
		boolean isOperator = IsiOperator.isNumericOperator(elem);
		if (!isNumber) {
			throw new IsiSemanticException("ERROR: elem '"+elem+" is not a number.");
		} else if (!isOperator && !isNumber) {
			throw new IsiSemanticException("ERROR: elem '"+elem+" is not a numeric operator.");
		} else if (type != IsiTypes.NUMBER) {
			throw new IsiSemanticException("ERROR: expecting a numeric type, but got '"+IsiTerm.typeToString(type)+"'");
		}

	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double n = Double.parseDouble(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isSignedNumber(String strNum) {
	    if (isNumeric(strNum) && (strNum.substring(0, 1).contentEquals("+") || strNum.substring(0, 1).contentEquals("-"))) {
	    	return true;
	    }
	    return false;
	}
	
	public void openParentheses() {
		if (lastChange == 0) {
			this.openParenthesesFirst();
		} else if (lastChange == 1){
			this.openParenthesesSecond();
		} else if (lastChange == 2) {
			buffer += "(";
		} else if (lastChange == 3) {
			opBuffer += "(";
		}
    }
	
	public void closeParentheses() {
		if (lastChange == 0) {
			this.closeParenthesesFirst();
		} else if (lastChange == 1){
			this.closeParenthesesSecond();
		} else if (lastChange == 2) {
			buffer += ")";
		} else if (lastChange == 3) {
			opBuffer += "(";
		}
	}

	public int getLastChange() {
		return lastChange;
	}

	public void setLastChange(int lastChange) {
		this.lastChange = lastChange;
	}
	
	

}
