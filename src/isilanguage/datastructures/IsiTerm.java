package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class IsiTerm implements IsiTypes{

	private String content;
	private int type;
	
	public IsiTerm(String content, int type) {
		if (type == -1) {
			throw new IsiSemanticException("ERROR: type not found: "+type);
		}
		this.content = content;
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public int getType() {
		return type;
	}

	public IsiTerm checkTypeExpression(String op, IsiTerm t) {
		int termType = t.getType();
		if (IsiOperator.isCorrectOperator(op, t.getContent(), termType)) {
			if (this.getType() == termType) {
				return new IsiTerm(this.getContent()+op+t.getContent(), termType);
			} else {
				String msg = "Type mismatch: '"+this.getContent()+"' is a "+typeToString(this.getType())
						+", but '"+t.getContent()+"' is a "+typeToString(termType)+".";
				throw new IsiSemanticException(msg);
			}
		}
		return null;
	}
	
	public static void checkAttributionType(IsiVariable var, IsiTerm term) {
		int termType = term.getType();
		if (var.getType() != termType){
			String msg = "Type mismatch: Variable '"+var.getName()+"' is a "+typeToString(var.getType())
				+", but '"+term.getContent()+"' is a "+typeToString(termType)+".";
			throw new IsiSemanticException(msg);
		}
	}
	
	private static String typeToString(int type) {
		if (type == NUMBER) {
			return "numeric type";
		} else if (type == TEXT) {
			return "textual type";
		}
		return "(type not found)";
	}

}
