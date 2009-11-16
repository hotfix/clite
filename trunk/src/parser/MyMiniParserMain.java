package parser;
import lexyaccgen.*;

public class MyMiniParserMain {

	static int error_code = 0; 
	static String infile;
	static int labcnt = 0;	
	static Yytoken nextToken;
	static boolean begin_found	= false;
	static boolean end_found	= false;
	static MyScanner1 scanner	= null;

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

	private static void Error(String str) throws Exception {
		error_code = 1;
		throw new Exception("Error at line "+ new Integer(scanner.getLine()).toString() + ": " + str);
	}
	
	private static void eval_Declaration() throws Exception {
		
		//add type to symtableentry
		Insymbol();
		if (nextToken.getTokenType() == MyScanner1.IDENTIFIER) {
			//add varname to symtableentry
			Insymbol();
			//while()
		}
		else Error("'Identifier' expected\n");
		
		Insymbol();	
	}
	
	private static void eval_Statement() throws Exception {	
		
		switch(nextToken.getTokenType()){
			case MyScanner1.DATATYPE: eval_Declaration(); break;
			case MyScanner1.KEYWORD: 
				if(nextToken.getLexem().equals("struct")){ eval_Declaration(); break; }
				if(nextToken.getLexem().equals("if")){ eval_IF_Statement(); break; }
				if(nextToken.getLexem().equals("for")){ eval_FOR_Statement(); break; }
				if(nextToken.getLexem().equals("do")){ eval_DO_Statement(); break; }
				if(nextToken.getLexem().equals("while")){ eval_WHILE_Statement(); break; }
				//if(nextToken.getLexem().equals("scan")){ eval_SCAN_Statement(); break; }
				//if(nextToken.getLexem().equals("print")){ eval_PRINT_Statement(); break; }
		
			default: if(isUserDataType() == true) eval_Declaration();
		}

		Insymbol();		
	}

	private static boolean isUserDataType() {
		// TODO Auto-generated method stub
		
		//check in the SymTable if nextToken is a user defined data type
		return false;
	}

	private static void eval_WHILE_Statement() {
		// TODO Auto-generated method stub
		
	}

	private static void eval_DO_Statement() {
		// TODO Auto-generated method stub
		
	}

	private static void eval_FOR_Statement() {
		// TODO Auto-generated method stub
		
	}

	private static void eval_IF_Statement() {
		// TODO Auto-generated method stub
		
	}

	private static void eval_StatementSequence() {
		
		
	}

	private static void eval_Function_Dec() throws Exception {

		Insymbol();
	}
	
	private static void eval_Program() throws Exception {
		
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.PROGRAMSY) Error("'Program' declaration expected\n");
		else Insymbol();
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("Program name not correct\n");
		else Insymbol();
		if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("Semicolon expected\n");
		else Insymbol();
		
		while (nextToken.getTokenType() != MyScanner1.BEGINSY) {
			eval_Function_Dec();
		}
		begin_found = true;
		Insymbol();		
		while (nextToken.getTokenType() != MyScanner1.ENDSY) {			
			eval_Statement();
		}
		end_found = true;
	}
	

	private static void Insymbol() throws EOFException {
		try {				
			while ( (nextToken = scanner.yylex()).getTokenType() == MyScanner1.WHITESPACE );			
				
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + infile + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + infile + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
		if (nextToken.getTokenType() == MyScanner1.EOF) throw new EOFException("Unexpected EOF");
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
					
					
					eval_Program();
					
					if (scanner.yylex().getTokenType() != MyScanner1.EOF) Error("End Of File expected\n");
					else 
					if (error_code == 0)System.out.println("OK!\n");
					
					System.out.println(MyScanner1.symtable.getTableAsString());
					//	          
					// Und hier ist Schluss
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");					
				} catch (EOFException e) {
					System.out.println(e.getMessage());
					if (begin_found == false) System.out.println("'Begin' not found!\n");
					else
					if (end_found == false) System.out.println("'End' not found!\n");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
