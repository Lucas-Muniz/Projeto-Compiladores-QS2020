// Generated from IsiLang.g4 by ANTLR 4.7.1
package isilanguage.parser;

	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
	import isilanguage.datastructures.AbstractExpression;
	import isilanguage.datastructures.NumericExpressionBuilder;
	import isilanguage.datastructures.TextualExpressionBuilder;
	import isilanguage.datastructures.RelationalExpressionBuilder;
	import isilanguage.datastructures.BooleanExpressionBuilder;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, BOOLEANOUNARIO=15, BOOLEANOBINARIO=16, 
		BOOL=17, AP=18, FP=19, SC=20, POINT=21, OP=22, OP_INC_DEC=23, OP_INC_DEC_EQ=24, 
		ATTR=25, VIR=26, ACH=27, FCH=28, OPREL=29, ID=30, NUMBER=31, SIGNEDNUMBER=32, 
		SIGN=33, TEXTO=34, COMENTARIO=35, WS=36;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_condicao = 13, RULE_exprbooleana = 14, RULE_booleano = 15, RULE_relacao = 16, 
		RULE_operacao = 17, RULE_termonumerico = 18, RULE_comentarios = 19;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo", "condicao", 
		"exprbooleana", "booleano", "relacao", "operacao", "termonumerico", "comentarios"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'booleano'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
		"'faca'", "'para'", "'nao'", null, null, "'('", "')'", "';'", "'.'", null, 
		null, null, "':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "BOOLEANOUNARIO", "BOOLEANOBINARIO", "BOOL", "AP", "FP", 
		"SC", "POINT", "OP", "OP_INC_DEC", "OP_INC_DEC_EQ", "ATTR", "VIR", "ACH", 
		"FCH", "OPREL", "ID", "NUMBER", "SIGNEDNUMBER", "SIGN", "TEXTO", "COMENTARIO", 
		"WS"
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


		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _attribID;
		private String _attribTerm;
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
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTargetPrettyPrinter();
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
			setState(40);
			match(T__0);
			setState(41);
			decl();
			setState(42);
			bloco();
			setState(43);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				declaravar();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
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
			setState(51);
			match(T__2);
			setState(52);
			tipo();
			setState(53);
			match(ID);

				                              _varName = _input.LT(-1).getText();
				                              _varValue = null;
				                              symbol = new IsiVariable(_varName, _tipo, _varValue);
				                              if (!symbolTable.exists(_varName)){
				                                symbolTable.add(symbol);	
				                              }
				                              else{
				                  	            throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                              }
			                                 
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(55);
				match(VIR);
				setState(56);
				match(ID);

					                             _varName = _input.LT(-1).getText();
					                             _varValue = null;
					                             symbol = new IsiVariable(_varName, _tipo, _varValue);
					                             if (!symbolTable.exists(_varName)){
					                               symbolTable.add(symbol);	
					                             }
					                             else{
					                  	           throw new IsiSemanticException("Symbol "+_varName+" already declared");
					                             }
				                                
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
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
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(T__3);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(T__4);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
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
			          
			setState(75); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				cmd();
				}
				}
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
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
		public ComentariosContext comentarios() {
			return getRuleContext(ComentariosContext.class,0);
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
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				cmdleitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(82);
				cmdselecao();
				}
				break;
			case T__11:
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				cmdrepeticao();
				}
				break;
			case COMENTARIO:
				enterOuterAlt(_localctx, 6);
				{
				setState(84);
				comentarios();
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
			setState(87);
			match(T__6);
			setState(88);
			match(AP);
			setState(89);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  atribuiVariavel(_readID);
			                        
			setState(91);
			match(FP);
			setState(92);
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
			setState(95);
			match(T__7);
			setState(96);
			match(AP);
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(97);
				match(TEXTO);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case ID:
				{
				setState(99);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                    _writeID = _input.LT(-1).getText();
					                    useVariavel(_writeID);
				                       
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(103);
			match(FP);
			setState(104);
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
			setState(107);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                    
			                    _exprType = obtemTipoId(_exprID);
			                    
			                    if(_exprType == IsiTypes.NUMBER){
			                    	_expr = new NumericExpressionBuilder();
			                    } else if (_exprType == IsiTypes.BOOLEAN){
			                    	_expr = new BooleanExpressionBuilder();
			                    } else if (_exprType == IsiTypes.TEXT){
			                    	_expr = new TextualExpressionBuilder();
			                    }
			                    
			                   
			setState(109);
			match(ATTR);
			 _exprContent = ""; 
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(111);
				expr();
				}
				break;
			case 2:
				{
				setState(112);
				condicao();
				_exprContent = _condition;
				}
				break;
			}
			setState(117);
			match(POINT);
			 
			                 //_exprContent = _expr.getExpression();
			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 atribuiVariavel(_exprID);
			               	 stack.peek().add(cmd);
			               	 //verificaTipoAtribuicao(_exprID, _term);
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
			setState(120);
			match(T__8);
			setState(121);
			match(AP);
			setState(122);
			condicao();
			_exprDecision = _condition;
			setState(124);
			match(FP);
			setState(125);
			match(T__9);
			setState(126);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				cmd();
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
			setState(133);
			match(FCH);

			                       listaTrue = stack.pop();	
			                       listaFalse = new ArrayList<AbstractCommand>();
			                   	   CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			                    
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(135);
				match(T__10);
				setState(136);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(138);
					cmd();
					}
					}
					setState(141); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				}
				setState(143);
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
		public List<TerminalNode> ATTR() { return getTokens(IsiLangParser.ATTR); }
		public TerminalNode ATTR(int i) {
			return getToken(IsiLangParser.ATTR, i);
		}
		public TerminalNode OP_INC_DEC() { return getToken(IsiLangParser.OP_INC_DEC, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> SIGNEDNUMBER() { return getTokens(IsiLangParser.SIGNEDNUMBER); }
		public TerminalNode SIGNEDNUMBER(int i) {
			return getToken(IsiLangParser.SIGNEDNUMBER, i);
		}
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode OP_INC_DEC_EQ() { return getToken(IsiLangParser.OP_INC_DEC_EQ, 0); }
		public TerminalNode OP() { return getToken(IsiLangParser.OP, 0); }
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
			setState(273);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(T__11);
				setState(151);
				match(AP);
				setState(152);
				condicao();
				_exprDecision = _condition;
				                   		            System.out.println(_exprDecision);
				setState(154);
				match(FP);
				setState(155);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                      	  	stack.push(curThread);
				                   		  
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(157);
					cmd();
					}
					}
					setState(160); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(162);
				match(FCH);

				                        	commands = stack.pop();	
				                    	  

				                   			CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
				                   			stack.peek().add(cmd);
				                   		  
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(T__12);
				setState(167);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                       	 	stack.push(curThread);
				                   	   	  
				setState(170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(169);
					cmd();
					}
					}
					setState(172); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(174);
				match(FCH);

				                         	commands = stack.pop();	
				                       	  
				setState(176);
				match(T__11);
				setState(177);
				match(AP);
				setState(178);
				condicao();
				_exprDecision = _condition;
				setState(180);
				match(FP);
				setState(181);
				match(POINT);

				                   	   	  CommandFaca cmd = new CommandFaca(_exprDecision, commands);
				                   		  stack.peek().add(cmd);
				                   	   
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				match(T__13);
				setState(185);
				match(AP);
				_exprAttrib = "";
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << ID))) != 0)) {
					{
					setState(211);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__3:
					case T__4:
					case T__5:
						{
						{
						setState(187);
						tipo();
						setState(188);
						match(ID);
						_varName = _input.LT(-1).getText();
									                          _varValue = null;
									                              symbol = new IsiVariable(_varName, _tipo, _varValue);
									                              
									                              if (!symbolTable.exists(_varName)){
									                                symbolTable.add(symbol);	
									                                _exprAttrib = _varName;
									                              }
									                              else{
									                  	            throw new IsiSemanticException("Symbol "+_varName+" already declared");
									                              }
									                              
									                           
									                            
						setState(190);
						match(ATTR);
						 _exprAttrib += _input.LT(-1).getText();
						setState(195);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(192);
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
							setState(194);
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
						_exprContent =_input.LT(-1).getText(); _exprAttrib +=_exprContent;  

						                                   	 
											               	 atribuiVariavel(_varName);

											               	 
											               	 _term = null;
											               	 op = null;
											            
						}
						}
						break;
					case ID:
						{
						{
						setState(200);
						match(ID);
						 _varName = _input.LT(-1).getText();
															if (!symbolTable.exists(_varName)){
									                             throw new IsiSemanticException("Symbol "+_varName+" not declared");
									                        }
									                        else{
									                            _exprAttrib = _varName;
									                        }
													     
						}
						setState(203);
						match(ATTR);
						 _exprAttrib += _input.LT(-1).getText();
						setState(208);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(205);
							match(ID);
							 _varName = _input.LT(-1).getText();
																if (!symbolTable.exists(_varName)){
										                            throw new IsiSemanticException("Symbol "+_varName+" not declared");
										                        }
															 
							}
							break;
						case NUMBER:
							{
							setState(207);
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

				setState(215);
				match(POINT);
				_exprDecision = "";
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(217);
					match(ID);
					 _varName = _input.LT(-1).getText();
															if (!symbolTable.exists(_varName)){
								                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
								                              }
								                              else{
								                  	            _exprDecision += _varName;
								                              }
													   
					setState(219);
					match(OPREL);
					 _exprDecision += _input.LT(-1).getText(); 
					setState(224);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ID:
						{
						setState(221);
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
						setState(223);
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
					_exprDecision +=_input.LT(-1).getText(); 
					}
				}

				 _exprStep = "";
				setState(230);
				match(POINT);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(231);
					match(ID);
					 _varName = _input.LT(-1).getText();
															if (!symbolTable.exists(_varName)){
								                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
								                            }
													  
					 _exprStep += _input.LT(-1).getText();
					setState(257);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OP_INC_DEC:
						{
						setState(234);
						match(OP_INC_DEC);
						 _exprStep += _input.LT(-1).getText();
						}
						break;
					case OP_INC_DEC_EQ:
						{
						{
						setState(236);
						match(OP_INC_DEC_EQ);
						 _exprStep += _input.LT(-1).getText();
						setState(241);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(238);
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
							setState(240);
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
						setState(244);
						match(ATTR);
						 _exprStep += _input.LT(-1).getText();
						setState(246);
						match(ID);
						 _varName = _input.LT(-1).getText();
																	if (!symbolTable.exists(_varName)){
										                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
										                              }
																 
						 _exprStep += _varName;
						setState(249);
						match(OP);
						 _exprStep += _input.LT(-1).getText();
						setState(254);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(251);
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
							setState(253);
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

				setState(261);
				match(FP);
				setState(262);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
												stack.push(curThread);
											
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(264);
					cmd();
					}
					}
					setState(267); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ID) | (1L << COMENTARIO))) != 0) );
				setState(269);
				match(FCH);

												listaTrue = stack.pop();	
											

												CommandFor cmd = new CommandFor(_exprAttrib, _exprDecision, _exprStep, listaTrue);
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
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(275);
				termo();
				}
				break;
			case 2:
				{
				setState(276);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	                _exprContent += _attribTerm;
				              	       
				              	                 /* Verificação de tipo*/
					               	             _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					               	             
					               	             _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				                               
				}
				break;
			}
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP || _la==SIGNEDNUMBER) {
				{
				setState(285);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP:
					{
					{
					setState(280);
					match(OP);
					 op = _input.LT(-1).getText();
						                    _exprContent += op;
						                    _expr.addOperator(op);
					setState(282);
					termo();
					}
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(283);
					match(SIGNEDNUMBER);
					 _attribTerm = _input.LT(-1).getText();
					              	                _exprContent += _attribTerm;
					              	                op = "+";
					              	       
					              	                /* VerificaÃ§Ã£o de tipo*/
						               	            _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
						               	            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
					                              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(289);
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
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				match(ID);
				 _attribTerm = _input.LT(-1).getText();
				                   verificaID(_attribTerm);
					               _exprContent += _attribTerm;
					               useVariavel(_attribTerm);
					               /* Verifica se uma variÃ¡vel usada foi atribuÃ­da */
					               verificaAtribuicao(_attribTerm);
					               
					               /* VerificaÃ§Ã£o de tipo*/
					               //_term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
					               
					               _expr.addElement(_attribTerm, obtemTipoId(_attribTerm));
					               
				                 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(NUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	       _exprContent += _attribTerm;
				              	       
				              	       /* Verificacao de tipo*/
					               	   _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					               	   
					               	   _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				                      
				}
				break;
			case SIGNEDNUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	             if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
				              	             	_attribTerm = _attribTerm.substring(1);
				              	             }
				              	             _exprContent += _attribTerm;
				              	             /* Verificação de tipo*/
					               		     _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					               		     
					               		     _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				                            
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(296);
				match(TEXTO);
				 _attribTerm = _input.LT(-1).getText();
				              	      _exprContent += _attribTerm;
				              	       
				              	      /* Verificação de tipo*/
					               	  _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.TEXT, op);
					               	  
					               	  _expr.addElement(_attribTerm, IsiTypes.TEXT);
				                    
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(298);
				match(AP);
				_exprContent += _input.LT(-1).getText();
				                   _expr.openParentheses(); 
				setState(300);
				expr();
				setState(301);
				match(FP);
				 _exprContent += _input.LT(-1).getText();
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
			 _booleanExpr = "";
			             _expr = new BooleanExpressionBuilder();
			            
			setState(307);
			exprbooleana();
			 
			             //_condition =_booleanExpr;
			             _condition = _expr.getExpression();
			             _booleanExpr = ""; 
			           
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
			setState(310);
			booleano();
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOLEANOBINARIO) {
				{
				{
				setState(311);
				match(BOOLEANOBINARIO);
				 
				                                   bool = _input.LT(-1).getText();
				                                   if (bool.contentEquals("ou")){
				                   		           		_booleanExpr +=  " || ";
				                   		           		bool = "||";
				                                   } else if (bool.contentEquals("e")) {
				                   		           		_booleanExpr +=  " && ";
				                   		           		bool = "&&";
				                                   } else {
				                                      throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
				                                   } 
				                                   _expr.addOperator(bool);
				                                   
				setState(313);
				booleano();
				}
				}
				setState(318);
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
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				 _partialBooleanExpr = ""; 
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BOOLEANOUNARIO) {
					{
					setState(320);
					match(BOOLEANOUNARIO);
					 
					                             bool = _input.LT(-1).getText();
					                             if (bool.contentEquals("nao")){
					                   		      	_booleanExpr +=  "!";
					                   		      	bool = "!";
					                   			 } else {
					                                throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
					                             }
					                             _expr.addOperator(bool);
					                            
					}
				}

				setState(324);
				match(AP);
				 
				                             _booleanExpr += _input.LT(-1).getText();
				                             _expr.openParentheses();
				                            
				setState(326);
				exprbooleana();
				setState(327);
				match(FP);
				 _booleanExpr +=  _input.LT(-1).getText();
				                              _exprContent = "";
				                              _partialBooleanExpr = ""; 
				                              _expr.closeParentheses();
				                            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 _term = null;
				setState(331);
				relacao();
				   
				                              //_booleanTerm = new IsiTerm(_term.getContent(), IsiTypes.BOOLEAN);
				                              //_booleanExpr +=  _term.getContent();
				                              op = null;
				                              _exprContent = "";
				                            
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(334);
				match(BOOL);

				                   bool = _input.LT(-1).getText();
				                   if (bool.contentEquals("verdadeiro")){
				                   		_booleanExpr +=  "true";
				                   		bool = "true";
				                   } else if (bool.contentEquals("falso")) {
				                   		_booleanExpr +=  "false";
				                   		bool = "false";
				                   } else {
				                       throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
				                   }
				                   _expr.addElement(bool, IsiTypes.BOOLEAN);
				                  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
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
			setState(339);
			expr();
			setState(340);
			match(OPREL);
			 _exprContent += " "+ _input.LT(-1).getText()+" ";
			                           op = _input.LT(-1).getText();
			                           if (op != null && !IsiOperator.isRelationalOperator(op)){
			                                throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
			                           }
			                           _expr.addOperator(op);
			                         
			setState(342);
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
			setState(344);
			match(AP);
			 _booleanExpr +=  _input.LT(-1).getText();
			                            _expr.openParentheses();
			setState(346);
			termonumerico();
			setState(347);
			match(OP);
			 _exprContent += _input.LT(-1).getText();
			                           op = _input.LT(-1).getText();
			                           if (op != null && !IsiOperator.isNumericOperator(op)){
			                                throw new IsiSemanticException("Expecting a numeric operator, but got '"+op+"'");
			                           }
			                           _expr.addOperator(op);
			                         
			setState(349);
			termonumerico();
			setState(350);
			match(FP);
			 _booleanExpr +=  _input.LT(-1).getText();
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
			setState(359);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(353);
				match(ID);
				 _attribTerm = _input.LT(-1).getText();
				                       verificaID(_attribTerm);
					                   _exprContent += _attribTerm;
					                   useVariavel(_attribTerm);
					                   /* Verifica se uma variável usada foi atribui­da */
					                   verificaAtribuicao(_attribTerm);
					               
					                   /* Verificacao de tipo*/
					                   _term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
					                   
					                   _expr.addElement(_attribTerm, obtemTipoId(_attribTerm) );
					               
				                      
				}
				break;
			case NUMBER:
				{
				setState(355);
				match(NUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	            _exprContent += _attribTerm;
				              	       
				              	           /* VerificaÃ§Ã£o de tipo*/
					               	       _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					               	       
					               	       _expr.addElement(_attribTerm, IsiTypes.NUMBER);
				                          
				}
				break;
			case SIGNEDNUMBER:
				{
				setState(357);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	                  _exprContent += _attribTerm;
				              	                   /* Verificacao de tipo*/
					               		          _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					               		          
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

	public static class ComentariosContext extends ParserRuleContext {
		public List<TerminalNode> COMENTARIO() { return getTokens(IsiLangParser.COMENTARIO); }
		public TerminalNode COMENTARIO(int i) {
			return getToken(IsiLangParser.COMENTARIO, i);
		}
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
		enterRule(_localctx, 38, RULE_comentarios);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(363); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(361);
					match(COMENTARIO);

					                              _textComment = (_textComment == null ? "" : _textComment) + _input.LT(-1).getText() + " ";
					                          
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(365); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u0174\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3\62\n\3"+
		"\r\3\16\3\63\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4=\n\4\f\4\16\4@\13\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5J\n\5\3\6\3\6\6\6N\n\6\r\6\16\6O\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7X\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\th\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\nv\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13"+
		"\u0084\n\13\r\13\16\13\u0085\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u008e"+
		"\n\13\r\13\16\13\u008f\3\13\3\13\3\13\5\13\u0095\n\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a1\n\f\r\f\16\f\u00a2\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\6\f\u00ad\n\f\r\f\16\f\u00ae\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c6"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d3\n\f\3\f\5\f"+
		"\u00d6\n\f\5\f\u00d8\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e3"+
		"\n\f\3\f\5\f\u00e6\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u00f4\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0101\n"+
		"\f\3\f\5\f\u0104\n\f\5\f\u0106\n\f\3\f\3\f\3\f\3\f\6\f\u010c\n\f\r\f\16"+
		"\f\u010d\3\f\3\f\3\f\3\f\5\f\u0114\n\f\3\r\3\r\3\r\5\r\u0119\n\r\3\r\3"+
		"\r\3\r\3\r\3\r\7\r\u0120\n\r\f\r\16\r\u0123\13\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0133\n\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u013d\n\20\f\20\16\20\u0140\13"+
		"\20\3\21\3\21\3\21\5\21\u0145\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0154\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u016a\n\24\3\25\3\25\6\25\u016e\n\25\r\25\16\25\u016f\3\25"+
		"\3\25\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\3\3\2"+
		"!\"\2\u018d\2*\3\2\2\2\4\61\3\2\2\2\6\65\3\2\2\2\bI\3\2\2\2\nK\3\2\2\2"+
		"\fW\3\2\2\2\16Y\3\2\2\2\20a\3\2\2\2\22m\3\2\2\2\24z\3\2\2\2\26\u0113\3"+
		"\2\2\2\30\u0118\3\2\2\2\32\u0132\3\2\2\2\34\u0134\3\2\2\2\36\u0138\3\2"+
		"\2\2 \u0153\3\2\2\2\"\u0155\3\2\2\2$\u015a\3\2\2\2&\u0169\3\2\2\2(\u016d"+
		"\3\2\2\2*+\7\3\2\2+,\5\4\3\2,-\5\n\6\2-.\7\4\2\2./\b\2\1\2/\3\3\2\2\2"+
		"\60\62\5\6\4\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2"+
		"\64\5\3\2\2\2\65\66\7\5\2\2\66\67\5\b\5\2\678\7 \2\28>\b\4\1\29:\7\34"+
		"\2\2:;\7 \2\2;=\b\4\1\2<9\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2"+
		"\2\2@>\3\2\2\2AB\7\27\2\2B\7\3\2\2\2CD\7\6\2\2DJ\b\5\1\2EF\7\7\2\2FJ\b"+
		"\5\1\2GH\7\b\2\2HJ\b\5\1\2IC\3\2\2\2IE\3\2\2\2IG\3\2\2\2J\t\3\2\2\2KM"+
		"\b\6\1\2LN\5\f\7\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\13\3\2\2\2"+
		"QX\5\16\b\2RX\5\20\t\2SX\5\22\n\2TX\5\24\13\2UX\5\26\f\2VX\5(\25\2WQ\3"+
		"\2\2\2WR\3\2\2\2WS\3\2\2\2WT\3\2\2\2WU\3\2\2\2WV\3\2\2\2X\r\3\2\2\2YZ"+
		"\7\t\2\2Z[\7\24\2\2[\\\7 \2\2\\]\b\b\1\2]^\7\25\2\2^_\7\27\2\2_`\b\b\1"+
		"\2`\17\3\2\2\2ab\7\n\2\2bg\7\24\2\2cd\7$\2\2dh\b\t\1\2ef\7 \2\2fh\b\t"+
		"\1\2gc\3\2\2\2ge\3\2\2\2hi\3\2\2\2ij\7\25\2\2jk\7\27\2\2kl\b\t\1\2l\21"+
		"\3\2\2\2mn\7 \2\2no\b\n\1\2op\7\33\2\2pu\b\n\1\2qv\5\30\r\2rs\5\34\17"+
		"\2st\b\n\1\2tv\3\2\2\2uq\3\2\2\2ur\3\2\2\2vw\3\2\2\2wx\7\27\2\2xy\b\n"+
		"\1\2y\23\3\2\2\2z{\7\13\2\2{|\7\24\2\2|}\5\34\17\2}~\b\13\1\2~\177\7\25"+
		"\2\2\177\u0080\7\f\2\2\u0080\u0081\7\35\2\2\u0081\u0083\b\13\1\2\u0082"+
		"\u0084\5\f\7\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\36\2\2\u0088"+
		"\u0094\b\13\1\2\u0089\u008a\7\r\2\2\u008a\u008b\7\35\2\2\u008b\u008d\b"+
		"\13\1\2\u008c\u008e\5\f\7\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\36"+
		"\2\2\u0092\u0093\b\13\1\2\u0093\u0095\3\2\2\2\u0094\u0089\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\13\1\2\u0097\25\3\2\2"+
		"\2\u0098\u0099\7\16\2\2\u0099\u009a\7\24\2\2\u009a\u009b\5\34\17\2\u009b"+
		"\u009c\b\f\1\2\u009c\u009d\7\25\2\2\u009d\u009e\7\35\2\2\u009e\u00a0\b"+
		"\f\1\2\u009f\u00a1\5\f\7\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\36"+
		"\2\2\u00a5\u00a6\b\f\1\2\u00a6\u00a7\b\f\1\2\u00a7\u0114\3\2\2\2\u00a8"+
		"\u00a9\7\17\2\2\u00a9\u00aa\7\35\2\2\u00aa\u00ac\b\f\1\2\u00ab\u00ad\5"+
		"\f\7\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\36\2\2\u00b1\u00b2\b"+
		"\f\1\2\u00b2\u00b3\7\16\2\2\u00b3\u00b4\7\24\2\2\u00b4\u00b5\5\34\17\2"+
		"\u00b5\u00b6\b\f\1\2\u00b6\u00b7\7\25\2\2\u00b7\u00b8\7\27\2\2\u00b8\u00b9"+
		"\b\f\1\2\u00b9\u0114\3\2\2\2\u00ba\u00bb\7\20\2\2\u00bb\u00bc\7\24\2\2"+
		"\u00bc\u00d7\b\f\1\2\u00bd\u00be\5\b\5\2\u00be\u00bf\7 \2\2\u00bf\u00c0"+
		"\b\f\1\2\u00c0\u00c1\7\33\2\2\u00c1\u00c5\b\f\1\2\u00c2\u00c3\7 \2\2\u00c3"+
		"\u00c6\b\f\1\2\u00c4\u00c6\t\2\2\2\u00c5\u00c2\3\2\2\2\u00c5\u00c4\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\f\1\2\u00c8\u00c9\b\f\1\2\u00c9"+
		"\u00d6\3\2\2\2\u00ca\u00cb\7 \2\2\u00cb\u00cc\b\f\1\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00ce\7\33\2\2\u00ce\u00d2\b\f\1\2\u00cf\u00d0\7 \2\2\u00d0"+
		"\u00d3\b\f\1\2\u00d1\u00d3\7!\2\2\u00d2\u00cf\3\2\2\2\u00d2\u00d1\3\2"+
		"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\b\f\1\2\u00d5\u00bd\3\2\2\2\u00d5"+
		"\u00ca\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\7\27\2\2\u00da\u00e5\b\f\1\2\u00db"+
		"\u00dc\7 \2\2\u00dc\u00dd\b\f\1\2\u00dd\u00de\7\37\2\2\u00de\u00e2\b\f"+
		"\1\2\u00df\u00e0\7 \2\2\u00e0\u00e3\b\f\1\2\u00e1\u00e3\t\2\2\2\u00e2"+
		"\u00df\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\b\f"+
		"\1\2\u00e5\u00db\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e8\b\f\1\2\u00e8\u0105\7\27\2\2\u00e9\u00ea\7 \2\2\u00ea\u00eb\b\f"+
		"\1\2\u00eb\u0103\b\f\1\2\u00ec\u00ed\7\31\2\2\u00ed\u0104\b\f\1\2\u00ee"+
		"\u00ef\7\32\2\2\u00ef\u00f3\b\f\1\2\u00f0\u00f1\7 \2\2\u00f1\u00f4\b\f"+
		"\1\2\u00f2\u00f4\t\2\2\2\u00f3\u00f0\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u0104\b\f\1\2\u00f6\u00f7\7\33\2\2\u00f7\u00f8\b"+
		"\f\1\2\u00f8\u00f9\7 \2\2\u00f9\u00fa\b\f\1\2\u00fa\u00fb\b\f\1\2\u00fb"+
		"\u00fc\7\30\2\2\u00fc\u0100\b\f\1\2\u00fd\u00fe\7 \2\2\u00fe\u0101\b\f"+
		"\1\2\u00ff\u0101\t\2\2\2\u0100\u00fd\3\2\2\2\u0100\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0104\b\f\1\2\u0103\u00ec\3\2\2\2\u0103\u00ee\3\2"+
		"\2\2\u0103\u00f6\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u00e9\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\7\25\2\2\u0108\u0109\7"+
		"\35\2\2\u0109\u010b\b\f\1\2\u010a\u010c\5\f\7\2\u010b\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0110\7\36\2\2\u0110\u0111\b\f\1\2\u0111\u0112\b\f\1\2\u0112"+
		"\u0114\3\2\2\2\u0113\u0098\3\2\2\2\u0113\u00a8\3\2\2\2\u0113\u00ba\3\2"+
		"\2\2\u0114\27\3\2\2\2\u0115\u0119\5\32\16\2\u0116\u0117\7\"\2\2\u0117"+
		"\u0119\b\r\1\2\u0118\u0115\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u0121\3\2"+
		"\2\2\u011a\u011b\7\30\2\2\u011b\u011c\b\r\1\2\u011c\u0120\5\32\16\2\u011d"+
		"\u011e\7\"\2\2\u011e\u0120\b\r\1\2\u011f\u011a\3\2\2\2\u011f\u011d\3\2"+
		"\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122"+
		"\31\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0125\7 \2\2\u0125\u0133\b\16\1"+
		"\2\u0126\u0127\7!\2\2\u0127\u0133\b\16\1\2\u0128\u0129\7\"\2\2\u0129\u0133"+
		"\b\16\1\2\u012a\u012b\7$\2\2\u012b\u0133\b\16\1\2\u012c\u012d\7\24\2\2"+
		"\u012d\u012e\b\16\1\2\u012e\u012f\5\30\r\2\u012f\u0130\7\25\2\2\u0130"+
		"\u0131\b\16\1\2\u0131\u0133\3\2\2\2\u0132\u0124\3\2\2\2\u0132\u0126\3"+
		"\2\2\2\u0132\u0128\3\2\2\2\u0132\u012a\3\2\2\2\u0132\u012c\3\2\2\2\u0133"+
		"\33\3\2\2\2\u0134\u0135\b\17\1\2\u0135\u0136\5\36\20\2\u0136\u0137\b\17"+
		"\1\2\u0137\35\3\2\2\2\u0138\u013e\5 \21\2\u0139\u013a\7\22\2\2\u013a\u013b"+
		"\b\20\1\2\u013b\u013d\5 \21\2\u013c\u0139\3\2\2\2\u013d\u0140\3\2\2\2"+
		"\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\37\3\2\2\2\u0140\u013e"+
		"\3\2\2\2\u0141\u0144\b\21\1\2\u0142\u0143\7\21\2\2\u0143\u0145\b\21\1"+
		"\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147"+
		"\7\24\2\2\u0147\u0148\b\21\1\2\u0148\u0149\5\36\20\2\u0149\u014a\7\25"+
		"\2\2\u014a\u014b\b\21\1\2\u014b\u0154\3\2\2\2\u014c\u014d\b\21\1\2\u014d"+
		"\u014e\5\"\22\2\u014e\u014f\b\21\1\2\u014f\u0154\3\2\2\2\u0150\u0151\7"+
		"\23\2\2\u0151\u0154\b\21\1\2\u0152\u0154\5\30\r\2\u0153\u0141\3\2\2\2"+
		"\u0153\u014c\3\2\2\2\u0153\u0150\3\2\2\2\u0153\u0152\3\2\2\2\u0154!\3"+
		"\2\2\2\u0155\u0156\5\30\r\2\u0156\u0157\7\37\2\2\u0157\u0158\b\22\1\2"+
		"\u0158\u0159\5\30\r\2\u0159#\3\2\2\2\u015a\u015b\7\24\2\2\u015b\u015c"+
		"\b\23\1\2\u015c\u015d\5&\24\2\u015d\u015e\7\30\2\2\u015e\u015f\b\23\1"+
		"\2\u015f\u0160\5&\24\2\u0160\u0161\7\25\2\2\u0161\u0162\b\23\1\2\u0162"+
		"%\3\2\2\2\u0163\u0164\7 \2\2\u0164\u016a\b\24\1\2\u0165\u0166\7!\2\2\u0166"+
		"\u016a\b\24\1\2\u0167\u0168\7\"\2\2\u0168\u016a\b\24\1\2\u0169\u0163\3"+
		"\2\2\2\u0169\u0165\3\2\2\2\u0169\u0167\3\2\2\2\u016a\'\3\2\2\2\u016b\u016c"+
		"\7%\2\2\u016c\u016e\b\25\1\2\u016d\u016b\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\b\25"+
		"\1\2\u0172)\3\2\2\2#\63>IOWgu\u0085\u008f\u0094\u00a2\u00ae\u00c5\u00d2"+
		"\u00d5\u00d7\u00e2\u00e5\u00f3\u0100\u0103\u0105\u010d\u0113\u0118\u011f"+
		"\u0121\u0132\u013e\u0144\u0153\u0169\u016f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}