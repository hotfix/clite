package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;

public class ProgNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String		programName;
	private ListNode	functions;
	private ListNode	statements;
	

	public ProgNode(String programName, ListNode functions, ListNode statements) {

		this.programName	= programName;
		this.functions 		= functions;
		this.statements		= statements;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public ListNode getFunctions() {
		return functions;
	}

	public void setFunctions(ListNode functions) {
		this.functions = functions;
	}

	public ListNode getStatements() {
		return statements;
	}

	public void setStatements(ListNode statements) {
		this.statements = statements;
	}

	@Override
	public void print(int indentation) {
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ProgNode: " + getProgramName());
		functions.print(indentation+2);
		statements.print(indentation+2);
	}

	@Override
	public void Compile() {

		functions.Compile();		
		//CodeGen.OutInstr(new IntVal(CodeGen.progst.size()));		
		statements.Compile();
	}

	@Override
	public AbstractDescr Compile(SymTable env) {
		functions.Compile();		
		//CodeGen.OutInstr(new IntVal(CodeGen.progst.size()));		
		statements.Compile(env);
		
		return null;
	}

}
