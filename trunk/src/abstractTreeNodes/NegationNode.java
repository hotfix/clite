package abstractTreeNodes;

public class NegationNode extends AbstractNode {
	
	private AbstractNode child;	
	
	public NegationNode(AbstractNode child) {
		this.child = child;
	}

	@Override
	public void Compile() {
		// TODO Auto-generated method stub
		super.Compile();
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("NegationNode");
		child.print(indentation+2);
	}

	public String toString() {
		return new String("NegationNode " + "\n  " + child.toString());
	}
}
