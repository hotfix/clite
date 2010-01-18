package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;

public class NegationNode extends BinNode {
	
	private static final long serialVersionUID = 1L;
	
	public NegationNode(AbstractNode child) {
		
		SetL(new IntNode("0"));
		SetR(child);
		op = Ops.subop;
	}

	@Override
	public AbstractDescr Compile(SymTable env) {
		super.Compile(env);
		return null;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("NegationNode");
		GetR().print(indentation+2);
	}

	public String toString() {
		return new String("NegationNode " + "\n  " + GetR().toString());
	}
}
