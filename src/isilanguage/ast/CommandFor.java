package isilanguage.ast;

import java.util.ArrayList;

public class CommandFor extends AbstractCommand {
	
	private String attrib;
	private String condition;
	private String step;
	private ArrayList<AbstractCommand> commands;	
	
	public CommandFor(String attrib,  String condition, String step,  ArrayList<AbstractCommand> cmd) {
		this.attrib = attrib.replace(":=", "=");
		this.condition = condition;
		this.step = step.replace(":=", "=");
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
	
	@Override
	public String generateJavaCode(int n) {
		String space = generateSpace(n);
		StringBuilder str = new StringBuilder();
		str.append(space+"for ("+ attrib + "; " + condition + "; " + step + ") {\n");
		for (AbstractCommand cmd: commands) {
			str.append(cmd.generateJavaCode(space.length()+numSpace)+"\n");
		}
		str.append(space+"}");
		return str.toString();
	}
	
	public String toString() {
		return "CommandFor [attrib = " + attrib + ", condition = " + condition + ", step = " + step +  ", commands = " + commands + "]";
	}

}
