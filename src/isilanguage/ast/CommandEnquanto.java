package isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> commands;
	
	/* Comando enquanto (while) composto por uma condição e uma lista de comandos */
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> cmd) {
		this.condition = condition;
		this.commands = cmd;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: commands) {
			str.append("    " + cmd.generateJavaCode()+"\n");
		}
		str.append("}");
		return str.toString();
	}
	
	/* Geração do código Java identado */
	@Override
	public String generateJavaCode(int n) {
		String space = generateSpace(n);
		StringBuilder str = new StringBuilder();
		str.append(space+"while ("+condition+") {\n");
		for (AbstractCommand cmd: commands) {
			str.append(cmd.generateJavaCode(space.length()+numSpace)+"\n");
		}
		str.append(space+"}");
		return str.toString();
	}
	
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", commands=" + commands + "]";
	}

}
