package isilanguage.ast;

import java.util.ArrayList;

public class CommandFaca extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> commands;
	
	/* Comando faça (do..while) formado por uma condição e uma lista de comandos */
	public CommandFaca(String condition, ArrayList<AbstractCommand> cmd) {
		this.condition = condition;
		this.commands = cmd;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("do {\n");
		for (AbstractCommand cmd: commands) {
			str.append("    " + cmd.generateJavaCode()+"\n");
		}
		str.append("} ");
		str.append("while ("+condition+");\n");
		return str.toString();
	}
	
	/* Geração do código Java identado */
	@Override
	public String generateJavaCode(int n) {
		String space = generateSpace(n);
		StringBuilder str = new StringBuilder();
		str.append(space+"do {\n");
		for (AbstractCommand cmd: commands) {
			str.append(cmd.generateJavaCode(space.length()+numSpace)+"\n");
		}
		str.append(space+"} ");
		str.append("while ("+condition+");\n");
		return str.toString();
	}
	
	public String toString() {
		return "CommandFaca [commands=" + commands + ", condition=" + condition + "]";
	}

}