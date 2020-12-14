package isilanguage.ast;

import isilanguage.datastructures.IsiVariable;

public class CommandAtribuicao extends AbstractCommand{

	private String id;
	private String expr;
	
	/* Comando de atrbuição, com um identificador e uma expressão */
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		return id + " = "+expr+";";
	}
	
	/* Geração do código Java identado */
	@Override
	public String generateJavaCode(int n) {
		return generateSpace(n) + id + " = "+expr+";";
	}
	
	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}
	
	

}
