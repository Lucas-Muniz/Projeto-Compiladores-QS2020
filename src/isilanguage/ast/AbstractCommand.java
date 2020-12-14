package isilanguage.ast;

/* Classe abstrata que representa um comando da IsiLanguage */
public abstract class AbstractCommand {
    public int numSpace = 4;
	
	public abstract String generateJavaCode();
	public abstract String generateJavaCode(int n);
	
	/* Função que gera string de espaços, usados na identação do código gerado
	 * pelo compilador */
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
