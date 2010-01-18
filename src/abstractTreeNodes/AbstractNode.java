package abstractTreeNodes;

import java.io.Serializable;

import symTable.AbstractDescr;
import symTable.SymTable;

public class AbstractNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int op;

	public AbstractNode() {
		op = 0;
	}

	public int GetOp() {
		return op;
	}
	
	public AbstractDescr getDescriptor(SymTable env) {
		return null;
	}
	public AbstractDescr Compile(SymTable env) {
		System.out.println("AbstractCompile");
		return null;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("AbstractNode");
	}	
}
