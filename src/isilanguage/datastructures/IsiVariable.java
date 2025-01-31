package isilanguage.datastructures;

public class IsiVariable extends IsiSymbol implements IsiTypes{
	private int type;
	private String value;
	private boolean isUsed;
	private boolean isAttributed;
	
	/* Variável da IsiLanguage, formada por um identificador, um tipo e um valor */
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.isUsed = false;
		this.isAttributed = false;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void initializeVariable() {
		this.isAttributed = true;
	}
	
	public void useVariable() {
		this.isUsed = true;
	}
	
	public boolean wasUsed() {
		return this.isUsed;
	}
	
	public boolean wasAttributed() {
		return this.isAttributed;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
       String str;
       if (type == NUMBER) {
    	   str = "double ";
       } else if (type == TEXT){
    	   str = "String ";
       } else {
    	   str = "boolean ";
       }
       return str + " "+super.name+";";
	}
	
	/* Geração do código Java identado */
	public String generateJavaCode(int n) {
	       return generateSpace(n) + this.generateJavaCode();
		}
	
	

}
