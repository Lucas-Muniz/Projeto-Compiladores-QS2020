package isilanguage.datastructures;

import isilanguage.exceptions.IsiSemanticException;

public class IsiTerm implements IsiTypes{

	private String content;
	private int type;
	
	public IsiTerm(String content, int type) {
		/*if (type == -1) {
			throw new IsiSemanticException("ERROR: type not found: "+type);
		}*/
		this.content = content;
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}
	
	protected void setContent(String content) {
		this.content = content;
	}
	
	protected void addToContent(String element) {
		this.content += element;
	}

	public int getType() {
		return type;
	}
	
	protected void setType(int type) {
		if (type == NUMBER || type == TEXT || type == BOOLEAN) {
			this.type = type;
		} else {
			this.type =  -1;
		}
	}
	
	public boolean isSignedNumber(IsiTerm t) {
		if (t.getType() == NUMBER) {
			String sign = getSign(t);
			if (sign.contentEquals("+") || sign.contentEquals("-")) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public String getSign(IsiTerm t) {
		return t.getContent().substring(0, 1);
	}

	public IsiTerm checkTypeExpression(String op, IsiTerm t) {
		int termType = t.getType();
		if (IsiOperator.isCorrectOperator(op, t.getContent(), termType)) {
			if (this.getType() == termType) {
				if (isSignedNumber(t)) {
					//String sign = getSign(t);
					return new IsiTerm(this.getContent()+op+"("+t.getContent()+")", termType);
				}
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
	
	public static String typeToString(int type) {
		if (type == NUMBER) {
			return "numeric type";
		} else if (type == TEXT) {
			return "textual type";
		} else if (type == BOOLEAN) {
			return "boolean type";
		}
		return "(type not found)";
	}

}
