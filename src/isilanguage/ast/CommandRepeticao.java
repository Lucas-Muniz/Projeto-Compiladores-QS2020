package isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> commands;
	
	/* Comando de repetição (while), composto por uma condição e uma lista de comandos */
	public CommandRepeticao(String condition, ArrayList<AbstractCommand> cmd) {
		this.condition = condition;
		this.commands = cmd;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: commands) {
			str.append(cmd.generateJavaCode()+"\n");
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
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", commands=" + commands + "]";
	}

}