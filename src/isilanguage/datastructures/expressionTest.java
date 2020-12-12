package isilanguage.datastructures;

public class expressionTest {
	public static void main(String args[]) {
		NumericExpressionBuilder n = new NumericExpressionBuilder();
		n.openParentheses();
		n.addElement("var", 0);
		n.closeParentheses();
		n.addOperator("-");
		n.openParentheses();
		n.addElement("2", 0);
		n.addOperator("*");
		n.addElement("1", 0);
		n.closeParentheses();
		n.addOperator("/");
		n.openParentheses();
		n.addElement("1", 0);
		n.addOperator("+");
		n.addElement("-1", 0);
		n.closeParentheses();
		System.out.println(n.getExpression());
		
		n = new NumericExpressionBuilder();
		n.addElement("10", 0);
		n.addOperator("/");
		n.openParentheses();
		n.addElement("-2", 0);
		n.closeParentheses();
		System.out.println(n.getExpression());
		
		RelationalExpressionBuilder r = new RelationalExpressionBuilder();
		
		r.openParentheses();
		r.addElement("ta", 0);
		r.addOperator("/");
		r.openParentheses();
		r.addElement("1", 0);
		r.addOperator("+");
		r.addElement("1", 0);
		r.closeParentheses();
		r.addOperator("<");
		r.openParentheses();
		r.addElement("2", 0);
		r.addOperator("-");
		r.addElement("test", 0);
		r.addOperator("*");
		r.addElement("3", 0);
		r.closeParentheses();
		r.closeParentheses();
		System.out.println(r.getExpression());
		
		BooleanExpressionBuilder b = new BooleanExpressionBuilder();
		b.addOperator("not");
		b.openParentheses();
		b.addElement(" f < 2 ", 2);
		b.addOperator("&&");
		b.addElement("1", 0);
		b.addOperator("-");
		b.addElement("test", 0);
		b.addOperator("<");
		b.openParentheses();
		b.addElement("3", 0);
		b.addOperator("+");
		b.addElement("3", 0);
		b.closeParentheses();
		b.addOperator("&&");
		b.addOperator("!");
		b.openParentheses();
		b.addOperator("not");
		b.addElement("true", 2);
		b.addOperator("||");
		b.addElement("false", 2);
		b.closeParentheses();
		b.addOperator("||");
		b.openParentheses();
		b.openParentheses();
		b.addElement("1", 0);
		b.closeParentheses();
		b.addOperator("/");
		b.addElement("3", 0);
		b.addOperator("==");
		b.addElement("-3", 0);
		b.closeParentheses();
		b.addOperator("&&");
		b.openParentheses();
		b.addElement("true", 2);
		b.closeParentheses();
		b.addOperator("||");
		b.addElement("false", 2);
		b.closeParentheses();
		System.out.println(b.getExpression());
		
		//f < 10 ou ( verdadeiro  e falso ) ou nao ( falso ) e f <= 10/2
		b = new BooleanExpressionBuilder();
		b.addElement("a", IsiTypes.NUMBER);
		b.addOperator("+");
		b.addElement("1", IsiTypes.NUMBER);
		b.addOperator("<");
		b.addElement("a", IsiTypes.NUMBER);
		b.addOperator("+");
		b.addElement("1", IsiTypes.NUMBER);
		b.addOperator("&&");
		b.addElement("true", IsiTypes.BOOLEAN);
		//b.addOperator("||");
		//b.addElement("true", IsiTypes.BOOLEAN);
		System.out.println(b.getExpression());
		
		b = new BooleanExpressionBuilder();
		b.addElement("bool", IsiTypes.NUMBER);
		System.out.println(b.getExpression());
		
		/*
		b = new BooleanExpressionBuilder();
		b.addElement("f", IsiTypes.NUMBER);
		b.addOperator("<");
		b.addElement("10", IsiTypes.NUMBER);
		b.addOperator("||");
		b.openParentheses();
		b.addElement("true", IsiTypes.BOOLEAN);
		b.addOperator("&&");
		b.addElement("false", IsiTypes.BOOLEAN);
		b.closeParentheses();
		b.addOperator("||");
		b.addOperator("!");
		b.openParentheses();
		b.addElement("false", IsiTypes.BOOLEAN);
		b.closeParentheses();
		b.addOperator("&&");
		b.addElement("f", IsiTypes.NUMBER);
		b.addOperator("<=");
		b.addElement("10", IsiTypes.NUMBER);
		b.addOperator("/");
		b.addElement("2", IsiTypes.NUMBER);
		System.out.println(b.getExpression()); */
		
		
		
		TextualExpressionBuilder t = new TextualExpressionBuilder();
		t.addElement("\"text\"", 1);
		t.addOperator("+");
		t.openParentheses();
		t.addElement("varText", 1);
		t.addOperator("+");
		t.addElement("t2", 1);
		t.closeParentheses();
		t.addOperator("+");
		t.addElement("t3", 1);
		t.addOperator("+");
		t.addElement("t4", 1);
		System.out.println(t.getExpression());
		
		t = new TextualExpressionBuilder();
		t.addElement("\"hello\"", 1);
		t.addOperator("+");
		t.addElement("\" \"", 1);
		t.addOperator("+");
		t.openParentheses();
		t.addElement("\"world\"", 1);
		t.closeParentheses();
		
		System.out.println(t.getExpression());
		
	}
}
