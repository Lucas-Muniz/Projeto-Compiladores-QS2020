package isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> commands;
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> cmd) {
		this.condition = condition;
		this.commands = cmd;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: commands) {
			str.append("    " + cmd.generateJavaCode()+"\n");
		}
		str.append("}");
		return str.toString();
	}
	
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", commands=" + commands + "]";
	}

}