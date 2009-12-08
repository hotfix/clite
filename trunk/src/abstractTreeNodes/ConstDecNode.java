package abstractTreeNodes;

public class ConstDecNode extends BinNode {

	public ConstDecNode () {}
	
	public ConstDecNode(IdfNode left_side, IntNode right_side) {
		SetL(left_side);
		SetR(right_side);
	}

	public String toString() {
		return new String("ConstDecNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public void Compile() {

	}
}
