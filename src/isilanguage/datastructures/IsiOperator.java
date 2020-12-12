package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class IsiOperator implements IsiTypes {
	
	public static boolean isNumericOperator(String op) {
		if (op.contentEquals("+") || op.contentEquals("-") || op.contentEquals("*") || op.contentEquals("/")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNegativeSignOperator(String op) {
		if (op.contentEquals("-")) {
			return true;
		}
		return false;
	}
	
	public static boolean isTextualOperator(String op) {
		if (op.contentEquals("+")) {
			return true;
		}
		return false;
	}
	
	public static boolean isRelationalOperator(String op) {
		if (op.contentEquals("<") || op.contentEquals(">") || op.contentEquals("<=") || op.contentEquals(">=")
				|| op.contentEquals("!=") || op.contentEquals("==")) {
			return true;
		}
		return false;
	}
	
	public static boolean isBooleanOperator(String op) {
		if (op.contentEquals("&&") || op.contentEquals("||")) {
			return true;
		}
		return false;
	}
	
	public static boolean isCorrectOperator(String op, String term, int termType) {
		if (termType == -1) {
			throw new IsiSemanticException("ERROR: type not found: "+termType);
		}
		if (termType == IsiTypes.NUMBER && !isNumericOperator(op) && !isRelationalOperator(op)) {
			throw new IsiSemanticException("'"+term+"' is numeric type, but '"+op+"' is not a numeric operator.");
		} else if (termType == IsiTypes.TEXT && !isTextualOperator(op)) {
			throw new IsiSemanticException("'"+term+"' is text type, but '"+op+"' is not a textual operator.");
		} else if (termType == IsiTypes.BOOLEAN && !isBooleanOperator(op) && !isRelationalOperator(op)) {
			throw new IsiSemanticException("'"+term+"' is boolean type, but '"+op+"' is not a boolean operator.");
		}
		return true;	
	}
	
}
