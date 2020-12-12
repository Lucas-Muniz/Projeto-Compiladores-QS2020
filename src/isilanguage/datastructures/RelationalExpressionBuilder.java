package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class RelationalExpressionBuilder extends AbstractExpression{
	public RelationalExpressionBuilder() {
		super(IsiTypes.BOOLEAN);
	}

	private NumericExpressionBuilder builder = new NumericExpressionBuilder();
	
	@Override
	public void addOperator(String op) {
		if (operator == null) {
			if (IsiOperator.isRelationalOperator(op)) {
				operator = op;
				if (builder.firstComplete && builder.secondComplete) {
					firstTerm = new IsiTerm(firstTerm.getContent()+builder.getExpression(), builder.getExpectedType());
				} else {
					firstTerm = new IsiTerm(firstTerm.getContent()+builder.firstTerm.getContent(), builder.getExpectedType());
				}
				firstComplete = true;
				builder = new NumericExpressionBuilder();
			} else if (IsiOperator.isNumericOperator(op)){ 
				builder.addOperator(op);
			}	else {
				throw new IsiSemanticException("ERROR: elem '"+op+" is not a relational operator.");
			}
			
		} else {
			if (!IsiOperator.isRelationalOperator(op)) {
				builder.addOperator(op);
			} else {
				throw new IsiSemanticException("ERROR: elem '"+op+" is not a valid for relational expressions.");
			}
			//throw new IsiSemanticException("ERROR: elem '"+op+" is not a valid for relational expressions.");
		}
		
	}

	@Override
	public void addElement(String elem, int type) {
		if (!IsiOperator.isRelationalOperator(elem)) {
			if (type == IsiTypes.NUMBER) {
				builder.addElement(elem, type);
			} else if (IsiOperator.isNumericOperator(elem)) {
				builder.addOperator(elem);
			} else {
				throw new IsiSemanticException("ERROR: elem '"+elem+" is not a numeric type or a numeric operator.");
			}
		} else {
			throw new IsiSemanticException("ERROR: bad formed expression, '"+elem+"' is a relational operator.");
		} 
		
	}
	
	@Override
	public String getExpression() {
		if (builder.firstComplete && builder.secondComplete) {
			secondTerm = new IsiTerm(secondTerm.getContent()+builder.getExpression(), builder.getExpectedType());
		} else {
			secondTerm = new IsiTerm(secondTerm.getContent()+builder.firstTerm.getContent(), builder.getExpectedType());
		}
		
		secondComplete = true;
		int t1 = firstTerm.getType();
		int t2 = secondTerm.getType();
		if (t1 == -1 || t2 == -1) {
			throw new IsiSemanticException("ERROR: bad formed expression.");
		}
		checkParentheses();
		if (isComplete() && checkExpressionOperation()) {
			return firstTerm.getContent()+space+operator+space+secondTerm.getContent();
		} else {
			return "ERROR: Incomplete Expression";
		}
	}
		
	

	@Override
	public void checkElement(String elem, int type) {
		return ;
	}
	
	public void openParentheses() {
		builder.openParentheses();
    }
	
	public void closeParentheses() {
		builder.closeParentheses();
	}

	public NumericExpressionBuilder getBuilder() {
		return builder;
	}

}
