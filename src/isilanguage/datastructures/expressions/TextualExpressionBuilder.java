package isilanguage.datastructures.expressions;

import isilanguage.datastructures.IsiOperator;
import isilanguage.datastructures.IsiTerm;
import isilanguage.datastructures.IsiTypes;
import isilanguage.exceptions.IsiSemanticException;

public class TextualExpressionBuilder extends AbstractExpression{
	/*Legend: 0 -> first part, 1 -> second part, 2 -> operator and 3 -> storaged operator */
	private int lastChange = 0;
	private String opBuffer = "";

	public TextualExpressionBuilder() {
		super(IsiTypes.TEXT);
	}

	/* Adição do operador textual na expressão */
	@Override
	public void addOperator(String op) {
		if (op != null) {
			if (operator == null) {
				if (IsiOperator.isTextualOperator(op)) {
					operator = op;
					lastChange = 2;
				} else {
					throw new IsiSemanticException("ERROR: expecting a textual operator, but got '"+op+"'.");
				}
			} else {
				operatorBuilder = op;
				lastChange = 3;
			}
		} else {
			throw new IsiSemanticException("ERROR: expecting a textual operator, but got a null string.");
		}
		
	}

	/* Adição de um elemento na expressão textual */
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
					throw new IsiSemanticException("ERROR: expecting a textual operator, but got '"+operatorBuilder+"'.");
				}
				
			}
			operatorBuilder = null;
			opBuffer = "";
		}
		
	}

	/* Adiciona '(' na expressão */
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
	
	/* Adiciona ')' na expressão */
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
