package isilanguage.datastructures.expressions;

import isilanguage.datastructures.IsiOperator;
import isilanguage.datastructures.IsiTerm;
import isilanguage.datastructures.IsiTypes;
import isilanguage.exceptions.IsiSemanticException;

public class RelationalExpressionBuilder extends AbstractExpression{
	public RelationalExpressionBuilder() {
		super(IsiTypes.BOOLEAN);
	}

	private NumericExpressionBuilder builder = new NumericExpressionBuilder();
	
	/* Adiciona um operador na expressão relacioanal */
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
				throw new IsiSemanticException("ERROR: elem '"+op+"' is not a relational operator.");
			}
			
		} else {
			if (!IsiOperator.isRelationalOperator(op)) {
				builder.addOperator(op);
			} else {
				throw new IsiSemanticException("ERROR: elem '"+op+"' is not a valid for relational expressions.");
			}
		}
	}

	/* Adiciona um elemento na expressão relacional */
	@Override
	public void addElement(String elem, int type) {
		if (!IsiOperator.isRelationalOperator(elem)) {
			if (type == IsiTypes.NUMBER) {
				builder.addElement(elem, type);
			} else if (IsiOperator.isNumericOperator(elem)) {
				builder.addOperator(elem);
			} else {
				throw new IsiSemanticException("ERROR: elem '"+elem+"' is not a numeric type or a numeric operator.");
			}
		} else {
			throw new IsiSemanticException("ERROR: bad formed expression, '"+elem+"' is a relational operator.");
		} 
		
	}
	
	/* Gera a string da expressão recebida/armazenada */
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

		if (isComplete() && checkExpressionOperation()) {
			return firstTerm.getContent()+space+operator+space+secondTerm.getContent();
		} else {
			return "ERROR: Incomplete Expression";
		}
	}
		
	/* Adiciona '(' na expressão */
	public void openParentheses() {
		builder.openParentheses();
    }
	
	/* Adiciona ')' na expressão */
	public void closeParentheses() {
		builder.closeParentheses();
	}

	public NumericExpressionBuilder getBuilder() {
		return builder;
	}

}
