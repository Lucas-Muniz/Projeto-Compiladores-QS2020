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
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, POINT=16, OP=17, ATTR=18, 
		VIR=19, ACH=20, FCH=21, OPREL=22, ID=23, NUMBER=24, SIGNEDNUMBER=25, SIGN=26, 
		TEXTO=27, WS=28;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'faca'", 
		"'('", "')'", "';'", "'.'", null, "':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AP", "FP", "SC", "POINT", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
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
		private String _exprDecision;
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
			setState(26);
			match(T__0);
			setState(27);
			decl();
			setState(28);
			bloco();
			setState(29);
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
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				declaravar();
				}
				}
				setState(35); 
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
			setState(37);
			match(T__2);
			setState(38);
			tipo();
			setState(39);
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
			                                 
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(41);
				match(VIR);
				setState(42);
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
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
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
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__3);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
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
			          
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				cmd();
				}
				}
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				cmdselecao();
				}
				break;
			case T__10:
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				cmdrepeticao();
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
			setState(70);
			match(T__5);
			setState(71);
			match(AP);
			setState(72);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  atribuiVariavel(_readID);
			                        
			setState(74);
			match(FP);
			setState(75);
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
			setState(78);
			match(T__6);
			setState(79);
			match(AP);
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(80);
				match(TEXTO);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case ID:
				{
				setState(82);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                    _writeID = _input.LT(-1).getText();
					                    useVariavel(_writeID);
				                       
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(86);
			match(FP);
			setState(87);
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
			setState(90);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                   
			setState(92);
			match(ATTR);
			 _exprContent = ""; 
			setState(94);
			expr();
			setState(95);
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
			setState(98);
			match(T__7);
			setState(99);
			match(AP);
			setState(100);
			match(ID);
			 op = null;
			                            type = -1;
			                            _exprDecision = _input.LT(-1).getText();
			                            useVariavel(_input.LT(-1).getText());
			                            _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
			setState(102);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText();
			                            op = _input.LT(-1).getText();
			                            if (op != null && !IsiOperator.isRelationalOperator(op)){
			                            	throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
			                            }
			                           
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(104);
				match(ID);
				 useVariavel(_input.LT(-1).getText());
				                            type = obtemTipoId(_input.LT(-1).getText());
				}
				break;
			case NUMBER:
				{
				setState(106);
				match(NUMBER);
				 type = IsiTypes.NUMBER;
				}
				break;
			case SIGNEDNUMBER:
				{
				setState(108);
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
			setState(113);
			match(FP);
			setState(114);
			match(T__8);
			setState(115);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				cmd();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(122);
			match(FCH);

			                       listaTrue = stack.pop();	
			                    
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(124);
				match(T__9);
				setState(125);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(127);
					cmd();
					}
					}
					setState(130); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(132);
				match(FCH);

				                   		listaFalse = stack.pop();
				                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                   		stack.peek().add(cmd);
				                   	
				}
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
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode SIGNEDNUMBER() { return getToken(IsiLangParser.SIGNEDNUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode POINT() { return getToken(IsiLangParser.POINT, 0); }
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
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(T__10);
				setState(138);
				match(AP);
				setState(139);
				match(ID);
				  op = null;
				                                   type = -1;
				                                   _exprDecision = _input.LT(-1).getText();
				                                   useVariavel(_input.LT(-1).getText());
				                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
				setState(141);
				match(OPREL);
				 _exprDecision += _input.LT(-1).getText();
				                                   op = _input.LT(-1).getText();
				                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
				                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
				                                   } 
				                                  
				setState(149);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(143);
					match(ID);
					 useVariavel(_input.LT(-1).getText());
					                                   type = obtemTipoId(_input.LT(-1).getText()); 
					}
					break;
				case NUMBER:
					{
					setState(145);
					match(NUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(147);
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
				setState(152);
				match(FP);
				setState(153);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                      	  	stack.push(curThread);
				                   		  
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(155);
					cmd();
					}
					}
					setState(158); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(160);
				match(FCH);

				                        	commands = stack.pop();	
				                    	  

				                   			CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
				                   			stack.peek().add(cmd);
				                   		  
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(T__11);
				setState(165);
				match(ACH);
				 curThread = new ArrayList<AbstractCommand>(); 
				                       	 	stack.push(curThread);
				                   	   	  
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(167);
					cmd();
					}
					}
					setState(170); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(172);
				match(FCH);

				                         	commands = stack.pop();	
				                       	  
				setState(174);
				match(T__10);
				setState(175);
				match(AP);
				setState(176);
				match(ID);
				 op = null;
				                                   type = -1;
				                                   _exprDecision = _input.LT(-1).getText();
				                                   useVariavel(_input.LT(-1).getText());
				                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); 
				setState(178);
				match(OPREL);
				 _exprDecision += _input.LT(-1).getText();
				                                   op = _input.LT(-1).getText();
				                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
				                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
				                                   }  
				                                  
				setState(186);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(180);
					match(ID);
					 useVariavel(_input.LT(-1).getText());
					                                   type = obtemTipoId(_input.LT(-1).getText());
					}
					break;
				case NUMBER:
					{
					setState(182);
					match(NUMBER);
					 type = IsiTypes.NUMBER;
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(184);
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
				setState(189);
				match(FP);
				setState(190);
				match(POINT);

				                   	   	  CommandFaca cmd = new CommandFaca(_exprDecision, commands);
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
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(195);
				termo();
				}
				break;
			case 2:
				{
				setState(196);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	                _exprContent += _attribTerm;
				              	       
				              	                 /* Verificação de tipo*/
					               	             _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                               
				}
				break;
			}
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP || _la==SIGNEDNUMBER) {
				{
				setState(205);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP:
					{
					{
					setState(200);
					match(OP);
					 op = _input.LT(-1).getText();
						                    _exprContent += op;
					setState(202);
					termo();
					}
					}
					break;
				case SIGNEDNUMBER:
					{
					setState(203);
					match(SIGNEDNUMBER);
					 _attribTerm = _input.LT(-1).getText();
					              	                _exprContent += _attribTerm;
					              	                op = "+";
					              	       
					              	                /* Verificação de tipo*/
						               	            _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
					                              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(209);
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
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				match(ID);
				 _attribTerm = _input.LT(-1).getText();
				                   verificaID(_attribTerm);
					               _exprContent += _attribTerm;
					               useVariavel(_attribTerm);
					               /* Verifica se uma variável usada foi atribuída */
					               verificaAtribuicao(_attribTerm);
					               
					               /* Verificação de tipo*/
					               _term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
					               
				                 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(NUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	       _exprContent += _attribTerm;
				              	       
				              	       /* Verificação de tipo*/
					               	   _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                      
				}
				break;
			case SIGNEDNUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				match(SIGNEDNUMBER);
				 _attribTerm = _input.LT(-1).getText();
				              	             if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
				              	             	_attribTerm = _attribTerm.substring(1);
				              	             }
				              	             _exprContent += _attribTerm;
				              	             /* Verificação de tipo*/
					               		     _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
				                            
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
				match(TEXTO);
				 _attribTerm = _input.LT(-1).getText();
				              	      _exprContent += _attribTerm;
				              	       
				              	      /* Verificação de tipo*/
					               	  _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.TEXT, op);
				                    
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(218);
				match(AP);
				_exprContent += _input.LT(-1).getText(); 
				setState(220);
				expr();
				setState(221);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00e5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3$\n\3\r"+
		"\3\16\3%\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\5\5:\n\5\3\6\3\6\6\6>\n\6\r\6\16\6?\3\7\3\7\3\7\3\7\3"+
		"\7\5\7G\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\tW\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13q\n\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\6\13y\n\13\r\13\16\13z\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u0083\n\13\r\13\16\13\u0084\3\13\3\13\3\13\5\13\u008a\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0098\n\f\3\f\3\f\3\f\3\f"+
		"\3\f\6\f\u009f\n\f\r\f\16\f\u00a0\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f"+
		"\u00ab\n\f\r\f\16\f\u00ac\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u00bd\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c4\n\f\3\r\3\r\3"+
		"\r\5\r\u00c9\n\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d0\n\r\f\r\16\r\u00d3\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16\u00e3\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u00f3"+
		"\2\34\3\2\2\2\4#\3\2\2\2\6\'\3\2\2\2\b9\3\2\2\2\n;\3\2\2\2\fF\3\2\2\2"+
		"\16H\3\2\2\2\20P\3\2\2\2\22\\\3\2\2\2\24d\3\2\2\2\26\u00c3\3\2\2\2\30"+
		"\u00c8\3\2\2\2\32\u00e2\3\2\2\2\34\35\7\3\2\2\35\36\5\4\3\2\36\37\5\n"+
		"\6\2\37 \7\4\2\2 !\b\2\1\2!\3\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2"+
		"%#\3\2\2\2%&\3\2\2\2&\5\3\2\2\2\'(\7\5\2\2()\5\b\5\2)*\7\31\2\2*\60\b"+
		"\4\1\2+,\7\25\2\2,-\7\31\2\2-/\b\4\1\2.+\3\2\2\2/\62\3\2\2\2\60.\3\2\2"+
		"\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7\22\2\2\64\7\3\2\2"+
		"\2\65\66\7\6\2\2\66:\b\5\1\2\678\7\7\2\28:\b\5\1\29\65\3\2\2\29\67\3\2"+
		"\2\2:\t\3\2\2\2;=\b\6\1\2<>\5\f\7\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3"+
		"\2\2\2@\13\3\2\2\2AG\5\16\b\2BG\5\20\t\2CG\5\22\n\2DG\5\24\13\2EG\5\26"+
		"\f\2FA\3\2\2\2FB\3\2\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\r\3\2\2\2HI\7"+
		"\b\2\2IJ\7\17\2\2JK\7\31\2\2KL\b\b\1\2LM\7\20\2\2MN\7\22\2\2NO\b\b\1\2"+
		"O\17\3\2\2\2PQ\7\t\2\2QV\7\17\2\2RS\7\35\2\2SW\b\t\1\2TU\7\31\2\2UW\b"+
		"\t\1\2VR\3\2\2\2VT\3\2\2\2WX\3\2\2\2XY\7\20\2\2YZ\7\22\2\2Z[\b\t\1\2["+
		"\21\3\2\2\2\\]\7\31\2\2]^\b\n\1\2^_\7\24\2\2_`\b\n\1\2`a\5\30\r\2ab\7"+
		"\22\2\2bc\b\n\1\2c\23\3\2\2\2de\7\n\2\2ef\7\17\2\2fg\7\31\2\2gh\b\13\1"+
		"\2hi\7\30\2\2ip\b\13\1\2jk\7\31\2\2kq\b\13\1\2lm\7\32\2\2mq\b\13\1\2n"+
		"o\7\33\2\2oq\b\13\1\2pj\3\2\2\2pl\3\2\2\2pn\3\2\2\2qr\3\2\2\2rs\b\13\1"+
		"\2st\7\20\2\2tu\7\13\2\2uv\7\26\2\2vx\b\13\1\2wy\5\f\7\2xw\3\2\2\2yz\3"+
		"\2\2\2zx\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\27\2\2}\u0089\b\13\1\2~\177\7"+
		"\f\2\2\177\u0080\7\26\2\2\u0080\u0082\b\13\1\2\u0081\u0083\5\f\7\2\u0082"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\27\2\2\u0087\u0088\b\13\1\2\u0088"+
		"\u008a\3\2\2\2\u0089~\3\2\2\2\u0089\u008a\3\2\2\2\u008a\25\3\2\2\2\u008b"+
		"\u008c\7\r\2\2\u008c\u008d\7\17\2\2\u008d\u008e\7\31\2\2\u008e\u008f\b"+
		"\f\1\2\u008f\u0090\7\30\2\2\u0090\u0097\b\f\1\2\u0091\u0092\7\31\2\2\u0092"+
		"\u0098\b\f\1\2\u0093\u0094\7\32\2\2\u0094\u0098\b\f\1\2\u0095\u0096\7"+
		"\33\2\2\u0096\u0098\b\f\1\2\u0097\u0091\3\2\2\2\u0097\u0093\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\b\f\1\2\u009a\u009b\7\20"+
		"\2\2\u009b\u009c\7\26\2\2\u009c\u009e\b\f\1\2\u009d\u009f\5\f\7\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7\27\2\2\u00a3\u00a4\b\f\1\2\u00a4"+
		"\u00a5\b\f\1\2\u00a5\u00c4\3\2\2\2\u00a6\u00a7\7\16\2\2\u00a7\u00a8\7"+
		"\26\2\2\u00a8\u00aa\b\f\1\2\u00a9\u00ab\5\f\7\2\u00aa\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\7\27\2\2\u00af\u00b0\b\f\1\2\u00b0\u00b1\7\r\2\2\u00b1"+
		"\u00b2\7\17\2\2\u00b2\u00b3\7\31\2\2\u00b3\u00b4\b\f\1\2\u00b4\u00b5\7"+
		"\30\2\2\u00b5\u00bc\b\f\1\2\u00b6\u00b7\7\31\2\2\u00b7\u00bd\b\f\1\2\u00b8"+
		"\u00b9\7\32\2\2\u00b9\u00bd\b\f\1\2\u00ba\u00bb\7\33\2\2\u00bb\u00bd\b"+
		"\f\1\2\u00bc\u00b6\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\b\f\1\2\u00bf\u00c0\7\20\2\2\u00c0\u00c1\7"+
		"\22\2\2\u00c1\u00c2\b\f\1\2\u00c2\u00c4\3\2\2\2\u00c3\u008b\3\2\2\2\u00c3"+
		"\u00a6\3\2\2\2\u00c4\27\3\2\2\2\u00c5\u00c9\5\32\16\2\u00c6\u00c7\7\33"+
		"\2\2\u00c7\u00c9\b\r\1\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00d1\3\2\2\2\u00ca\u00cb\7\23\2\2\u00cb\u00cc\b\r\1\2\u00cc\u00d0\5"+
		"\32\16\2\u00cd\u00ce\7\33\2\2\u00ce\u00d0\b\r\1\2\u00cf\u00ca\3\2\2\2"+
		"\u00cf\u00cd\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\31\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d5\7\31\2\2\u00d5"+
		"\u00e3\b\16\1\2\u00d6\u00d7\7\32\2\2\u00d7\u00e3\b\16\1\2\u00d8\u00d9"+
		"\7\33\2\2\u00d9\u00e3\b\16\1\2\u00da\u00db\7\35\2\2\u00db\u00e3\b\16\1"+
		"\2\u00dc\u00dd\7\17\2\2\u00dd\u00de\b\16\1\2\u00de\u00df\5\30\r\2\u00df"+
		"\u00e0\7\20\2\2\u00e0\u00e1\b\16\1\2\u00e1\u00e3\3\2\2\2\u00e2\u00d4\3"+
		"\2\2\2\u00e2\u00d6\3\2\2\2\u00e2\u00d8\3\2\2\2\u00e2\u00da\3\2\2\2\u00e2"+
		"\u00dc\3\2\2\2\u00e3\33\3\2\2\2\25%\609?FVpz\u0084\u0089\u0097\u00a0\u00ac"+
		"\u00bc\u00c3\u00c8\u00cf\u00d1\u00e2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}