package abstractTreeNodes;

public class AssNode extends BinNode {

	public AssNode () {}
	
	public AssNode(AbstractNode left_side, AbstractNode right_side) {
		SetL(left_side);
		SetR(right_side);
	}

	public String toString() {
		return new String("AssNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public void Compile() {

	}
}
