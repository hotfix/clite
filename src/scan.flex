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


%{
// Hier kann beliebiger zu kopierender Code stehen.

	public static final int UNDEFTOK	= 255;
	public static final int WHITESPACE 	= 256;
	public static final int LPAR		= 259;
	public static final int RPAR		= 260;	
	public static final int COUTSY		= 261;
	public static final int CINSY		= 262;
	public static final int LSBRACE		= 263;
	public static final int RSBRACE		= 264;
	public static final int BEGINSY		= 265;
	public static final int ENDSY		= 266;
	public static final int BEGINBLOCK	= 284;
	public static final int ENDBLOCK	= 285;	
	public static final int ASSIGNOP 	= 286;
    public static final int COMMA		= 289;
    public static final int ENDOP		= 290;	
	public static final int IDENTIFIER	= 301;
	public static final int INTEGER		= 302;
	public static final int FLOAT		= 303;
	public static final int STRING		= 304;
	public static final int BOOL		= 305;
	public static final int COMPARE		= 306;
	public static final int MATHOP		= 307;
	public static final int KEYWORD		= 308;
	public static final int FUNCTION	= 309;
	public static final int DATATYPE	= 310;
       		
    static int intval; 
    static String strval;
%}

// Makrodefinitionen
BLANK		=	[ \t\n\r]
COMMENT		=	(\/\*([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*\*+\/) | (\/\/.*)

DIGIT		=	[0-9]
NNDIGIT		=	[1-9] 	//Not Null Digit
ALPHA		=	[a-zA-Z]
UINTEGER	= 	{DIGIT} | ( {NNDIGIT} {DIGIT}* )
INTEGER		=	[-]?{UINTEGER}
FLOAT       =	{INTEGER}[.]{UINTEGER}
BOOL		=	"true"|"false"
STRING		=	\"[^\"\n\r]*\"


MATHOP		=	"+"|"-"|"*"|"/"
COMPARE		=	"=="|"!="|"<"|">"|"<="|">="
KEYWORD		=	"if"|"else"|"for"|"do"|"while"|"cin"|"cout"|"goto"
FUNCTION	=	"sin"|"cos"|"tan"|"div"|"mod"|"sqrt"
DATATYPE	=	"int"|"float"|"char"|"cstring"|"bool"

%%///////////////////////////////////////////////////////////////////////////

{BLANK}*	{return new Yytoken(WHITESPACE, yytext());}
{STRING}	{return new Yytoken(STRING, yytext());}  //strings eat up comments
{COMMENT}	{return new Yytoken(WHITESPACE, yytext());}
{INTEGER} 	{intval = Integer.parseInt(yytext());
             return new Yytoken(INTEGER, yytext());}
{FLOAT}     {return new Yytoken(FLOAT, yytext());}      
{BOOL}		{return new Yytoken(BOOL, yytext());}
{MATHOP}	{return new Yytoken(MATHOP, yytext());}
{COMPARE}	{return new Yytoken(COMPARE, yytext());}
{KEYWORD}	{return new Yytoken(KEYWORD, yytext());}
{FUNCTION}	{return new Yytoken(FUNCTION, yytext());}
{DATATYPE}	{return new Yytoken(DATATYPE, yytext());}

[Bb][Ee][Gg][Ii][Nn]	
            {return new Yytoken(BEGINSY, yytext());}
[Ee][Nn][Dd]			
         	{return new Yytoken(ENDSY, yytext());}


{ALPHA}({ALPHA}|{DIGIT}|"_")*	
			{strval = new String(yytext()); 
			return new Yytoken(IDENTIFIER, yytext());}
	
"<<"		{return new Yytoken(COUTSY, yytext());}
">>"		{return new Yytoken(CINSY, yytext());}
\{			{return new Yytoken(BEGINBLOCK, yytext());}
\}		 	{return new Yytoken(ENDBLOCK, yytext());}
\(			{return new Yytoken(LPAR, yytext());}
\)			{return new Yytoken(RPAR, yytext());}
\[			{return new Yytoken(LSBRACE, yytext());}
\]			{return new Yytoken(RSBRACE, yytext());}
\,			{return new Yytoken(COMMA, yytext());}
\;			{return new Yytoken(ENDOP, yytext());}
\=			{return new Yytoken(ASSIGNOP, yytext());}
.			{return new Yytoken(UNDEFTOK, yytext());}
