package lexyaccgen;
%%
// Setzen, wenn Scanner-Klasse public sein soll
%public
// Setzen, wenn Scanner-Klasse nicht Standardnamen haben soll
%class MyScanner1
// Setzen, wenn man seine eigene main Methode schreibt!
//%byaccj
// Setzen, wenn eine main Methode generiert werden soll!
//%standalone
%line
%column
%eofval{
	return new Yytoken(EOF, new String());
%eofval}


%{
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
    public static final int DOT			= 291;
	public static final int IDENTIFIER	= 301;
	public static final int INTEGER		= 302;
	//public static final int UINTEGER	= 303;
	//public static final int FLOAT		= 303;
	//public static final int CHAR		= 304;
	public static final int STRING		= 305;
	//public static final int BOOL		= 306;
	public static final int COMPARE		= 307;
	public static final int MATHOP		= 308;
	public static final int KEYWORD		= 309;
	//public static final int FUNCTION	= 310;
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
%}

// Makrodefinitionen
BLANK		=	[ \t\n\r\f] 
COMMENT		=	(\/\*([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*\*+\/) | (\/\/.*)

DIGIT		=	[0-9]
NNDIGIT		=	[1-9] 	//Not Null Digit
ALPHA		=	[a-zA-Z]

INTEGER	= 	{DIGIT} | ( {NNDIGIT} {DIGIT}* )
//UINTEGER	= 	{DIGIT} | ( {NNDIGIT} {DIGIT}* )
//INTEGER		=	[-]?{UINTEGER}
//FLOAT       =	{INTEGER}[.]{UINTEGER}
//BOOL		=	"true" | "false"
STRING		=	\"[^\"\n\r]*\"
MATHOP		=	"+"|"-"|"*"|"/"
COMPARE		=	"=="|"!="|"<"|">"|"<="|">="
KEYWORD		=	"if"|"else"|"for"|"do"|"while"|"scan"|"print"|"struct"|"return"
//FUNCTION	=	"sin"|"cos"|"tan"|"div"|"mod"|"sqrt"
DATATYPE	=	"int"|"cstring" //|"float"|"char"|"bool"

%%///////////////////////////////////////////////////////////////////////////

{BLANK}*	{return new Yytoken(WHITESPACE, yytext());}
//{EOL}		{return new Yytoken(EOL, yytext());}
{STRING}	{
				//symtable.addSymbol(yytext(), CONST, STRING, "");
				return new Yytoken(STRING, yytext());}  //strings eat up comments
{COMMENT}	{return new Yytoken(WHITESPACE, yytext());}
{INTEGER} 	{
				//intval = Integer.parseInt(yytext());
				//symtable.addSymbol(yytext(), CONST, INTEGER, "");
				return new Yytoken(INTEGER, yytext());
			}
//{UINTEGER} 	{return new Yytoken(UINTEGER, yytext());}
/*
{FLOAT}     {
				symtable.addSymbol(yytext(), CONST, FLOAT, "");
				return new Yytoken(FLOAT, yytext());
			}      
{BOOL}		{
				symtable.addSymbol(yytext(), CONST, BOOL, "");
				return new Yytoken(BOOL, yytext());
			}
*/
{MATHOP}	{return new Yytoken(MATHOP, yytext());}
{COMPARE}	{return new Yytoken(COMPARE, yytext());}
{KEYWORD}	{return new Yytoken(KEYWORD, yytext());}
//{FUNCTION}	{return new Yytoken(FUNCTION, yytext());}
{DATATYPE}	{
				String s = new String(yytext());
				//if(s.equals("int")) 	lastDataType = INTEGER;
				//if(s.equals("float")) 	lastDataType = FLOAT;
				//if(s.equals("char")) 	lastDataType = CHAR;
				//if(s.equals("bool")) 	lastDataType = BOOL;
				//if(s.equals("cstring")) lastDataType = STRING;			
				
				//if(s.equals("int")) 	return new Yytoken(INTEGER, s);
				//if(s.equals("cstring")) return new Yytoken(STRING, s);
				return new Yytoken(DATATYPE, s);
			}

[Pp][Rr][Oo][Gg][Rr][Aa][Mm]	
                 {return new Yytoken(PROGRAMSY, yytext());}

[Bb][Ee][Gg][Ii][Nn]	
            {return new Yytoken(BEGINSY, yytext());}
[Ee][Nn][Dd]			
         	{return new Yytoken(ENDSY, yytext());}


{ALPHA}({ALPHA}|{DIGIT}|"_")*	
			{
				//strval = new String(yytext()); 
				//symtable.addSymbol(strval, VAR, lastDataType, "");
				return new Yytoken(IDENTIFIER, yytext());
			}	
//"<<"		{return new Yytoken(COUTSY, yytext());}
//">>"		{return new Yytoken(CINSY, yytext());}
\{			{return new Yytoken(BEGINBLOCK, yytext());}
\}		 	{return new Yytoken(ENDBLOCK, yytext());}
\(			{return new Yytoken(LPAR, yytext());}
\)			{return new Yytoken(RPAR, yytext());}
\[			{return new Yytoken(LSBRACE, yytext());}
\]			{return new Yytoken(RSBRACE, yytext());}
\.			{return new Yytoken(DOT, yytext());}
\,			{return new Yytoken(COMMA, yytext());}
\;			{return new Yytoken(ENDOP, yytext());}
\=			{return new Yytoken(ASSIGNOP, yytext());}
.			{
				System.err.println("Undefined token \"" + yytext() + "\" on line: " + (yyline+1) + ", column: " + (yycolumn+1) + "\n");
				return new Yytoken(UNDEFTOK, yytext());
				
			}