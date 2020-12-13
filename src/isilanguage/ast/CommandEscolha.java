package isilanguage.ast;

import java.util.ArrayList;
import java.util.Stack;

public class CommandEscolha extends AbstractCommand {
	private String casoBase;
	private ArrayList<String> casos;
	private ArrayList<ArrayList<AbstractCommand>> commandslist;
	private ArrayList<AbstractCommand> defaultCommandList;
	
	public CommandEscolha(String casoBase, ArrayList<String> casos, ArrayList<ArrayList<AbstractCommand>> cmd, ArrayList<AbstractCommand> defaultCmd) {
		this.casoBase = casoBase;
		this.casos = casos;
		this.commandslist = cmd;
		this.defaultCommandList = defaultCmd;
	}
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("switch ("+casoBase+") {");
		for (String caso: casos) {
			str.append("\n case "+caso+" : \n");
			for (ArrayList<AbstractCommand> commands: commandslist) {
				for (AbstractCommand cmd: commands) {
					str.append("    " + cmd.generateJavaCode()+"\n");
				}		
			}
			str.append("break;\n");
		}
		if (defaultCommandList != null) {
			str.append("default : \n");
			for (AbstractCommand cmd: defaultCommandList) {
				str.append("    " + cmd.generateJavaCode()+"\n");
			}	
		}
		
		str.append("}");
		return str.toString();
	}
	
	@Override
	public String generateJavaCode(int n) {
		StringBuilder str = new StringBuilder();
		String space1 = generateSpace(n);
		String space2 = generateSpace(n+numSpace);
		ArrayList<AbstractCommand> commands;
		str.append(space1+"switch ("+casoBase+") {\n");
		if (casos.size() == commandslist.size()) {
			for (int i = 0; i < casos.size(); i++) {
				str.append(space2+"case "+casos.get(i)+" : \n");
				commands = commandslist.get(i);
				for (AbstractCommand cmd: commands) {
					str.append(cmd.generateJavaCode(space2.length()+numSpace)+"\n");
				}
				str.append(space2+"break;\n");
			}
		}
		
		if (defaultCommandList != null) {
			str.append(space2+"default : \n");
			for (AbstractCommand cmd: defaultCommandList) {
				str.append(cmd.generateJavaCode(space2.length()+numSpace)+"\n");
			}	
		}
		str.append(space1+"}");
		return str.toString();
	}
	
	public String toString() {
		StringBuilder cmd = new StringBuilder();
		StringBuilder defaultCmd = new StringBuilder();
		cmd.append("");
		defaultCmd.append("");
		if (commandslist != null && commandslist.size() > 0) {
			for (ArrayList<AbstractCommand> commands: commandslist) {
				cmd.append("[ ");
				for (AbstractCommand command: commands) {
					cmd.append(command.toString()+"; ");
				}	
				cmd.append(" ] ");
			}
		}
		if (commandslist != null && defaultCommandList.size() > 0) {
			defaultCmd.append("[ ");
			for (AbstractCommand command: defaultCommandList) {
				defaultCmd.append(command.toString()+"; ");
			}
			defaultCmd.append(" ] ");
		}
		
		return "CommandEscolha [casoBase=" + casoBase + ", casos=" + casos + ", [ comandos=" + cmd +"], comandos padrao="+ defaultCmd +" ]";
	}

}
