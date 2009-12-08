package parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import abstractTreeNodes.*;

import sun.security.jca.GetInstance.Instance;
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
		}else Error("Error in eval_Declaration");//wtf?
		return node;
	}

	private static AbstractNode eval_Expression() throws Exception {
		
		AbstractNode result = eval_part1();
		BinNode bin_result = null;
		
		while ( (nextToken.getTokenType() == MyScanner1.MATHOP) &&
				( nextToken.getLexem().equals("+") || 
				  nextToken.getLexem().equals("-") 
				)
			  ) 
		{
			int op;
			if (nextToken.getLexem().equals("+") == true) 
				op = Ops.addop;
			else 
				op = Ops.subop;
			
			Insymbol();
			
			bin_result = new BinNode(op, result, eval_part1());
			result = bin_result;
		}		

		return result;
	}

	private static AbstractNode eval_part1() throws Exception {
		
		AbstractNode result = eval_part2();
		BinNode bin_result = null;
		
		while (nextToken.getTokenType() == MyScanner1.COMPARE)			   
		{
			int op = 0;
			if (nextToken.getLexem().equals("<") == true)	op =  Ops.vergleich;
			if (nextToken.getLexem().equals(">") == true)	op =  Ops.vergleich;
			if (nextToken.getLexem().equals("<=") == true)	op =  Ops.vergleich;
			if (nextToken.getLexem().equals(">=") == true)	op =  Ops.vergleich;
			if (nextToken.getLexem().equals("==") == true)	op =  Ops.vergleich;
			if (nextToken.getLexem().equals("!=") == true)	op =  Ops.vergleich;
			
			Insymbol();
			
			bin_result = new BinNode(op, result, eval_part2());
			result = bin_result;
		}	
		
		return result;
	}

	private static AbstractNode eval_part2() throws Exception {
		
		AbstractNode result = eval_part3();
		BinNode bin_result = null;
		
		while ( (nextToken.getTokenType() == MyScanner1.MATHOP) &&
				( nextToken.getLexem().equals("*") || 
				  nextToken.getLexem().equals("/") 
				)
			  ) 
		{
			int op;
			if (nextToken.getLexem().equals("*") == true) 
				op = Ops.multop;
			else 
				op = Ops.divop;
			
			Insymbol();
			
			bin_result = new BinNode(op, result, eval_part1());
			result = bin_result;
		}
		
		return result;
	}

	private static AbstractNode eval_part3() throws Exception {
		
		AbstractNode result = null;;
		
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
				result = eval_part4();
			}
		}
		else {
			result = eval_part4();
		}
		
		return result;
	}

	private static AbstractNode eval_part4() throws Exception {
		
		AbstractNode result = null;
		
		if ( (nextToken.getTokenType() == MyScanner1.LPAR) ) {
			Insymbol();	
			result = eval_Expression();
			if ( (nextToken.getTokenType() != MyScanner1.RPAR) ) Error("RPAR expected\n");
			else Insymbol();
		}
		else
		if ( (nextToken.getTokenType() == MyScanner1.IDENTIFIER) ) {
			String idf = nextToken.getLexem();
			Insymbol();
			if ( (nextToken.getTokenType() == MyScanner1.LSBRACE) || (nextToken.getTokenType() == MyScanner1.DOT) ) {
				result = eval_Selector(idf);
			}
			else result = new IdfNode(idf);
		}
		else {
			if ( (nextToken.getTokenType() == MyScanner1.INTEGER) ) {
				result = new IntNode(nextToken.getLexem());
				Insymbol();				
			}
			else Error("Identifier or UInteger expected\n");
		}
		return result;
	}

	private static void putback_Token() {
		
		tempToken = nextToken;
		nextToken = prevToken;
		skip = true;
	}
	
	private static BinNode eval_Selector(String ident) throws Exception {
		BinNode result = null;
		BinNode result2 = null;
		String fieldIdf = "";
		AbstractNode rightSide = null;
		
		if ( (nextToken.getTokenType() == MyScanner1.LSBRACE) ) {			
			Insymbol();
			result = new ArrayRefNode();
			rightSide = eval_Expression();
			if ( (nextToken.getTokenType() != MyScanner1.RSBRACE) ) Error("eval_Selector::RSBRACE expected\n");
			else Insymbol();
		}
		else
		if ( (nextToken.getTokenType() == MyScanner1.DOT) ) {
			Insymbol();
			if ( (nextToken.getTokenType() != MyScanner1.IDENTIFIER) ) Error("eval_Selector::Identifier expected\n");
			else{
				rightSide = new IdfNode(nextToken.getLexem());
				fieldIdf = nextToken.getLexem();
				result = new StructRefNode();
				Insymbol();
			}
		}
		else return result;
		result2 = eval_Selector(fieldIdf);
		if(result2 == null){
			result.SetL(new IdfNode(ident));
			result.SetR(rightSide);
			
		}else{
			result.SetL(new IdfNode(ident));
			result.SetR(result2);
		}
		return result;
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

	//TODO:baum
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
		return new AbstractNode();	
	}

	//TODO:baum
	private static AbstractNode eval_FunctionCall() throws Exception {
		
		if ( (nextToken.getTokenType() != MyScanner1.IDENTIFIER) ) Error("Identifier expected\n");		
		Insymbol();
		eval_ActualParameters();
		return new AbstractNode();		
	}

	//TODO:baum
	private static AbstractNode eval_Assignment() throws Exception {
		
		String var = nextToken.getLexem();
		AbstractNode left_side = eval_Variable();
		
		if ( (nextToken.getTokenType() == MyScanner1.LSBRACE) || 
			 (nextToken.getTokenType() == MyScanner1.DOT) )
		{ 
			left_side = eval_Selector(var);
		}	
		
		if (nextToken.getTokenType() != MyScanner1.ASSIGNOP) Error("'ASSIGNOP' expected\n");
		else Insymbol();
		
		AbstractNode right_side = eval_Expression();	
		
		return new AssNode(left_side, right_side);
	}

	private static IdfNode eval_Variable() throws Exception {
		
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER) Error("'IDENTIFIER' expected\n");
		
		IdfNode node = new IdfNode(nextToken.getLexem());
		Insymbol();	
		return node;
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

	//TODO:baum
	private static AbstractNode eval_WHILE_Statement() throws Exception {
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
		//?
		return new AbstractNode();
	}

	//TODO:baum
	private static AbstractNode eval_DO_Statement() throws Exception {
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
		//?
		return new AbstractNode();		
	}

	//TODO:baum
	private static AbstractNode eval_FOR_Statement() throws Exception {		
		
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
		return new AbstractNode();
	}


	//TODO:baum
	private static AbstractNode eval_IF_Statement() throws Exception {
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
		return new AbstractNode();
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
			if (nextToken.getTokenType() != MyScanner1.ENDOP) {
				eval_Expression();
			}
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
				
		//Insymbol();
		if (nextToken.getTokenType() != MyScanner1.PROGRAMSY){
			Error("'Program' declaration expected\n");
			//return null;
		}
		Insymbol();
		
		if (nextToken.getTokenType() != MyScanner1.IDENTIFIER){
			Error("Program name not correct\n");
		}
		
		programName = nextToken.getLexem();
		Insymbol();
		
		if (nextToken.getTokenType() != MyScanner1.ENDOP) Error("Semicolon expected\n");
		Insymbol();
		
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
		
		System.out.println("Parser Version 0.2");
		
		if (argv.length == 0) {
			System.out.println("Usage : java Parser <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				try {
					// Hier geht es los:
					//	        	
					infile = argv[i];
					scanner = new MyScanner1(new java.io.FileReader(infile));
					
					Insymbol();
					root = eval_Expression();//eval_Program();
					begin_found = true;
					end_found = true;
					
					System.out.println(root.toString());
					
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
