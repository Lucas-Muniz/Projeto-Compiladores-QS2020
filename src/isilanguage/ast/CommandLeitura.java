package isilanguage.ast;

import isilanguage.datastructures.IsiVariable;
import isilanguage.exceptions.IsiSemanticException;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;
	
	/* Comando de leitura, que recebe um identificador e uma variável */
	public CommandLeitura (String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		String function;
		if (var.getType() == IsiVariable.NUMBER) {
			function = "nextDouble();";
		} else if (var.getType() == IsiVariable.TEXT) {
			function = "nextLine();";
		} else if (var.getType() == IsiVariable.BOOLEAN) {
			function = "nextBoolean();";
		} else {
			throw new IsiSemanticException("type not found for reading command.");
		}
		return id +" = _key." + function;
	}
	
	/* Geração do código Java identado */
	@Override
	public String generateJavaCode(int n) {
		String function;
		if (var.getType() == IsiVariable.NUMBER) {
			function = "nextDouble();";
		} else if (var.getType() == IsiVariable.TEXT) {
			function = "nextLine();";
		} else if (var.getType() == IsiVariable.BOOLEAN) {
			function = "nextBoolean();";
		} else {
			throw new IsiSemanticException("type not found for reading command.");
		}
		return generateSpace(n) + id +" = _key." + function;
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
