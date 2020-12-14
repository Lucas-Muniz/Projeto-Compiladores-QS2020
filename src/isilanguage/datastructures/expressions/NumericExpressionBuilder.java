package isilanguage.datastructures.expressions;

import isilanguage.datastructures.IsiOperator;
import isilanguage.datastructures.IsiTerm;
import isilanguage.datastructures.IsiTypes;
import isilanguage.exceptions.IsiSemanticException;

public class NumericExpressionBuilder extends AbstractExpression{
	/*Legend: 0 -> first part, 1 -> second part, 2 -> operator and 3 -> storaged operator */
	private int lastChange = 0;
	private String opBuffer = "";
	
	public NumericExpressionBuilder() {
		super(IsiTypes.NUMBER);
	}

	/* Adiciona um operador na expressão numérica */
	@Override
	public void addOperator(String op) {
		if (op != null) {
			if (operator == null) {
				if (IsiOperator.isNumericOperator(op)) {
					operator = op;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a numeric operator, but got '"+operatorBuilder+"'.");
				}
			} else {
				operatorBuilder = op;
				lastChange = 3;
			}
		} else {
			throw new IsiSemanticException("ERROR: expecting a numeric operator, but got a null string.");
		}

	}

	/* Adição de um elemento na expressão numérica */
	@Override
	public void addElement(String elem, int type) {
		if (type != IsiTypes.NUMBER) {
			throw new IsiSemanticException("ERROR: expecting a numeric type, but '"+elem+"' is a "+IsiTerm.typeToString(type)+".");
		}
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
					throw new IsiSemanticException("ERROR: expecting a numeric operator, but got '"+operatorBuilder+"'.");
				}
				
			}
			operatorBuilder = null;
			opBuffer = "";
		}
		
	}
	
	/* Verifica se a string é um número */
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
	
	/* Verifica se a string é um numero com sinal */
	public static boolean isSignedNumber(String strNum) {
	    if (isNumeric(strNum) && (strNum.substring(0, 1).contentEquals("+") || strNum.substring(0, 1).contentEquals("-"))) {
	    	return true;
	    }
	    return false;
	}
	
	/* Adição de um '(' na expressão */
	public void openParentheses() {
		if (lastChange == 0) {
			this.openParenthesesFirst();
		} else if (lastChange == 1){
			this.openParenthesesSecond();
		} else if (lastChange == 2) {
			openedParentheses++;
			buffer += "(";
		} else if (lastChange == 3) {
			openedParentheses++;
			opBuffer += "(";
		}
    }
	
	/* Adição de um ')' na expressão */
	public void closeParentheses() {
		if (lastChange == 0) {
			this.closeParenthesesFirst();
		} else if (lastChange == 1){
			this.closeParenthesesSecond();
		} else if (lastChange == 2) {
			openedParentheses -= 1;
			buffer += ")";
		} else if (lastChange == 3) {
			openedParentheses -= 1;
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
