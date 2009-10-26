package lexyaccgen;
%%

// Setzen, wenn Scanner-Klasse public sein soll
%public
// Setzen, wenn Scanner-Klasse nicht Standardnamen haben soll
%class MyScanner1
// Setzen, wenn man seine eigene main Methode schreibt!
%byaccj
// Setzen, wenn eine main Methode generiert werden soll!
//%standalone


%{
// Hier kann beliebiger zu kopierender Code stehen.


	public static final int errorsy		= 255;

	public static final int whitespace 	= 256;
	public static final int ident 		= 257;
	public static final int intconst	= 258;
	public static final int lpar		= 259;
	public static final int rpar		= 260;
	public static final int addop		= 261;
	public static final int subop		= 262;
	public static final int multop		= 263;
	public static final int divop		= 264;
	public static final int beginsy		= 265;
	public static final int endsy		= 266;
	public static final int forsy		= 267;
	public static final int dosy		= 268;
	public static final int whilesy		= 269;
	public static final int ifsy		= 270;
	public static final int elsesy		= 271;
	public static final int intsy		= 272;
	public static final int floatsy		= 273;
	public static final int charsy		= 274;
	public static final int cstringsy	= 275;
	public static final int sinfu		= 276;
	public static final int cosfu		= 277;
	public static final int tanfu		= 278;
	public static final int divfu	    = 279;
	public static final int modfu		= 280;
	public static final int sqrtfu		= 281;
	public static final int cinfu		= 282;
	public static final int coutfu		= 283;
	public static final int beginblock	= 284;
	public static final int endblock	= 285;
	public static final int assignop 	= 286;
    public static final int coutsy		= 287;
    public static final int cinsy		= 288;
    public static final int comma		= 289;
    public static final int endop		= 290;
	
    public static final int  UNDEFTOK	= 300;
	public static final int  IDENTIER	= 301;
	public static final int  INTEGER	= 302;
	public static final int  FLOAT		= 303;
	public static final int  STRING		= 304;
	public static final int  BOOL		= 305;
	public static final int  COMPARE	= 306;
	public static final int  MATHOP		= 307;
	public static final int  KEYWORD	= 308;
	public static final int  FUNCTION	= 309;
	public static final int  DATATYPE	= 310;
    
    
    		
    static int intval; 
    static String strval;
%}

// Makrodefinitionen
BLANK		=	[ \t\n\r]
DIGIT		=	[0-9]
NNDIGIT		=	[1-9] 	//Not Null Digit
ALPHA		=	[a-zA-Z]
UINTEGER	= 	{DIGIT} | ( {NNDIGIT} {DIGIT}* )
INTEGER		=	[-]?{UINTEGER}
FLOAT       =	{INTEGER}[.]{UINTEGER}
BOOL		=	"true"|"false"

MATHOP		=	"+"|"-"|"*"|"/"
COMPARE		=	"=="|"!="|"<"|">"|"<="|">="
KEYWORD		=	"if"|"else"|"for"|"do"|"while"|"cin"|"cout"
FUNCTION	=	"sin"|"cos"|"tan"|"div"|"mod"|"sqrt"
DATATYPE	=	"int"|"float"|"char"|"cstring"|"bool"

%%

{BLANK}*	{return whitespace;}
{INTEGER} 	{intval = Integer.parseInt(yytext());
             return intconst;}
{FLOAT}     {return FLOAT;}      
{BOOL}		{return BOOL;}
{MATHOP}	{return MATHOP;}
{COMPARE}	{return COMPARE;}
{KEYWORD}	{return KEYWORD;}
{FUNCTION}	{return FUNCTION;}
{DATATYPE}	{return DATATYPE;}

[Bb][Ee][Gg][Ii][Nn]	{return beginsy;}
[Ee][Nn][Dd]			{return endsy;}


{ALPHA}({ALPHA}|{DIGIT})*	{strval = new String(yytext()); return ident;}
	
"<<"			{return coutsy;}
">>"			{return cinsy;}
\{				{return beginblock;}
\}		 		{return endblock;}
\(				{return lpar;}
\)				{return rpar;}
\,				{return comma;}
\;				{return endop;}
\=				{return assignop;}



.				{return errorsy;}




 
