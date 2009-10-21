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
{ALPHA}({ALPHA}|{DIGIT})*	
				{strval = new String(yytext());
				 return ident;}
\(				{return lpar;}
\)				{return rpar;}
\+				{return addop;}
\*				{return multop;}
\-				{return subop;}
\/				{return divop;}
.				{return errorsy;}



 
