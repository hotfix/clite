/* The following code was generated by JFlex 1.4.3 on 23.11.09 16:34 */

package lexyaccgen;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 23.11.09 16:34 from the specification file
 * <tt>C:/Users/Muxxxa/workspace/CI/Clite/src/scan.flex</tt>
 */
public class MyScanner1 {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\5\1\0\1\1\1\4\22\0\1\1\1\15\1\12"+
    "\5\0\1\62\1\63\1\3\1\13\1\66\1\11\1\0\1\2\1\6"+
    "\11\7\1\0\1\67\1\16\1\14\1\16\2\0\1\50\1\52\1\10"+
    "\1\56\1\53\1\10\1\47\1\10\1\54\3\10\1\51\1\55\1\46"+
    "\1\44\1\10\1\45\10\10\1\64\1\0\1\65\1\0\1\57\1\0"+
    "\1\32\1\43\1\31\1\26\1\21\1\20\1\42\1\30\1\17\2\10"+
    "\1\22\1\40\1\33\1\24\1\34\1\41\1\25\1\23\1\35\1\36"+
    "\1\37\1\27\3\10\1\60\1\0\1\61\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\1\2\3\2\4\1\5\1\3\1\2"+
    "\1\6\1\2\1\7\17\5\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\1\2\0\1\20\1\7"+
    "\1\21\24\5\1\0\1\22\2\5\1\23\1\24\13\5"+
    "\1\1\10\5\1\25\2\5\1\26";

