package isilanguage.ast;

public abstract class AbstractCommand {
    public int numSpace = 4;
	
	public abstract String generateJavaCode();
	public abstract String generateJavaCode(int n);
	
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
