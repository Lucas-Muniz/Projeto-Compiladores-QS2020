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
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, POINT=17, 
		OP=18, INC=19, ATTR=20, VIR=21, ACH=22, FCH=23, OPREL=24, ID=25, NUMBER=26, 
		SIGNEDNUMBER=27, SIGN=28, TEXTO=29, WS=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "POINT", "OP", "INC", 
		"ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "SIGNEDNUMBER", 
		"SIGN", "TEXTO", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'faca'", 
		"'para'", "'('", "')'", "';'", "'.'", null, "'++'", "':='", "','", "'{'", 
		"'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "AP", "FP", "SC", "POINT", "OP", "INC", "ATTR", "VIR", "ACH", 
		"FCH", "OPREL", "ID", "NUMBER", "SIGNEDNUMBER", "SIGN", "TEXTO", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00f0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u00b5\n\31\3\32\3\32\7\32\u00b9\n\32\f\32\16\32\u00bc"+
		"\13\32\3\33\6\33\u00bf\n\33\r\33\16\33\u00c0\3\33\3\33\6\33\u00c5\n\33"+
		"\r\33\16\33\u00c6\5\33\u00c9\n\33\3\34\3\34\6\34\u00cd\n\34\r\34\16\34"+
		"\u00ce\3\34\3\34\6\34\u00d3\n\34\r\34\16\34\u00d4\5\34\u00d7\n\34\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\5\36\u00df\n\36\3\36\7\36\u00e2\n\36\f\36\16"+
		"\36\u00e5\13\36\3\36\3\36\3\36\3\36\5\36\u00eb\n\36\3\37\3\37\3\37\3\37"+
		"\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= \3\2\n\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\4\2--//"+
		"\7\2\f\f\"\"\62;C\\c|\5\2\13\f\17\17\"\"\2\u00fd\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3"+
		"\2\2\2\5H\3\2\2\2\7Q\3\2\2\2\tY\3\2\2\2\13`\3\2\2\2\rf\3\2\2\2\17k\3\2"+
		"\2\2\21s\3\2\2\2\23v\3\2\2\2\25|\3\2\2\2\27\u0082\3\2\2\2\31\u008b\3\2"+
		"\2\2\33\u0090\3\2\2\2\35\u0095\3\2\2\2\37\u0097\3\2\2\2!\u0099\3\2\2\2"+
		"#\u009b\3\2\2\2%\u009d\3\2\2\2\'\u009f\3\2\2\2)\u00a2\3\2\2\2+\u00a5\3"+
		"\2\2\2-\u00a7\3\2\2\2/\u00a9\3\2\2\2\61\u00b4\3\2\2\2\63\u00b6\3\2\2\2"+
		"\65\u00be\3\2\2\2\67\u00ca\3\2\2\29\u00d8\3\2\2\2;\u00de\3\2\2\2=\u00ec"+
		"\3\2\2\2?@\7r\2\2@A\7t\2\2AB\7q\2\2BC\7i\2\2CD\7t\2\2DE\7c\2\2EF\7o\2"+
		"\2FG\7c\2\2G\4\3\2\2\2HI\7h\2\2IJ\7k\2\2JK\7o\2\2KL\7r\2\2LM\7t\2\2MN"+
		"\7q\2\2NO\7i\2\2OP\7\60\2\2P\6\3\2\2\2QR\7f\2\2RS\7g\2\2ST\7e\2\2TU\7"+
		"n\2\2UV\7c\2\2VW\7t\2\2WX\7g\2\2X\b\3\2\2\2YZ\7p\2\2Z[\7w\2\2[\\\7o\2"+
		"\2\\]\7g\2\2]^\7t\2\2^_\7q\2\2_\n\3\2\2\2`a\7v\2\2ab\7g\2\2bc\7z\2\2c"+
		"d\7v\2\2de\7q\2\2e\f\3\2\2\2fg\7n\2\2gh\7g\2\2hi\7k\2\2ij\7c\2\2j\16\3"+
		"\2\2\2kl\7g\2\2lm\7u\2\2mn\7e\2\2no\7t\2\2op\7g\2\2pq\7x\2\2qr\7c\2\2"+
		"r\20\3\2\2\2st\7u\2\2tu\7g\2\2u\22\3\2\2\2vw\7g\2\2wx\7p\2\2xy\7v\2\2"+
		"yz\7c\2\2z{\7q\2\2{\24\3\2\2\2|}\7u\2\2}~\7g\2\2~\177\7p\2\2\177\u0080"+
		"\7c\2\2\u0080\u0081\7q\2\2\u0081\26\3\2\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7p\2\2\u0084\u0085\7s\2\2\u0085\u0086\7w\2\2\u0086\u0087\7c\2\2\u0087"+
		"\u0088\7p\2\2\u0088\u0089\7v\2\2\u0089\u008a\7q\2\2\u008a\30\3\2\2\2\u008b"+
		"\u008c\7h\2\2\u008c\u008d\7c\2\2\u008d\u008e\7e\2\2\u008e\u008f\7c\2\2"+
		"\u008f\32\3\2\2\2\u0090\u0091\7r\2\2\u0091\u0092\7c\2\2\u0092\u0093\7"+
		"t\2\2\u0093\u0094\7c\2\2\u0094\34\3\2\2\2\u0095\u0096\7*\2\2\u0096\36"+
		"\3\2\2\2\u0097\u0098\7+\2\2\u0098 \3\2\2\2\u0099\u009a\7=\2\2\u009a\""+
		"\3\2\2\2\u009b\u009c\7\60\2\2\u009c$\3\2\2\2\u009d\u009e\t\2\2\2\u009e"+
		"&\3\2\2\2\u009f\u00a0\7-\2\2\u00a0\u00a1\7-\2\2\u00a1(\3\2\2\2\u00a2\u00a3"+
		"\7<\2\2\u00a3\u00a4\7?\2\2\u00a4*\3\2\2\2\u00a5\u00a6\7.\2\2\u00a6,\3"+
		"\2\2\2\u00a7\u00a8\7}\2\2\u00a8.\3\2\2\2\u00a9\u00aa\7\177\2\2\u00aa\60"+
		"\3\2\2\2\u00ab\u00b5\t\3\2\2\u00ac\u00ad\7@\2\2\u00ad\u00b5\7?\2\2\u00ae"+
		"\u00af\7>\2\2\u00af\u00b5\7?\2\2\u00b0\u00b1\7?\2\2\u00b1\u00b5\7?\2\2"+
		"\u00b2\u00b3\7#\2\2\u00b3\u00b5\7?\2\2\u00b4\u00ab\3\2\2\2\u00b4\u00ac"+
		"\3\2\2\2\u00b4\u00ae\3\2\2\2\u00b4\u00b0\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5"+
		"\62\3\2\2\2\u00b6\u00ba\t\4\2\2\u00b7\u00b9\t\5\2\2\u00b8\u00b7\3\2\2"+
		"\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\64"+
		"\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00bf\t\6\2\2\u00be\u00bd\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c8\3\2"+
		"\2\2\u00c2\u00c4\7\60\2\2\u00c3\u00c5\t\6\2\2\u00c4\u00c3\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2"+
		"\2\2\u00c8\u00c2\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\66\3\2\2\2\u00ca\u00cc"+
		"\t\7\2\2\u00cb\u00cd\t\6\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d6\3\2\2\2\u00d0\u00d2\7\60"+
		"\2\2\u00d1\u00d3\t\6\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d0\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d78\3\2\2\2\u00d8\u00d9\t\7\2\2\u00d9:\3\2"+
		"\2\2\u00da\u00df\7$\2\2\u00db\u00dc\7\u00e4\2\2\u00dc\u00dd\7\u20ae\2"+
		"\2\u00dd\u00df\7\u0155\2\2\u00de\u00da\3\2\2\2\u00de\u00db\3\2\2\2\u00df"+
		"\u00e3\3\2\2\2\u00e0\u00e2\t\b\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00ea\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00e7\7\u00e4\2\2\u00e7\u00e8\7\u20ae\2\2\u00e8\u00eb"+
		"\7\uffff\2\2\u00e9\u00eb\7$\2\2\u00ea\u00e6\3\2\2\2\u00ea\u00e9\3\2\2"+
		"\2\u00eb<\3\2\2\2\u00ec\u00ed\t\t\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef"+
		"\b\37\2\2\u00ef>\3\2\2\2\20\2\u00b4\u00b8\u00ba\u00c0\u00c6\u00c8\u00ce"+
		"\u00d4\u00d6\u00de\u00e1\u00e3\u00ea\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}