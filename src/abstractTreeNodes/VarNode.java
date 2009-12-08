package abstractTreeNodes;

public class VarNode extends BinNode {

	private static final long serialVersionUID = 1L;

	public VarNode () {}
	
	public VarNode(IdfNode varName, AbstractNode type) {
		SetL(varName);
		SetR(type);
	}

	public String toString() {
		return new String("VarNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public void Compile() {

	}
}
