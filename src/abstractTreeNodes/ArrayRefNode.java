package abstractTreeNodes;

public class ArrayRefNode extends BinNode {

	public ArrayRefNode() {
		// TODO Auto-generated constructor stub
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ArrayRefNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
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
