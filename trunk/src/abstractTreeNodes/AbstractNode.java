package abstractTreeNodes;

import java.io.*;

public class AbstractNode implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String nodeName = "AbstractNode";
	
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
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("AbstractNode");
	}
	
}
