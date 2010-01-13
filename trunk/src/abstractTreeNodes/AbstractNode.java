package abstractTreeNodes;

import java.io.*;
import java.util.HashMap;

import symTable.AbstractDescr;
import symTable.AbstractEntry;
import symTable.ArrayDescr;
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

	public void Compile() {
		System.out.println("AbstractCompile");
	}
	
	public AbstractDescr Compile(SymTable env) {
		System.out.println("AbstractCompile2");
		return null;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("AbstractNode");
	}	
}
