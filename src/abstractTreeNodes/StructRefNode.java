package abstractTreeNodes;

public class StructRefNode extends BinNode {	

	public StructRefNode() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return new String("StructRefNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public void Compile() {

	}
}
