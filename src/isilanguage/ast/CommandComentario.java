package isilanguage.ast;

public class CommandComentario extends AbstractCommand  {
	
	private String texto;	
	
	public CommandComentario(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("/* \n");
		str.append("    " + texto +"\n");
		str.append("*/ \n");
		return str.toString();
	}
	
	public String toString() {
		return "CommandComentario [comentario = " + texto + "]";
	}

	@Override
	public String generateJavaCode(int n) {
		String space = generateSpace(n);
		StringBuilder str = new StringBuilder();
		str.append(space+"/* \n");
		str.append("    " + texto +"\n");
		str.append(space+"*/ \n");
		return str.toString();
	}
}
