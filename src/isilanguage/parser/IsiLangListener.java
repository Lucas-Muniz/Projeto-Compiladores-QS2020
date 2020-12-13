// Generated from IsiLang.g4 by ANTLR 4.7.1
package isilanguage.parser;

	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
	import isilanguage.datastructures.expressions.AbstractExpression;
	import isilanguage.datastructures.expressions.NumericExpressionBuilder;
	import isilanguage.datastructures.expressions.TextualExpressionBuilder;
	import isilanguage.datastructures.expressions.RelationalExpressionBuilder;
	import isilanguage.datastructures.expressions.BooleanExpressionBuilder;
	import isilanguage.exceptions.IsiSemanticException;
	import isilanguage.ast.IsiProgram;
	import isilanguage.ast.AbstractCommand;
	import isilanguage.ast.CommandLeitura;
	import isilanguage.ast.CommandEscrita;
	import isilanguage.ast.CommandAtribuicao;
	import isilanguage.ast.CommandDecisao;
	import isilanguage.ast.CommandEnquanto;
	import isilanguage.ast.CommandFaca;
	import isilanguage.ast.CommandFor;
	import isilanguage.ast.CommandComentario;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLangParser}.
 */
public interface IsiLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(IsiLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(IsiLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(IsiLangParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(IsiLangParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(IsiLangParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(IsiLangParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(IsiLangParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(IsiLangParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void enterCmdselecao(IsiLangParser.CmdselecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void exitCmdselecao(IsiLangParser.CmdselecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void enterCmdrepeticao(IsiLangParser.CmdrepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void exitCmdrepeticao(IsiLangParser.CmdrepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterCondicao(IsiLangParser.CondicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitCondicao(IsiLangParser.CondicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#exprbooleana}.
	 * @param ctx the parse tree
	 */
	void enterExprbooleana(IsiLangParser.ExprbooleanaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#exprbooleana}.
	 * @param ctx the parse tree
	 */
	void exitExprbooleana(IsiLangParser.ExprbooleanaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#booleano}.
	 * @param ctx the parse tree
	 */
	void enterBooleano(IsiLangParser.BooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#booleano}.
	 * @param ctx the parse tree
	 */
	void exitBooleano(IsiLangParser.BooleanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#relacao}.
	 * @param ctx the parse tree
	 */
	void enterRelacao(IsiLangParser.RelacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#relacao}.
	 * @param ctx the parse tree
	 */
	void exitRelacao(IsiLangParser.RelacaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#operacao}.
	 * @param ctx the parse tree
	 */
	void enterOperacao(IsiLangParser.OperacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#operacao}.
	 * @param ctx the parse tree
	 */
	void exitOperacao(IsiLangParser.OperacaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#termonumerico}.
	 * @param ctx the parse tree
	 */
	void enterTermonumerico(IsiLangParser.TermonumericoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#termonumerico}.
	 * @param ctx the parse tree
	 */
	void exitTermonumerico(IsiLangParser.TermonumericoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#comentarios}.
	 * @param ctx the parse tree
	 */
	void enterComentarios(IsiLangParser.ComentariosContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#comentarios}.
	 * @param ctx the parse tree
	 */
	void exitComentarios(IsiLangParser.ComentariosContext ctx);
}