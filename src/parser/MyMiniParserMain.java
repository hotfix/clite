package parser;
import lexyaccgen.*;

public class MyMiniParserMain {

	static int nextsymbol = 0;
	static int labcnt = 0;
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
			while ((nextsymbol = scanner.yylex()) == MyScanner1.WHITESPACE)
				;
			// System.out.println("Insymbol: " + nextsymbol);
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
					while (nextsymbol > 0) {
						System.out.println(nextsymbol);
						Insymbol();
					}
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
