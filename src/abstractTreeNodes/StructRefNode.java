package abstractTreeNodes;

public class StructRefNode extends BinNode {	

	private static final long serialVersionUID = 1L;

	public StructRefNode() {
		// TODO Auto-generated constructor stub
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("StructRefNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}	
	
	public String toString() {
		return new String("StructRefNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
	

}
