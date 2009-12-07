package abstractTreeNodes;

public class ArrayRefNode extends BinNode {

	public ArrayRefNode() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return new String("ArrayRefNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	
	@Override
	public void Compile() {

	}

}
