package isilanguage.exceptions;

public class IsiSemanticException extends RuntimeException{
	/* Erro semântico encontrado durante a compilação */
	public IsiSemanticException(String msg) {
		super(msg);
	}

}
