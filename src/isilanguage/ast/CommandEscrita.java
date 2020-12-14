package isilanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	
	/* Comando de escrita de um identificador */
	public CommandEscrita(String id) {
		this.id = id;
	}
	
	/* Geração do código Java */
	@Override
	public String generateJavaCode() {
		return "System.out.println("+id+");";
	}
	
	/* Geração do código Java identado */
	@Override
	public String generateJavaCode(int n) {
		return generateSpace(n)+"System.out.println("+id+");";
	}
	
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	

}