  private static int [] zzUnpackAction() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\70\0\160\0\250\0\70\0\70\0\340\0\u0118"+
    "\0\u0150\0\u0188\0\u01c0\0\u01c0\0\u01c0\0\u01f8\0\u0230\0\u0268"+
    "\0\u02a0\0\u02d8\0\u0310\0\u0348\0\u0380\0\u03b8\0\u03f0\0\u0428"+
    "\0\u0460\0\u0498\0\u04d0\0\u0508\0\70\0\70\0\70\0\70"+
    "\0\70\0\70\0\70\0\70\0\u0540\0\u0578\0\u0188\0\70"+
    "\0\70\0\u0118\0\u05b0\0\u05e8\0\u0620\0\u0658\0\u0690\0\u06c8"+
    "\0\u0700\0\u0738\0\u0770\0\u07a8\0\u07e0\0\u0818\0\u0850\0\u0888"+
    "\0\u08c0\0\u08f8\0\u0930\0\u0968\0\u09a0\0\u09d8\0\u0a10\0\u0118"+
    "\0\u0a48\0\u0a80\0\u0118\0\u0118\0\u0ab8\0\u0af0\0\u0b28\0\u0b60"+
    "\0\u0b98\0\u0bd0\0\u0c08\0\u0c40\0\u0c78\0\u0cb0\0\u0ce8\0\70"+
    "\0\u0d20\0\u0d58\0\u0d90\0\u0dc8\0\u0e00\0\u0e38\0\u0e70\0\u0ea8"+
    "\0\u0118\0\u0ee0\0\u0f18\0\u0118";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\2\3\1\6\1\7\1\10"+
    "\1\11\1\12\1\5\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\10\1\21\1\10\1\22\1\23\1\24\1\10"+
    "\1\25\2\10\1\26\1\27\2\10\1\30\2\10\1\31"+
    "\1\32\5\10\1\33\1\34\3\10\1\2\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\71\0\1\3"+
    "\2\0\2\3\64\0\1\45\1\46\72\0\2\7\66\0"+
    "\3\10\6\0\41\10\16\0\1\6\1\7\60\0\4\47"+
    "\2\0\4\47\1\50\55\47\14\0\1\51\61\0\3\10"+
    "\6\0\1\10\1\52\12\10\1\53\24\10\16\0\3\10"+
    "\6\0\3\10\1\54\1\10\1\55\33\10\16\0\3\10"+
    "\6\0\3\10\1\56\10\10\1\57\21\10\1\57\2\10"+
    "\16\0\3\10\6\0\1\60\11\10\1\61\3\10\1\62"+
    "\3\10\1\63\16\10\16\0\3\10\6\0\2\10\1\64"+
    "\36\10\16\0\3\10\6\0\1\65\4\10\1\52\33\10"+
    "\16\0\3\10\6\0\11\10\1\66\27\10\16\0\3\10"+
    "\6\0\4\10\1\67\1\70\3\10\1\71\27\10\16\0"+
    "\3\10\6\0\6\10\1\72\17\10\1\73\12\10\16\0"+
    "\3\10\6\0\13\10\1\60\25\10\16\0\3\10\6\0"+
    "\5\10\1\74\33\10\16\0\3\10\6\0\2\10\1\75"+
    "\2\10\1\76\26\10\1\75\4\10\16\0\3\10\6\0"+
    "\6\10\1\73\17\10\1\73\12\10\16\0\3\10\6\0"+
    "\2\10\1\75\31\10\1\75\4\10\16\0\3\10\6\0"+
    "\14\10\1\57\21\10\1\57\2\10\10\0\5\45\1\0"+
    "\62\45\3\46\1\77\64\46\6\0\3\10\6\0\16\10"+
    "\1\100\22\10\16\0\3\10\6\0\5\10\1\101\33\10"+
    "\16\0\3\10\6\0\6\10\1\52\32\10\16\0\3\10"+
    "\6\0\4\10\1\102\34\10\16\0\3\10\6\0\7\10"+
    "\1\103\27\10\1\103\1\10\16\0\3\10\6\0\14\10"+
    "\1\104\24\10\16\0\3\10\6\0\13\10\1\105\25\10"+
    "\16\0\3\10\6\0\6\10\1\106\32\10\16\0\3\10"+
    "\6\0\6\10\1\107\32\10\16\0\3\10\6\0\16\10"+
    "\1\110\22\10\16\0\3\10\6\0\20\10\1\104\20\10"+
    "\16\0\3\10\6\0\1\111\40\10\16\0\3\10\6\0"+
    "\16\10\1\112\22\10\16\0\3\10\6\0\4\10\1\104"+
    "\34\10\16\0\3\10\6\0\13\10\1\113\25\10\16\0"+
    "\3\10\6\0\1\114\4\10\1\115\21\10\1\115\11\10"+
    "\16\0\3\10\6\0\5\10\1\115\21\10\1\115\11\10"+
    "\16\0\3\10\6\0\7\10\1\104\31\10\16\0\3\10"+
    "\6\0\23\10\1\116\4\10\1\116\10\10\16\0\3\10"+
    "\6\0\5\10\1\117\33\10\10\0\2\46\1\120\1\77"+
    "\64\46\6\0\3\10\6\0\13\10\1\53\25\10\16\0"+
    "\3\10\6\0\2\10\1\52\36\10\16\0\3\10\6\0"+
    "\14\10\1\52\24\10\16\0\3\10\6\0\17\10\1\121"+
    "\21\10\16\0\3\10\6\0\16\10\1\104\22\10\16\0"+
    "\3\10\6\0\17\10\1\122\21\10\16\0\3\10\6\0"+
    "\3\10\1\102\35\10\16\0\3\10\6\0\6\10\1\123"+
    "\32\10\16\0\3\10\6\0\6\10\1\100\32\10\16\0"+
    "\3\10\6\0\14\10\1\124\24\10\16\0\3\10\6\0"+
    "\23\10\1\125\4\10\1\125\10\10\16\0\3\10\6\0"+
    "\1\126\34\10\1\126\3\10\16\0\3\10\6\0\3\10"+
    "\1\100\35\10\16\0\3\10\6\0\12\10\1\124\26\10"+
    "\16\0\3\10\6\0\6\10\1\105\32\10\16\0\3\10"+
    "\6\0\1\127\40\10\16\0\3\10\6\0\16\10\1\52"+
    "\22\10\16\0\3\10\6\0\6\10\1\130\17\10\1\130"+
    "\12\10\16\0\3\10\6\0\14\10\1\131\21\10\1\131"+
    "\2\10\16\0\3\10\6\0\14\10\1\132\24\10\16\0"+
    "\3\10\6\0\13\10\1\133\15\10\1\133\7\10\16\0"+
    "\3\10\6\0\23\10\1\100\15\10\16\0\3\10\6\0"+
    "\21\10\1\134\10\10\1\134\6\10\10\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3920];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\2\1\2\11\26\1\10\11\1\1\2\0"+
    "\2\11\25\1\1\0\20\1\1\11\14\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
// Hier kann beliebiger zu kopierender Code stehen.

