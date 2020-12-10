package isilanguage.ast;

import java.util.ArrayList;

public class CommandFor extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> commands;	
	
	public CommandFor(String condition, ArrayList<AbstractCommand> cmd) {
		this.condition = condition.replace(":=", "=");
		this.commands = cmd;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("for ("+ condition + ") {\n");
		for (AbstractCommand cmd: commands) {
			str.append("    " + cmd.generateJavaCode()+"\n");
		}
		str.append("}");
		return str.toString();
	}
	
	public String toString() {
		return "CommandFor [condition = " + condition + ", commands = " + commands + "]";
	}

}
