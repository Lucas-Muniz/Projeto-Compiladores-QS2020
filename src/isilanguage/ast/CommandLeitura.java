package isilanguage.ast;

import isilanguage.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;
	
	public CommandLeitura (String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	@Override
	public String generateJavaCode() {
		return id +"= _key." + (var.getType()== IsiVariable.NUMBER? "nextDouble();": "nextLine();");
	}
	
	@Override
	public String generateJavaCode(int n) {
		return generateSpace(n) + id +" = _key." + (var.getType()== IsiVariable.NUMBER? "nextDouble();": "nextLine();");
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
