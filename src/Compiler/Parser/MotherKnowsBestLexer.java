// Generated from /Users/SteinerT/Desktop/Compiler/myCompiler/src/Compiler/Parser/MotherKnowsBest.g4 by ANTLR 4.6
package Compiler.Parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MotherKnowsBestLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, IDENTIFIER=49, INTEGER=50, STRING=51, LINECOMMENT=52, 
		WHITESPACE=53;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
		"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "IDENTIFIER", 
		"INTEGER", "STRING", "CHAR", "LINECOMMENT", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'{'", "'}'", "'('", "','", "')'", "'='", "';'", "'if'", 
		"'else'", "'while'", "'for'", "'return'", "'break'", "'continue'", "'++'", 
		"'--'", "'['", "']'", "'.'", "'+'", "'-'", "'~'", "'!'", "'new'", "'*'", 
		"'/'", "'%'", "'<<'", "'>>'", "'<'", "'>'", "'<='", "'>='", "'!='", "'=='", 
		"'&'", "'^'", "'|'", "'&&'", "'||'", "'void'", "'int'", "'bool'", "'string'", 
		"'true'", "'false'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "IDENTIFIER", "INTEGER", "STRING", "LINECOMMENT", "WHITESPACE"
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


	public MotherKnowsBestLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MotherKnowsBest.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\67\u013c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 "+
		"\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3)\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3"+
		".\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\61\3\61\3\62\3\62\7\62\u0113\n\62\f\62\16\62\u0116\13\62\3\63\6\63\u0119"+
		"\n\63\r\63\16\63\u011a\3\64\3\64\7\64\u011f\n\64\f\64\16\64\u0122\13\64"+
		"\3\64\3\64\3\65\3\65\3\65\5\65\u0129\n\65\3\66\3\66\3\66\3\66\7\66\u012f"+
		"\n\66\f\66\16\66\u0132\13\66\3\66\3\66\3\67\6\67\u0137\n\67\r\67\16\67"+
		"\u0138\3\67\3\67\2\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\2k\66m\67\3\2\t\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\6\2\f\f\17"+
		"\17$$^^\f\2$$))AA^^cdhhppttvvxx\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u0140"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o\3\2\2"+
		"\2\5u\3\2\2\2\7w\3\2\2\2\ty\3\2\2\2\13{\3\2\2\2\r}\3\2\2\2\17\177\3\2"+
		"\2\2\21\u0081\3\2\2\2\23\u0083\3\2\2\2\25\u0086\3\2\2\2\27\u008b\3\2\2"+
		"\2\31\u0091\3\2\2\2\33\u0095\3\2\2\2\35\u009c\3\2\2\2\37\u00a2\3\2\2\2"+
		"!\u00ab\3\2\2\2#\u00ae\3\2\2\2%\u00b1\3\2\2\2\'\u00b3\3\2\2\2)\u00b5\3"+
		"\2\2\2+\u00b7\3\2\2\2-\u00b9\3\2\2\2/\u00bb\3\2\2\2\61\u00bd\3\2\2\2\63"+
		"\u00bf\3\2\2\2\65\u00c3\3\2\2\2\67\u00c5\3\2\2\29\u00c7\3\2\2\2;\u00c9"+
		"\3\2\2\2=\u00cc\3\2\2\2?\u00cf\3\2\2\2A\u00d1\3\2\2\2C\u00d3\3\2\2\2E"+
		"\u00d6\3\2\2\2G\u00d9\3\2\2\2I\u00dc\3\2\2\2K\u00df\3\2\2\2M\u00e1\3\2"+
		"\2\2O\u00e3\3\2\2\2Q\u00e5\3\2\2\2S\u00e8\3\2\2\2U\u00eb\3\2\2\2W\u00f0"+
		"\3\2\2\2Y\u00f4\3\2\2\2[\u00f9\3\2\2\2]\u0100\3\2\2\2_\u0105\3\2\2\2a"+
		"\u010b\3\2\2\2c\u0110\3\2\2\2e\u0118\3\2\2\2g\u011c\3\2\2\2i\u0128\3\2"+
		"\2\2k\u012a\3\2\2\2m\u0136\3\2\2\2op\7e\2\2pq\7n\2\2qr\7c\2\2rs\7u\2\2"+
		"st\7u\2\2t\4\3\2\2\2uv\7}\2\2v\6\3\2\2\2wx\7\177\2\2x\b\3\2\2\2yz\7*\2"+
		"\2z\n\3\2\2\2{|\7.\2\2|\f\3\2\2\2}~\7+\2\2~\16\3\2\2\2\177\u0080\7?\2"+
		"\2\u0080\20\3\2\2\2\u0081\u0082\7=\2\2\u0082\22\3\2\2\2\u0083\u0084\7"+
		"k\2\2\u0084\u0085\7h\2\2\u0085\24\3\2\2\2\u0086\u0087\7g\2\2\u0087\u0088"+
		"\7n\2\2\u0088\u0089\7u\2\2\u0089\u008a\7g\2\2\u008a\26\3\2\2\2\u008b\u008c"+
		"\7y\2\2\u008c\u008d\7j\2\2\u008d\u008e\7k\2\2\u008e\u008f\7n\2\2\u008f"+
		"\u0090\7g\2\2\u0090\30\3\2\2\2\u0091\u0092\7h\2\2\u0092\u0093\7q\2\2\u0093"+
		"\u0094\7t\2\2\u0094\32\3\2\2\2\u0095\u0096\7t\2\2\u0096\u0097\7g\2\2\u0097"+
		"\u0098\7v\2\2\u0098\u0099\7w\2\2\u0099\u009a\7t\2\2\u009a\u009b\7p\2\2"+
		"\u009b\34\3\2\2\2\u009c\u009d\7d\2\2\u009d\u009e\7t\2\2\u009e\u009f\7"+
		"g\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7m\2\2\u00a1\36\3\2\2\2\u00a2\u00a3"+
		"\7e\2\2\u00a3\u00a4\7q\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7v\2\2\u00a6"+
		"\u00a7\7k\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7w\2\2\u00a9\u00aa\7g\2\2"+
		"\u00aa \3\2\2\2\u00ab\u00ac\7-\2\2\u00ac\u00ad\7-\2\2\u00ad\"\3\2\2\2"+
		"\u00ae\u00af\7/\2\2\u00af\u00b0\7/\2\2\u00b0$\3\2\2\2\u00b1\u00b2\7]\2"+
		"\2\u00b2&\3\2\2\2\u00b3\u00b4\7_\2\2\u00b4(\3\2\2\2\u00b5\u00b6\7\60\2"+
		"\2\u00b6*\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8,\3\2\2\2\u00b9\u00ba\7/\2\2"+
		"\u00ba.\3\2\2\2\u00bb\u00bc\7\u0080\2\2\u00bc\60\3\2\2\2\u00bd\u00be\7"+
		"#\2\2\u00be\62\3\2\2\2\u00bf\u00c0\7p\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2"+
		"\7y\2\2\u00c2\64\3\2\2\2\u00c3\u00c4\7,\2\2\u00c4\66\3\2\2\2\u00c5\u00c6"+
		"\7\61\2\2\u00c68\3\2\2\2\u00c7\u00c8\7\'\2\2\u00c8:\3\2\2\2\u00c9\u00ca"+
		"\7>\2\2\u00ca\u00cb\7>\2\2\u00cb<\3\2\2\2\u00cc\u00cd\7@\2\2\u00cd\u00ce"+
		"\7@\2\2\u00ce>\3\2\2\2\u00cf\u00d0\7>\2\2\u00d0@\3\2\2\2\u00d1\u00d2\7"+
		"@\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7>\2\2\u00d4\u00d5\7?\2\2\u00d5D\3\2"+
		"\2\2\u00d6\u00d7\7@\2\2\u00d7\u00d8\7?\2\2\u00d8F\3\2\2\2\u00d9\u00da"+
		"\7#\2\2\u00da\u00db\7?\2\2\u00dbH\3\2\2\2\u00dc\u00dd\7?\2\2\u00dd\u00de"+
		"\7?\2\2\u00deJ\3\2\2\2\u00df\u00e0\7(\2\2\u00e0L\3\2\2\2\u00e1\u00e2\7"+
		"`\2\2\u00e2N\3\2\2\2\u00e3\u00e4\7~\2\2\u00e4P\3\2\2\2\u00e5\u00e6\7("+
		"\2\2\u00e6\u00e7\7(\2\2\u00e7R\3\2\2\2\u00e8\u00e9\7~\2\2\u00e9\u00ea"+
		"\7~\2\2\u00eaT\3\2\2\2\u00eb\u00ec\7x\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee"+
		"\7k\2\2\u00ee\u00ef\7f\2\2\u00efV\3\2\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2"+
		"\7p\2\2\u00f2\u00f3\7v\2\2\u00f3X\3\2\2\2\u00f4\u00f5\7d\2\2\u00f5\u00f6"+
		"\7q\2\2\u00f6\u00f7\7q\2\2\u00f7\u00f8\7n\2\2\u00f8Z\3\2\2\2\u00f9\u00fa"+
		"\7u\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7k\2\2\u00fd"+
		"\u00fe\7p\2\2\u00fe\u00ff\7i\2\2\u00ff\\\3\2\2\2\u0100\u0101\7v\2\2\u0101"+
		"\u0102\7t\2\2\u0102\u0103\7w\2\2\u0103\u0104\7g\2\2\u0104^\3\2\2\2\u0105"+
		"\u0106\7h\2\2\u0106\u0107\7c\2\2\u0107\u0108\7n\2\2\u0108\u0109\7u\2\2"+
		"\u0109\u010a\7g\2\2\u010a`\3\2\2\2\u010b\u010c\7p\2\2\u010c\u010d\7w\2"+
		"\2\u010d\u010e\7n\2\2\u010e\u010f\7n\2\2\u010fb\3\2\2\2\u0110\u0114\t"+
		"\2\2\2\u0111\u0113\t\3\2\2\u0112\u0111\3\2\2\2\u0113\u0116\3\2\2\2\u0114"+
		"\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115d\3\2\2\2\u0116\u0114\3\2\2\2"+
		"\u0117\u0119\t\4\2\2\u0118\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0118"+
		"\3\2\2\2\u011a\u011b\3\2\2\2\u011bf\3\2\2\2\u011c\u0120\7$\2\2\u011d\u011f"+
		"\5i\65\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7$"+
		"\2\2\u0124h\3\2\2\2\u0125\u0129\n\5\2\2\u0126\u0127\7^\2\2\u0127\u0129"+
		"\t\6\2\2\u0128\u0125\3\2\2\2\u0128\u0126\3\2\2\2\u0129j\3\2\2\2\u012a"+
		"\u012b\7\61\2\2\u012b\u012c\7\61\2\2\u012c\u0130\3\2\2\2\u012d\u012f\n"+
		"\7\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0134\b\66"+
		"\2\2\u0134l\3\2\2\2\u0135\u0137\t\b\2\2\u0136\u0135\3\2\2\2\u0137\u0138"+
		"\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\3\2\2\2\u013a"+
		"\u013b\b\67\2\2\u013bn\3\2\2\2\t\2\u0114\u011a\u0120\u0128\u0130\u0138"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}