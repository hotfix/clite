package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;
import instructions.IntVal;
import codeGen.CodeGen;

public class IntNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	private Integer num;
	
	public IntNode(String i) {		
		num = new Integer(i);
	}
	
	public int getI() {
		return num;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("IntNode " + num.toString());
	}	
	
	public String toString() {
		return new String("IntNode " + num.toString());
	} 
	
	@Override
	public void Compile() {
		CodeGen.OutInstr(new IntVal(num));
	}
	
	@Override
	public AbstractDescr Compile(SymTable env) {
		CodeGen.OutInstr(new IntVal(num));
		return null;
	}
}
