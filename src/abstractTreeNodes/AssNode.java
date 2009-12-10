package abstractTreeNodes;

public class AssNode extends BinNode {

	private static final long serialVersionUID = 1L;

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
	public void SetR(AbstractNode fr) {
		// TODO Auto-generated method stub
		if (fr != null) {
			super.SetR(fr);
		}
		else {
			super.SetR(new IntNode("0"));
		}
	}

	@Override
	public void Compile() {

	}
}
