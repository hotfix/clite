package abstractTreeNodes;

import java.io.*;

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
}
