package parser;
import lexyaccgen.*;

public class MyMiniParserMain {

	//static int nextsymbol = 0;
	static Yytoken nextToken;
	static int labcnt = 0;
	static int linecnt = 0;
	static String infile;
	static MyScanner1 scanner = null;

	/*
	 * Hilfsroutinen
	 */

	public static void Compile(String str) {
		System.out.println(str);
	}

	public static void OutStr(String str) {
		System.out.print(str + " ");
	}

	public static void OutInt(int i) {
		System.out.print(i + " ");
	}

	public static void OutOp(String op) {
		System.out.print(op);
	}

	public static void Error(String str) {
		System.out.println("Error: " + str);
		
	}

	public static void Insymbol() {
		try {				
				while ( ((nextToken = scanner.yylex()) != null) && (nextToken.getTokenType() == MyScanner1.WHITESPACE));
				
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + infile + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + infile + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
	}

	/*
	 * main
	 */

	public static void main(String[] argv) {
		System.out.println("MyMiniParser Version 0.0");

		if (argv.length == 0) {
			System.out.println("Usage : java MyMiniParser <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				try {
					// Hier geht es los:
					//	        	
					infile = argv[i];
					scanner = new MyScanner1(new java.io.FileReader(infile));
					Insymbol();
					while (nextToken != null) {
						if (nextToken.getTokenType() == MyScanner1.UNDEFTOK) break;
						System.out.println(nextToken.toString());
						Insymbol();
					}
					
					System.out.println(MyScanner1.symtable.getTableAsString());
					//	          
					// Und hier ist Schluss
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}
		}
	}

}
