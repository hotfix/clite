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

    public static final int errorsy		= 255;
    static int intval; 
    static String strval;
%}
// Makrodefinitionen
BLANK=[ \t\n\r]
DIGIT=[0-9]
ALPHA=[a-zA-Z]
INTEGER={DIGIT}+

%%

{BLANK}*	{return whitespace;}
{INTEGER} 	{intval = Integer.parseInt(yytext());
             return intconst;}

[Bb][Ee][Gg][Ii][Nn]	{return beginsy;}
[Ee][Nn][Dd]			{return endsy;}

for				{return forsy;}
do				{return dosy;}
while			{return whilesy;}
if				{return ifsy;}
else			{return elsesy;}
int				{return intsy;}
float			{return floatsy;}
char			{return charsy;}
cstring			{return cstringsy;}
sin				{return sinfu;}
cos				{return cosfu;}
tan				{return tanfu;}
div				{return divfu;}
mod				{return modfu;}
sqrt			{return sqrtfu;}
cin				{return cinfu;}
cout			{return coutfu;}

{ALPHA}({ALPHA}|{DIGIT})*	
				{strval = new String(yytext());
				 return ident;}
\(				{return lpar;}
\)				{return rpar;}
\+				{return addop;}
\*				{return multop;}
\-				{return subop;}
\/				{return divop;}
\<				{return smallerop;}
\>				{return biggerrop;}
\=				{return biggerrop;}
\=\=

.				{return errorsy;}




 
