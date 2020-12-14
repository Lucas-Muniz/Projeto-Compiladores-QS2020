package isilanguage.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import isilanguage.datastructures.IsiSymbol;
import isilanguage.datastructures.IsiSymbolTable;

/* Classe que representa um programa escrito na IsiLanguage */
public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;

	/* Geração do código Java do programa escrito em IsiLanguage (não identado) */
	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class MainClass{ \n");
		str.append("  public static void main(String args[]){\n ");
		str.append("      Scanner _key = new Scanner(System.in);\n");
		for (IsiSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode()+"\n");
		}
		str.append("\n");
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode()+"\n");
		}
		str.append("  }");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	/* Geração do código Java do programa escrito em IsiLanguage identado, 
	 * gravado no arquivo MainClass.java */
	public void generateTargetPrettyPrinter() {
		int n = 8;
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n\n");
		str.append("public class MainClass{ \n");
		str.append("    public static void main(String args[]){\n ");
		str.append("       Scanner _key = new Scanner(System.in);\n");
		for (IsiSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode(n)+"\n");
		}
		str.append("\n");
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode(n)+"\n");
		}
		str.append("    }\n");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	/* Geração do código Java do programa escrito em IsiLanguage identado, 
	 * gravado no arquivo fileName.java */
	public void generateTargetPrettyPrinter(String fileName) {
		int n = 8;
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n\n");
		str.append("public class "+fileName+"{ \n");
		str.append("    public static void main(String args[]){\n ");
		str.append("       Scanner _key = new Scanner(System.in);\n");
		for (IsiSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode(n)+"\n");
		}
		str.append("\n");
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode(n)+"\n");
		}
		str.append("    }\n");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File(fileName+".java"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
