package parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import abstractTreeNodes.AbstractNode;
import abstractTreeNodes.BinNode;
import abstractTreeNodes.FielListNode;
import abstractTreeNodes.FieldListNode;
import abstractTreeNodes.FormalParamsNode;
import abstractTreeNodes.FunctionDecNode;
import abstractTreeNodes.ProgNode;
import abstractTreeNodes.StructDecNode;
import abstractTreeNodes.VarDecNode;

import symTable.AbstractEntry;
import symTable.SymTable;

import lexyaccgen.*;

public class Parser {

	static String 		infile;
	static int 			error_code	= 0;	
	static int 			labcnt		= 0;	
	static Yytoken 		tempToken	= null;
	static Yytoken 		prevToken	= null;
	static Yytoken 		nextToken	= null;
	static MyScanner1 	scanner		= null;
	static boolean 		skip		= false;
	static boolean 		begin_found	= false;
	static boolean 		end_found	= false;
	static AbstractNode root;
	
	public static  HashMap<String, AbstractEntry> env =
		new HashMap<String, AbstractEntry>(); 

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
	
	/*tests if sy in s1, gives errormessage if no
	and skips until sy in s1+s2 */
	void test(ArrayList<Integer> s1, ArrayList<Integer> s2, String err) throws Exception {
		if (s1.contains(nextToken.getTokenType()) == false) {
			Error(err);
			s1.addAll(s2);
			while(s1.contains(nextToken.getTokenType()) == false) Insymbol();
		}
	}
	
	private static AbstractNode eval_Declaration() throws Exception {
		AbstractNode node = null;
		if(nextToken.getLexem().equals("struct")){
			node = eval_Struct_Declaration();
		}else if (nextToken.getTokenType() == MyScanner1.DATATYPE){
			node = eval_Var_Dec();
		}else Error("Error in eval_Declaration");
		return node;
	}

	private static AbstractNode eval_Expression() throws Exception {
		
		//TODO: nischt fertisch
		eval_part1();
		while ( (nextToken.getTokenType() == MyScanner1.MATHOP) &&
				( nextToken.getLexem().equals("+") || 
				  nextToken.getLexem().equals("-") 
				)
			  ) {
			Insymbol();
			eval_part1();
		}
		
		return new AbstractNode();
	}

	private static void eval_part1() throws Exception {
		eval_part2();
		while ( (nextToken.getTokenType() == MyScanner1.COMPARE) &&
				( nextToken.getLexem().equals("<")	|| 
				  nextToken.getLexem().equals(">")	||
				  nextToken.getLexem().equals("==") || 
				  nextToken.getLexem().equals(">=") ||
				  nextToken.getLexem().equals("<=") || 
				  nextToken.getLexem().equals("!=")
				)
			  ) {
			Insymbol();
			eval_part2();
		}
	}

	private static void eval_part2() throws Exception {
		eval_part3();
		while ( (nextToken.getTokenType() == MyScanner1.MATHOP) &&
				( nextToken.getLexem().equals("*") || 
				  nextToken.getLexem().equals("/") 
				)
			  ) {
			Insymbol();
			eval_part3();
		}
	}

	private static void eval_part3() throws Exception {
		
		if ( (nextToken.getTokenType() == MyScanner1.MATHOP) &&
				( nextToken.getLexem().equals("+") || 
				  nextToken.getLexem().equals("-") 
				)
			  ) {
			Insymbol();
		}
		if ( (nextToken.getTokenType() == MyScanner1.IDENTIFIER) ) {			
			Insymbol();
			if ( (nextToken.getTokenType() == MyScanner1.LPAR) ) {
				putback_Token();
				eval_FunctionCall();
			}
			else {
				putback_Token();
				eval_part4();
			}
		}
		else {
			eval_part4();
		}
	}

