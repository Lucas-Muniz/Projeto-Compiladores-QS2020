package isilanguage.datastructures.expressions;

import isilanguage.datastructures.IsiOperator;
import isilanguage.datastructures.IsiTerm;
import isilanguage.datastructures.IsiTypes;
import isilanguage.exceptions.IsiSemanticException;

public abstract class AbstractExpression implements IsiTypes{
	protected String buffer = "";
	protected String operatorBuilder = null;
	protected String space = " "; 
	protected IsiTerm firstTerm;
	protected IsiTerm secondTerm;
	protected String operator;
	protected boolean firstComplete;
	protected boolean secondComplete;
	protected int openedParentheses;
	protected int expressionType;
	protected int expectedExpressionType;
	
	public AbstractExpression(int expectedExpressionType) {
		this.firstTerm = new IsiTerm("", -1);
		this.firstComplete = false;
		this.secondTerm = new IsiTerm("", -1);
		this.secondComplete = false;
		this.openedParentheses = 0;
		this.operator = null;
		this.expressionType = -1;
		this.expectedExpressionType = expectedExpressionType;
	}
	
	public abstract void addOperator(String op);
	public abstract void addElement(String elem, int type);
	
	
	public boolean isComplete() {
		if (operator != null && firstComplete && secondComplete) {
			return true;
		}
		return false;
	}
	
	public boolean isCorrect() {
		if (getExpressionType(firstTerm.getType(), operator, secondTerm.getType()) == expectedExpressionType) {
			return true;
		}
		return false;
	}
	
	public String getExpression() {
		checkParentheses();
		if (isComplete()) {
			if (checkExpressionOperation() && this.operatorBuilder == null) {
				return firstTerm.getContent()+space+operator+space+this.buffer+secondTerm.getContent();
			} else {
				return "ERROR: Invalid Expression";
			}
		} else if (firstComplete){
			return firstTerm.getContent();
		}  else {
			return "ERROR: Incomplete Expression";
		}
	}
	
	public int getOpenedParentheses() {
		return openedParentheses;
	}
	
	public void openParenthesesFirst() {
		openedParentheses++;
		firstTerm.addToContent("(");
	}
	
	public void closeParenthesesFirst() {
		openedParentheses -= 1;
		firstTerm.addToContent(")");
	}
	
	public void openParenthesesSecond() {
		openedParentheses++;
		secondTerm.addToContent("(");
	}
	
	public void closeParenthesesSecond() {
		openedParentheses -= 1;
		secondTerm.addToContent(")");
	}
	
	public void checkParentheses() {
		if (openedParentheses != 0) {
			String msg;
			if (openedParentheses < 0) {
				msg = "You need to close "+(-openedParentheses) +" parentheses.";
			} else {
				msg = "You need to open "+openedParentheses +" parentheses.";
			}
		}
	}
	
	public int getExpectedType() {
		return expectedExpressionType;
	}
	
	public boolean isCorrectOperator(String op, int type) {
		if (type == NUMBER && (IsiOperator.isRelationalOperator(op) || IsiOperator.isNumericOperator(op))) {
			return true;
		} else if (type == BOOLEAN && IsiOperator.isBooleanOperator(op)) {
			return true;
		} else if (type == TEXT && IsiOperator.isTextualOperator(op)) {
			return true;
		}
		return false;
	}
	
	public int getExpressionType(int type1, String op, int type2) {
		if (type1 == type2 &&  type1 == NUMBER && IsiOperator.isRelationalOperator(op)) {
			return BOOLEAN;
		} else if (type1 == type2 &&  type1 == NUMBER && IsiOperator.isNumericOperator(op)) {
			return NUMBER;
		} else if (type1 == type2 &&  type1 == BOOLEAN && IsiOperator.isBooleanOperator(op)) {
			return BOOLEAN;
		} else if (type1 == type2 &&  type1 == TEXT && IsiOperator.isTextualOperator(op)) {
			return TEXT;
		}
		return -1;
	}
	
	public boolean checkOperator() {
		if (operator != null) {
			if (isCorrectOperator(operator, firstTerm.getType()) && isCorrectOperator(operator, secondTerm.getType())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkExpressionOperation() {
		if (operator != null) {
			if (isCorrectOperator(operator, firstTerm.getType()) && isCorrectOperator(operator, secondTerm.getType())) {;
				return true;
			}
			throw new IsiSemanticException("ERROR: operator '"+operator+"' invalid for '"
					+firstTerm.getContent()+"' and '"+secondTerm.getContent()+"'.");
		}
		return false;
	}
	
    public void openParentheses() {
		if (!firstComplete || operator == null) {
			this.openParenthesesFirst();
		} else {
			this.openParenthesesSecond();
		}
    }
	
	public void closeParentheses() {
		if (!firstComplete || operator == null) {
			this.closeParenthesesFirst();
		}  else {
			this.closeParenthesesSecond();
		}
	}

	
}
