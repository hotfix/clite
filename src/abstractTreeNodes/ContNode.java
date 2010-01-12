package abstractTreeNodes;

public class ContNode extends AbstractNode {

	private static final long serialVersionUID = 1L;
	private AbstractNode child;
	
	public ContNode(AbstractNode child) {
		
		this.child = child;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ContNode");
		child.print(indentation+2);
	}	
	
	public String toString() {
		return new String("ContNode" + 
				"\n  " + child.toString() );				
	}
	
	@Override
	public void Compile() {

	}
}
