package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;

public class VarNode extends BinNode {

	private static final long serialVersionUID = 1L;
	
	private boolean isconst = false;

	public VarNode () {}
	
	public VarNode(IdfNode varName, AbstractNode type, boolean isconst) {
		
		this.isconst = isconst;
		SetL(varName);
		SetR(type);
	}

	public void print(int indentation) {
		String cnst = new String("");
		if(isconst == true) cnst = "const";
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("VarNode " + cnst);
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}	
	
	public String toString() {
		String cnst = new String("");
		if(isconst == true) cnst = "const";
		return new String("VarNode " + cnst + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public AbstractDescr Compile(SymTable env) {
		System.out.println("VarNode::Compile");
		
		AbstractDescr descr = GetR().getDescriptor(env);
		if (isconst == true) descr.setConst(true);
		
		env.addVariable(
				((IdfNode)GetL()).GetS(), 
				descr
		);
		if(descr.GetOp() != Ops.structtyp) 
			((IdfNode)GetL()).Compile(env);

		return null;
	}
}
