// Generated from IsiLang.g4 by ANTLR 4.7.1
package isilanguage.parser;

	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
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
		T__9=10, T__10=11, T__11=12, T__12=13, START_COMMENT=14, END_COMMENT=15, 
		AP=16, FP=17, SC=18, POINT=19, OP=20, OP_INC_DEC=21, OP_INC_DEC_EQ=22, 
		ATTR=23, VIR=24, ACH=25, FCH=26, OPREL=27, ID=28, NUMBER=29, SIGNEDNUMBER=30, 
		SIGN=31, TEXTO=32, WS=33;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_comentarios = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo", "comentarios"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'faca'", 
		"'para'", "'/*'", "'*/'", "'('", "')'", "';'", "'.'", null, null, null, 
		"':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "START_COMMENT", "END_COMMENT", "AP", "FP", "SC", "POINT", 
		"OP", "OP_INC_DEC", "OP_INC_DEC_EQ", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
		"ID", "NUMBER", "SIGNEDNUMBER", "SIGN", "TEXTO", "WS"
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
			 /* VerificaÃ§Ã£o de tipo*/
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
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
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
			setState(39);
			match(T__2);
			setState(40);
			tipo();
			setState(41);
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
			                                 
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(43);
				match(VIR);
				setState(44);
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
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
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
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(T__3);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__4);
				 _tipo = IsiVariable.TEXT;  
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
			          
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				cmd();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
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
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				cmdselecao();
				}
				break;
			case T__10:
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(69);
				cmdrepeticao();
				}
				break;
			case START_COMMENT:
				enterOuterAlt(_localctx, 6);
				{
				setState(70);
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
			setState(73);
			match(T__5);
			setState(74);
			match(AP);
			setState(75);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  atribuiVariavel(_readID);
			                        
			setState(77);
			match(FP);
			setState(78);
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
			setState(81);
			match(T__6);
			setState(82);
			match(AP);
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(83);
				match(TEXTO);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case ID:
				{
				setState(85);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                    _writeID = _input.LT(-1).getText();
					                    useVariavel(_writeID);
				                       
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(89);
			match(FP);
			setState(90);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
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
			setState(93);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                   
			setState(95);
			match(ATTR);
			 _exprContent = ""; 
			setState(97);
			expr();
			setState(98);
			match(POINT);

			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 atribuiVariavel(_exprID);
			               	 stack.peek().add(cmd);
			               	 verificaTipoAtribuicao(_exprID, _term);
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
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode SIGNEDNUMBER() { return getToken(IsiLangParser.SIGNEDNUMBER, 0); }
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
			setState(101);
			match(T__7);
			setState(102);
			match(AP);
			setState(103);
			match(ID);
			 op = null;
			                            type = -1;
			                            _exprDecision = _input.LT(-1).getText();
			                            useVariavel(_input.LT(-1).getText());
			                            _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
			setState(105);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText();
			                            op = _input.LT(-1).getText();
			                            if (op != null && !IsiOperator.isRelationalOperator(op)){
			                            	throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
			                            }
			                           
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(107);
				match(ID);
				 useVariavel(_input.LT(-1).getText());
				                            type = obtemTipoId(_input.LT(-1).getText());
				}
				break;
			case NUMBER:
				{
				setState(109);
				match(NUMBER);
				 type = IsiTypes.NUMBER;
				}
				break;
			case SIGNEDNUMBER:
				{
				setState(111);
				match(SIGNEDNUMBER);
				 type = IsiTypes.NUMBER;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprDecision += _input.LT(-1).getText(); 
			                                     _attribTerm = _input.LT(-1).getText();
			                                     _term = atualizaTipoTermo(_attribTerm, _term, type, op);
			                                     _term = null;
			                                     op = null;
			setState(116);
			match(FP);
			setState(117);
			match(T__8);
			setState(118);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120);
				cmd();
				}
				}
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
			setState(125);
			match(FCH);

			                       listaTrue = stack.pop();	
			                       listaFalse = new ArrayList<AbstractCommand>();
			                   	   CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			                    
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(127);
				match(T__9);
				setState(128);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(130);
					cmd();
					}
					}
					setState(133); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
				}
				setState(135);
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
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> SIGNEDNUMBER() { return getTokens(IsiLangParser.SIGNEDNUMBER); }
		public TerminalNode SIGNEDNUMBER(int i) {
			return getToken(IsiLangParser.SIGNEDNUMBER, i);
		}
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
		public List<TerminalNode> ATTR() { return getTokens(IsiLangParser.ATTR); }
		public TerminalNode ATTR(int i) {
			return getToken(IsiLangParser.ATTR, i);
		}
		public TerminalNode OP_INC_DEC() { return getToken(IsiLangParser.OP_INC_DEC, 0); }
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
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(T__10);
				setState(143);
				match(AP);
				setState(144);
				match(ID);
				  op = null;
				                                   type = -1;
				                                   _exprDecision = _input.LT(-1).getText();
				                                   useVariavel(_input.LT(-1).getText());
				                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
				setState(146);
				match(OPREL);
				 _exprDecision += _input.LT(-1).getText();
				                                   op = _input.LT(-1).getText();
				                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
				                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
				                                   } 
				                                  
				setState(154);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(148);
					match(ID);
					 useVariavel(_input.LT(-1).getText());
					                                   type = obtemTipoId(_input.LT(-1).getText()); 
					}
					break;
				case NUMBER:
					{
					setState(150);
					match(NUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(152);
					match(SIGNEDNUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 _exprDecision += _input.LT(-1).getText(); 
				                                            _attribTerm = _input.LT(-1).getText();
				                                            _term = atualizaTipoTermo(_attribTerm, _term, type, op);
				                                            _term = null;
				                                            op = null;  
				setState(157);
				match(FP);
				setState(158);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                      	  	stack.push(curThread);
				                   		  
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(160);
					cmd();
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
				setState(165);
				match(FCH);

				                        	commands = stack.pop();	
				                    	  

				                   			CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
				                   			stack.peek().add(cmd);
				                   		  
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(T__11);
				setState(170);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                       	 	stack.push(curThread);
				                   	   	  
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(172);
					cmd();
					}
					}
					setState(175); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
				setState(177);
				match(FCH);

				                         	commands = stack.pop();	
				                       	  
				setState(179);
				match(T__10);
				setState(180);
				match(AP);
				setState(181);
				match(ID);
				 op = null;
				                                   type = -1;
				                                   _exprDecision = _input.LT(-1).getText();
				                                   useVariavel(_input.LT(-1).getText());
				                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
				setState(183);
				match(OPREL);
				 _exprDecision += _input.LT(-1).getText();
				                                   op = _input.LT(-1).getText();
				                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
				                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
				                                   }  
				                                  
				setState(191);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(185);
					match(ID);
					 useVariavel(_input.LT(-1).getText());
					                                   type = obtemTipoId(_input.LT(-1).getText());
					}
					break;
				case NUMBER:
					{
					setState(187);
					match(NUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(189);
					match(SIGNEDNUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 _exprDecision += _input.LT(-1).getText(); 
				                                            _attribTerm = _input.LT(-1).getText();
				                                            _term = atualizaTipoTermo(_attribTerm, _term, type, op);
				                                            _term = null;
				                                            op = null;
				setState(194);
				match(FP);
				setState(195);
				match(POINT);

				                   	   	  CommandFaca cmd = new CommandFaca(_exprDecision, commands);
				                   		  stack.peek().add(cmd);
				                   	   
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(198);
				match(T__12);
				setState(199);
				match(AP);
				_exprAttrib = "";
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << ID))) != 0)) {
					{
					setState(225);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__3:
					case T__4:
						{
						{
						setState(201);
						tipo();
						setState(202);
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
									                              
									                           
									                            
						setState(204);
						match(ATTR);
						 _exprAttrib += _input.LT(-1).getText();
						setState(209);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(206);
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
							setState(208);
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
						setState(214);
						match(ID);
						 _varName = _input.LT(-1).getText();
															if (!symbolTable.exists(_varName)){
									                             throw new IsiSemanticException("Symbol "+_varName+" not declared");
									                        }
									                        else{
									                            _exprAttrib = _varName;
									                        }
													     
						}
						setState(217);
						match(ATTR);
						 _exprAttrib += _input.LT(-1).getText();
						setState(222);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ID:
							{
							setState(219);
							match(ID);
							 _varName = _input.LT(-1).getText();
																if (!symbolTable.exists(_varName)){
										                            throw new IsiSemanticException("Symbol "+_varName+" not declared");
										                        }
															 
							}
							break;
						case NUMBER:
							{
							setState(221);
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

				setState(229);
				match(POINT);
				_exprDecision = "";
				setState(241);
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
								                              else{
								                  	            _exprDecision += _varName;
								                              }
													   
					setState(233);
					match(OPREL);
					 _exprDecision += _input.LT(-1).getText(); 
					setState(238);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ID:
						{
						setState(235);
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
						setState(237);
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << START_COMMENT) | (1L << ID))) != 0) );
				setState(283);
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
			setState(292);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(289);
				termo();
				}
				break;
			case 2:
				{
				setState(290);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	                _exprContent += _attribTerm;
				              	       
				              	                 /* VerificaÃ§Ã£o de tipo*/
					               	             _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                               
				}
				break;
			}
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP || _la==SIGNEDNUMBER) {
				{
				setState(299);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP:
					{
					{
					setState(294);
					match(OP);
					 op = _input.LT(-1).getText();
						                    _exprContent += op;
					setState(296);
					termo();
					}
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(297);
					match(SIGNEDNUMBER);
					 _attribTerm = _input.LT(-1).getText();
					              	                _exprContent += _attribTerm;
					              	                op = "+";
					              	       
					              	                /* VerificaÃƒÂ§ÃƒÂ£o de tipo*/
						               	            _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					                              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(303);
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
			setState(318);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				match(ID);
				 _attribTerm = _input.LT(-1).getText();
				                   verificaID(_attribTerm);
					               _exprContent += _attribTerm;
					               useVariavel(_attribTerm);
					               /* Verifica se uma variÃƒÂ¡vel usada foi atribuÃƒÂ­da */
					               verificaAtribuicao(_attribTerm);
					               
					               /* VerificaÃƒÂ§ÃƒÂ£o de tipo*/
					               _term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
					               
				                 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(NUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	       _exprContent += _attribTerm;
				              	       
				              	       /* VerificaÃƒÂ§ÃƒÂ£o de tipo*/
					               	   _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                      
				}
				break;
			case SIGNEDNUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	             if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
				              	             	_attribTerm = _attribTerm.substring(1);
				              	             }
				              	             _exprContent += _attribTerm;
				              	             /* VerificaÃ§Ã£o de tipo*/
					               		     _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                            
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(310);
				match(TEXTO);
				 _attribTerm = _input.LT(-1).getText();
				              	      _exprContent += _attribTerm;
				              	       
				              	      /* VerificaÃ§Ã£o de tipo*/
					               	  _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.TEXT, op);
				                    
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(312);
				match(AP);
				_exprContent += _input.LT(-1).getText(); 
				setState(314);
				expr();
				setState(315);
				match(FP);
				 _exprContent += _input.LT(-1).getText(); 
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

	public static class ComentariosContext extends ParserRuleContext {
		public TerminalNode START_COMMENT() { return getToken(IsiLangParser.START_COMMENT, 0); }
		public TerminalNode END_COMMENT() { return getToken(IsiLangParser.END_COMMENT, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> TEXTO() { return getTokens(IsiLangParser.TEXTO); }
		public TerminalNode TEXTO(int i) {
			return getToken(IsiLangParser.TEXTO, i);
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
		enterRule(_localctx, 26, RULE_comentarios);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(START_COMMENT);
			setState(324); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(324);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(321);
					match(ID);

					                           _textComment = (_textComment == null ? "" : _textComment) + _input.LT(-1).getText() + " ";
					                           
					}
					break;
				case TEXTO:
					{
					setState(323);
					match(TEXTO);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID || _la==TEXTO );

				     	               CommandComentario cmd = new CommandComentario(_textComment);
				     	               stack.peek().add(cmd);
						            
			setState(329);
			match(END_COMMENT);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u014e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64"+
		"\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\6\6@\n\6\r\6\16\6A\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\5\7J\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\tZ\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"t\n\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13|\n\13\r\13\16\13}\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\6\13\u0086\n\13\r\13\16\13\u0087\3\13\3\13\3\13\5"+
		"\13\u008d\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u009d\n\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a4\n\f\r\f\16\f\u00a5\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00b0\n\f\r\f\16\f\u00b1\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c2\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d4\n\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e1\n\f\3\f\5\f\u00e4\n"+
		"\f\5\f\u00e6\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00f1\n\f\3\f"+
		"\5\f\u00f4\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0102"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010f\n\f\3\f\5\f"+
		"\u0112\n\f\5\f\u0114\n\f\3\f\3\f\3\f\3\f\6\f\u011a\n\f\r\f\16\f\u011b"+
		"\3\f\3\f\3\f\3\f\5\f\u0122\n\f\3\r\3\r\3\r\5\r\u0127\n\r\3\r\3\r\3\r\3"+
		"\r\3\r\7\r\u012e\n\r\f\r\16\r\u0131\13\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0141\n\16\3\17\3\17\3\17"+
		"\3\17\6\17\u0147\n\17\r\17\16\17\u0148\3\17\3\17\3\17\3\17\2\2\20\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\2\3\3\2\37 \2\u016b\2\36\3\2\2\2\4%\3"+
		"\2\2\2\6)\3\2\2\2\b;\3\2\2\2\n=\3\2\2\2\fI\3\2\2\2\16K\3\2\2\2\20S\3\2"+
		"\2\2\22_\3\2\2\2\24g\3\2\2\2\26\u0121\3\2\2\2\30\u0126\3\2\2\2\32\u0140"+
		"\3\2\2\2\34\u0142\3\2\2\2\36\37\7\3\2\2\37 \5\4\3\2 !\5\n\6\2!\"\7\4\2"+
		"\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6\4\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'("+
		"\3\2\2\2(\5\3\2\2\2)*\7\5\2\2*+\5\b\5\2+,\7\36\2\2,\62\b\4\1\2-.\7\32"+
		"\2\2./\7\36\2\2/\61\b\4\1\2\60-\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66\7\25\2\2\66\7\3\2\2\2\67"+
		"8\7\6\2\28<\b\5\1\29:\7\7\2\2:<\b\5\1\2;\67\3\2\2\2;9\3\2\2\2<\t\3\2\2"+
		"\2=?\b\6\1\2>@\5\f\7\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\13\3\2"+
		"\2\2CJ\5\16\b\2DJ\5\20\t\2EJ\5\22\n\2FJ\5\24\13\2GJ\5\26\f\2HJ\5\34\17"+
		"\2IC\3\2\2\2ID\3\2\2\2IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2J\r\3\2"+
		"\2\2KL\7\b\2\2LM\7\22\2\2MN\7\36\2\2NO\b\b\1\2OP\7\23\2\2PQ\7\25\2\2Q"+
		"R\b\b\1\2R\17\3\2\2\2ST\7\t\2\2TY\7\22\2\2UV\7\"\2\2VZ\b\t\1\2WX\7\36"+
		"\2\2XZ\b\t\1\2YU\3\2\2\2YW\3\2\2\2Z[\3\2\2\2[\\\7\23\2\2\\]\7\25\2\2]"+
		"^\b\t\1\2^\21\3\2\2\2_`\7\36\2\2`a\b\n\1\2ab\7\31\2\2bc\b\n\1\2cd\5\30"+
		"\r\2de\7\25\2\2ef\b\n\1\2f\23\3\2\2\2gh\7\n\2\2hi\7\22\2\2ij\7\36\2\2"+
		"jk\b\13\1\2kl\7\35\2\2ls\b\13\1\2mn\7\36\2\2nt\b\13\1\2op\7\37\2\2pt\b"+
		"\13\1\2qr\7 \2\2rt\b\13\1\2sm\3\2\2\2so\3\2\2\2sq\3\2\2\2tu\3\2\2\2uv"+
		"\b\13\1\2vw\7\23\2\2wx\7\13\2\2xy\7\33\2\2y{\b\13\1\2z|\5\f\7\2{z\3\2"+
		"\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\34\2\2\u0080"+
		"\u008c\b\13\1\2\u0081\u0082\7\f\2\2\u0082\u0083\7\33\2\2\u0083\u0085\b"+
		"\13\1\2\u0084\u0086\5\f\7\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7\34"+
		"\2\2\u008a\u008b\b\13\1\2\u008b\u008d\3\2\2\2\u008c\u0081\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\b\13\1\2\u008f\25\3\2\2"+
		"\2\u0090\u0091\7\r\2\2\u0091\u0092\7\22\2\2\u0092\u0093\7\36\2\2\u0093"+
		"\u0094\b\f\1\2\u0094\u0095\7\35\2\2\u0095\u009c\b\f\1\2\u0096\u0097\7"+
		"\36\2\2\u0097\u009d\b\f\1\2\u0098\u0099\7\37\2\2\u0099\u009d\b\f\1\2\u009a"+
		"\u009b\7 \2\2\u009b\u009d\b\f\1\2\u009c\u0096\3\2\2\2\u009c\u0098\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\f\1\2\u009f"+
		"\u00a0\7\23\2\2\u00a0\u00a1\7\33\2\2\u00a1\u00a3\b\f\1\2\u00a2\u00a4\5"+
		"\f\7\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\7\34\2\2\u00a8\u00a9\b"+
		"\f\1\2\u00a9\u00aa\b\f\1\2\u00aa\u0122\3\2\2\2\u00ab\u00ac\7\16\2\2\u00ac"+
		"\u00ad\7\33\2\2\u00ad\u00af\b\f\1\2\u00ae\u00b0\5\f\7\2\u00af\u00ae\3"+
		"\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b4\7\34\2\2\u00b4\u00b5\b\f\1\2\u00b5\u00b6\7"+
		"\r\2\2\u00b6\u00b7\7\22\2\2\u00b7\u00b8\7\36\2\2\u00b8\u00b9\b\f\1\2\u00b9"+
		"\u00ba\7\35\2\2\u00ba\u00c1\b\f\1\2\u00bb\u00bc\7\36\2\2\u00bc\u00c2\b"+
		"\f\1\2\u00bd\u00be\7\37\2\2\u00be\u00c2\b\f\1\2\u00bf\u00c0\7 \2\2\u00c0"+
		"\u00c2\b\f\1\2\u00c1\u00bb\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\b\f\1\2\u00c4\u00c5\7\23\2\2\u00c5"+
		"\u00c6\7\25\2\2\u00c6\u00c7\b\f\1\2\u00c7\u0122\3\2\2\2\u00c8\u00c9\7"+
		"\17\2\2\u00c9\u00ca\7\22\2\2\u00ca\u00e5\b\f\1\2\u00cb\u00cc\5\b\5\2\u00cc"+
		"\u00cd\7\36\2\2\u00cd\u00ce\b\f\1\2\u00ce\u00cf\7\31\2\2\u00cf\u00d3\b"+
		"\f\1\2\u00d0\u00d1\7\36\2\2\u00d1\u00d4\b\f\1\2\u00d2\u00d4\t\2\2\2\u00d3"+
		"\u00d0\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b\f"+
		"\1\2\u00d6\u00d7\b\f\1\2\u00d7\u00e4\3\2\2\2\u00d8\u00d9\7\36\2\2\u00d9"+
		"\u00da\b\f\1\2\u00da\u00db\3\2\2\2\u00db\u00dc\7\31\2\2\u00dc\u00e0\b"+
		"\f\1\2\u00dd\u00de\7\36\2\2\u00de\u00e1\b\f\1\2\u00df\u00e1\7\37\2\2\u00e0"+
		"\u00dd\3\2\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\b\f"+
		"\1\2\u00e3\u00cb\3\2\2\2\u00e3\u00d8\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7\25"+
		"\2\2\u00e8\u00f3\b\f\1\2\u00e9\u00ea\7\36\2\2\u00ea\u00eb\b\f\1\2\u00eb"+
		"\u00ec\7\35\2\2\u00ec\u00f0\b\f\1\2\u00ed\u00ee\7\36\2\2\u00ee\u00f1\b"+
		"\f\1\2\u00ef\u00f1\t\2\2\2\u00f0\u00ed\3\2\2\2\u00f0\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f4\b\f\1\2\u00f3\u00e9\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b\f\1\2\u00f6\u0113\7\25\2\2\u00f7"+
		"\u00f8\7\36\2\2\u00f8\u00f9\b\f\1\2\u00f9\u0111\b\f\1\2\u00fa\u00fb\7"+
		"\27\2\2\u00fb\u0112\b\f\1\2\u00fc\u00fd\7\30\2\2\u00fd\u0101\b\f\1\2\u00fe"+
		"\u00ff\7\36\2\2\u00ff\u0102\b\f\1\2\u0100\u0102\t\2\2\2\u0101\u00fe\3"+
		"\2\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0112\b\f\1\2\u0104"+
		"\u0105\7\31\2\2\u0105\u0106\b\f\1\2\u0106\u0107\7\36\2\2\u0107\u0108\b"+
		"\f\1\2\u0108\u0109\b\f\1\2\u0109\u010a\7\26\2\2\u010a\u010e\b\f\1\2\u010b"+
		"\u010c\7\36\2\2\u010c\u010f\b\f\1\2\u010d\u010f\t\2\2\2\u010e\u010b\3"+
		"\2\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\b\f\1\2\u0111"+
		"\u00fa\3\2\2\2\u0111\u00fc\3\2\2\2\u0111\u0104\3\2\2\2\u0112\u0114\3\2"+
		"\2\2\u0113\u00f7\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0116\7\23\2\2\u0116\u0117\7\33\2\2\u0117\u0119\b\f\1\2\u0118\u011a\5"+
		"\f\7\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b"+
		"\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\7\34\2\2\u011e\u011f\b"+
		"\f\1\2\u011f\u0120\b\f\1\2\u0120\u0122\3\2\2\2\u0121\u0090\3\2\2\2\u0121"+
		"\u00ab\3\2\2\2\u0121\u00c8\3\2\2\2\u0122\27\3\2\2\2\u0123\u0127\5\32\16"+
		"\2\u0124\u0125\7 \2\2\u0125\u0127\b\r\1\2\u0126\u0123\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0127\u012f\3\2\2\2\u0128\u0129\7\26\2\2\u0129\u012a\b\r\1\2"+
		"\u012a\u012e\5\32\16\2\u012b\u012c\7 \2\2\u012c\u012e\b\r\1\2\u012d\u0128"+
		"\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f"+
		"\u0130\3\2\2\2\u0130\31\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7\36\2"+
		"\2\u0133\u0141\b\16\1\2\u0134\u0135\7\37\2\2\u0135\u0141\b\16\1\2\u0136"+
		"\u0137\7 \2\2\u0137\u0141\b\16\1\2\u0138\u0139\7\"\2\2\u0139\u0141\b\16"+
		"\1\2\u013a\u013b\7\22\2\2\u013b\u013c\b\16\1\2\u013c\u013d\5\30\r\2\u013d"+
		"\u013e\7\23\2\2\u013e\u013f\b\16\1\2\u013f\u0141\3\2\2\2\u0140\u0132\3"+
		"\2\2\2\u0140\u0134\3\2\2\2\u0140\u0136\3\2\2\2\u0140\u0138\3\2\2\2\u0140"+
		"\u013a\3\2\2\2\u0141\33\3\2\2\2\u0142\u0146\7\20\2\2\u0143\u0144\7\36"+
		"\2\2\u0144\u0147\b\17\1\2\u0145\u0147\7\"\2\2\u0146\u0143\3\2\2\2\u0146"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2"+
		"\2\2\u0149\u014a\3\2\2\2\u014a\u014b\b\17\1\2\u014b\u014c\7\21\2\2\u014c"+
		"\35\3\2\2\2\"\'\62;AIYs}\u0087\u008c\u009c\u00a5\u00b1\u00c1\u00d3\u00e0"+
		"\u00e3\u00e5\u00f0\u00f3\u0101\u010e\u0111\u0113\u011b\u0121\u0126\u012d"+
		"\u012f\u0140\u0146\u0148";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}