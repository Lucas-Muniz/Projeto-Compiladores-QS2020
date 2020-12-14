package isilanguage.ast;

public class CommandComentario extends AbstractCommand  {
	
	private String texto;	
	
	public CommandComentario(String texto) {
		this.texto = texto;
	}
	
	/* Geração do comentário no código Java identado */
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
    
	/* Geração do comentário identado */
	@Override
	public String generateJavaCode(int n) {
		String space = generateSpace(n);
		StringBuilder str = new StringBuilder();
		texto = texto.replace("\n", "\n" + space);
		str.append(space + texto);
		return str.toString();
	}
}