package isilanguage.datastructures;

public abstract class IsiSymbol {
	
	protected String name;
	
	public abstract String generateJavaCode();
	public abstract String generateJavaCode(int n);
	
	public IsiSymbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IsiSymbol [name=" + name + "]";
	}
	
	public String generateSpace(int n) {
		if (n <= 0) {
			return "";
		} else {
			String spaces = "";
			for (int i = 0; i < n; i++) {
				spaces += " ";
			}
			return spaces;
		}
	}
	

}
