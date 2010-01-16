package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;

public class ConstDecNode extends BinNode {

	private static final long serialVersionUID = 1L;

	public ConstDecNode () {}
	
	

	public ConstDecNode(IdfNode left_side, IntNode right_side) {
		SetL(left_side);
		SetR(right_side);
	}

	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ConstDecNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}
	
	public String toString() {
		return new String("ConstDecNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public AbstractDescr Compile(SymTable env) {
		System.out.println("ConstDecNode::Compile2");
		AbstractDescr descr = GetR().getDescriptor(env);
		descr.setConst(true);
		env.addVariable(
				((IdfNode)GetL()).GetS(), 
				descr
		);
		((IdfNode)GetL()).Compile(env);
		//TODO: null ???
		return null;
	}
}
