package abstractTreeNodes;

public class VarNode extends BinNode {

	private static final long serialVersionUID = 1L;

	public VarNode () {}
	
	public VarNode(IdfNode varName, AbstractNode type) {
		SetL(varName);
		SetR(type);
	}

	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("VarNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
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