	public static final int UNDEFTOK	= 255;
	public static final int WHITESPACE 	= 256;
	public static final int EOL		 	= 257;
	public static final int EOF		 	= 258;
	public static final int LPAR		= 259;
	public static final int RPAR		= 260;	
	public static final int LSBRACE		= 263;
	public static final int RSBRACE		= 264;
	public static final int BEGINSY		= 265;
	public static final int ENDSY		= 266;
	public static final int PROGRAMSY	= 283;//new
	public static final int BEGINBLOCK	= 284;
	public static final int ENDBLOCK	= 285;	
	public static final int ASSIGNOP 	= 286;
    public static final int COMMA		= 289;
    public static final int ENDOP		= 290;	
	public static final int IDENTIFIER	= 301;
	public static final int INTEGER		= 302;
	//public static final int FLOAT		= 303;
	//public static final int CHAR		= 304;
	public static final int STRING		= 305;
	//public static final int BOOL		= 306;
	public static final int COMPARE		= 307;
	public static final int MATHOP		= 308;
	public static final int KEYWORD		= 309;
	public static final int FUNCTION	= 310;
	public static final int DATATYPE	= 311;
	
	public static final int VAR			= 1000;
	public static final int CONST		= 1001;
       		
    static int intval; 
    static String strval;
    
    //static int lastDataType = 0;
    
    //public static SymTable symtable = new SymTable();
    
    public int getLine() {
    	return(yyline+1);
    }
    
    public int getColumn() {
    	return(yycolumn+1);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public MyScanner1(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public MyScanner1(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 154) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Yytoken yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return new Yytoken(ENDOP, yytext());
          }
        case 23: break;
        case 4: 
          { //intval = Integer.parseInt(yytext());
				//symtable.addSymbol(yytext(), CONST, INTEGER, "");
				return new Yytoken(INTEGER, yytext());
          }
        case 24: break;
        case 22: 
          { return new Yytoken(PROGRAMSY, yytext());
          }
        case 25: break;
        case 13: 
          { return new Yytoken(RSBRACE, yytext());
          }
        case 26: break;
        case 1: 
          { return new Yytoken(WHITESPACE, yytext());
          }
        case 27: break;
        case 5: 
          { //strval = new String(yytext()); 
				//symtable.addSymbol(strval, VAR, lastDataType, "");
				return new Yytoken(IDENTIFIER, yytext());
          }
        case 28: break;
        case 20: 
          { return new Yytoken(FUNCTION, yytext());
          }
        case 29: break;
        case 18: 
          { String s = new String(yytext());
				//if(s.equals("int")) 	lastDataType = INTEGER;
				//if(s.equals("float")) lastDataType = FLOAT;
				//if(s.equals("char")) 	lastDataType = CHAR;
				//if(s.equals("bool")) 	lastDataType = BOOL;
				//if(s.equals("cstring")) lastDataType = STRING;			
				
				if(s.equals("int")) 	return new Yytoken(INTEGER, s);
				if(s.equals("cstring")) return new Yytoken(STRING, s);
          }
        case 30: break;
        case 14: 
          { return new Yytoken(COMMA, yytext());
          }
        case 31: break;
        case 6: 
          { return new Yytoken(ASSIGNOP, yytext());
          }
        case 32: break;
        case 17: 
          { return new Yytoken(KEYWORD, yytext());
          }
        case 33: break;
        case 9: 
          { return new Yytoken(ENDBLOCK, yytext());
          }
        case 34: break;
        case 19: 
          { return new Yytoken(ENDSY, yytext());
          }
        case 35: break;
        case 7: 
          { return new Yytoken(COMPARE, yytext());
          }
        case 36: break;
        case 16: 
          { //symtable.addSymbol(yytext(), CONST, STRING, "");
				return new Yytoken(STRING, yytext());
          }
        case 37: break;
        case 12: 
          { return new Yytoken(LSBRACE, yytext());
          }
        case 38: break;
        case 21: 
          { return new Yytoken(BEGINSY, yytext());
          }
        case 39: break;
        case 3: 
          { return new Yytoken(MATHOP, yytext());
          }
        case 40: break;
        case 11: 
          { return new Yytoken(RPAR, yytext());
          }
        case 41: break;
        case 2: 
          { System.err.println("Undefined token \"" + yytext() + "\" on line: " + (yyline+1) + ", column: " + (yycolumn+1) + "\n");
				return new Yytoken(UNDEFTOK, yytext());
          }
        case 42: break;
        case 8: 
          { return new Yytoken(BEGINBLOCK, yytext());
          }
        case 43: break;
        case 10: 
          { return new Yytoken(LPAR, yytext());
          }
        case 44: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              { 	return new Yytoken(EOF, new String());
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
