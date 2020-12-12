package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class TextualExpressionBuilder extends AbstractExpression{
	//private String operatorBuilder = null;
	/*Legend: 0 -> first part, 1 -> second part, 2 -> operator and 3 -> storaged operator */
	private int lastChange = 0;
	//private String buffer = "";
	private String opBuffer = "";

	public TextualExpressionBuilder() {
		super(IsiTypes.TEXT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addOperator(String op) {
		if (op != null) {
			if (operator == null) {
				if (IsiOperator.isTextualOperator(op)) {
					operator = op;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a textual operator, but got '"+op+"'");
				}
			} else {
				operatorBuilder = op;
				lastChange = 3;
			}
		} else {
			throw new IsiSemanticException("ERROR: expecting a textual operator, but got a null string.");
		}
		
	}

	@Override
	public void addElement(String elem, int type) {
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
				if (IsiOperator.isTextualOperator(operatorBuilder)) {
					operator = operatorBuilder;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a textual operator, but got '"+operatorBuilder+"'");
				}
				
			}
			operatorBuilder = null;
			opBuffer = "";
		}
		
	}

	@Override
	public void checkElement(String elem, int type) {
		if (type != IsiTypes.TEXT) {
			throw new IsiSemanticException("ERROR: elem '"+elem+" is not textual type.");
		}
		
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

}
