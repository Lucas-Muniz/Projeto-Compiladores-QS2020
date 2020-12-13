package isilanguage.datastructures.expressions;

import isilanguage.datastructures.IsiOperator;
import isilanguage.datastructures.IsiTerm;
import isilanguage.datastructures.IsiTypes;
import isilanguage.exceptions.IsiSemanticException;

public class BooleanExpressionBuilder extends AbstractExpression{
	private RelationalExpressionBuilder builder = new RelationalExpressionBuilder();
	private String operatorBuilder = null;
	private int lastChange;
	//private String buffer = "";
	private String bufferNot = "";
	
	
	public BooleanExpressionBuilder() {
		super(IsiTypes.BOOLEAN);
	}
	

	@Override
	public void addOperator(String op) {
		if (operator == null) {
			if (IsiOperator.isBooleanOperator(op)) {
				operator = op;
				lastChange = 1;
				if (builder.firstComplete) {
					firstTerm = new IsiTerm(firstTerm.getContent()+builder.getExpression(), builder.getExpectedType());
					firstComplete = true;
					builder = new RelationalExpressionBuilder();
					//buffer = "";
					lastChange = 1;
				}
			} else if(op.contentEquals("not") || op.contentEquals("!")) {
				//buffer += "!";
				firstTerm.addToContent("!");
				//lastChange = 0;
			} else if (IsiOperator.isRelationalOperator(op) || IsiOperator.isNumericOperator(op)){ 
				builder.addOperator(op);
				lastChange = 2;
			} else {
				throw new IsiSemanticException("ERROR: elem '"+op+" is not a relational operator.");
			}
			
		} else {
			if (IsiOperator.isBooleanOperator(op)) {
				if (builder.firstComplete) {
					firstTerm.addToContent(space+operator+space+buffer+space+builder.getExpression());
					buffer = "";
					bufferNot = "";
					firstTerm.setType(getExpressionType(firstTerm.getType(), operator, secondTerm.getType()));
					lastChange = 0;
				} 
				operator = op;
				lastChange = 1; 
				builder = new RelationalExpressionBuilder();
			} else if(op.contentEquals("not") || op.contentEquals("!")) {
				buffer += "!";
				//bufferNot = "!";
				//lastChange = 0;
			}else if (IsiOperator.isRelationalOperator(op) || IsiOperator.isNumericOperator(op)){ 
				builder.addOperator(op);
				lastChange = 2;
			} else {
				throw new IsiSemanticException("ERROR: elem '"+op+"' is not a valid for boolean expressions.");
			}
			//throw new IsiSemanticException("ERROR: elem '"+op+" is not a valid for relational expressions.");
		}
		
	}

	@Override
	public void addElement(String elem, int type) {
		if (!IsiOperator.isBooleanOperator(elem) && !(elem.contentEquals("not") || elem.contentEquals("!"))) {
			if (isBooleanValue(elem) || type == IsiTypes.BOOLEAN) {
				if (!builder.firstComplete && !builder.secondComplete && builder.operator == null) {
					if (operator == null) {
						firstTerm.addToContent(bufferNot+elem+space+buffer);
					} else {
						firstTerm.addToContent(space+operator+space+buffer+elem);
					}
					buffer = "";
					bufferNot = "";
					firstComplete = true;
					firstTerm.setType(IsiTypes.BOOLEAN);
					lastChange = 0;
				} else {
					throw new IsiSemanticException("ERROR: bad formed expression.");
				}
			} else if (type == IsiTypes.BOOLEAN || type == IsiTypes.NUMBER) {
				builder.addElement(elem, type);
				lastChange = 2;
			} else if (IsiOperator.isRelationalOperator(elem)) {
				builder.addOperator(elem);
				lastChange = 2;
			} else {
				throw new IsiSemanticException("ERROR: elem '"+elem+"' is not a boolean type or a relational operator.");
			}
		}
		
	}

	
	@Override
	public String getExpression() {
		if (builder.firstComplete && builder.secondComplete) {
			secondTerm = new IsiTerm(secondTerm.getContent()+builder.getExpression(), builder.getExpectedType());
			secondComplete = true;
		} else if (builder.firstComplete){
			secondTerm = new IsiTerm(secondTerm.getContent()+builder.getExpression(), builder.getExpectedType());
			secondComplete = true;
		} else {
			secondComplete = false;
		}

		checkParentheses();
		if (operator != null && firstComplete && secondComplete) {
			if (isComplete() && checkExpressionOperation()) {
				return firstTerm.getContent()+space+operator+space+secondTerm.getContent();
			} else {
				return "ERROR: Incomplete or incorrect expression.";
			}
		} else if (firstComplete) {
			return firstTerm.getContent();
		} else if (secondComplete){
			return secondTerm.getContent();
		} else {
			return "ERROR: Incomplete or incorrect expression.";
		}
	}
	
	public boolean isBooleanValue(String str) {
		if (str.contentEquals("true") || str.contentEquals("false")) {
			return true;
		}
		return false;
	}
	
	public void openParentheses() {
		if (lastChange == 0) {
			this.openParenthesesFirst();
		} else if (lastChange == 1){
			buffer += "(";
		} else {
			builder.openParentheses();
		}
    }
	
	public void closeParentheses() {
		if (lastChange == 0) {
			this.closeParenthesesFirst();
		} else if (lastChange == 1){
			buffer += "(";
		} else {
			builder.closeParentheses();
		}
	}

}
