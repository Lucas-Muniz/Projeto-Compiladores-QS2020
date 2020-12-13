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
	import isilanguage.ast.CommandEscolha;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, POINTS=19, BOOLEANOUNARIO=20, BOOLEANOBINARIO=21, BOOL=22, AP=23, 
		FP=24, SC=25, POINT=26, OP=27, OP_INC_DEC=28, OP_INC_DEC_EQ=29, ATTR=30, 
		VIR=31, ACH=32, FCH=33, OPREL=34, ID=35, NUMBER=36, SIGNEDNUMBER=37, SIGN=38, 
		TEXTO=39, COMENTARIO=40, WS=41;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_condicao = 13, RULE_exprbooleana = 14, RULE_booleano = 15, RULE_relacao = 16, 
		RULE_operacao = 17, RULE_termonumerico = 18, RULE_cmdopcao = 19, RULE_comentarios = 20;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo", "condicao", 
		"exprbooleana", "booleano", "relacao", "operacao", "termonumerico", "cmdopcao", 
		"comentarios"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'booleano'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
		"'faca'", "'para'", "'escolha'", "'caso'", "'pare'", "'padrao'", "':'", 
		"'nao'", null, null, "'('", "')'", "';'", "'.'", null, null, null, "':='", 
		"','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "POINTS", "BOOLEANOUNARIO", 
		"BOOLEANOBINARIO", "BOOL", "AP", "FP", "SC", "POINT", "OP", "OP_INC_DEC", 
		"OP_INC_DEC_EQ", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
		"SIGNEDNUMBER", "SIGN", "TEXTO", "COMENTARIO", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo, _tipoCaso;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread, defaultCommandList;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private ArrayList<String> listaTexto;
		private ArrayList<ArrayList<AbstractCommand>> listaComandos;
		private String _readID;
		private String _writeID;
		private String _attribID;
		private String _attribTerm;
		private String _casos, _casoBase;
		private IsiTerm _term = null; 
		private IsiTerm _newTerm = null;
		private String op, sign;
		private int type;
		private String _exprID;
		private String _exprContent;
		private String _exprAttrib;
		private String _exprDecision;
		private String _exprStep;
		private String _textComment;
		private int _tipoVariavel;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> commands;
		private AbstractExpression _expr;
		private int _exprType;
		private String bool, _booleanExpr, _partialBooleanExpr, _condition;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			}
		}
		
		public void atribuiVariavel(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			} else {
				IsiSymbol var = symbolTable.get(id);
				if (var instanceof IsiVariable){
					((IsiVariable) var).initializeVariable();
					symbolTable.add(var);
				} 
				
			}
		}
		
		public void useVariavel(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			} else {
				IsiSymbol var = symbolTable.get(id);
				if (var instanceof IsiVariable){
					((IsiVariable) var).useVariable();
					symbolTable.add(var);
				} 
				
			}
		}
		
		public void verificaAtribuicao(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			} else {
				IsiSymbol var = symbolTable.get(id);
				if (var instanceof IsiVariable && !((IsiVariable) var).wasAttributed()){
					throw new IsiSemanticException("Symbol "+id+" is used but not declared");
				} 
				
			}
		}
		
		public int obtemTipoId(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			} else {
				IsiSymbol var = symbolTable.get(id);
				if (var instanceof IsiVariable){
					return ((IsiVariable) var).getType();
				} 
				return -1;
				
			}
		}
		
		public IsiTerm atualizaTipoTermo(String termo, IsiTerm _term, int tipo, String op){
			 /* Verificação de tipo*/
		     if (_term == null){
		     	return  new IsiTerm(termo, tipo);
		     } else {
		        IsiTerm newTerm = new IsiTerm(termo, tipo);
		        return _term.checkTypeExpression(op, newTerm);
		     }
		}
		
		public void verificaTipoAtribuicao(String id, IsiTerm term){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			} else {
				IsiSymbol var = symbolTable.get(id);
				if (var instanceof IsiVariable){
					IsiTerm.checkAttributionType((IsiVariable) var, term);
				} 
				
			}
		}
		
		public String obterSimboloBooleano(String bool){
		    String symbol;
	        if (bool.contentEquals("verdadeiro")){
	        	return "true";
	        } else if (bool.contentEquals("falso")) {
	        	return "false";
	        } else {
	        	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
	       }
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTargetPrettyPrinter("GeneratedCode");
		}

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__0);
			setState(43);
			decl();
			setState(44);
			bloco();
			setState(45);
			match(T__1);
			  
					program.setVarTable(symbolTable);
			        program.setComandos(stack.pop());
			        symbolTable.verifyVariables();
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(49); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(48);
					declaravar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(51); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T__2);
			setState(54);
			tipo();
			setState(55);
			match(ID);

					_varName = _input.LT(-1).getText();
				    _varValue = null;
				    symbol = new IsiVariable(_varName, _tipo, _varValue);
				    if (!symbolTable.exists(_varName)){
				    	symbolTable.add(symbol);	
				    } else {
				    	throw new IsiSemanticException("Symbol "+_varName+" already declared");
				    }
			   
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(57);
				match(ATTR);

							if(_tipo == IsiTypes.NUMBER){
								_expr = new NumericExpressionBuilder();
							} else if (_tipo == IsiTypes.BOOLEAN){
								_expr = new BooleanExpressionBuilder();
							} else if (_tipo == IsiTypes.TEXT){
								_expr = new TextualExpressionBuilder();
							}
						
				setState(63);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(59);
					expr();
					}
					break;
				case 2:
					{
					setState(60);
					condicao();

									_exprContent = _condition;
								
					}
					break;
				}

							CommandAtribuicao cmd = new CommandAtribuicao(_varName,  _expr.getExpression());
							atribuiVariavel(_varName);
							stack.peek().add(cmd);
							_term = null;
							op = null;
						
				}
				break;
			case POINT:
			case VIR:
				{
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VIR) {
					{
					{
					setState(67);
					match(VIR);
					setState(68);
					match(ID);

									_varName = _input.LT(-1).getText();
						            _varValue = null;
						            symbol = new IsiVariable(_varName, _tipo, _varValue);
						            if (!symbolTable.exists(_varName)){
						            	symbolTable.add(symbol);	
						            } else {
						            	throw new IsiSemanticException("Symbol "+_varName+" already declared");
						            }
					            
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(77);
			match(POINT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(T__3);
				 
						_tipo = IsiVariable.NUMBER;
					
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				match(T__4);
				 
						_tipo = IsiVariable.TEXT;
					
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(T__5);
				 
						_tipo = IsiVariable.BOOLEAN;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
				curThread = new ArrayList<AbstractCommand>(); 
				stack.push(curThread);  

			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(88);
				cmd();
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdopcaoContext cmdopcao() {
			return getRuleContext(CmdopcaoContext.class,0);
		}
		public ComentariosContext comentarios() {
			return getRuleContext(ComentariosContext.class,0);
		}
		public DeclaravarContext declaravar() {
			return getRuleContext(DeclaravarContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				cmdleitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				cmdselecao();
				}
				break;
			case T__11:
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 5);
				{
				setState(97);
				cmdrepeticao();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 6);
				{
				setState(98);
				cmdopcao();
				}
				break;
			case COMENTARIO:
				enterOuterAlt(_localctx, 7);
				{
				setState(99);
				comentarios();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 8);
				{
				setState(100);
				declaravar();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__6);
			setState(104);
			match(AP);
			setState(105);
			match(ID);
			 
					verificaID(_input.LT(-1).getText());
			        _readID = _input.LT(-1).getText();
			        atribuiVariavel(_readID);
			    
			setState(107);
			match(FP);
			setState(108);
			match(POINT);

					IsiVariable var = (IsiVariable)symbolTable.get(_readID);
					CommandLeitura cmd = new CommandLeitura(_readID, var);
					stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__7);
			setState(112);
			match(AP);
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(113);
				match(TEXTO);

							_writeID = _input.LT(-1).getText();
						
				}
				break;
			case ID:
				{
				setState(115);
				match(ID);
				 
							verificaID(_input.LT(-1).getText());
					        _writeID = _input.LT(-1).getText();
					        useVariavel(_writeID);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(119);
			match(FP);
			setState(120);
			match(POINT);

					CommandEscrita cmd = new CommandEscrita(_writeID);
					stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(ID);
			 
					_exprID = _input.LT(-1).getText();
					verificaID(_exprID);
			        _exprType = obtemTipoId(_exprID);
			                    
			        if(_exprType == IsiTypes.NUMBER){
			        	_expr = new NumericExpressionBuilder();
			        } else if (_exprType == IsiTypes.BOOLEAN){
			        	_expr = new BooleanExpressionBuilder();
			        } else if (_exprType == IsiTypes.TEXT){
			        	_expr = new TextualExpressionBuilder();
			        }
			                    
			    
			setState(125);
			match(ATTR);
			 
					_exprContent = "";
				
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(127);
				expr();
				 
						  	_exprContent = _expr.getExpression();
						       
				}
				break;
			case 2:
				{
				setState(130);
				condicao();

							_exprContent = _condition;
						
				}
				break;
			}
			setState(135);
			match(POINT);
			 
			        CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			        atribuiVariavel(_exprID);
			        stack.peek().add(cmd);
			        _term = null;
			        op = null;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__8);
			setState(139);
			match(AP);
			setState(140);
			condicao();

					_exprDecision = _condition;
				
			setState(142);
			match(FP);
			setState(143);
			match(T__9);
			setState(144);
			match(ACH);
			 
					curThread = new ArrayList<AbstractCommand>(); 
			        stack.push(curThread);
			    
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146);
				cmd();
				}
				}
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
			setState(151);
			match(FCH);

					listaTrue = stack.pop();	
			        listaFalse = new ArrayList<AbstractCommand>();
			        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			    
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(153);
				match(T__10);
				setState(154);
				match(ACH);

							curThread = new ArrayList<AbstractCommand>();
				            stack.push(curThread);
				        
				{
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(156);
					cmd();
					}
					}
					setState(159); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				}
				setState(161);
				match(FCH);

							listaFalse = stack.pop();
				             cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				        
				}
			}


					stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> POINT() { return getTokens(IsiLangParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(IsiLangParser.POINT, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(IsiLangParser.BOOL, 0); }
		public List<TerminalNode> ATTR() { return getTokens(IsiLangParser.ATTR); }
		public TerminalNode ATTR(int i) {
			return getToken(IsiLangParser.ATTR, i);
		}
		public TerminalNode OP_INC_DEC() { return getToken(IsiLangParser.OP_INC_DEC, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public TerminalNode OP_INC_DEC_EQ() { return getToken(IsiLangParser.OP_INC_DEC_EQ, 0); }
		public TerminalNode OP() { return getToken(IsiLangParser.OP, 0); }
		public List<TerminalNode> SIGNEDNUMBER() { return getTokens(IsiLangParser.SIGNEDNUMBER); }
		public TerminalNode SIGNEDNUMBER(int i) {
			return getToken(IsiLangParser.SIGNEDNUMBER, i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			setState(286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(T__11);
				setState(169);
				match(AP);
				setState(170);
				condicao();

						_exprDecision = _condition;
				    
				setState(172);
				match(FP);
				setState(173);
				match(ACH);
				 
						curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);
				    
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(175);
					cmd();
					}
					}
					setState(178); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(180);
				match(FCH);

						commands = stack.pop();	
						CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
				        stack.peek().add(cmd);
				    
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(T__12);
				setState(184);
				match(ACH);
				 
						curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);
				    
				setState(187); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(186);
					cmd();
					}
					}
					setState(189); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(191);
				match(FCH);

						commands = stack.pop();	
				    
				setState(193);
				match(T__11);
				setState(194);
				match(AP);
				setState(195);
				condicao();

						_exprDecision = _condition;
					
				setState(197);
				match(FP);
				setState(198);
				match(POINT);

						CommandFaca cmd = new CommandFaca(_exprDecision, commands);
				        stack.peek().add(cmd);
				    
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(T__13);
				setState(202);
				match(AP);

						_exprAttrib = "";
					
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << ID))) != 0)) {
					{
					setState(228);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__3:
					case T__4:
					case T__5:
						{
						{
						setState(204);
						tipo();
						setState(205);
						match(ID);

											_varName = _input.LT(-1).getText();
									        _varValue = null;
									        symbol = new IsiVariable(_varName, _tipo, _varValue);
									                              
									        if (!symbolTable.exists(_varName)){
									        	symbolTable.add(symbol);	
									        	_exprAttrib = _varName;
									        } else {
									        	throw new IsiSemanticException("Symbol "+_varName+" already declared");
									        }
									    
						setState(207);
						match(ATTR);
						 
											_exprAttrib += _input.LT(-1).getText();
										
						setState(212);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(209);
							match(ID);
							 
													_varName = _input.LT(-1).getText();
													if (!symbolTable.exists(_varName)){
										            	throw new IsiSemanticException("Symbol "+_varName+" not declared");
										            }
												
							}
							break;
						case NUMBER:
						case SIGNEDNUMBER:
							{
							setState(211);
							_la = _input.LA(1);
							if ( !(_la==NUMBER || _la==SIGNEDNUMBER) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}

											_exprContent =_input.LT(-1).getText(); 
											_exprAttrib +=_exprContent;
										

											atribuiVariavel(_varName);
											_term = null;
											op = null;
										
						}
						}
						break;
					case ID:
						{
						{
						setState(217);
						match(ID);
						 
											_varName = _input.LT(-1).getText();
											if (!symbolTable.exists(_varName)){
									        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
									        }
									        else{
									        _exprAttrib = _varName;
									        }
										
						}
						setState(220);
						match(ATTR);
						 
										_exprAttrib += _input.LT(-1).getText();
									
						setState(225);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(222);
							match(ID);
							 
												_varName = _input.LT(-1).getText();
												if (!symbolTable.exists(_varName)){
										        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
										        }
											
							}
							break;
						case NUMBER:
							{
							setState(224);
							match(NUMBER);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}

										_exprAttrib +=_input.LT(-1).getText();
									
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				setState(232);
				match(POINT);
				_exprDecision = "";
				setState(241);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(234);
					match(ID);

								_expr = new RelationalExpressionBuilder();
								_varName = _input.LT(-1).getText();
								if (!symbolTable.exists(_varName)){
									throw new IsiSemanticException("Symbol "+_varName+" not declared");
								} else {
									_exprDecision += _varName;
								}
								_expr.addElement(_varName, obtemTipoId(_varName));
							
					setState(236);
					match(OPREL);
					 ;
								_exprDecision += _input.LT(-1).getText();
								_expr.addOperator(_input.LT(-1).getText());
						    
					setState(238);
					expr();
					}
					break;
				case BOOL:
					{
					setState(239);
					match(BOOL);

								_expr = new BooleanExpressionBuilder();
								bool = obterSimboloBooleano(_input.LT(-1).getText());
								_expr.addElement(bool, IsiTypes.BOOLEAN);
								
							
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 
						_exprStep = ""; 
						_exprDecision = _expr.getExpression();
					
				setState(244);
				match(POINT);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(245);
					match(ID);
					 
								_varName = _input.LT(-1).getText();
								if (!symbolTable.exists(_varName)){
									throw new IsiSemanticException("Symbol "+_varName+" not declared");
								}
							
					 
								_exprStep += _input.LT(-1).getText();
							
					setState(271);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OP_INC_DEC:
						{
						setState(248);
						match(OP_INC_DEC);
						 
										_exprStep += _input.LT(-1).getText();
									
						}
						break;
					case OP_INC_DEC_EQ:
						{
						{
						setState(250);
						match(OP_INC_DEC_EQ);
						 
											_exprStep += _input.LT(-1).getText();
										
						setState(255);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(252);
							match(ID);
							 
													_varName = _input.LT(-1).getText();
													if (!symbolTable.exists(_varName)){
											        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
											        }
												
							}
							break;
						case NUMBER:
						case SIGNEDNUMBER:
							{
							setState(254);
							_la = _input.LA(1);
							if ( !(_la==NUMBER || _la==SIGNEDNUMBER) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}

											_exprStep +=_input.LT(-1).getText();
										
						}
						}
						break;
					case ATTR:
						{
						{
						setState(258);
						match(ATTR);
						 
											_exprStep += _input.LT(-1).getText();
										
						setState(260);
						match(ID);
						 
											_varName = _input.LT(-1).getText();
											if (!symbolTable.exists(_varName)){
										    	throw new IsiSemanticException("Symbol "+_varName+" not declared");
										    }
										
						 
											_exprStep += _varName;
										
						setState(263);
						match(OP);
						 
											_exprStep += _input.LT(-1).getText();
										
						setState(268);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(265);
							match(ID);
							 
													_varName = _input.LT(-1).getText();
													if (!symbolTable.exists(_varName)){
											        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
											        }
												
							}
							break;
						case NUMBER:
						case SIGNEDNUMBER:
							{
							setState(267);
							_la = _input.LA(1);
							if ( !(_la==NUMBER || _la==SIGNEDNUMBER) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}

											_exprStep +=_input.LT(-1).getText();
										
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				setState(275);
				match(FP);
				setState(276);
				match(ACH);
				 
						curThread = new ArrayList<AbstractCommand>(); 
						stack.push(curThread);
					
				setState(279); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(278);
					cmd();
					}
					}
					setState(281); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(283);
				match(FCH);

						commands = stack.pop();	
						CommandFor cmd = new CommandFor(_exprAttrib, _exprDecision, _exprStep, commands);
						stack.peek().add(cmd);
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> SIGNEDNUMBER() { return getTokens(IsiLangParser.SIGNEDNUMBER); }
		public TerminalNode SIGNEDNUMBER(int i) {
			return getToken(IsiLangParser.SIGNEDNUMBER, i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(288);
				termo();
				}
				break;
			case 2:
				{
				setState(289);
				match(SIGNEDNUMBER);
				 
							_attribTerm = _input.LT(-1).getText();
				            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				        
				}
				break;
			}
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP || _la==SIGNEDNUMBER) {
				{
				setState(298);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP:
					{
					{
					setState(293);
					match(OP);
					 
									op = _input.LT(-1).getText();
						            _expr.addOperator(op);
						        
					setState(295);
					termo();
					}
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(296);
					match(SIGNEDNUMBER);
					 
								_attribTerm = _input.LT(-1).getText();

					            op = "+";
					            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
					        
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode SIGNEDNUMBER() { return getToken(IsiLangParser.SIGNEDNUMBER, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termo);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				match(ID);
				 
						_attribTerm = _input.LT(-1).getText();
				        verificaID(_attribTerm);
					    useVariavel(_attribTerm);
					    /* Verifica se uma variavel usada foi atribuida */
					    verificaAtribuicao(_attribTerm);
					    _expr.addElement(_attribTerm, obtemTipoId(_attribTerm));                
					               
				     
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				match(NUMBER);
				 
						_attribTerm = _input.LT(-1).getText();
				        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				    
				}
				break;
			case SIGNEDNUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				match(SIGNEDNUMBER);
				 
						_attribTerm = _input.LT(-1).getText();
				        if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
				        	_attribTerm = _attribTerm.substring(1);
				        }
					               		     
					  _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				    
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(309);
				match(TEXTO);
				 
						_attribTerm = _input.LT(-1).getText();       	  
					    _expr.addElement(_attribTerm, IsiTypes.TEXT);
				    
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(311);
				match(AP);

				        _expr.openParentheses(); 
				     
				setState(313);
				expr();
				setState(314);
				match(FP);
				 
				        _expr.closeParentheses(); 
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondicaoContext extends ParserRuleContext {
		public ExprbooleanaContext exprbooleana() {
			return getRuleContext(ExprbooleanaContext.class,0);
		}
		public CondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCondicao(this);
		}
	}

	public final CondicaoContext condicao() throws RecognitionException {
		CondicaoContext _localctx = new CondicaoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_condicao);
		try {
			enterOuterAlt(_localctx, 1);
			{

			    _expr = new BooleanExpressionBuilder();

			setState(320);
			exprbooleana();
			 
			      _condition = _expr.getExpression();
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprbooleanaContext extends ParserRuleContext {
		public List<BooleanoContext> booleano() {
			return getRuleContexts(BooleanoContext.class);
		}
		public BooleanoContext booleano(int i) {
			return getRuleContext(BooleanoContext.class,i);
		}
		public List<TerminalNode> BOOLEANOBINARIO() { return getTokens(IsiLangParser.BOOLEANOBINARIO); }
		public TerminalNode BOOLEANOBINARIO(int i) {
			return getToken(IsiLangParser.BOOLEANOBINARIO, i);
		}
		public ExprbooleanaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprbooleana; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExprbooleana(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExprbooleana(this);
		}
	}

	public final ExprbooleanaContext exprbooleana() throws RecognitionException {
		ExprbooleanaContext _localctx = new ExprbooleanaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprbooleana);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			booleano();
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOLEANOBINARIO) {
				{
				{
				setState(324);
				match(BOOLEANOBINARIO);

							bool = _input.LT(-1).getText();
				            if (bool.contentEquals("ou")){
				                bool = "||";
				            } else if (bool.contentEquals("e")) {
				                bool = "&&";
				            } else {
				            	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
				            } 
				            _expr.addOperator(bool);
				       
				setState(326);
				booleano();
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanoContext extends ParserRuleContext {
		public ExprbooleanaContext exprbooleana() {
			return getRuleContext(ExprbooleanaContext.class,0);
		}
		public TerminalNode BOOLEANOUNARIO() { return getToken(IsiLangParser.BOOLEANOUNARIO, 0); }
		public RelacaoContext relacao() {
			return getRuleContext(RelacaoContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(IsiLangParser.BOOL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BooleanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBooleano(this);
		}
	}

	public final BooleanoContext booleano() throws RecognitionException {
		BooleanoContext _localctx = new BooleanoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleano);
		int _la;
		try {
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				 
						
					
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BOOLEANOUNARIO) {
					{
					setState(333);
					match(BOOLEANOUNARIO);

								bool = _input.LT(-1).getText();
					            if (bool.contentEquals("nao")){
					            	bool = "!";
					            } else {
					            	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
					            }
					            _expr.addOperator(bool);
					        
					}
				}

				setState(337);
				match(AP);

				        _expr.openParentheses();
				    
				setState(339);
				exprbooleana();
				setState(340);
				match(FP);
				 
				        _expr.closeParentheses();
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 _term = null;
				setState(344);
				relacao();

						op = null;
				        _exprContent = "";
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(347);
				match(BOOL);

						bool = _input.LT(-1).getText();
				        bool = obterSimboloBooleano(bool);
				        _expr.addElement(bool, IsiTypes.BOOLEAN);
				     
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(349);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelacaoContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public RelacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterRelacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitRelacao(this);
		}
	}

	public final RelacaoContext relacao() throws RecognitionException {
		RelacaoContext _localctx = new RelacaoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_relacao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			expr();
			setState(353);
			match(OPREL);
			 
					
			        op = _input.LT(-1).getText();
			        if (op != null && !IsiOperator.isRelationalOperator(op)){
			         	throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
			        }
			        _expr.addOperator(op);
			    
			setState(355);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperacaoContext extends ParserRuleContext {
		public List<TermonumericoContext> termonumerico() {
			return getRuleContexts(TermonumericoContext.class);
		}
		public TermonumericoContext termonumerico(int i) {
			return getRuleContext(TermonumericoContext.class,i);
		}
		public TerminalNode OP() { return getToken(IsiLangParser.OP, 0); }
		public OperacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterOperacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitOperacao(this);
		}
	}

	public final OperacaoContext operacao() throws RecognitionException {
		OperacaoContext _localctx = new OperacaoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_operacao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(AP);
			 _booleanExpr +=  _input.LT(-1).getText();
			                            _expr.openParentheses();
			setState(359);
			termonumerico();
			setState(360);
			match(OP);
			 
			        op = _input.LT(-1).getText();
			        if (op != null && !IsiOperator.isNumericOperator(op)){
			      	  	  throw new IsiSemanticException("Expecting a numeric operator, but got '"+op+"'");
			        }
			        _expr.addOperator(op);
			    
			setState(362);
			termonumerico();
			setState(363);
			match(FP);
			 
			      _expr.closeParentheses();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermonumericoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode SIGNEDNUMBER() { return getToken(IsiLangParser.SIGNEDNUMBER, 0); }
		public TermonumericoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termonumerico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermonumerico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermonumerico(this);
		}
	}

	public final TermonumericoContext termonumerico() throws RecognitionException {
		TermonumericoContext _localctx = new TermonumericoContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_termonumerico);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(366);
				match(ID);
				 
						  _attribTerm = _input.LT(-1).getText();
				          verificaID(_attribTerm);
					      useVariavel(_attribTerm);
					      /* Verifica se uma variavel usada foi atribuida */
					      verificaAtribuicao(_attribTerm);
					      _expr.addElement(_attribTerm, obtemTipoId(_attribTerm) );
					               
				        
				}
				break;
			case NUMBER:
				{
				setState(368);
				match(NUMBER);
				 
							_attribTerm = _input.LT(-1).getText();
				            _exprContent += _attribTerm;
					        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				         
				}
				break;
			case SIGNEDNUMBER:
				{
				setState(370);
				match(SIGNEDNUMBER);
				 
							_attribTerm = _input.LT(-1).getText();		          
					        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdopcaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> POINTS() { return getTokens(IsiLangParser.POINTS); }
		public TerminalNode POINTS(int i) {
			return getToken(IsiLangParser.POINTS, i);
		}
		public List<TerminalNode> POINT() { return getTokens(IsiLangParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(IsiLangParser.POINT, i);
		}
		public List<TerminalNode> TEXTO() { return getTokens(IsiLangParser.TEXTO); }
		public TerminalNode TEXTO(int i) {
			return getToken(IsiLangParser.TEXTO, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdopcaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdopcao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdopcao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdopcao(this);
		}
	}

	public final CmdopcaoContext cmdopcao() throws RecognitionException {
		CmdopcaoContext _localctx = new CmdopcaoContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_cmdopcao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(T__14);
			setState(375);
			match(AP);
			setState(376);
			match(ID);
			 
								 		_casoBase = _input.LT(-1).getText();
								 		verificaID(_casoBase);
								 		useVariavel(_casoBase);
								 		/* Verifica se uma variavel usada foi atribuida */
								 		verificaAtribuicao(_casoBase);
								 		listaTexto = new ArrayList<String>();
								 		listaComandos = new ArrayList<ArrayList<AbstractCommand>>();
								 		defaultCommandList = new ArrayList<AbstractCommand>();
								 		_tipo = obtemTipoId(_casoBase);
								 		if (_tipo == IsiTypes.BOOLEAN){
								 			throw new IsiSemanticException(" Incompatible type: boolean cannot be used in switch strucuture.");
								 		} else if (_tipo == IsiTypes.NUMBER){
								 			throw new IsiSemanticException(" Incompatible type: number cannot be used in switch strucuture.");
								 		}
								 		
								 		
								 	
			setState(378);
			match(FP);
			setState(379);
			match(ACH);
			setState(396); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(380);
				match(T__15);
				{
				setState(381);
				match(TEXTO);

									 	_tipoCaso = IsiTypes.TEXT;
									 	_casos = _input.LT(-1).getText();
									 
				}
				 
									 			if (_tipoCaso != _tipo){
									 				throw new IsiSemanticException("Expecting a case with a "+IsiTerm.typeToString(_tipo)
									 					+" but got a case with a "+IsiTerm.typeToString(_tipoCaso)+".");
									 			}
									          	listaTexto.add(_casos);
									          	
									        
				setState(385);
				match(POINTS);
				 
									 	curThread = new ArrayList<AbstractCommand>();
									 	stack.push(curThread);
				                   	 
				setState(388); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(387);
					cmd();
					}
					}
					setState(390); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(392);
				match(T__16);
				setState(393);
				match(POINT);

									 	listaComandos.add(stack.pop());
									 
				}
				}
				setState(398); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__15 );
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(400);
				match(T__17);

									 	curThread = new ArrayList<AbstractCommand>();
									 	stack.push(curThread);
									 
				setState(402);
				match(POINTS);
				setState(404); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(403);
					cmd();
					}
					}
					setState(406); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << ID) | (1L << COMENTARIO))) != 0) );

										 	defaultCommandList = stack.pop();
										 
				}
			}

			setState(412);
			match(FCH);

				     	      	 	CommandEscolha cmd = new CommandEscolha(_casoBase, listaTexto, listaComandos, defaultCommandList);
				     	      		stack.peek().add(cmd);
						   	     
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComentariosContext extends ParserRuleContext {
		public TerminalNode COMENTARIO() { return getToken(IsiLangParser.COMENTARIO, 0); }
		public ComentariosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comentarios; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterComentarios(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitComentarios(this);
		}
	}

	public final ComentariosContext comentarios() throws RecognitionException {
		ComentariosContext _localctx = new ComentariosContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_comentarios);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(415);
			match(COMENTARIO);
			   _textComment = _input.LT(-1).getText();
			        
			}

					CommandComentario cmd = new CommandComentario(_textComment);
					stack.peek().add(cmd);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u01a7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3\64\n\3\r\3\16\3\65\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4B\n\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4I\n\4\f\4\16\4L\13\4\5\4N\n\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5X\n\5\3\6\3\6\6\6\\\n\6\r\6\16\6]\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7h\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tx\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\5\n\u0088\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\6\13\u0096\n\13\r\13\16\13\u0097\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\6\13\u00a0\n\13\r\13\16\13\u00a1\3\13\3\13\3\13\5\13\u00a7\n\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00b3\n\f\r\f\16\f\u00b4"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00be\n\f\r\f\16\f\u00bf\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u00d7\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e4"+
		"\n\f\3\f\5\f\u00e7\n\f\5\f\u00e9\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u00f4\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0102"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010f\n\f\3\f\5\f"+
		"\u0112\n\f\5\f\u0114\n\f\3\f\3\f\3\f\3\f\6\f\u011a\n\f\r\f\16\f\u011b"+
		"\3\f\3\f\3\f\5\f\u0121\n\f\3\r\3\r\3\r\5\r\u0126\n\r\3\r\3\r\3\r\3\r\3"+
		"\r\7\r\u012d\n\r\f\r\16\r\u0130\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0140\n\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\7\20\u014a\n\20\f\20\16\20\u014d\13\20\3\21\3\21"+
		"\3\21\5\21\u0152\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u0161\n\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0177"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\6\25\u0187\n\25\r\25\16\25\u0188\3\25\3\25\3\25\3\25\6\25\u018f"+
		"\n\25\r\25\16\25\u0190\3\25\3\25\3\25\3\25\6\25\u0197\n\25\r\25\16\25"+
		"\u0198\3\25\3\25\5\25\u019d\n\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\2\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\3\3\2"+
		"&\'\2\u01c5\2,\3\2\2\2\4\63\3\2\2\2\6\67\3\2\2\2\bW\3\2\2\2\nY\3\2\2\2"+
		"\fg\3\2\2\2\16i\3\2\2\2\20q\3\2\2\2\22}\3\2\2\2\24\u008c\3\2\2\2\26\u0120"+
		"\3\2\2\2\30\u0125\3\2\2\2\32\u013f\3\2\2\2\34\u0141\3\2\2\2\36\u0145\3"+
		"\2\2\2 \u0160\3\2\2\2\"\u0162\3\2\2\2$\u0167\3\2\2\2&\u0176\3\2\2\2(\u0178"+
		"\3\2\2\2*\u01a1\3\2\2\2,-\7\3\2\2-.\5\4\3\2./\5\n\6\2/\60\7\4\2\2\60\61"+
		"\b\2\1\2\61\3\3\2\2\2\62\64\5\6\4\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63"+
		"\3\2\2\2\65\66\3\2\2\2\66\5\3\2\2\2\678\7\5\2\289\5\b\5\29:\7%\2\2:M\b"+
		"\4\1\2;<\7 \2\2<A\b\4\1\2=B\5\30\r\2>?\5\34\17\2?@\b\4\1\2@B\3\2\2\2A"+
		"=\3\2\2\2A>\3\2\2\2BC\3\2\2\2CD\b\4\1\2DN\3\2\2\2EF\7!\2\2FG\7%\2\2GI"+
		"\b\4\1\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2\2M"+
		";\3\2\2\2MJ\3\2\2\2NO\3\2\2\2OP\7\34\2\2P\7\3\2\2\2QR\7\6\2\2RX\b\5\1"+
		"\2ST\7\7\2\2TX\b\5\1\2UV\7\b\2\2VX\b\5\1\2WQ\3\2\2\2WS\3\2\2\2WU\3\2\2"+
		"\2X\t\3\2\2\2Y[\b\6\1\2Z\\\5\f\7\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3"+
		"\2\2\2^\13\3\2\2\2_h\5\16\b\2`h\5\20\t\2ah\5\22\n\2bh\5\24\13\2ch\5\26"+
		"\f\2dh\5(\25\2eh\5*\26\2fh\5\6\4\2g_\3\2\2\2g`\3\2\2\2ga\3\2\2\2gb\3\2"+
		"\2\2gc\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\r\3\2\2\2ij\7\t\2\2jk\7"+
		"\31\2\2kl\7%\2\2lm\b\b\1\2mn\7\32\2\2no\7\34\2\2op\b\b\1\2p\17\3\2\2\2"+
		"qr\7\n\2\2rw\7\31\2\2st\7)\2\2tx\b\t\1\2uv\7%\2\2vx\b\t\1\2ws\3\2\2\2"+
		"wu\3\2\2\2xy\3\2\2\2yz\7\32\2\2z{\7\34\2\2{|\b\t\1\2|\21\3\2\2\2}~\7%"+
		"\2\2~\177\b\n\1\2\177\u0080\7 \2\2\u0080\u0087\b\n\1\2\u0081\u0082\5\30"+
		"\r\2\u0082\u0083\b\n\1\2\u0083\u0088\3\2\2\2\u0084\u0085\5\34\17\2\u0085"+
		"\u0086\b\n\1\2\u0086\u0088\3\2\2\2\u0087\u0081\3\2\2\2\u0087\u0084\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7\34\2\2\u008a\u008b\b\n\1\2\u008b"+
		"\23\3\2\2\2\u008c\u008d\7\13\2\2\u008d\u008e\7\31\2\2\u008e\u008f\5\34"+
		"\17\2\u008f\u0090\b\13\1\2\u0090\u0091\7\32\2\2\u0091\u0092\7\f\2\2\u0092"+
		"\u0093\7\"\2\2\u0093\u0095\b\13\1\2\u0094\u0096\5\f\7\2\u0095\u0094\3"+
		"\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\7#\2\2\u009a\u00a6\b\13\1\2\u009b\u009c\7\r"+
		"\2\2\u009c\u009d\7\"\2\2\u009d\u009f\b\13\1\2\u009e\u00a0\5\f\7\2\u009f"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7#\2\2\u00a4\u00a5\b\13\1\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u009b\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\b\13\1\2\u00a9\25\3\2\2\2\u00aa\u00ab\7\16\2\2\u00ab"+
		"\u00ac\7\31\2\2\u00ac\u00ad\5\34\17\2\u00ad\u00ae\b\f\1\2\u00ae\u00af"+
		"\7\32\2\2\u00af\u00b0\7\"\2\2\u00b0\u00b2\b\f\1\2\u00b1\u00b3\5\f\7\2"+
		"\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7#\2\2\u00b7\u00b8\b\f\1\2\u00b8"+
		"\u0121\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\7\"\2\2\u00bb\u00bd\b"+
		"\f\1\2\u00bc\u00be\5\f\7\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7#"+
		"\2\2\u00c2\u00c3\b\f\1\2\u00c3\u00c4\7\16\2\2\u00c4\u00c5\7\31\2\2\u00c5"+
		"\u00c6\5\34\17\2\u00c6\u00c7\b\f\1\2\u00c7\u00c8\7\32\2\2\u00c8\u00c9"+
		"\7\34\2\2\u00c9\u00ca\b\f\1\2\u00ca\u0121\3\2\2\2\u00cb\u00cc\7\20\2\2"+
		"\u00cc\u00cd\7\31\2\2\u00cd\u00e8\b\f\1\2\u00ce\u00cf\5\b\5\2\u00cf\u00d0"+
		"\7%\2\2\u00d0\u00d1\b\f\1\2\u00d1\u00d2\7 \2\2\u00d2\u00d6\b\f\1\2\u00d3"+
		"\u00d4\7%\2\2\u00d4\u00d7\b\f\1\2\u00d5\u00d7\t\2\2\2\u00d6\u00d3\3\2"+
		"\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\f\1\2\u00d9"+
		"\u00da\b\f\1\2\u00da\u00e7\3\2\2\2\u00db\u00dc\7%\2\2\u00dc\u00dd\b\f"+
		"\1\2\u00dd\u00de\3\2\2\2\u00de\u00df\7 \2\2\u00df\u00e3\b\f\1\2\u00e0"+
		"\u00e1\7%\2\2\u00e1\u00e4\b\f\1\2\u00e2\u00e4\7&\2\2\u00e3\u00e0\3\2\2"+
		"\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\b\f\1\2\u00e6\u00ce"+
		"\3\2\2\2\u00e6\u00db\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\7\34\2\2\u00eb\u00f3\b"+
		"\f\1\2\u00ec\u00ed\7%\2\2\u00ed\u00ee\b\f\1\2\u00ee\u00ef\7$\2\2\u00ef"+
		"\u00f0\b\f\1\2\u00f0\u00f4\5\30\r\2\u00f1\u00f2\7\30\2\2\u00f2\u00f4\b"+
		"\f\1\2\u00f3\u00ec\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f6\b\f\1\2\u00f6\u0113\7\34\2\2\u00f7\u00f8\7%\2\2\u00f8\u00f9\b\f"+
		"\1\2\u00f9\u0111\b\f\1\2\u00fa\u00fb\7\36\2\2\u00fb\u0112\b\f\1\2\u00fc"+
		"\u00fd\7\37\2\2\u00fd\u0101\b\f\1\2\u00fe\u00ff\7%\2\2\u00ff\u0102\b\f"+
		"\1\2\u0100\u0102\t\2\2\2\u0101\u00fe\3\2\2\2\u0101\u0100\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\u0112\b\f\1\2\u0104\u0105\7 \2\2\u0105\u0106\b\f"+
		"\1\2\u0106\u0107\7%\2\2\u0107\u0108\b\f\1\2\u0108\u0109\b\f\1\2\u0109"+
		"\u010a\7\35\2\2\u010a\u010e\b\f\1\2\u010b\u010c\7%\2\2\u010c\u010f\b\f"+
		"\1\2\u010d\u010f\t\2\2\2\u010e\u010b\3\2\2\2\u010e\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0112\b\f\1\2\u0111\u00fa\3\2\2\2\u0111\u00fc\3\2"+
		"\2\2\u0111\u0104\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u00f7\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\7\32\2\2\u0116\u0117\7"+
		"\"\2\2\u0117\u0119\b\f\1\2\u0118\u011a\5\f\7\2\u0119\u0118\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\u011e\7#\2\2\u011e\u011f\b\f\1\2\u011f\u0121\3\2\2\2\u0120"+
		"\u00aa\3\2\2\2\u0120\u00b9\3\2\2\2\u0120\u00cb\3\2\2\2\u0121\27\3\2\2"+
		"\2\u0122\u0126\5\32\16\2\u0123\u0124\7\'\2\2\u0124\u0126\b\r\1\2\u0125"+
		"\u0122\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u012e\3\2\2\2\u0127\u0128\7\35"+
		"\2\2\u0128\u0129\b\r\1\2\u0129\u012d\5\32\16\2\u012a\u012b\7\'\2\2\u012b"+
		"\u012d\b\r\1\2\u012c\u0127\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2"+
		"\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\31\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0131\u0132\7%\2\2\u0132\u0140\b\16\1\2\u0133\u0134\7&\2\2\u0134"+
		"\u0140\b\16\1\2\u0135\u0136\7\'\2\2\u0136\u0140\b\16\1\2\u0137\u0138\7"+
		")\2\2\u0138\u0140\b\16\1\2\u0139\u013a\7\31\2\2\u013a\u013b\b\16\1\2\u013b"+
		"\u013c\5\30\r\2\u013c\u013d\7\32\2\2\u013d\u013e\b\16\1\2\u013e\u0140"+
		"\3\2\2\2\u013f\u0131\3\2\2\2\u013f\u0133\3\2\2\2\u013f\u0135\3\2\2\2\u013f"+
		"\u0137\3\2\2\2\u013f\u0139\3\2\2\2\u0140\33\3\2\2\2\u0141\u0142\b\17\1"+
		"\2\u0142\u0143\5\36\20\2\u0143\u0144\b\17\1\2\u0144\35\3\2\2\2\u0145\u014b"+
		"\5 \21\2\u0146\u0147\7\27\2\2\u0147\u0148\b\20\1\2\u0148\u014a\5 \21\2"+
		"\u0149\u0146\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c"+
		"\3\2\2\2\u014c\37\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u0151\b\21\1\2\u014f"+
		"\u0150\7\26\2\2\u0150\u0152\b\21\1\2\u0151\u014f\3\2\2\2\u0151\u0152\3"+
		"\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\7\31\2\2\u0154\u0155\b\21\1\2\u0155"+
		"\u0156\5\36\20\2\u0156\u0157\7\32\2\2\u0157\u0158\b\21\1\2\u0158\u0161"+
		"\3\2\2\2\u0159\u015a\b\21\1\2\u015a\u015b\5\"\22\2\u015b\u015c\b\21\1"+
		"\2\u015c\u0161\3\2\2\2\u015d\u015e\7\30\2\2\u015e\u0161\b\21\1\2\u015f"+
		"\u0161\5\30\r\2\u0160\u014e\3\2\2\2\u0160\u0159\3\2\2\2\u0160\u015d\3"+
		"\2\2\2\u0160\u015f\3\2\2\2\u0161!\3\2\2\2\u0162\u0163\5\30\r\2\u0163\u0164"+
		"\7$\2\2\u0164\u0165\b\22\1\2\u0165\u0166\5\30\r\2\u0166#\3\2\2\2\u0167"+
		"\u0168\7\31\2\2\u0168\u0169\b\23\1\2\u0169\u016a\5&\24\2\u016a\u016b\7"+
		"\35\2\2\u016b\u016c\b\23\1\2\u016c\u016d\5&\24\2\u016d\u016e\7\32\2\2"+
		"\u016e\u016f\b\23\1\2\u016f%\3\2\2\2\u0170\u0171\7%\2\2\u0171\u0177\b"+
		"\24\1\2\u0172\u0173\7&\2\2\u0173\u0177\b\24\1\2\u0174\u0175\7\'\2\2\u0175"+
		"\u0177\b\24\1\2\u0176\u0170\3\2\2\2\u0176\u0172\3\2\2\2\u0176\u0174\3"+
		"\2\2\2\u0177\'\3\2\2\2\u0178\u0179\7\21\2\2\u0179\u017a\7\31\2\2\u017a"+
		"\u017b\7%\2\2\u017b\u017c\b\25\1\2\u017c\u017d\7\32\2\2\u017d\u018e\7"+
		"\"\2\2\u017e\u017f\7\22\2\2\u017f\u0180\7)\2\2\u0180\u0181\b\25\1\2\u0181"+
		"\u0182\3\2\2\2\u0182\u0183\b\25\1\2\u0183\u0184\7\25\2\2\u0184\u0186\b"+
		"\25\1\2\u0185\u0187\5\f\7\2\u0186\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188"+
		"\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018b\7\23"+
		"\2\2\u018b\u018c\7\34\2\2\u018c\u018d\b\25\1\2\u018d\u018f\3\2\2\2\u018e"+
		"\u017e\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u019c\3\2\2\2\u0192\u0193\7\24\2\2\u0193\u0194\b\25\1\2\u0194"+
		"\u0196\7\25\2\2\u0195\u0197\5\f\7\2\u0196\u0195\3\2\2\2\u0197\u0198\3"+
		"\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2\2\2\u019a"+
		"\u019b\b\25\1\2\u019b\u019d\3\2\2\2\u019c\u0192\3\2\2\2\u019c\u019d\3"+
		"\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\7#\2\2\u019f\u01a0\b\25\1\2\u01a0"+
		")\3\2\2\2\u01a1\u01a2\7*\2\2\u01a2\u01a3\b\26\1\2\u01a3\u01a4\3\2\2\2"+
		"\u01a4\u01a5\b\26\1\2\u01a5+\3\2\2\2\'\65AJMW]gw\u0087\u0097\u00a1\u00a6"+
		"\u00b4\u00bf\u00d6\u00e3\u00e6\u00e8\u00f3\u0101\u010e\u0111\u0113\u011b"+
		"\u0120\u0125\u012c\u012e\u013f\u014b\u0151\u0160\u0176\u0188\u0190\u0198"+
		"\u019c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}