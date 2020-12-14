package isilanguage.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import isilanguage.exceptions.IsiSemanticException;
import isilanguage.parser.IsiLangLexer;
import isilanguage.parser.IsiLangParser;

/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 * 
 * Arquivo fonte: extensao .isi
 * 
 */
public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			// leitura do arquivo "input.isi" e isso é entrada para o Analisador Lexico
			lexer = new IsiLangLexer(CharStreams.fromFileName("programa.isi"));
			
			// criação de um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// criação do parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			try {
				/* leitura do programa escrito na IsiLanguage */
				parser.prog();
			} catch (Exception e) {
				System.out.println("Syntax error");
				System.out.println(e);
				System.exit(-1);
			}
			
			
			System.out.println("Compilation Successful");
			
			/* exibição dos comandos identificados no programa em IsiLanguage */
			parser.exibeComandos();
			
			/* geração do código Java (identado) */
			parser.generateCode();
			
		}
		catch(IsiSemanticException ex) {
			System.err.println("Semantic error - "+ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("ERROR "+ex.getMessage());
		}
		
	}

}
