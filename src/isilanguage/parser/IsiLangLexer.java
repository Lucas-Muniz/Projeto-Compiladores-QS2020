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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "POINTS", "BOOLEANOUNARIO", "BOOLEANOBINARIO", "BOOL", "AP", 
		"FP", "SC", "POINT", "OP", "OP_INC_DEC", "OP_INC_DEC_EQ", "ATTR", "VIR", 
		"ACH", "FCH", "OPREL", "ID", "NUMBER", "SIGNEDNUMBER", "SIGN", "TEXTO", 
		"COMENTARIO", "WS"
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
					throw new IsiSemanticException("Symbol "+id+" is used but not attributed");
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u0150\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\5"+
		"\26\u00d7\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u00e8\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\35\3\35\5\35\u00f8\n\35\3\36\3\36\3\36\3\36"+
		"\5\36\u00fe\n\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\5#\u0112\n#\3$\3$\7$\u0116\n$\f$\16$\u0119\13$\3%\6%\u011c\n"+
		"%\r%\16%\u011d\3%\3%\6%\u0122\n%\r%\16%\u0123\5%\u0126\n%\3&\3&\6&\u012a"+
		"\n&\r&\16&\u012b\3&\3&\6&\u0130\n&\r&\16&\u0131\5&\u0134\n&\3\'\3\'\3"+
		"(\3(\7(\u013a\n(\f(\16(\u013d\13(\3(\3(\3)\3)\3)\3)\7)\u0145\n)\f)\16"+
		")\u0148\13)\3)\3)\3)\3*\3*\3*\3*\3\u0146\2+\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"\3\2\f\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\4\2--//\4\2$$"+
		"\u201e\u201e\7\2\f\f\"\"\62;C\\c|\4\2$$\u201f\u201f\5\2\13\f\17\17\"\""+
		"\2\u0160\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\3U\3\2\2\2\5^\3\2\2\2\7g\3\2\2\2\to\3\2\2\2\13v\3\2\2\2\r|\3"+
		"\2\2\2\17\u0085\3\2\2\2\21\u008a\3\2\2\2\23\u0092\3\2\2\2\25\u0095\3\2"+
		"\2\2\27\u009b\3\2\2\2\31\u00a1\3\2\2\2\33\u00aa\3\2\2\2\35\u00af\3\2\2"+
		"\2\37\u00b4\3\2\2\2!\u00bc\3\2\2\2#\u00c1\3\2\2\2%\u00c6\3\2\2\2\'\u00cd"+
		"\3\2\2\2)\u00cf\3\2\2\2+\u00d6\3\2\2\2-\u00e7\3\2\2\2/\u00e9\3\2\2\2\61"+
		"\u00eb\3\2\2\2\63\u00ed\3\2\2\2\65\u00ef\3\2\2\2\67\u00f1\3\2\2\29\u00f7"+
		"\3\2\2\2;\u00fd\3\2\2\2=\u00ff\3\2\2\2?\u0102\3\2\2\2A\u0104\3\2\2\2C"+
		"\u0106\3\2\2\2E\u0111\3\2\2\2G\u0113\3\2\2\2I\u011b\3\2\2\2K\u0127\3\2"+
		"\2\2M\u0135\3\2\2\2O\u0137\3\2\2\2Q\u0140\3\2\2\2S\u014c\3\2\2\2UV\7r"+
		"\2\2VW\7t\2\2WX\7q\2\2XY\7i\2\2YZ\7t\2\2Z[\7c\2\2[\\\7o\2\2\\]\7c\2\2"+
		"]\4\3\2\2\2^_\7h\2\2_`\7k\2\2`a\7o\2\2ab\7r\2\2bc\7t\2\2cd\7q\2\2de\7"+
		"i\2\2ef\7\60\2\2f\6\3\2\2\2gh\7f\2\2hi\7g\2\2ij\7e\2\2jk\7n\2\2kl\7c\2"+
		"\2lm\7t\2\2mn\7g\2\2n\b\3\2\2\2op\7p\2\2pq\7w\2\2qr\7o\2\2rs\7g\2\2st"+
		"\7t\2\2tu\7q\2\2u\n\3\2\2\2vw\7v\2\2wx\7g\2\2xy\7z\2\2yz\7v\2\2z{\7q\2"+
		"\2{\f\3\2\2\2|}\7d\2\2}~\7q\2\2~\177\7q\2\2\177\u0080\7n\2\2\u0080\u0081"+
		"\7g\2\2\u0081\u0082\7c\2\2\u0082\u0083\7p\2\2\u0083\u0084\7q\2\2\u0084"+
		"\16\3\2\2\2\u0085\u0086\7n\2\2\u0086\u0087\7g\2\2\u0087\u0088\7k\2\2\u0088"+
		"\u0089\7c\2\2\u0089\20\3\2\2\2\u008a\u008b\7g\2\2\u008b\u008c\7u\2\2\u008c"+
		"\u008d\7e\2\2\u008d\u008e\7t\2\2\u008e\u008f\7g\2\2\u008f\u0090\7x\2\2"+
		"\u0090\u0091\7c\2\2\u0091\22\3\2\2\2\u0092\u0093\7u\2\2\u0093\u0094\7"+
		"g\2\2\u0094\24\3\2\2\2\u0095\u0096\7g\2\2\u0096\u0097\7p\2\2\u0097\u0098"+
		"\7v\2\2\u0098\u0099\7c\2\2\u0099\u009a\7q\2\2\u009a\26\3\2\2\2\u009b\u009c"+
		"\7u\2\2\u009c\u009d\7g\2\2\u009d\u009e\7p\2\2\u009e\u009f\7c\2\2\u009f"+
		"\u00a0\7q\2\2\u00a0\30\3\2\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3"+
		"\u00a4\7s\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7p\2\2"+
		"\u00a7\u00a8\7v\2\2\u00a8\u00a9\7q\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7"+
		"h\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7e\2\2\u00ad\u00ae\7c\2\2\u00ae\34"+
		"\3\2\2\2\u00af\u00b0\7r\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7t\2\2\u00b2"+
		"\u00b3\7c\2\2\u00b3\36\3\2\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7u\2\2\u00b6"+
		"\u00b7\7e\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7n\2\2\u00b9\u00ba\7j\2\2"+
		"\u00ba\u00bb\7c\2\2\u00bb \3\2\2\2\u00bc\u00bd\7e\2\2\u00bd\u00be\7c\2"+
		"\2\u00be\u00bf\7u\2\2\u00bf\u00c0\7q\2\2\u00c0\"\3\2\2\2\u00c1\u00c2\7"+
		"r\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7g\2\2\u00c5$"+
		"\3\2\2\2\u00c6\u00c7\7r\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7f\2\2\u00c9"+
		"\u00ca\7t\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc\7q\2\2\u00cc&\3\2\2\2\u00cd"+
		"\u00ce\7<\2\2\u00ce(\3\2\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7c\2\2\u00d1"+
		"\u00d2\7q\2\2\u00d2*\3\2\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d7\7w\2\2\u00d5"+
		"\u00d7\7g\2\2\u00d6\u00d3\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7,\3\2\2\2\u00d8"+
		"\u00d9\7x\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7f\2\2"+
		"\u00dc\u00dd\7c\2\2\u00dd\u00de\7f\2\2\u00de\u00df\7g\2\2\u00df\u00e0"+
		"\7k\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e8\7q\2\2\u00e2\u00e3\7h\2\2\u00e3"+
		"\u00e4\7c\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e8\7q\2\2"+
		"\u00e7\u00d8\3\2\2\2\u00e7\u00e2\3\2\2\2\u00e8.\3\2\2\2\u00e9\u00ea\7"+
		"*\2\2\u00ea\60\3\2\2\2\u00eb\u00ec\7+\2\2\u00ec\62\3\2\2\2\u00ed\u00ee"+
		"\7=\2\2\u00ee\64\3\2\2\2\u00ef\u00f0\7\60\2\2\u00f0\66\3\2\2\2\u00f1\u00f2"+
		"\t\2\2\2\u00f28\3\2\2\2\u00f3\u00f4\7-\2\2\u00f4\u00f8\7-\2\2\u00f5\u00f6"+
		"\7/\2\2\u00f6\u00f8\7/\2\2\u00f7\u00f3\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8"+
		":\3\2\2\2\u00f9\u00fa\7-\2\2\u00fa\u00fe\7?\2\2\u00fb\u00fc\7/\2\2\u00fc"+
		"\u00fe\7?\2\2\u00fd\u00f9\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe<\3\2\2\2\u00ff"+
		"\u0100\7<\2\2\u0100\u0101\7?\2\2\u0101>\3\2\2\2\u0102\u0103\7.\2\2\u0103"+
		"@\3\2\2\2\u0104\u0105\7}\2\2\u0105B\3\2\2\2\u0106\u0107\7\177\2\2\u0107"+
		"D\3\2\2\2\u0108\u0112\t\3\2\2\u0109\u010a\7@\2\2\u010a\u0112\7?\2\2\u010b"+
		"\u010c\7>\2\2\u010c\u0112\7?\2\2\u010d\u010e\7?\2\2\u010e\u0112\7?\2\2"+
		"\u010f\u0110\7#\2\2\u0110\u0112\7?\2\2\u0111\u0108\3\2\2\2\u0111\u0109"+
		"\3\2\2\2\u0111\u010b\3\2\2\2\u0111\u010d\3\2\2\2\u0111\u010f\3\2\2\2\u0112"+
		"F\3\2\2\2\u0113\u0117\t\4\2\2\u0114\u0116\t\5\2\2\u0115\u0114\3\2\2\2"+
		"\u0116\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118H\3"+
		"\2\2\2\u0119\u0117\3\2\2\2\u011a\u011c\t\6\2\2\u011b\u011a\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0125\3\2"+
		"\2\2\u011f\u0121\7\60\2\2\u0120\u0122\t\6\2\2\u0121\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2"+
		"\2\2\u0125\u011f\3\2\2\2\u0125\u0126\3\2\2\2\u0126J\3\2\2\2\u0127\u0129"+
		"\t\7\2\2\u0128\u012a\t\6\2\2\u0129\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u0133\3\2\2\2\u012d\u012f\7\60"+
		"\2\2\u012e\u0130\t\6\2\2\u012f\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u012d\3\2"+
		"\2\2\u0133\u0134\3\2\2\2\u0134L\3\2\2\2\u0135\u0136\t\7\2\2\u0136N\3\2"+
		"\2\2\u0137\u013b\t\b\2\2\u0138\u013a\t\t\2\2\u0139\u0138\3\2\2\2\u013a"+
		"\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013e\u013f\t\n\2\2\u013fP\3\2\2\2\u0140\u0141"+
		"\7\61\2\2\u0141\u0142\7,\2\2\u0142\u0146\3\2\2\2\u0143\u0145\13\2\2\2"+
		"\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0147\3\2\2\2\u0146\u0144"+
		"\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014a\7,\2\2\u014a"+
		"\u014b\7\61\2\2\u014bR\3\2\2\2\u014c\u014d\t\13\2\2\u014d\u014e\3\2\2"+
		"\2\u014e\u014f\b*\2\2\u014fT\3\2\2\2\23\2\u00d6\u00e7\u00f7\u00fd\u0111"+
		"\u0115\u0117\u011d\u0123\u0125\u012b\u0131\u0133\u0139\u013b\u0146\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}