	private static void eval_part4() throws Exception {
		
		if ( (nextToken.getTokenType() == MyScanner1.LPAR) ) {
			Insymbol();	
			eval_Expression();
			if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) Error("RPAR expected\n");
			else Insymbol();
		}
		else
		if ( (nextToken.getTokenType() == MyScanner1.IDENTIFIER) ) {
			Insymbol();
			if ( (nextToken.getTokenType() == MyScanner1.LSBRACE) || (nextToken.getTokenType() == MyScanner1.DOT) ) {
				eval_Selector();
			}
		}
		else {
			if ( (nextToken.getTokenType() == MyScanner1.INTEGER) ) {
				
				Insymbol();
			}
			else Error("Identifier or UInteger expected\n");
		}	
	}

	private static void putback_Token() {
		
		tempToken = nextToken;
		nextToken = prevToken;
		skip = true;
	}
	
	private static void eval_Selector() throws Exception {
		if ( (nextToken.getTokenType() == MyScanner1.LPAR) ) {			
			Insymbol();
			eval_Expression();
			Insymbol();
			if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) Error("eval_Selector::RPAR expected\n");
			else Insymbol();
		}
		else
		if ( (nextToken.getTokenType() == MyScanner1.DOT) ) {
			Insymbol();
			if ( (nextToken.getTokenType() != MyScanner1.IDENTIFIER) ) Error("eval_Selector::Identifier expected\n");
			else Insymbol();
		}
		else return;
		eval_Selector();
	}

	private static void eval_ActualParameters() throws Exception {
		
		if ( (nextToken.getTokenType() != MyScanner1.LPAR) ) Error("LPAR expected\n");
		Insymbol();
		if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) {
			eval_Expression();
			while( (nextToken.getTokenType() == MyScanner1.COMMA) ) {
				Insymbol();
				eval_Expression();
			}
			if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) Error("RPAR expected\n");
		}		
		Insymbol();		
	}

	private static AbstractNode eval_Statement() throws Exception {
		AbstractNode node;
		
		switch(nextToken.getTokenType()) {
			case MyScanner1.DATATYPE: 						  node = eval_Declaration();			break;
			case MyScanner1.KEYWORD: 
				if(nextToken.getLexem().equals("struct"))	{ node = eval_Struct_Declaration();	break; }
				if(nextToken.getLexem().equals("if"))		{ node = eval_IF_Statement(); 			break; }
				if(nextToken.getLexem().equals("for"))		{ node = eval_FOR_Statement();			break; }
				if(nextToken.getLexem().equals("do"))		{ node = eval_DO_Statement();			break; }
				if(nextToken.getLexem().equals("while"))	{ node = eval_WHILE_Statement();		break; }
				//if(nextToken.getLexem().equals("scan"))	{ eval_SCAN_Statement(); break; }
				//if(nextToken.getLexem().equals("print"))	{ eval_PRINT_Statement(); break; }
		
			case MyScanner1.IDENTIFIER:
				Insymbol();
				if (nextToken.getTokenType() == MyScanner1.ASSIGNOP) {
					putback_Token();
					node = eval_Assignment();				
				}
				else
				if (nextToken.getTokenType() == MyScanner1.LPAR) {
					putback_Token();
					node = eval_FunctionCall();
				}
				else
				if (nextToken.getTokenType() == MyScanner1.IDENTIFIER) {
					putback_Token();
					if(isUserDataType() == true) node = eval_Declaration();
					else Error("Unknown datatype '" + nextToken.getLexem() + "'\n");
				}	
				if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("'ENDOP' expected\n");
				Insymbol();
				break;
				
			default: Error("Strange statement...\n");
		}
		return node;	
	}

	private static void eval_FunctionCall() throws Exception {
		
		if ( (nextToken.getTokenType() != MyScanner1.IDENTIFIER) ) Error("Identifier expected\n");		
		Insymbol();
		eval_ActualParameters();		
	}

	private static void eval_Assignment() throws Exception {
		
		eval_Variable();
		
		if ( (nextToken.getTokenType() == MyScanner1.LSBRACE) || 
			 (nextToken.getTokenType() == MyScanner1.DOT) )
		{ 
			eval_Selector();
		}	
		
		if (nextToken.getTokenType() != MyScanner1.ASSIGNOP) Error("'ASSIGNOP' expected\n");
		else Insymbol();
		
		eval_Expression();		
	}

	private static void eval_Variable() throws Exception {
		
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("'IDENTIFIER' expected\n");
		else Insymbol();	
	}
	
	private static StructDecNode eval_Struct_Declaration() throws Exception {
		String structName ="";
		AbstractNode fieldlist;
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("struct 'IDENTIFIER' expected\n");
		else{
			structName = nextToken.getLexem();
			Insymbol();
		}
		
		fieldlist = eval_FieldlList();
		
		return new StructDecNode(structName, fieldlist);
		
	}

	private static FieldListNode eval_FieldlList() throws Exception {
		List<AbstractNode> varDecNode = new ArrayList<AbstractNode>();
		
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'BEGINBLOCK' in struct expected\n");
		else Insymbol();
		
		varDecNode.add(eval_Var_Dec());
		
		while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
			varDecNode.add(eval_Var_Dec());
			//Insymbol();
		}
		
		if (nextToken.getTokenType() != MyScanner1.ENDBLOCK) Error("'ENDBLOCK' in struct expected\n");
		else Insymbol();
		return new FieldListNode(varDecNode);
	}

	private static VarDecNode eval_Var_Dec() throws Exception {
		
		Map<String, ArrayList<Object>> varDec = new HashMap<String, ArrayList<Object>>();
		String type = "";
		String variable = "";
		AbstractNode expressionNode;
		ArrayList<Object> valueAndType = new ArrayList<Object>();
		if(nextToken.getTokenType() != MyScanner1.DATATYPE) Error("'Datatype' in struct declaration expected\n");
		else{
			type = nextToken.getLexem();
			Insymbol();
		}
		
		if (nextToken.getTokenType() == MyScanner1.IDENTIFIER) {
			//add varname to symtableentry
			variable = nextToken.getLexem();
			Insymbol();
			if (nextToken.getTokenType() == MyScanner1.ASSIGNOP) {
				Insymbol();
				expressionNode = eval_Expression();
				valueAndType.add(expressionNode);
				valueAndType.add(type);
				varDec.put(variable,valueAndType);
			}
			while(nextToken.getTokenType() == MyScanner1.COMMA) {
				Insymbol();
				if (nextToken.getTokenType() == MyScanner1.IDENTIFIER) {
					//add varname to symtableentry
					variable = nextToken.getLexem();
					Insymbol();
					if (nextToken.getTokenType() == MyScanner1.ASSIGNOP) {
						Insymbol();
						expressionNode = eval_Expression();
						valueAndType.add(expressionNode);
						valueAndType.add(type);
						varDec.put(variable,valueAndType);
					}
				}
				else Error("'Identifier' expected\n");
			}
			if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("Semicolon expected\n");
			else Insymbol();
		}
		else Error("'Identifier' expected\n");		
		
		return new VarDecNode(varDec);
	}

	private static boolean isUserDataType() {
		// TODO check in the SymTable if nextToken is a user defined data type
		return false;
	}

	private static void eval_WHILE_Statement() throws Exception {
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.LPAR) Error("'Lpar' expected\n");
		else Insymbol();
		
		eval_Expression();
		
		if (nextToken.getTokenType() != MyScanner1.RPAR) Error("'Rpar' expected\n");
		else Insymbol();
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'Beginblock' in while statement expected\n");
		else Insymbol();
		
		while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
			eval_Statement();
		}
		Insymbol();
	}

	private static void eval_DO_Statement() throws Exception {
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'Beginblock' in do while statement expected\n");
		else Insymbol();
		
		while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
			eval_Statement();
		}
		if (!nextToken.getLexem().equals("while")) Error("Keyword while in (do while) statement expected\n");
		else Insymbol();
		
		if (nextToken.getTokenType() != MyScanner1.LPAR) Error("'Lpar' expected\n");
		else Insymbol();
		
		eval_Expression();
		
		if (nextToken.getTokenType() != MyScanner1.RPAR) Error("'Rpar' expected\n");
		else Insymbol();		
	}


	private static void eval_FOR_Statement() throws Exception {		
		
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.LPAR) Error("'Lpar' expected\n");
		else Insymbol();
		
		eval_Assignment();
		
		if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("'ENDOPSY' in for Expression expected\n");
		else Insymbol();
		
		eval_Expression();
				
		if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("'ENDOPSY' in for Expression expected\n");
		else Insymbol();
		
		eval_Assignment();
		
		if (nextToken.getTokenType() != MyScanner1.RPAR) Error("'Rpar' expected\n");
		else Insymbol();
		
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'Beginblock' in for statement expected\n");
		else Insymbol();
		
		while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
			eval_Statement();
		}
		Insymbol();
	}


	private static void eval_IF_Statement() throws Exception {
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.LPAR) Error("'Lpar' expected\n");
		else Insymbol();
		
		eval_Expression();
		
		if (nextToken.getTokenType() != MyScanner1.RPAR) Error("'Rpar' expected\n");
		else Insymbol();
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'Beginblock' in IF statement expected\n");
		else Insymbol();
		
		while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
			eval_Statement();
		}
		
		Insymbol();
		
		// else part alternative
		if(nextToken.getLexem().equals("else")){
			Insymbol();
			if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'Beginblock' in IF statement expected\n");
			else Insymbol();
			
			while(nextToken.getTokenType() != MyScanner1.ENDBLOCK){
				eval_Statement();
			}
			Insymbol();
		}
	}

	//private static void eval_StatementSequence() {		
	//}

	private static FunctionDecNode eval_Function_Dec() throws Exception {
		String functionName = "";
		String returnType = "";
		AbstractNode formalParams = null;
		AbstractNode returnValue = null;//TODO:
		List<AbstractNode> statements = new ArrayList<AbstractNode>();

		if (nextToken.getTokenType() == MyScanner1.DATATYPE) {
			returnType = nextToken.getLexem();
			Insymbol();
		}
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("Function name expected\n");
		else{
			functionName = nextToken.getLexem();
			Insymbol();
		}
		formalParams = eval_FormalParameters();
		if (nextToken.getTokenType() != MyScanner1.BEGINBLOCK) Error("'{' expected\n");
		else Insymbol();
		while ( (nextToken.getTokenType() != MyScanner1.ENDBLOCK) && (nextToken.getTokenType() != MyScanner1.RETURNSY) ) {			
			statements.add(eval_Statement());
		}
		if (nextToken.getTokenType() == MyScanner1.RETURNSY) {
			Insymbol();
			//TODO: returnvalue
			if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("';' expected\n");
			else Insymbol();
			if (nextToken.getTokenType() != MyScanner1.ENDBLOCK) Error("'}' expected\n");
			else Insymbol();
		}
		return new FunctionDecNode();
	}
	
	private static FormalParamsNode eval_FormalParameters() throws Exception {
		//List<BinNode> params = new ArrayList<BinNode>();
		Map<String,String>params = new HashMap<String, String>();
		String type = "";
		if ( (nextToken.getTokenType() != MyScanner1.LPAR) ) Error("LPAR expected\n");
		Insymbol();
		if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) {
			if (nextToken.getTokenType() != MyScanner1.DATATYPE) Error("DATATYPE expected\n");
			else{
				type = nextToken.getLexem();
				Insymbol();
			}
			if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("IDENTIFIER expected\n");
			else{
				params.put(nextToken.getLexem(), type);
				Insymbol();
			}
			while( (nextToken.getTokenType() == MyScanner1.COMMA) ) {
				Insymbol();
				if (nextToken.getTokenType() != MyScanner1.DATATYPE) Error("DATATYPE expected\n");
				else{
					type = nextToken.getLexem();					
					Insymbol();
				}
					
				if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("IDENTIFIER expected\n");
				else{
					params.put(nextToken.getLexem(), type);
					Insymbol();
				}
			}
			if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) Error("RPAR expected\n");
		}		
		Insymbol();	
		return new FormalParamsNode(params);
	}

	private static ProgNode eval_Program() throws Exception {
		String programName = "";
		List<AbstractNode> functions = new ArrayList<AbstractNode>();
		List<AbstractNode> statements = new ArrayList<AbstractNode>();
		
		
		Insymbol();
		if (nextToken.getTokenType() != MyScanner1.PROGRAMSY){
			Error("'Program' declaration expected\n");
			//return null;
		}
		else{ Insymbol(); }
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER){
			Error("Program name not correct\n");
		}
		else{
			programName = nextToken.getLexem();
			Insymbol();
		}
		if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("Semicolon expected\n");
		else Insymbol();
		
		while (nextToken.getTokenType() != MyScanner1.BEGINSY) {
			functions.add(eval_Function_Dec());
		}
		begin_found = true;
		Insymbol();		
		while (nextToken.getTokenType() != MyScanner1.ENDSY) {			
			statements.add(eval_Statement());
		}
		end_found = true;
		
		return new ProgNode(programName,functions, statements);
	}
	

	private static void Insymbol() throws EOFException {
		
		prevToken = nextToken;
		
		if(skip == true) {
			nextToken = tempToken;
			skip = false;
		}
		else {
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
					
					
					root = eval_Program();
					
					if (scanner.yylex().getTokenType() != MyScanner1.EOF) Error("End Of File expected\n");
					else 
					if (error_code == 0)System.out.println("OK!\n");
					
					//System.out.println(MyScanner1.symtable.getTableAsString());
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
